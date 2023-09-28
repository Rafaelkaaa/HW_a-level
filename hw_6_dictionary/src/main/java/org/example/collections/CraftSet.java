package org.example.collections;

import org.example.node.NodeDictionary;

public class CraftSet<K> {
    Dictionary<K,Object> dictionary = new Dictionary<>();

    public boolean put(K key){
       return dictionary.put(key,null);
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }
}
