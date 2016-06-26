/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.tokenizer.opennlp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class OpenNLPTokenizerManager
{

	private OpenNLPTokenizerLoadLanguageResources llr = null;
	private Tokenizer tokenizer = null;
	private InputStream modelIn = null;

	public OpenNLPTokenizerManager(String language)
	{
		System.err.println("Tokeniser");
		// loads the model for the the language
		this.llr = new OpenNLPTokenizerLoadLanguageResources(language);
		// Loading token detection model
		modelIn = llr.getTokenDetectorModelIn();

		TokenizerModel tokanizerModel = null;
		try
		{
			tokanizerModel = new TokenizerModel(modelIn);
			tokenizer = new TokenizerME(tokanizerModel);

		} catch (final IOException ioe)
		{
			System.err.println("Error in the OpenNLPTokenizerManager, when loading the tokenizer model!");
			ioe.printStackTrace();
		}
	}

	public List<String> getTokenizedSentence(String sentence)
	{
		List<String> tokenList = new ArrayList<String>();
		String tokens[] = null;

		tokens = tokenizer.tokenize(sentence);
		for (String s : tokens)
			tokenList.add(s.toString());
		return tokenList;

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
