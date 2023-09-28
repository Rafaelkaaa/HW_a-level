package org.example.collections;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.node.NodeDictionary;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dictionary<K, V> {
    CraftArrayList<CraftArrayList<NodeDictionary<K, V>>> arrayList = new CraftArrayList<>(new CraftArrayList[10]);
    int count = 0;
    NodeDictionary<K, V> node;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count > 0;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);

            if (arrayList != null) {
                for (int j = 0; j < arrayList.size(); j++) {
                    node = arrayList.get(j);

                    if (node.getKey().equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);

            if (arrayList != null) {
                for (int j = 0; j < arrayList.size(); j++) {
                    node = arrayList.get(j);
                    if (node.getValue() != null) {
                        if (node.getValue().equals(value)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public V get(K key) {
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.get(i).size(); j++) {
                if (arrayList.get(i).get(j).getKey().equals(key)) {
                    return arrayList.get(i).get(j).getValue();
                }
            }
        }
        throw new IllegalArgumentException("Dictionary isn't contain key " + key);
    }

    public boolean put(K key, V value) {
        if (containsKey(key)) {
            for (int i = 0; i < arrayList.size(); i++) {
                CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);

                for (int j = 0; j < arrayList.size(); j++) {
                    node = arrayList.get(j);
                    if (node.getKey().equals(key)) {
                        arrayList.update(arrayList.get(j), new NodeDictionary<>(key, value));
                    }
                }
            }
        } else {
            node = new NodeDictionary<>(key, value);
            int nodeIndex = node.hashCode() % 10;
            if (nodeIndex < 0) {
                nodeIndex *= -1;
            }

            while (arrayList.get(nodeIndex) == null) {
                arrayList.create(new CraftArrayList<>());
            }

            arrayList.get(nodeIndex).create(new NodeDictionary<>(key, value));
        }
        count++;
        return containsValue(value) && containsKey(key);
    }

    public boolean remove(K key) {
        for (int i = 0; i < arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);

            for (int j = 0; j < arrayList.size(); j++) {
                node = arrayList.get(j);

                if (node.getKey().equals(key)) {
                    arrayList.remove(j);
                    count--;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        for (int i = 0; i < dictionary.arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = dictionary.arrayList.get(i);

            for (int j = 0; j < arrayList.size(); j++) {
                node = arrayList.get(j);

                if (!put(node.getKey(), node.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean clear() {
        arrayList = new CraftArrayList<>(new CraftArrayList[15]);
        count = 0;
        if (isEmpty()) {
            return true;
        }
        return false;
    }

    public CraftSet<K> keySet() {
        CraftSet<K> keys = new CraftSet<>();
        for (int i = 0; i < arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);

            for (int j = 0; j < arrayList.size(); j++) {
                node = arrayList.get(j);

                if (node != null) {
                    keys.put(node.getKey());
                }
            }
        }
        return keys;
    }

    public CraftArrayList<V> values() {
        CraftArrayList<V> values = new CraftArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);

            for (int j = 0; j < arrayList.size(); j++) {
                node = arrayList.get(j);

                if (node != null) {
                    values.create(node.getValue());
                }
            }
        }
        return values;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Dictionary{");
        for (int i = 0; i < arrayList.size(); i++) {
            CraftArrayList<NodeDictionary<K, V>> arrayList = this.arrayList.get(i);
            for (int j = 0; j < arrayList.size(); j++) {
                node = arrayList.get(j);

                builder.append('{').append(node.getKey().toString());
                if(node.getValue()!= null) {
                    builder.append(" = ").append(node.getValue().toString());
                }
                builder.append("}");
            }
        }
        builder.append('}');
        return builder.toString();
    }
}
