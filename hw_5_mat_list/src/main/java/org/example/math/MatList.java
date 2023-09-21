package org.example.math;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.collections.CraftArrayList;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MatList<E extends Number> {
    CraftArrayList<E> matList;

    public MatList(MatList... matList) {
        this.matList = matList[0].getMatList();
        if (matList.length > 1) {
            for (int i = 1; i < matList.length; i++) {
                this.matList.concat(matList[i].getMatList());
            }
        }
    }

    public MatList(E[]... numbers) {
        this.matList = new CraftArrayList<>(numbers[0]);
        if (numbers.length > 0) {
            for (int i = 1; i < numbers.length; i++) {
                this.matList.concat(new CraftArrayList<E>(numbers[i]));
            }
        }
    }

    public MatList() {
        matList = new CraftArrayList<>();
    }

    public void add(E n) {
        matList.create(n);
    }

    public void add(E... n) {
        matList.concat(new MatList<>(n).getMatList());
    }

    public  void join(MatList... ml) {
        MatList mathList = new MatList<>(ml);
        matList.concat(mathList.getMatList());
    }

    public  void intersection(MatList... ml) {
        join(ml);
        for (int i = 0; i < ml.length; i++) {
            for (int j = 0; j < matList.size(); j++) {
                if (!ml[i].getMatList().contains(matList.get(j))) {
                    matList.remove(j);
                }
            }
        }
    }

    public  void sortDesc() {
        matList.sortDesc();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        MatList ml = cut(firstIndex, lastIndex);
        ml.sortDesc();
        insertInMl(firstIndex, lastIndex, ml);
    }

    public void sortDesc(E value) {
        for (int i = 0; i < matList.size(); i++) {
            if (get(i).equals(value)) {
                sortDesc(i, matList.size() - 1);
                return;
            }
        }
    }

    public void sortAsc() {
        matList.sortAsc();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        MatList ml = cut(firstIndex, lastIndex);
        ml.sortAsc();
        insertInMl(firstIndex, lastIndex, ml);
    }

    public void sortAsc(E value) {
        for (int i = 0; i < matList.size(); i++) {
            if (get(i).equals(value)) {
                sortAsc(i, matList.size() - 1);
                return;
            }
        }
    }

    public Number get(int index) {
        return matList.get(index);
    }

    public Number getMax() {
        E n = matList.get(0);
        for (int i = 1; i < matList.size(); i++) {
            if (n.doubleValue() < matList.get(i).doubleValue()) {
                n = matList.get(i);
            }
        }
        return n;
    }

    public Number getMin() {
        E n = matList.get(0);
        for (int i = 1; i < matList.size(); i++) {
            if (n.doubleValue() > matList.get(i).doubleValue()) {
                n = matList.get(i);
            }
        }
        return n;
    }

    public Number getAverage() {
        double sum = 0;
        for (int i = 0; i < matList.size(); i++) {
            sum += matList.get(i).doubleValue();
        }
        return sum / matList.size();
    }

    public Number getMedian() {
        matList.sortAsc();
        int size = matList.size();
        matList.sortAsc();
        if (size % 2 == 0) {
            double firstE = matList.get(size / 2 - 1).doubleValue();
            double secondE = matList.get(size / 2).doubleValue();
            return (firstE + secondE) / 2;
        }
        return  get(size / 2);
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex + 1];
        int count = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            numbers[count++] = matList.get(i);
        }
        return numbers;
    }

    public Number[] toArray() {
        Number[] numbers = new Number[matList.size()];
        for (int i = 0; i <= matList.size(); i++) {
            numbers[i] = matList.get(i);
        }
        return numbers;
    }

    public MatList cut(int firstIndex, int lastIndex) {
        MatList ml = new MatList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            ml.getMatList().create(this.matList.get(i));
        }
        return ml;
    }

    public void clear() {
        matList = new CraftArrayList<>();
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            if (matList.contains((E) number)) {
                matList.remove((E) number);
            }
        }
    }

    @Override
    public String toString() {
        return matList.toString();
    }

    private void insertInMl(int firstIndex, int lastIndex, MatList ml) {
        int count = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            this.matList.updateByIndex(i, (E) ml.get(count++));
        }
    }
}
