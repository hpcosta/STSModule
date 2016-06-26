/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet.auxobjects;

/**
 * Represents a term in the sentence.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class Term
{
	private String term = "";
	private String postagg = "";

	/**
	 * 
	 */
	public Term(String term, String postagg)
	{
		this.term = term;
		this.postagg = postagg;
	}

	/**
	 * @return the term
	 */
	public String getTerm()
	{
		return term;
	}

	/**
	 * @return the postagg
	 */
	public String getPostagg()
	{
		return postagg;
	}

	public String toString()
	{
		return term + " | " + postagg;
	}
}
