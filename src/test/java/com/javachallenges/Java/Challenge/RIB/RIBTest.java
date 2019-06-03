package com.javachallenges.Java.Challenge.RIB;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.javachallenges.Java.Challenge.Others.StringMatcher;
import inet.ipaddr.AddressStringException;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RIBTest {

    @Test
    public void ribGroupByNextHopShouldWorkOk() throws IOException {
        // given
        String filePath = "json/full_rib_asn_6057.json";
        File file = ResourceUtils.getFile("classpath:" + filePath);
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        ArrayList<Map<String, String>> fullRib =
                new GsonBuilder()
                        .create()
                        .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), type);

        // when
        Map<String, List<IPAddress>> groupedByNextHop = RIB.groupByNextHop(fullRib);

        // then
        assertThat(groupedByNextHop.get("198.32.132.152").size(), equalTo(570));
    }

    @Test
    public void ribGroupByNextHopShouldWorkOkWhenFullRibIsEmpty() {
        // given
        ArrayList<Map<String, String>> fullRib = new ArrayList<>();

        // when
        Map<String, List<IPAddress>> groupedByNextHop = RIB.groupByNextHop(fullRib);

        // then
        assertThat(groupedByNextHop.size(), equalTo(0));
    }

    @Test
    public void filterByIPv4EntriesShouldWorkOk() throws FileNotFoundException {
        // given
        String filePath = "json/full_rib_asn_6057.json";
        File file = ResourceUtils.getFile("classpath:" + filePath);
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        ArrayList<Map<String, String>> fullRib =
                new GsonBuilder()
                        .create()
                        .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), type);

        // when
        Map<String, List<IPAddress>> groupedByNextHop = RIB.groupByNextHop(fullRib);
        Map<String, List<IPAddress>> filterByIPv4Entries = RIB.filterByIPv4Entries(groupedByNextHop);

        // then
        assertThat(filterByIPv4Entries.entrySet().stream().filter(x -> !x.getKey().matches(StringMatcher.IP_REGEX)).count(), equalTo(0L));
    }

    @Test
    public void sortRIBShouldWorkOk() throws FileNotFoundException {
        // given
        String filePath = "json/full_rib_asn_6057.json";
        File file = ResourceUtils.getFile("classpath:" + filePath);
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        ArrayList<Map<String, String>> fullRib =
                new GsonBuilder()
                        .create()
                        .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), type);

        // when
        Map<String, List<IPAddress>> groupedByNextHop = RIB.groupByNextHop(fullRib);
        Map<String, List<IPAddress>> filterByIPv4Entries = RIB.filterByIPv4Entries(groupedByNextHop);
        Map<String, List<IPAddress>> sortedRIB = RIB.sortRIB(filterByIPv4Entries);

        // then
        int maxNetworkPrefix = sortedRIB.get("198.32.132.75").get(0).getNetworkPrefixLength();
        int mediumNetworkPrefix = sortedRIB.get("198.32.132.75").get(255).getNetworkPrefixLength();
        int another = sortedRIB.get("198.32.132.75").get(500).getNetworkPrefixLength();
        assertThat(maxNetworkPrefix <= mediumNetworkPrefix, is(true));
        assertThat(mediumNetworkPrefix <= another, is(true));
    }

    @Test
    public void removeRIBRedundancyShouldWorkOk() throws FileNotFoundException {
        // given
        String filePath = "json/full_rib_asn_6057.json";
        File file = ResourceUtils.getFile("classpath:" + filePath);
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        ArrayList<Map<String, String>> fullRib =
                new GsonBuilder()
                        .create()
                        .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), type);

        // when
        Map<String, List<IPAddress>> groupedByNextHop = RIB.groupByNextHop(fullRib);
        Map<String, List<IPAddress>> filterByIPv4Entries = RIB.filterByIPv4Entries(groupedByNextHop);
        Map<String, List<IPAddress>> sortedRIB = RIB.sortRIB(filterByIPv4Entries);
        int beforeSize = sortedRIB
                .entrySet()
                .parallelStream()
                .map(stringListEntry -> stringListEntry.getValue().size())
                .mapToInt(i -> i).sum();
        Map<String, List<IPAddress>> ribWithoutRedundancy = RIB.removeRIBRedundancy(sortedRIB);
        int lastSize = ribWithoutRedundancy
                .entrySet()
                .parallelStream()
                .map(stringListEntry -> stringListEntry.getValue().size())
                .mapToInt(i -> i).sum();

        // then
        assertThat(beforeSize > lastSize, equalTo(true));
    }

    @Test
    public void caseOne_AggregateDifferentIPAddressesShouldWorkOk() throws AddressStringException {
        // given
        String sIp1 = "192.168.0.0/24";
        String sIp2 = "192.168.1.0/24";
        IPAddress ip1 = new IPAddressString(sIp1).toAddress();
        IPAddress ip2 = new IPAddressString(sIp2).toAddress();

        // when
        IPAddress newIp = RIB.aggregate(ip1, ip2);

        // then
        assertThat(newIp.toString(), equalTo("192.168.0.0/23"));
    }

    @Test
    public void caseTwo_AggregateDifferentIPAddressesShouldWorkOk() throws AddressStringException {
        // given
        String sIp1 = "192.168.1.0/24";
        String sIp2 = "192.168.2.0/24";
        IPAddress ip1 = new IPAddressString(sIp1).toAddress();
        IPAddress ip2 = new IPAddressString(sIp2).toAddress();

        // when
        IPAddress newIp = RIB.aggregate(ip1, ip2);

        // then
        assertThat(newIp, equalTo(null));
    }

    @Test
    public void caseThree_AggregateDifferentIPAddressesShouldWorkOk() throws AddressStringException {
        // given
        String sIp1 = "192.168.2.0/24";
        String sIp2 = "192.168.3.0/24";
        IPAddress ip1 = new IPAddressString(sIp1).toAddress();
        IPAddress ip2 = new IPAddressString(sIp2).toAddress();

        // when
        IPAddress newIp = RIB.aggregate(ip1, ip2);

        // then
        assertThat(newIp.toString(), equalTo("192.168.2.0/23"));
    }

    @Test
    public void caseFor_AggregateDifferentIPAddressesShouldWorkOk() throws AddressStringException {
        // given
        String sIp1 = "192.128.0.0/16";
        String sIp2 = "192.129.0.0/16";
        IPAddress ip1 = new IPAddressString(sIp1).toAddress();
        IPAddress ip2 = new IPAddressString(sIp2).toAddress();

        // when
        IPAddress newIp = RIB.aggregate(ip1, ip2);

        // then
        assertThat(newIp.toString(), equalTo("192.128.0.0/15"));
    }

    @Test
    public void aggregateRIBShouldWorkOk() throws FileNotFoundException {
        // given
        String filePath = "json/full_rib_asn_6057.json";
        File file = ResourceUtils.getFile("classpath:" + filePath);
        Type type = new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType();
        ArrayList<Map<String, String>> fullRib =
                new GsonBuilder()
                        .create()
                        .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), type);
        String entry = "198.32.132.75";

        // when
        Map<String, List<IPAddress>> ribGroupedByNexHop = RIB.groupByNextHop(fullRib);
        Map<String, List<IPAddress>> ribFiltered = RIB.filterByIPv4Entries(ribGroupedByNexHop);
        Map<String, List<IPAddress>> sortedRIB = RIB.sortRIB(ribFiltered);
        Map<String, List<IPAddress>> cleanRIB = RIB.removeRIBRedundancy(sortedRIB);
        Map<String, List<IPAddress>> aggregatedRIB = RIB.aggregateRIB(fullRib);

        // then
        assertThat(aggregatedRIB.get(entry).size() < cleanRIB.get(entry).size(), equalTo(true));
    }
}
