package org.example.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.entity.City;
import org.example.entity.Route;

import java.util.ArrayList;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteService {
    final WayService wayService;
    final CityService cityService;
    @Getter
    List<Route> routes = new ArrayList<>();

    public RouteService(WayService wayService, CityService cityService) {
        this.wayService = wayService;
        this.cityService = cityService;
    }

    public boolean stringListToRouteList(List<String> stringList) {
        int deleteIndex = cityService.getCities().size() * 2 +
                wayService.getWays().size() * 2;
        for (int i = 0; i < deleteIndex; i++) {
            stringList.remove(0);
        }
        int routeLength = Integer.valueOf(stringList.get(0));
        for (int i = 1; i <= routeLength; i++) {
            String[] wayFromAndTo = stringList.get(i).split(" ");
            City from = cityService.findByName(wayFromAndTo[0]);
            City to = cityService.findByName(wayFromAndTo[1]);
            routes.add(new Route(from, to));
        }
        return routes.size() == routeLength;
    }
}
