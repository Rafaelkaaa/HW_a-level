package org.example;

import org.example.collections.Dictionary;

public class DictionaryApp {
    public static void main(String[] args) {
        Dictionary<String, Integer> dictionary = new Dictionary();
        System.out.println("dictionary.put(1984, 1949) = "
                + dictionary.put("1984", 1949));
        System.out.println("dictionary.put(Brave New World, 333) = "
                + dictionary.put("Brave New World", 333));
        System.out.println("dictionary.put(Fahrenheit 451, 1953) = "
                + dictionary.put("Fahrenheit 451", 1953));
        System.out.println("dictionary.put(Animal Farm, 1945) = "
                + dictionary.put("Animal Farm", 1945));
        System.out.println("dictionary = " + dictionary);
        System.out.println();

        Dictionary<String, Integer> subDictionary = new Dictionary();
        System.out.println("subDictionary.put(Lord of the Flies, 1954) = "
                + subDictionary.put("Lord of the Flies", 1954));
        System.out.println("subDictionary.put(Hard to Be a God, 1964) = "
                + subDictionary.put("Hard to Be a God", 1964));
        System.out.println("subDictionary.put(Brave New World, 1932) = "
                + subDictionary.put("Brave New World", 1932));
        System.out.println("subDictionary = " + subDictionary);
        System.out.println("subDictionary.putAll(subDictionary) = " + dictionary.putAll(subDictionary));
        System.out.println("dictionary = " + dictionary);


        System.out.println();
        System.out.println("dictionary = " + dictionary);
        System.out.println("dictionary.remove(Hard to Be a God) = "
                + dictionary.remove("Hard to Be a God"));
        System.out.println("dictionary.get(Animal Farm) = "
                + dictionary.get("Animal Farm"));
        System.out.println("dictionary.remove(ksejrg) = "
                + dictionary.remove("ksejrg"));
        System.out.println("dictionary = " + dictionary);

        System.out.println();
        System.out.println("dictionary = " + dictionary);
        System.out.println("dictionary.keySet() = " + dictionary.keySet());
        System.out.println("dictionary.values() = " + dictionary.values());

        System.out.println();
        System.out.println("dictionary.containsKey(\"Animal Farm\") = "
                + dictionary.containsKey("Animal Farm"));
        System.out.println("dictionary.containsValue(1945) = "
                + dictionary.containsValue(1945));
        System.out.println("dictionary.containsKey(\"akjn\") = "
                + dictionary.containsKey("akjn"));
        System.out.println("dictionary.containsValue(495876) = "
                + dictionary.containsValue(495876));
    }
}