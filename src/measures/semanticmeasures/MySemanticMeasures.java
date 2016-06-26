/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package measures.semanticmeasures;

import java.util.HashMap;

import constantsfilesfolders.Constants;

/**
 * This class is responsible for calling the {@link ADWLib} class. It can be used to measure the semantic similarity between two English
 * sentences.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class MySemanticMeasures
{
	private ADWLib adwLib = null;

	/**
	 * Default constructor. Only English available!
	 * 
	 * @param language
	 *                language
	 */
	public MySemanticMeasures(String language)
	{
		if (language.equalsIgnoreCase(Constants.EN))
		{
			adwLib = new ADWLib(language);
		} else
		{
			System.err.println("Error! Right now only English is allowed to measure the semantic similarity!!\nPlease use Constants.EN as a parameter!\n");
			return;
		}

	}

	/**
	 * Calculates the Semantic Similarity between two raw English sentences.
	 * 
	 * @param rawSentence1
	 *                raw sentence 1
	 * @param rawSentence2
	 *                raw sentence 2
	 */
	public void calculatingSemanticSimilarityScores(String rawSentence1, String rawSentence2)
	{
		adwLib.calculatingSemanticSimilarityScores(rawSentence1, rawSentence2);
	}

	/**
	 * 
	 * @return an hasMap with all the Semantic scores with disambiguation
	 */
	public HashMap<String, Double> getADWSemanticSimilarityMeasures_With_Disambiguation()
	{
		return adwLib.getSemanticSimilarityValues().getMeasuresWithDesambiguation();
	}

	/**
	 * 
	 * @return an hasMap with all the Semantic scores WITHOUT disambiguation
	 */
	public HashMap<String, Double> getADWSemanticSimilarityMeasures_WITHOUT_Disambiguation()
	{
		return adwLib.getSemanticSimilarityValues().getMeasuresWithoutDesambiguation();
	}

}
