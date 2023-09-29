package org.example;

import org.example.service.StringService;

public class ReverseApp {
    public static void main(String[] args) {

        System.out.println(StringService.reverse("Hello World Ri"));
        System.out.println(StringService.reverse("Hello World Ri", "lo World"));
        System.out.println(StringService.reverse("Hello World", 2, 9));
    }
}