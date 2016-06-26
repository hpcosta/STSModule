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
public class POStag
{
	private String posTag = "";
	private String universalPosTag = "";
	private double prob = 0.0;

	/**
	 * 
	 */
	public POStag()
	{
		super();
	}

	/**
	 * @param posTag
	 */
	public POStag(String posTag)
	{
		super();
		this.posTag = posTag.trim();
	}

	/**
	 * @return the posTag
	 */
	public String getPosTag()
	{
		return posTag;
	}

	/**
	 * @param posTag
	 *                the posTag to set
	 */
	public void setPosTag(String posTag)
	{
		this.posTag = posTag.trim();
	}

	/**
	 * @return the universalPosTag
	 */
	public String getUniversalPosTag()
	{
		return universalPosTag;
	}

	/**
	 * @param universalPosTag
	 *                the universalPosTag to set
	 */
	public void setUniversalPosTag(String universalPosTag)
	{
		this.universalPosTag = universalPosTag.trim();
	}

	/**
	 * @return the prob
	 */
	public double getProb()
	{
		return prob;
	}

	/**
	 * @param prob
	 *                the prob to set
	 */
	public void setProb(double prob)
	{
		this.prob = prob;
	}

	/**
	 * @return the posTag
	 */
	public String toString()
	{
		return posTag;
	}
}
