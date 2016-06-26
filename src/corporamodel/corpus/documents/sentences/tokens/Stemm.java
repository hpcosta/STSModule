/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel.corpus.documents.sentences.tokens;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2013
 */
public class Stemm
{
	private String stemm = "";

	/**
	 * @param stemm
	 */
	public Stemm(String stemm)
	{
		super();
		this.stemm = stemm.trim();
	}

	/**
	 * @return the stemm
	 */
	public String getStemm()
	{
		return stemm;
	}

	/**
	 * @param stemm
	 *                the stemm to set
	 */
	public void setStemm(String stemm)
	{
		this.stemm = stemm.trim();
	}

	/**
	 * @return the stemm
	 */
	public String toString()
	{
		return stemm;
	}
}
