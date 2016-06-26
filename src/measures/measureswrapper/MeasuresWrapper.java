/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package measures.measureswrapper;

import java.util.HashMap;

import constantsfilesfolders.Constants;

/**
 * Object that represents a set of measures (measureName - value).
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class MeasuresWrapper
{
	private HashMap<String, Double> measures = null;

	/**
	 * Default constructor.
	 */
	public MeasuresWrapper()
	{
		measures = new HashMap<String, Double>();
	}

	/**
	 * Adds an HashMap with measures.
	 * 
	 * @param measuresMap
	 *           measures
	 */
	public void addMeasures(HashMap<String, Double> measuresMap)
	{
		for (String measureName : measuresMap.keySet())
		{
			if (measures.containsKey(measureName)) System.err.println("This measures (" + measureName + ") already exists in the MeasuresWrapper!!!");
			measures.put(measureName, measuresMap.get(measureName));
		}

	}

	/**
	 * Adds a new measure.
	 * 
	 * @param measureName
	 *           measure name
	 * @param score
	 *           score
	 */
	public void addMeasure(String measureName, double score)
	{
		measures.put(measureName, score);
	}

	/**
	 * 
	 * @return an HashMap with all the measures.
	 */
	public HashMap<String, Double> getMeasures()
	{
		return measures;
	}

	/**
	 * 
	 * @return a String HashMap with all the measures.
	 */
	public HashMap<String, String> getMeasures2()
	{
		HashMap<String, String> measures2 = new HashMap<String, String>();
		for (String name : measures.keySet())
			measures2.put(name, String.valueOf(measures.get(name)));

		return measures2;
	}

	/**
	 * Returns a String with all the measures.
	 */
	public String toString()
	{
		StringBuffer str = new StringBuffer();
		// Semantic Measures without disambiguation
		if (measures.containsKey(Constants.MEASURE_WEIGHTEDOVERLAP))
		{
			str.append(Constants.MEASURE_WEIGHTEDOVERLAP + ": \t" + measures.get(Constants.MEASURE_WEIGHTEDOVERLAP) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_JACCARD))
		{
			str.append(Constants.MEASURE_JACCARD + ": \t\t" + measures.get(Constants.MEASURE_JACCARD) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_COSINE))
		{
			str.append(Constants.MEASURE_COSINE + ": \t\t" + measures.get(Constants.MEASURE_COSINE) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_JENSENSHANNON))
		{
			str.append(Constants.MEASURE_JENSENSHANNON + ": \t\t" + measures.get(Constants.MEASURE_JENSENSHANNON) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_KLDIVERGENCE))
		{
			str.append(Constants.MEASURE_KLDIVERGENCE + ": \t\t" + measures.get(Constants.MEASURE_KLDIVERGENCE) + "\n");
		}

		// semantic measures with disambiguation
		if (measures.containsKey(Constants.MEASURE_DIS + Constants.MEASURE_WEIGHTEDOVERLAP))
		{
			str.append(Constants.MEASURE_DIS + Constants.MEASURE_WEIGHTEDOVERLAP + ": \t"
					+ measures.get(Constants.MEASURE_DIS + Constants.MEASURE_WEIGHTEDOVERLAP) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_DIS + Constants.MEASURE_JACCARD))
		{
			str.append(Constants.MEASURE_DIS + Constants.MEASURE_JACCARD + ": \t\t" + measures.get(Constants.MEASURE_DIS + Constants.MEASURE_JACCARD)
					+ "\n");
		}
		if (measures.containsKey(Constants.MEASURE_DIS + Constants.MEASURE_COSINE))
		{
			str.append(Constants.MEASURE_DIS + Constants.MEASURE_COSINE + ": \t\t" + measures.get(Constants.MEASURE_DIS + Constants.MEASURE_COSINE)
					+ "\n");
		}
		if (measures.containsKey(Constants.MEASURE_DIS + Constants.MEASURE_JENSENSHANNON))
		{
			str.append(Constants.MEASURE_DIS + Constants.MEASURE_JENSENSHANNON + ": \t\t"
					+ measures.get(Constants.MEASURE_DIS + Constants.MEASURE_JENSENSHANNON) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_DIS + Constants.MEASURE_KLDIVERGENCE))
		{
			str.append(Constants.MEASURE_DIS + Constants.MEASURE_KLDIVERGENCE + ": \t\t"
					+ measures.get(Constants.MEASURE_DIS + Constants.MEASURE_KLDIVERGENCE) + "\n");
		}

		// Similarity Measures
		if (measures.containsKey(Constants.MEASURE_NUMBEROFCOMMONTOKENS))
		{
			str.append(Constants.MEASURE_NUMBEROFCOMMONTOKENS + ": \t\t" + measures.get(Constants.MEASURE_NUMBEROFCOMMONTOKENS) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_TOKENS + Constants.MEASURE_CHISQUARE))
		{
			str.append(Constants.MEASURE_TOKENS + Constants.MEASURE_CHISQUARE + ": \t\t"
					+ measures.get(Constants.MEASURE_TOKENS + Constants.MEASURE_CHISQUARE) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_TOKENS + Constants.MEASURE_SCC))
		{
			str.append(Constants.MEASURE_TOKENS + Constants.MEASURE_SCC + ": \t\t\t" + measures.get(Constants.MEASURE_TOKENS + Constants.MEASURE_SCC)
					+ "\n");
		}

		if (measures.containsKey(Constants.MEASURE_NUMBEROFCOMMONSTEMMS))
		{
			str.append(Constants.MEASURE_NUMBEROFCOMMONSTEMMS + ": \t\t" + measures.get(Constants.MEASURE_NUMBEROFCOMMONSTEMMS) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_STEMMS + Constants.MEASURE_CHISQUARE))
		{
			str.append(Constants.MEASURE_STEMMS + Constants.MEASURE_CHISQUARE + ": \t\t"
					+ measures.get(Constants.MEASURE_STEMMS + Constants.MEASURE_CHISQUARE) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_STEMMS + Constants.MEASURE_SCC))
		{
			str.append(Constants.MEASURE_STEMMS + Constants.MEASURE_SCC + ": \t\t\t" + measures.get(Constants.MEASURE_STEMMS + Constants.MEASURE_SCC)
					+ "\n");
		}

		if (measures.containsKey(Constants.MEASURE_NUMBEROFCOMMONLEMMAS))
		{
			str.append(Constants.MEASURE_NUMBEROFCOMMONLEMMAS + ": \t\t" + measures.get(Constants.MEASURE_NUMBEROFCOMMONLEMMAS) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_LEMMAS + Constants.MEASURE_CHISQUARE))
		{
			str.append(Constants.MEASURE_LEMMAS + Constants.MEASURE_CHISQUARE + ": \t\t"
					+ measures.get(Constants.MEASURE_LEMMAS + Constants.MEASURE_CHISQUARE) + "\n");
		}
		if (measures.containsKey(Constants.MEASURE_LEMMAS + Constants.MEASURE_SCC))
		{
			str.append(Constants.MEASURE_LEMMAS + Constants.MEASURE_SCC + ": \t\t\t" + measures.get(Constants.MEASURE_LEMMAS + Constants.MEASURE_SCC)
					+ "\n");
		}

		return str.toString();
	}

}
