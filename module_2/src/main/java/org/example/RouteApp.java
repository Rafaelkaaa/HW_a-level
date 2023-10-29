package org.example;

import org.example.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class RouteApp {
    public static void main(String[] args) {
        List<String> routes = new ArrayList<>();
        routes.add("Asgard Midgard");
        routes.add("Muspelheim Helheim");
        routes.add("Vanaheimr Niflheim");
        new Controller().start(routes);
    }
}
