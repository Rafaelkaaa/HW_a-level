package org.example;

import org.example.math.MatList;

import java.util.Arrays;

public class MatApp {
    public static void main(String[] args) {

        MatList matList = new MatList(new Integer[]{1, 3, 2}, new Integer[]{8, 6, 8});
        System.out.println(matList);

        matList.add(10);
        System.out.println("add(10) \n" + matList);

        matList.add(new Integer[]{12, 13, 15});
        System.out.println("add({12, 13, 15}) \n" + matList);

        matList.join(new MatList<>(new Integer[]{1, 1, 7}), new MatList<>(new Integer[]{5, 7, 4}));
        System.out.println("join({1, 1, 7},{5, 7, 4})\n" + matList);

        matList.intersection(new MatList(new Integer[]{1, 3, 8, 4, 12}));
        System.out.println("intersection ({1, 3, 8, 4, 12}) \n" + matList);

        matList.sortDesc();
        System.out.println("sortDesc() \n" + matList);

        matList.sortAsc();
        System.out.println("sortAsc() \n" + matList);

        matList.sortDesc(1, 6);
        System.out.println("sortDesc(1, 6) \n" + matList);

        matList.sortAsc(3, 7);
        System.out.println("sortAsc(3, 7) \n" + matList);

        matList.sortDesc(3);
        System.out.println("sortDesc(3) \n" + matList);

        matList.sortAsc(12);
        System.out.println("sortAsc(12)\n" + matList);

        System.out.println("get(5)\n" + matList.get(5));

        System.out.println("getMax()\n" + matList.getMax());

        System.out.println("getMin()\n" + matList.getMin());

        System.out.println("getAverage()\n" + matList.getAverage());

        System.out.println("getMedian()\n" + matList.getMedian());

        System.out.println("Number[] toArray()\n" + Arrays.toString(matList.toArray()));

        System.out.println("Number[] toArray(2, 6)\n" + Arrays.toString(matList.toArray(3, 5)));

        System.out.println("MatList cut(2, 6)\n" + matList.cut(2, 6));

        matList.clear(new Integer[]{1, 4, 12});
        System.out.println("clear({1,4,12})\n" + matList);

        matList.clear();
        System.out.println("clear({1,4,12})\n" + matList);
    }
}