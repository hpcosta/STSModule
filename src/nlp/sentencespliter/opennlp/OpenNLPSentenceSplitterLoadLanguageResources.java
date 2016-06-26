/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.sentencespliter.opennlp;

import java.io.InputStream;

import constantsfilesfolders.Constants;

/**
 * This class loads the Sentence Splitter Model for English or Portuguese.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class OpenNLPSentenceSplitterLoadLanguageResources
{
	private InputStream sentenceSplitterModelIn = null;

	/**
	 * Default Constructor
	 */
	public OpenNLPSentenceSplitterLoadLanguageResources(String language)
	{
		if (language.equalsIgnoreCase(Constants.EN))
		{
			sentenceSplitterModelIn = getClass().getResourceAsStream(Constants.EN_SENTENCE_DETECTOR);
		} else if (language.equalsIgnoreCase(Constants.PT))
		{
			sentenceSplitterModelIn = getClass().getResourceAsStream(Constants.PT_SENTENCE_DETECTOR);
		} else
		{
			System.err.println("> Warning in the OpenNLPLoadLanguageResources, the required language (" + language
					+ ") is not available!\n\t>Loading EN Sentence Splitter model by default!");
			
			sentenceSplitterModelIn = getClass().getResourceAsStream(Constants.EN_SENTENCE_DETECTOR);
		}

	}

	/**
	 * Returns the Sentence Splitter model as a InputStream object
	 * 
	 * @return the sentenceSplitterModelIn
	 */
	public InputStream getSentenceSplitterModelIn()
	{
		return sentenceSplitterModelIn;

	}
}
