/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet.auxobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Conceptual Sentence that collects a list of {@link TermConcepts}.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class ConceptualSentence
{

	private List<TermConcepts> termConceptsList = null;

	/**
	 * Default constructor
	 */
	public ConceptualSentence()
	{
		termConceptsList = new ArrayList<TermConcepts>();
	}

	public void addTermConcept(TermConcepts termConcept)
	{
		termConceptsList.add(termConcept);
	}

	/**
	 * @return the termConceptsList
	 */
	public List<TermConcepts> getTermConceptsList()
	{
		return termConceptsList;
	}

	/**
	 * 
	 * @return a list with all the terms
	 */
	public List<String> getOnlyTerms()
	{
		List<String> _terms = new ArrayList<String>();
		for (TermConcepts tc : termConceptsList)
			_terms.add(tc.getTermObject().getTerm());
		return _terms;
	}

	/**
	 * 
	 * @return a list with all the concepts
	 */
	public List<String> getOnlyConcepts()
	{
		List<String> _concepts = new ArrayList<String>();
		for (TermConcepts tc : termConceptsList)
			for (Concept c : tc.getConcepts())
				_concepts.add(c.getConcept());

		return _concepts;
	}

	/**
	 * 
	 * @return list with all the definitons
	 */
	public List<String> getOnlyDefinitions()
	{
		List<String> _definitions = new ArrayList<String>();
		for (TermConcepts tc : termConceptsList)
			for (Concept c : tc.getConcepts())
				_definitions.add(c.getDefinition());

		return _definitions;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		for (TermConcepts tc : termConceptsList)
		{
			buffer.append(tc.toString());
		}
		buffer.append("\n");
		return buffer.toString();
	}

}
