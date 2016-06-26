/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.tokenizer.opennlp;

import java.io.InputStream;

import constantsfilesfolders.Constants;

/**
 * This class loads the Tokeniser Detector for English or Portuguese.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class OpenNLPTokenizerLoadLanguageResources
{
	private InputStream tokenDetectorModelIn = null;

	/**
	 * Default Constructor.
	 */
	public OpenNLPTokenizerLoadLanguageResources(String language)
	{

		if (language.equalsIgnoreCase(Constants.EN))
		{
			tokenDetectorModelIn = getClass().getResourceAsStream(Constants.EN_TOKENIZER);
		} else if (language.equalsIgnoreCase(Constants.PT))
		{
			tokenDetectorModelIn = getClass().getResourceAsStream(Constants.PT_TOKENIZER);
		} else
		{
			System.err.println("> Warning in the OpenNLPTokenizerLoadLanguageResources, the required language (" + language
					+ ") is not available!\n\t>Loading EN Tokenizer model by default!\n");
			tokenDetectorModelIn = getClass().getResourceAsStream(Constants.EN_TOKENIZER);
		}
	}

	/**
	 * Returns the token detector model as a InputStream object
	 * 
	 * @return the tokenDetectorModelIn
	 */
	public InputStream getTokenDetectorModelIn()
	{
		return tokenDetectorModelIn;
	}

}
