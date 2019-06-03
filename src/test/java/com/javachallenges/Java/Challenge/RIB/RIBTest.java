package com.javachallenges.Java.Challenge.RIB;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.javachallenges.Java.Challenge.Others.StringMatcher;
import inet.ipaddr.IPAddress;
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
}
