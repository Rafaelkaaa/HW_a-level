package org.example;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.entity.City;
import org.example.entity.Way;
import org.example.service.CityService;
import org.example.service.WayService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OptimizeCostUtil {
    final WayService wayService;
    final CityService cityService;
    List<List<Way>> routes;
    City first;

    public OptimizeCostUtil(WayService wayService, CityService cityService) {
        this.wayService = wayService;
        this.cityService = cityService;
    }

    public int minRouteCost(City from, City to) {
        routes = new ArrayList<>();
        findAllRoutes(from, to);
        if (routes.size() < 1) {
            throw new RuntimeException("Cant find rout from "
                    + from.getName() + " to " + to.getName());
        }
        List<Integer> routeCosts = allRoutesCost();
        Collections.sort(routeCosts);
        return routeCosts.get(0);
    }

    public List findAllRoutes(City from, City to) {
        List<Way> ways = new ArrayList<>(wayService.idLikeCity1(from.getId()));

        if (routes.isEmpty()) {
            first = from;
            ways.forEach(way -> {
                routes.add(new ArrayList<>(Collections.singletonList(way)));
            });
        } else {
            fillInRoutesList(from, to, ways);
        }
        List<List<Way>> routes = routesRequiringContinuation(from, to);
        for (List<Way> route : routes) {
            findAllRoutes(cityService.findByID(route.get(route.size() - 1).getCityId2()), to);
        }
        return this.routes = this.routes.stream()
                .filter(route -> route.get(route.size() - 1).getCityId2() == to.getId() ||
                        route.get(route.size() - 1).getCityId1() == to.getId())
                .toList();
    }

    private List<List<Way>> routesRequiringContinuation(City from, City to) {
        List<List<Way>> routes = new ArrayList<>(this.routes
                .stream()
                .filter(route -> route.get(route.size() - 1).getCityId2() != to.getId()
                        && route.get(route.size() - 1).getCityId2() != first.getId()
                        && route.get(route.size() - 1).getCityId2() != from.getId())
                .toList());
        return routes;
    }

    private void fillInRoutesList(City from, City to, List<Way> ways) {
        List<List<Way>> routes = new ArrayList<>(this.routes);
        for (List<Way> route : this.routes) {
            if (route.get(route.size() - 1).getCityId1() == from.getId() &&
                    route.get(route.size() - 1).getCityId2() != to.getId()) {
                for (Way way : ways) {
                    List<Way> temp = new ArrayList<>(route);
                    if (way.getCityId1() == from.getId()
                            && way.getCityId2() != first.getId()) {
                        if (!temp.contains(way)) {
                            temp.add(way);
                            routes.add(temp);
                        }
                    }
                }
                routes.remove(route);
            }
        }
        this.routes = routes;
    }


    private List<Integer> allRoutesCost() {
        List<Integer> routeCosts = new ArrayList<>();
        routes.forEach(rout -> {
            AtomicInteger cost = new AtomicInteger();
            rout.forEach(way -> {
                cost.addAndGet(way.getCost());
            });
            routeCosts.add(cost.get());
        });
        return routeCosts;
    }
}