package com.javachallenges.Java.Challenge.RIB;

import com.javachallenges.Java.Challenge.Others.StringMatcher;
import inet.ipaddr.AddressStringException;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;

import java.util.*;
import java.util.stream.Collectors;

public class RIB {

    private static final String NEXT_HOP = "next_hop";
    private static final String PREFIX = "prefix";

    /**
     * esta metodo devuelve una lista de prefijos que pudieron
     * haber sido afectados por secuestro de rutas
     */
    public static Map<String, Set<String>> getAffectedPrefixes(ArrayList<Map<String, String>> data) {
        Map<String, Set<String>> list = groupByPrefix(data);
        return list
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public static Map<String, Set<String>> groupByPrefix(ArrayList<Map<String, String>> data) {
        return data
                .parallelStream()
                .collect(Collectors.groupingBy(entry -> entry.get("pref")))
                .entrySet()
                .parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        x -> x.getValue()
                                .parallelStream()
                                .map(y -> y.get("o_asn"))
                                .filter(s -> !s.equals(""))
                                .collect(Collectors.toSet())));
    }

    public static Map<String, List<IPAddress>> aggregateRIB(ArrayList<Map<String, String>> fullRib) {
        Map<String, List<IPAddress>> ribGroupedByNexHop = groupByNextHop(fullRib);
        Map<String, List<IPAddress>> ribFiltered = filterByIPv4Entries(ribGroupedByNexHop);
        Map<String, List<IPAddress>> sortedRIB = sortRIB(ribFiltered);
        Map<String, List<IPAddress>> cleanRIB = removeRIBRedundancy(sortedRIB);

        cleanRIB.entrySet().parallelStream().forEach(listEntry -> {
            List<IPAddress> ips = listEntry.getValue();
            IPAddress newIp = null;
            for (int i = 0; i < ips.size(); i++) {
                IPAddress oneIp = ips.get(i);
                for (int j = 0; j < ips.size(); j++) {
                    if (j != i) {
                        IPAddress twoIp = ips.get(j);
                        try {
                            newIp = aggregate(oneIp, twoIp);
                            if (newIp != null) {
                                ips.remove(i);
                                ips.remove(j);
                                i--;
                                ips.add(newIp);
                                break;
                            }
                        } catch (AddressStringException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return cleanRIB;
    }

    public static IPAddress aggregate(IPAddress ip1, IPAddress ip2) throws AddressStringException {
        IPAddress smaller = ip1.compareTo(ip2) < 0 ? ip1 : ip2;
        IPAddress bigger = smaller.equals(ip1) ? ip2 : ip1;
        IPAddress aggregation = null;
        if (smaller.getUpper().increment(+1).equals(bigger.getLower())) {
            int networkPrefixLength = smaller.getNetworkPrefixLength() - 1;
            String ipAddress = smaller.getLower().toString().replaceFirst("/[0-9]{2}", String.format("/%d", networkPrefixLength));
            IPAddress auxAggregation = new IPAddressString(ipAddress).toAddress();
            if (smaller.getLower().equals(auxAggregation.getLower()) && bigger.getUpper().equals(auxAggregation.getUpper()))
                aggregation = auxAggregation;
        }
        return aggregation;
    }

    public static Map<String, List<IPAddress>> groupByNextHop(ArrayList<Map<String, String>> fullRib) {
        return fullRib.stream()
                .collect(Collectors.groupingBy(entry -> entry.get(NEXT_HOP)))
                .entrySet()
                .parallelStream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                x -> x.getValue()
                                        .stream()
                                        .map(y -> {
                                            IPAddress ip = null;
                                            try {
                                                ip = new IPAddressString(y.get(PREFIX)).toAddress();
                                            } catch (Exception ignored) {
                                            }
                                            return ip;
                                        })
                                        .collect(Collectors.toList())));
    }

    public static Map<String, List<IPAddress>> filterByIPv4Entries(Map<String, List<IPAddress>> ribGroupedByNextHop) {
        return ribGroupedByNextHop
                .entrySet()
                .parallelStream()
                .filter(x -> x.getKey().matches(StringMatcher.IP_REGEX))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, List<IPAddress>> sortRIB(Map<String, List<IPAddress>> ribGroupedByNextHop) {
        return ribGroupedByNextHop
                .entrySet()
                .parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        x -> x.getValue().parallelStream().sorted(Comparator.comparing(IPAddress::getNetworkPrefixLength)).collect(Collectors.toList())));

    }

    public static Map<String, List<IPAddress>> removeRIBRedundancy(Map<String, List<IPAddress>> ribGroupedByNextHop) {
        return ribGroupedByNextHop
                .entrySet()
                .parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> removeRedundancy(x.getValue())));
    }

    private static List<IPAddress> removeRedundancy(List<IPAddress> list) {
        Iterator<IPAddress> iterator = list.iterator();
        while (iterator.hasNext()) {
            IPAddress itIPAddress = iterator.next();
            for (int i = 0; i < list.size(); i++) {
                IPAddress auxIPAddress = list.get(i);
                if (auxIPAddress.contains(itIPAddress) && !auxIPAddress.equals(itIPAddress)) {
                    iterator.remove();
                    break;
                }
            }
        }
        return list;
    }

}
