package org.example.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.entity.Way;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WayService {

    final List<Way> ways = new ArrayList<>();

    public void stringToWaysList(List<String> stringList, CityService cityService) {
        int countWays = 0;
        int stringListIndex = 1;
        for (int i = 0; i < cityService.getCities().size(); i++) {
            int countCityWays = Integer.parseInt(stringList.get(stringListIndex));
            String cityName = stringList.get(stringListIndex - 1);
            countWays += countCityWays;
            stringToWay(stringList, cityService, stringListIndex, countCityWays, cityName);
            stringListIndex += countCityWays + 2;
        }
    }

    public List<Way> findByCityId(int id) {
        List<Way> wayList = new ArrayList<>();
        for (Way way : ways) {
            if (way.getCityId2() == id || way.getCityId1() == id) {
                wayList.add(way);
            }
        }
        if (!wayList.isEmpty()) {
            return wayList;
        }
        throw new IllegalArgumentException("Way with city id " + id + " does not exist");
    }

    public List<Way> idLikeCity1 (int id) {
        List<Way> sortedId = findByCityId(id);
        for (Way way : sortedId) {
            if (way.getCityId1() != id) {
                int tempId = way.getCityId1();
                way.setCityId1(way.getCityId2());
                way.setCityId2(tempId);
            }
        }
        return sortedId;
    }

    private void stringToWay(List<String> stringList, CityService cityService, int i, int countCityWays, String cityName) {
        for (int j = i + 1; j <= i + countCityWays; j++) {
            String[] wayData = stringList.get(j).split(" ");
            Way way = new Way();
            way.setCityId1(cityService.findByName(cityName).getId());
            way.setCityId2(Integer.parseInt(wayData[0]));
            way.setCost(Integer.parseInt(wayData[1]));
            create(way);
        }
    }

    private void create(Way way) {
        if (!ways.contains(way)) {
            int tempId = way.getCityId1();
            way.setCityId1(way.getCityId2());
            way.setCityId2(tempId);
            if (!ways.contains(way)) {
                ways.add(way);
            }
        }
    }
}
