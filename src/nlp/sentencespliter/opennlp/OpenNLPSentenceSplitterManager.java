/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.sentencespliter.opennlp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class OpenNLPSentenceSplitterManager
{
	private OpenNLPSentenceSplitterLoadLanguageResources llr = null;
	private InputStream modelIn = null;
	private SentenceDetectorME _sentenceDetector = null;
	private List<String> sentencesList = null;

	/**
	 * At this point the language is not relevant... I only have one splitter model (EN)
	 * 
	 * @param rawText
	 *                - document
	 */
	public OpenNLPSentenceSplitterManager(String language)
	{
		System.err.println("Sentence Splitter");
		this.llr = new OpenNLPSentenceSplitterLoadLanguageResources(language);

		modelIn = llr.getSentenceSplitterModelIn();
		SentenceModel sentenceModel = null;
		try
		{
			sentenceModel = new SentenceModel(modelIn);
			_sentenceDetector = new SentenceDetectorME(sentenceModel);
		} catch (IOException e)
		{
			System.err.println("Error in the OpenNLPSentenceSplitterManager, when loading the sentence splitter model!");
			e.printStackTrace();
		}

	}

	/**
	 * Splits a document (raw text) into sentences
	 * 
	 * @param rawText
	 *                - raw text (document)
	 * @return the sentences
	 */
	public List<String> getSentencesList(String rawText)
	{
		sentencesList = new ArrayList<String>();
		String[] sentencesArray = _sentenceDetector.sentDetect(rawText);
		Collections.addAll(sentencesList, sentencesArray);
		return sentencesList;
	}

	public void closeModelIN()
	{

		if (modelIn != null)
		{
			try
			{
				modelIn.close();
			} catch (final IOException e)
			{
			} // oh well!
		}

	}
}
