/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet;

import java.util.HashMap;

import constantsfilesfolders.Constants;

/**
 * This class is responsible for calling the {@link BabelNetManager} class. It can be used to measure the conceptual similarity between two
 * raw sentences (English, Spanish and Portuguese).
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class MyBabelNet
{
	private BabelNetManager babelNetManager = null;

	/**
	 * Default constructor.
	 * 
	 * @param language
	 *                language (English, Spanish and Portuguese)
	 */
	public MyBabelNet(String language)
	{
		babelNetManager = new BabelNetManager(language);
	}

	/**
	 * Receives two raw sentences and returns an hashMap with all the conceptual similarity scores (MeasureName - Score).
	 * 
	 * @param rawText1
	 *                raw text1
	 * @param rawText2
	 *                raw text2
	 * @return an HashMap with all the conceptual similarity scores (MeasureName - Score)
	 */
	public HashMap<String, Double> getConceptualSimilarity(String rawText1, String rawText2)
	{
		return babelNetManager.getConceptualSimilarity(rawText1, rawText2);
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		MyBabelNet myBabelNet = new MyBabelNet(Constants.EN);
		String rawTextEN1 = "John said he is considered a witness but not a suspect.";
		String rawTextEN2 = "\"He is not a suspect anymore.\" John said.";
		// String rawTextES1 = "John dijo que él es considerado como testigo, y no como sospechoso.";
		// String rawTextES2 = "\"Él ya no es un sospechoso,\" John dijo.";

		HashMap<String, Double> conceptualSimilarity = myBabelNet.getConceptualSimilarity(rawTextEN1, rawTextEN2);
		for (String measure : conceptualSimilarity.keySet())
			System.err.println(measure + " " + conceptualSimilarity.get(measure));

	}
}
