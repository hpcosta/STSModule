/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package measures;

import java.util.HashMap;

import measures.semanticmeasures.MySemanticMeasures;
import constantsfilesfolders.Constants;

/**
 * This class is responsible for calling the {@link SemanticMeasuresManager} class. It can be used to measure the semantic similarity
 * between two English sentences.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class SemanticMeasuresManager
{
	private MySemanticMeasures semanticManager = null;

	/**
	 * Default Constructor. Only English available!
	 * 
	 * @param language
	 *                language of the sentences
	 */
	public SemanticMeasuresManager(String language)
	{
		semanticManager = new MySemanticMeasures(language);
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
		semanticManager.calculatingSemanticSimilarityScores(rawSentence1, rawSentence2);
	}

	/**
	 * 
	 * @return an hasMap with all the Semantic scores with disambiguation
	 */
	public HashMap<String, Double> getSemanticSimilarityMeasures_With_Disambiguation()
	{
		return semanticManager.getADWSemanticSimilarityMeasures_With_Disambiguation();
	}

	/**
	 * 
	 * @return an hasMap with all the Semantic scores WITHOUT disambiguation
	 */
	public HashMap<String, Double> getSemanticSimilarityMeasures_WITHOUT_Disambiguation()
	{
		return semanticManager.getADWSemanticSimilarityMeasures_WITHOUT_Disambiguation();
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		// String sentence1 = "The bird is bathing in the sink.";
		// String sentence2 = "Birdie is washing itself in the water basin.";
		// String sentence1 = "This guy is a bad ass.";
		// String sentence2 = "These guys are bad ass.";
		String sentence1 = "The udders of a dairy cow that is standing in a pasture near a large building.";
		String sentence2 = "A cows ass and some buildings.";

		SemanticMeasuresManager semanticSimilarity = new SemanticMeasuresManager(Constants.EN);
		semanticSimilarity.calculatingSemanticSimilarityScores(sentence1, sentence2);

		System.out.println("with desambiguation: ");
		System.out.println(semanticSimilarity.getSemanticSimilarityMeasures_With_Disambiguation());

		System.out.println("\nWITHOUT desambiguation: ");
		System.out.println(semanticSimilarity.getSemanticSimilarityMeasures_WITHOUT_Disambiguation());
	}
}
