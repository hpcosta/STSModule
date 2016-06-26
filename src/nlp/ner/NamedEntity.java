/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.ner;

/**
 * This class represents a Named Entity
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class NamedEntity
{
	private String namedEntity = "";
	private int frequency = 1;
	private String category = "";

	/**
	 * 
	 */
	public NamedEntity(String namedEntity, String category)
	{
		this.namedEntity = namedEntity;
		this.category = category;
	}

	public void incrementFrequency()
	{
		frequency += frequency;
	}

	/**
	 * @return the namedEntity
	 */
	public String getNamedEntity()
	{
		return namedEntity;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency()
	{
		return frequency;
	}

	/**
	 * @return the category
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @param frequency
	 *                the frequency to set
	 */
	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}

}
