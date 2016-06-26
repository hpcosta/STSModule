/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel;

/**
 * Class used to define the type of corpora (comparable or parallel). In the future can be used to specify different types of comparable
 * corpora.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class CorporaType
{
	String typeOfCorpora = "";

	/**
	 * @param typeOfCorpora
	 */
	public CorporaType(String typeOfCorpora)
	{
		this.typeOfCorpora = typeOfCorpora;
	}

	/**
	 * @return the type of corpora
	 */
	public String getTypeOfCorpora()
	{
		return typeOfCorpora;
	}

}
