/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.postagger.treetagger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import constantsfilesfolders.Constants;

/**
 * This class loads the language resources for Spanish, Portuguese, English, etc. such as the POS Tagger, Token Detector and Sentence
 * Splitter model
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.2/2014
 */

public class TreeTaggerLoadLanguageResources
{
	/**
	 * TreeTagger directory: take a look to the Constants class
	 */

	private String language = "";
	private String posTaggerModelIn = null;

	/**
	 * Loads the language resources, such as the POS Tagger, Token Detector and Sentence Splitter model
	 * 
	 * @param language
	 */
	public TreeTaggerLoadLanguageResources(String language)
	{
		super();
		this.language = language;
		treeTaggerLoader();
	}

	private void treeTaggerLoader()
	{
		/**
		 * Point TT4J to the TreeTagger installation directory. The executable is expected in the "bin" subdirectory - in this example at
		 * "/opt/treetagger/bin/tree-tagger"
		 * 
		 * If using linux, you need to install the TreeTagger using the command line as explained in the website and set the PATH, Then the
		 * change the Constants.TREETAGGER_PATH to the path in our computer
		 */
		loadingTreetaggerPropertiesFromFile();
		System.setProperty("treetagger.home", Constants.TREETAGGER_PATH);
	}

	/**
	 * Loading TreeTagger Path and Models from file.
	 */
	private void loadingTreetaggerPropertiesFromFile()
	{
		String line = "";
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.TREETAGGER_PROPERTIES), Constants.ENCODING_UTF8));

			while ((line = reader.readLine()) != null)
			{
				if (line.startsWith("treetaggerpath"))
				{
					System.err.println("\tTreeTagger path loaded from:\t" + line.split("=")[1].trim());
					Constants.TREETAGGER_PATH = line.split("=")[1].trim();

				} else if (line.startsWith("treetaggerESmodel"))
				{
					//System.err.println("TreeTagger ES model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_ES_POSTAGGERMODEL = line.split("=")[1].trim();

				} else if (line.startsWith("treetaggerENmodel"))
				{
					//System.err.println("TreeTagger EN model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_EN_POSTAGGERMODEL = line.split("=")[1].trim();

				} else if (line.startsWith("treetaggerPTmodel"))
				{
					//System.err.println("TreeTagger PT model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_PT_POSTAGGERMODEL = line.split("=")[1].trim();

				} else if (line.startsWith("treetaggerITmodel"))
				{
					//System.err.println("TreeTagger IT model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_IT_POSTAGGERMODEL = line.split("=")[1].trim();

				} else if (line.startsWith("treetaggerRUmodel"))
				{
					//System.err.println("TreeTagger RU model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_RU_POSTAGGERMODEL = line.split("=")[1].trim();

				} else if (line.startsWith("treetaggerFRmodel"))
				{
					//System.err.println("TreeTagger FR model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_FR_POSTAGGERMODEL = line.split("=")[1].trim();
				}
				else if (line.startsWith("treetaggerDEmodel"))
				{
					//System.err.println("TreeTagger DE model loaded from: " + line.split("=")[1].trim());
					Constants.TREETAGGER_DE_POSTAGGERMODEL = line.split("=")[1].trim();
				}

			}

		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			System.err.println("Error loading treetagger.properties file!!!");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the TreeTagger POS Tagger Model directory as a String object
	 * 
	 * @return the posTaggerModelIn
	 */
	public String getPosTaggerModelIn()
	{
		if (language.equalsIgnoreCase(Constants.EN))
		{
			//System.err.println(">Loading english PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_EN_POSTAGGERMODEL;

		} else if (language.equalsIgnoreCase(Constants.ES) == true)
		{
			//System.err.println(">Loading spanish PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_ES_POSTAGGERMODEL;

		} else if (language.equalsIgnoreCase(Constants.PT) == true)
		{
			//System.err.println(">Loading portuguese PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_PT_POSTAGGERMODEL;
		} else if (language.equalsIgnoreCase(Constants.FR) == true)
		{
			//System.err.println(">Loading french PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_FR_POSTAGGERMODEL;
		} else if (language.equalsIgnoreCase(Constants.DE) == true)
		{
			//System.err.println(">Loading german  PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_DE_POSTAGGERMODEL;
		} else if (language.equalsIgnoreCase(Constants.IT) == true)
		{
			//System.err.println(">Loading italian  PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_IT_POSTAGGERMODEL;
		} else if (language.equalsIgnoreCase(Constants.RU) == true)
		{
			//System.err.println(">Loading russian PosTagger model!");
			posTaggerModelIn = Constants.TREETAGGER_RU_POSTAGGERMODEL;
		} else
		{
			System.err
					.println("Error in the TreeTaggerLoadLanguageResources, the required language ("+ language+") is not available!\nLoading english model by default!");
			posTaggerModelIn = Constants.TREETAGGER_EN_POSTAGGERMODEL;
		}
		// System.out.println(posTaggerModelIn);
		return posTaggerModelIn;
	}

	/**
	 * Returns the token detector model as a InputStream object
	 * 
	 * @return the tokenDetectorModelIn
	 */
	public InputStream getTokenDetectorModelIn()
	{
		InputStream tokenDetectorModelIn = null;
		tokenDetectorModelIn = getClass().getResourceAsStream(Constants.EN_TOKENIZER);
		return tokenDetectorModelIn;
	}

	/**
	 * Returns the Sentence Splitter model as a InputStream object
	 * 
	 * @return the sentenceSplitterModelIn
	 */
	public InputStream getSentenceSplitterModelIn()
	{
		InputStream sentenceSplitterModelIn = null;
		sentenceSplitterModelIn = getClass().getResourceAsStream(Constants.EN_SENTENCE_DETECTOR);
		return sentenceSplitterModelIn;

	}
}
