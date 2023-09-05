package org.example.craft.collections;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CraftArrayList<O> {

    Object[] array = new Object[10];

    int lastIndex = 0;

    public void create(O value) {
        if (lastIndex == array.length) {
            Object[] tempArray = new Object[this.array.length * 2];
            System.arraycopy(array, 0, tempArray, 0, array.length);
            array = tempArray;
        }
        array[lastIndex] = value;
        lastIndex++;
    }


    public void remove(int index) {
        System.arraycopy(this.array, index + 1, this.array, index, this.array.length - 1 - index);
        lastIndex--;
    }

    public void remove(O value) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(value)) {
                remove(i);
                return;
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

    public int size() {
        return lastIndex;
    }

    public O get(int index) {
        return (O) this.array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
