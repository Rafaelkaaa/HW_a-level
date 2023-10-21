package org.example.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.entity.City;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityService {
    @Getter
    List<City> cities = new ArrayList<>();

    public boolean stringListToCitiesList(List<String> stringList) {
        int length = Integer.parseInt(stringList.get(0));
        stringList.remove(0);
        int stringListIndex = 0;
        for (int i = 0; i < length; i++) {
            City city = create(stringList, stringListIndex);
            stringListIndex += city.getCountWays() + 2;
        }
        return length == cities.size();
    }

    public City findByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        throw new IllegalArgumentException("City with name " + name + " does not exist");
    }

    public City findByID(int id) {
        for (City city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }
        throw new IllegalArgumentException("City with id " + id + " does not exist");
    }

    private City create(List<String> stringList, int stringListIndex) {
        City city = new City();
        city.setId(cities.size() + 1);
        city.setName(stringList.get(stringListIndex).trim());
        city.setCountWays(Integer.parseInt(stringList.get(stringListIndex + 1)));
        cities.add(city);
        return city;
    }
}
