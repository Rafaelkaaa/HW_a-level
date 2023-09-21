package org.example;

import org.example.math.MatList;

public class MatApp {
    public static void main(String[] args) {
        MatList matList = new MatList(new Integer[]{1,3,2}, new Integer[]{8,6,8});
                matList.join(new MatList<>(new Integer[]{1,1,7}),new MatList<>(new Integer[]{5,7,7}));
      System.out.println(matList);
        System.out.println(matList.getMedian());
    }
}