package org.example.collections;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Comparator;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CraftArrayList<O> {

    O[] array;
    int lastIndex = 0;

    public CraftArrayList(O[] array) {
        this.array = array;
        lastIndex = array.length;
    }

    public CraftArrayList() {
        array =(O[]) new Object[10];
    }

    public void create(O value) {
        if (array == null) {
            array =(O[]) new Object[10];
        }
        if (lastIndex == array.length) {
            O[] tempArray = (O[])new Object[this.array.length * 2];
            System.arraycopy(array, 0, tempArray, 0, array.length);
            array = tempArray;
        }
        array[lastIndex] = value;
        lastIndex++;
    }

    public void remove(int index) {
        Object[] newArray = new Object[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        array = (O[]) newArray;
        --lastIndex;
    }

    public void remove(O value) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(value)) {
                remove(i);
                i--;
            }
        }
    }

    public void update(O oldValue, O newValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(oldValue)) {
                array[i] = newValue;
                return;
            }
        }
    }

    public void updateByIndex(int index, O newValue){
        array[index] = newValue;
    }

    public boolean contains(O value) {
        if (array!= null){
            for (int i = 0; i < lastIndex; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        }
        return false;
    }

    public int size() {
        return lastIndex;
    }

    public O get(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public void concat(CraftArrayList<O> craftList) {
        for (int i = 0; i < craftList.size(); i++) {
            create(craftList.get(i));
        }
    }

    public void sortDesc(){
        array = (O[]) Arrays.stream(array)
                .filter(o->o!=null)
                .sorted((Comparator<? super O>) Comparator.reverseOrder())
                .toArray();
    }

    public void sortAsc(){
        array = (O[]) Arrays.stream(array)
                .filter(o->o!=null)
                .sorted()
                .toArray();
    }
}
