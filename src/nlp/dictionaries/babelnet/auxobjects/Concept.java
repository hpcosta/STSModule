/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet.auxobjects;

/**
 * Used to represent a concept and its definition.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class Concept
{

	private String concept = "";
	private String definition = "";
	private String posTagg = "";
	private String type = "";

	/**
	 * Default constructor.
	 * 
	 * @param concept
	 * @param definition
	 * @param posTagg
	 * @param type
	 */
	public Concept(String concept, String definition, String posTagg, String type)
	{
		this.concept = concept.trim();
		this.definition = definition.trim();
		this.posTagg = posTagg.trim();
		this.type = type.trim();
	}

	/**
	 * Default constructor.
	 * 
	 * @param concept
	 * @param definition
	 */
	public Concept(String concept, String definition)
	{
		this.concept = concept.trim();
		this.definition = definition.trim();

	}

	/**
	 * @return the concept
	 */
	public String getConcept()
	{
		return concept;
	}

	/**
	 * @return the definition
	 */
	public String getDefinition()
	{
		return definition;
	}

	/**
	 * @return the posTagg
	 */
	public String getPosTagg()
	{
		return posTagg;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Concept to String
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(concept + " \n\t\t" + definition + "\n");
		if (posTagg != "" && type != "")
			buffer.append("\t\t" + posTagg + " | " + type + "\n\n");
		return buffer.toString();
	}
}
