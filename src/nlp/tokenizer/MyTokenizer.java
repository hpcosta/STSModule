/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.tokenizer;

import java.util.List;

import nlp.tokenizer.opennlp.OpenNLPTokenizerManager;
import constantsfilesfolders.Constants;

/**
 * Tokenizer is used detect words which are separated by space.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class MyTokenizer
{

	private OpenNLPTokenizerManager openNLPTokenizer = null;
	private String tokenizerName = null;

	/**
	 * Default Tokenizer is the OpenNLP Tokenizer and the default language is EN.
	 *
	 */
	public MyTokenizer()
	{
		this.tokenizerName = Constants.TOKENIZER_OPENNLP;
		this.openNLPTokenizer = new OpenNLPTokenizerManager(Constants.EN);
	}

	/**
	 * Default Tokenizer is the OpenNLP Tokenizer.
	 *
	 * @param language
	 *                language
	 */
	public MyTokenizer(String language)
	{
		

		this.openNLPTokenizer = new OpenNLPTokenizerManager(language);
		this.tokenizerName = Constants.TOKENIZER_OPENNLP;
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
	public MyTokenizer(String tokenizerName, String language)
	{
		this.tokenizerName = tokenizerName;
		this.openNLPTokenizer = new OpenNLPTokenizerManager(language);

	}

	/**
	 * Returns a tokenized sentence.
	 * 
	 * @param sentence
	 *                sentence to be tokenizer by default by the OpenNLP Tokenizer
	 * @return the token list
	 */
	public List<String> getTokenisedSentenceList(String sentence)
	{
		if (tokenizerName.equalsIgnoreCase(Constants.TOKENIZER_OPENNLP))
		{
			return openNLPTokenizer.getTokenizedSentence(sentence);
		} else
		{
			System.err.println("Warning in the MyTokenizer class! Using OpenNLP Tokenizer by default. See Constants class to see available Tokenizers.");
			return openNLPTokenizer.getTokenizedSentence(sentence);
		}
	}

	public void closeModelIN()
	{
		openNLPTokenizer.closeModelIN();
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		String sentence = "Hi. How are you? This is Mike. Before starting the examples, you need to download the jar files required and add to your project build path. The jar files required are loaced at apache-opennlp-1.5.3-bin.zip which can be download here.";

		MyTokenizer tk = new MyTokenizer(Constants.TOKENIZER_OPENNLP, Constants.EN);
		for (String s : tk.getTokenisedSentenceList(sentence))
		{
			System.out.println(s);
		}
	}
}
