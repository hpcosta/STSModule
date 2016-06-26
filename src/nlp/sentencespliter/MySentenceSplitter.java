/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.sentencespliter;

import java.util.ArrayList;
import java.util.List;

import nlp.sentencespliter.opennlp.OpenNLPSentenceSplitterManager;
import constantsfilesfolders.Constants;

/**
 * Sentence Splitter is used for detecting sentence boundaries.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.2/2014
 */
public class MySentenceSplitter
{
	private OpenNLPSentenceSplitterManager openNLPSentenceSplitterManager = null;
	private String sentenceSplitterName = null;


	public MySentenceSplitter()
	{
		this.sentenceSplitterName = Constants.SENTENCESPLITTER_OPENNLP;
		this.openNLPSentenceSplitterManager = new OpenNLPSentenceSplitterManager(Constants.EN);
	}

	public MySentenceSplitter(String language)
	{
		this.sentenceSplitterName = Constants.SENTENCESPLITTER_OPENNLP;
		this.openNLPSentenceSplitterManager = new OpenNLPSentenceSplitterManager(language);
		
	}
	/**
	 * Constructor.
	 * 
	 * @param tokenizerName
	 *                name of the tokenizer (e.g. TOKENIZER_OPENNLP), see Constants class for more information.
	 * 
	 * @param language
	 *                language
	 */
	public MySentenceSplitter(String sentenceSplitterName, String language)
	{
		this.sentenceSplitterName = sentenceSplitterName;
		this.openNLPSentenceSplitterManager = new OpenNLPSentenceSplitterManager(language);

	}

	/**
	 * Splits a document (raw text) into sentences
	 * 
	 * @param rawText
	 *                - raw text (document)
	 * @return a list a sentences
	 */
	public List<String> getSentenceSplitterList(String rawText)
	{
		if (sentenceSplitterName.equalsIgnoreCase(Constants.SENTENCESPLITTER_OPENNLP))
		{
			return openNLPSentenceSplitterManager.getSentencesList(rawText);
		} else
		{
			System.err.println("Warning in the MySentenceSplitter! Using OpenNLP Sentence Splitter by default. See Constants class to see available Sentence Splitters.");
			return openNLPSentenceSplitterManager.getSentencesList(rawText);
		}
		
	}

	

	
	/**
	 * for testing purposes
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		String document = "Background 		\n	Little is known about genetic factors affecting intraocular pressure (IOP) in mice and other mammals. Hi. How are you? This is Mike. Before starting the examples, you need to download the jar files required and add to your project build path. The jar files required are loaced at apache-opennlp-1.5.3-bin.zip which can be download here.";

		MySentenceSplitter sd = new MySentenceSplitter(Constants.EN);
		for (String s : (ArrayList<String>) sd.getSentenceSplitterList(document))
		{
			System.out.println(s);
			System.out.println("###");
		}
	}
}
