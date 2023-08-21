package org.example;

import java.time.LocalTime;
import java.util.*;

public class ServiceDataFromUser {

    public long sumNumericalValues(String string) {
        long result = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
                result += Character.getNumericValue(string.charAt(i));
            }
        }
        return result;
    }

    public List<Map.Entry<Character, Long>> countSameSymbols(String string) {
        Map<Character, Long> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            putSymbolsToMap(map, string.charAt(i));
        }
        return sortEntries(map);
    }

    public String countDurationLessons(Long numberOfLessons) {
        LocalTime startTime = LocalTime.of(9, 00);
        Long durationLessons = 45 * numberOfLessons;
        Long durationBreaks = (numberOfLessons / 2 * 5) + ((numberOfLessons - 1) / 2 * 15);
        return startTime.plusMinutes(durationLessons + durationBreaks).toString();
    }

    private List<Map.Entry<Character, Long>> sortEntries(Map<Character, Long> map) {
        List<Map.Entry<Character, Long>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort(new ValueComparator());
        return sortedList;
    }

    private static void putSymbolsToMap(Map<Character, Long> map, Character key) {
        Long value = map.get(key);
        if (value != null) {
            map.put(key, ++value);
        } else {
            map.put(key, 1L);
        }
    }

    class ValueComparator implements Comparator<Map.Entry<Character, Long>> {
        @Override
        public int compare(Map.Entry<Character, Long> entry1, Map.Entry<Character, Long> entry2) {
            return entry2.getValue().compareTo(entry1.getValue());
        }
    }
}
