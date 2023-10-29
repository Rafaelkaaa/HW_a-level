package org.example.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.OptimizeCostUtil;
import org.example.service.CityService;
import org.example.service.FileService;
import org.example.service.RouteService;
import org.example.service.WayService;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Controller {

    final WayService wayService = new WayService();
    final CityService cityService = new CityService();

    public void start(List<String> routes) {
        String filePath = "input.txt";
        FileService fileService = new FileService(filePath);
        fileService.writeOutputIntoFile();
        fileService.appendStringIntoFile(Integer.toString(routes.size()),true);
        routes.forEach(route -> fileService.appendStringIntoFile(route,true));
        RouteService routeService = setupRouteService(fileService);
        OptimizeCostUtil optimizeCostUtil = new OptimizeCostUtil(wayService, cityService);
        routeService.getRoutes()
                .forEach(route -> {
                    fileService.appendStringIntoFile(Integer.toString(optimizeCostUtil
                            .minRouteCost(route.getFrom(), route.getTo())), true);
                });
    }

    private RouteService setupRouteService(FileService fileService) {
        List<String> listStrings = fileService.readFileText();
        cityService.stringListToCitiesList(listStrings);
        wayService.stringToWaysList(listStrings, cityService);
        RouteService routeService = new RouteService(wayService, cityService);
        routeService.stringListToRouteList(listStrings);
        fileService.appendStringIntoFile("Result:",true);
        return routeService;
    }
}
