package com.javachallenges.Java.Challenge.RIB;

import com.javachallenges.Java.Challenge.Others.StringMatcher;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;

import java.util.*;
import java.util.stream.Collectors;

public class RIB {

    private static final String NEXT_HOP = "next_hop";
    private static final String PREFIX = "prefix";

    public static Map<String, List<IPAddress>> aggregateRIB(ArrayList<Map<String, String>> fullRib) {
        Map<String, List<IPAddress>> ribGroupedByNexHop= groupByNextHop(fullRib);
        Map<String, List<IPAddress>> ribFiltered = filterByIPv4Entries(ribGroupedByNexHop);
        Map<String, List<IPAddress>> sortedRIB = sortRIB(ribFiltered);
        Map<String, List<IPAddress>> cleanRIB = removeRIBRedundancy(sortedRIB);
        // aggregateRIB

        return cleanRIB;
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

    public static Map<String, List<IPAddress>> sortRIB(Map<String, List<IPAddress>> ribGroupedByNextHop){
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
        while(iterator.hasNext()) {
            IPAddress itIPAddress = iterator.next();
            for (int i = 0 ; i < list.size(); i++) {
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
