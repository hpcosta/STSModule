/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package measures.semanticmeasures;

import java.util.HashMap;

import measures.measureswrapper.MeasuresWrapper;

/**
 * Class that wraps both measures, with and without disambiguation.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class ADWMeasures
{
	private MeasuresWrapper withDisambiguation = null;
	private MeasuresWrapper withoutDisambiguation = null;

	/**
	 * Default constructor.
	 * 
	 * @param withDesambiguation
	 *                Semantic Similarity scores with disambiguation
	 * @param withoutDesambiguation
	 *                Semantic Similarity scores WITHOUT disambiguation
	 */
	public ADWMeasures(MeasuresWrapper withDesambiguation, MeasuresWrapper withoutDesambiguation)
	{
		this.withDisambiguation = withDesambiguation;
		this.withoutDisambiguation = withoutDesambiguation;
	}

	/**
	 * 
	 * @return hashMap containing the measures with disambiguation
	 */
	public HashMap<String, Double> getMeasuresWithDesambiguation()
	{
		return withDisambiguation.getMeasures();
	}

	/**
	 * 
	 * @return hashMap containing the measures without disambiguation
	 */
	public HashMap<String, Double> getMeasuresWithoutDesambiguation()
	{
		return withoutDisambiguation.getMeasures();
	}

	/**
	 * 
	 * @return string with the Semantic Similarity measures with disambiguation
	 */
	public String toStringWithDisambiguation()
	{
		return withDisambiguation.toString();
	}

	/**
	 * 
	 * @return string with the Semantic Similarity measures WITHOUT disambiguation
	 */
	public String toStringWithoutDisambiguation()
	{
		return withoutDisambiguation.toString();
	}

}
