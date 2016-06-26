/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package constantsfilesfolders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class PublicFunctions
{

	/**
	 * Converts a List of Strings to lower case
	 * 
	 * @param text
	 *           - text
	 * @return a list of strings in lower case
	 */
	public static List<String> toLowerCase(List<String> text)
	{
		List<String> _text = new ArrayList<String>();
		for (String s : text)
			_text.add(s.toLowerCase());
		return _text;
	}

	/**
	 * Removes special characters, such as punctuation characters and decimal digit characters See:
	 * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
	 * 
	 * @param word
	 *           - word
	 * @return - word without special characters
	 */
	public static String removeSpecialChars(String word)
	{
		return word.replaceAll("(\\p{Punct}+|\\p{Digit}+|¿|¡)", "").replaceAll("\\s+", "").trim();
	}

	/**
	 * Sorts a Map<K, V> on key.
	 * 
	 * Based on: http://javarevisited.blogspot.com.es/2012/12/how-to-sort-hashmap -java-by-key-and-value.html
	 * 
	 * @param map
	 *           - a Map
	 * @param ascendingOrder
	 *           - {false = descending order}; {true = ascending order}
	 * @return - a sorted Map on key (ascending or descending order)
	 */
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByKeys(Map<K, V> map, final boolean ascendingOrder)
	{
		// List<K> keys = new LinkedList<K>(map.keySet());
		// Collections.sort(keys);
		Map<K, V> sortedMap = null;
		if (ascendingOrder)
		{
			sortedMap = new TreeMap(Collections.reverseOrder());
			sortedMap.putAll(map);
			return sortedMap;
		} else
		{
			List<K> keys = new LinkedList<K>(map.keySet());
			Collections.sort(keys);

			/**
			 * LinkedHashMap will keep the keys in the order they are inserted which is currently sorted on natural ordering
			 */
			sortedMap = new LinkedHashMap<K, V>();
			for (K key : keys)
			{
				sortedMap.put(key, map.get(key));
			}
			return sortedMap;
		}
	}

	/**
	 * Sorts a Map<K, V> on Values.
	 * 
	 * Based on: http://javarevisited.blogspot.com.es/2012/12/how-to-sort-hashmap -java-by-key-and-value.html
	 * 
	 * @param map
	 *           - a Map
	 * @param ascendingOrder
	 *           - {false = descending order}; {true = ascending order}
	 * @return - a sorted Map on value (ascending or descending order)
	 */
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map, final boolean ascendingOrder)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				if (ascendingOrder)
				{
					return o1.getValue().compareTo(o2.getValue());
				} else
				{
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// LinkedHashMap will keep the keys in the order they are inserted
		// which is currently sorted on natural ordering
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	/**
	 * Prints the keys and values inside a Map<K, V>
	 * 
	 * @param map
	 *           - generic Map
	 */
	public <K, V> void printMap(Map<K, V> map)
	{
		for (K key : map.keySet())
			System.err.println(map.get(key) + "\t" + key);
	}

	/**
	 * ############### For testing purposes ##############################################
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		PublicFunctions func = new PublicFunctions();
		// creating Hashtable for sorting
		Map<String, Integer> stringInt = new HashMap<String, Integer>();
		stringInt.put("England", 3);
		stringInt.put("USA", 1);
		stringInt.put("China", 2);
		stringInt.put("Russia", 4);

		Map<Integer, String> intString = new HashMap<Integer, String>();
		intString.put(3, "England");
		intString.put(1, "USA");
		intString.put(2, "China");
		intString.put(4, "Russia");

		System.out.println("SORT BY KEYS:");
		// printing hashtable without sorting
		System.out.println("Unsorted Map<String, Integer>: " + stringInt);
		// sorting Map e.g. HashMap, Hashtable by keys in Java
		Map<String, Integer> sortedStringInt = func.sortByKeys(stringInt, true);
		System.out.println("Sorted Map<String, Integer>: " + sortedStringInt);
		sortedStringInt = func.sortByKeys(stringInt, false);
		System.out.println("Sorted Map<String, Integer>: " + sortedStringInt);

		// printing hashtable without sorting
		System.out.println("Unsorted Map<Integer, String>: " + intString);
		// sorting Map e.g. HashMap, Hashtable by keys in Java
		Map<Integer, String> sortedIntString = func.sortByKeys(intString, true);
		System.out.println("Sorted Map<Integer, String>: " + sortedIntString);

		stringInt.put("Japan", 5);
		stringInt.put("Portugal", 0);
		intString.put(0, "Portugal");
		intString.put(5, "Japan");

		System.out.println("\n\nSORT BY VALUES:");
		// printing hashtable without sorting
		System.out.println("Unsorted Map<String, Integer>: " + stringInt);
		// sorting Map e.g. HashMap, Hashtable by keys in Java
		Map<String, Integer> sortedStringInt2 = func.sortByValues(stringInt, true);
		System.out.println("Sorted Map<String, Integer>: " + sortedStringInt2);

		// printing hashtable without sorting
		System.out.println("Unsorted Map<Integer, String>: " + intString);
		// sorting Map e.g. HashMap, Hashtable by keys in Java
		Map<Integer, String> sortedIntString2 = func.sortByValues(intString, false);
		System.out.println("Sorted Map<Integer, String>: " + sortedIntString2);

	}
}
