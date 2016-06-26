/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet;

import java.util.ArrayList;
import java.util.List;

import nlp.dictionaries.babelnet.auxobjects.Concept;

/**
 * Queries the BabelNet website and then parses the response and returns a list of {@link Concept}s. 
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class BabelNet
{
	private GetContent getContent = null;

	/**
	 * Default constructor.
	 * 
	 * @param language
	 *                language (English, Spanish and Portuguese)
	 */
	public BabelNet(String language)
	{
		getContent = new GetContent(language);
	}

	/**
	 * Queries the BabelNet website and then parses the response.
	 * 
	 * @param lemma
	 *                lemma to be queried in the BabelNet website
	 * @return a list of {@link Concept}s
	 */
	public List<Concept> getConcepts(String lemma)
	{
		// gets the HTML
		String pageContent = getContent.retrievingContent(lemma);
		// parses the HTML
		ParseBabelNetWebPage parseBabelNet = new ParseBabelNetWebPage();
		parseBabelNet.parseHTML(pageContent);
		// returns the retrieved list of {@link Concept}s
		return parseBabelNet.getConceptsList();
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{

		BabelNet babelNet = new BabelNet("en");

		List<Concept> concepts = null;

		concepts = new ArrayList<Concept>();
		concepts = babelNet.getConcepts("bath");
		for (Concept c : concepts)
			System.err.println(c.toString());

		System.err.println();
		concepts = new ArrayList<Concept>();
		concepts = babelNet.getConcepts("bird");
		for (Concept c : concepts)
			System.err.println(c.toString());
	}
}
