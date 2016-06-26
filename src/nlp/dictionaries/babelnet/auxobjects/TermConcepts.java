/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet.auxobjects;

import java.util.List;

/**
 * Relates the {@link Term} with the {@link Concept}s.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class TermConcepts
{
	private Term term = null;
	private List<Concept> concepts = null;

	/**
	 * Default constructor.
	 * 
	 * @param term
	 *                {@link Term}
	 * @param concepts
	 *                {@link Concept}
	 */
	public TermConcepts(Term term, List<Concept> concepts)
	{
		this.term = term;
		this.concepts = concepts;
	}

	/**
	 * @return the term
	 */
	public Term getTermObject()
	{
		return term;
	}

	/**
	 * @return the concept
	 */
	public List<Concept> getConcepts()
	{
		return concepts;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(term.toString() + "\n");
		for (Concept c : concepts)
			buffer.append("\t" + c.toString());

		buffer.append("\n");
		return buffer.toString();
	}
}
