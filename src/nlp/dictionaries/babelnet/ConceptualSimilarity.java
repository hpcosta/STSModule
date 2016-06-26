/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet;

import java.util.List;

import measures.measureswrapper.MeasuresWrapper;
import nlp.dictionaries.babelnet.auxobjects.ConceptualSentence;

/**
 * Calculates the conceptual similarity measures ("cMeasure, cJaccard, cLin, cPmi").
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class ConceptualSimilarity
{
	private String cMeasure = "cMeasure";
	private String cJaccard = "cJaccard";
	private String cLin = "cLin";
	private String cPmi = "cPmi";

	// private NLPManager nlpManager = null;
	// private MyStopwordChecker myStopwordChecker = null;

	/**
	 * 
	 */
	public ConceptualSimilarity(String language)
	{
		// nlpManager = new NLPManager(language);
		// myStopwordChecker = new MyStopwordChecker(language);
	}

	/**
	 * Calculates the conceptual similarity between two {@link ConceptualSentence}s
	 * 
	 * @param conceptualSentence1
	 *                {@link ConceptualSentence}
	 * @param conceptualSentence2
	 *                {@link ConceptualSentence}
	 * @return {@link MeasuresWrapper}
	 */
	public MeasuresWrapper getConceptualSimilarityScores(ConceptualSentence conceptualSentence1, ConceptualSentence conceptualSentence2)
	{
		MeasuresWrapper measuresWrapper = new MeasuresWrapper();
		/**
		 * Matches: Terms sentence1 in Concepts sentence2
		 */
		int termsS1ConceptsS2 = matchesConcepts(conceptualSentence1.getOnlyTerms(), conceptualSentence2.getOnlyConcepts());

		/**
		 * Matches: Terms sentence2 in Concepts sentence1
		 */
		int termsS2ConceptsS1 = matchesConcepts(conceptualSentence2.getOnlyTerms(), conceptualSentence1.getOnlyConcepts());

		// total matches
		int totalMaches = termsS1ConceptsS2 + termsS2ConceptsS1;

		int nConceptsText1 = 0;
		int nConceptsText2 = 0;
		for (String concept : conceptualSentence1.getOnlyConcepts())
			nConceptsText1 = nConceptsText1 + concept.split(", ").length;

		for (String concept : conceptualSentence2.getOnlyConcepts())
			nConceptsText2 = nConceptsText2 + concept.split(", ").length;

		// creating conceptual measures
		measuresWrapper.addMeasure(cMeasure, calculateMyConceptualSimilarityMeasure(totalMaches));
		measuresWrapper.addMeasure(cJaccard, calculateConceptualJaccard(totalMaches, nConceptsText1, nConceptsText2));
		measuresWrapper.addMeasure(cLin, calculateConceptualLin(totalMaches, nConceptsText1, nConceptsText2));
		measuresWrapper.addMeasure(cPmi, calculateConceptualPMI(totalMaches, nConceptsText1, nConceptsText2));

		// takes a lot of time lemmatising and searching for more than 5 definitions.
		// int matches3 = 0;
		// int matches4 = 0;
		// if (conceptualSentence2.getOnlyDefinitions().size() < 5 || conceptualSentence2.getOnlyDefinitions().size() < 5)
		// {
		// matches3 = matchesInDefinitions(conceptualSentence1.getOnlyTerms(), conceptualSentence2.getOnlyDefinitions());
		// total += matches3;
		// matches4 = matchesInDefinitions(conceptualSentence2.getOnlyTerms(), conceptualSentence1.getOnlyDefinitions());
		// total += matches4;
		// }

		return measuresWrapper;
	}

	/**
	 * Calculates the conceptual PMI
	 * 
	 * @param totalMatches
	 *                interception
	 * @param nConceptsText1
	 *                e1
	 * @param nConceptsText2
	 *                e2
	 * @return PMI score
	 */
	private double calculateConceptualPMI(int totalMatches, int nConceptsText1, int nConceptsText2)
	{
		if (totalMatches == 0)
			return 0.0;
		else
			return Math.log10(((double) totalMatches / (double) (nConceptsText1 * nConceptsText2)) * (double) 10000);
	}

	/**
	 * Calculates the conceptual Lin
	 * 
	 * @param totalMatches
	 *                interception
	 * @param nConceptsText1
	 *                e1
	 * @param nConceptsText2
	 *                e2
	 * @return Lin score
	 */
	private double calculateConceptualLin(int totalMatches, int nConceptsText1, int nConceptsText2)
	{

		if (totalMatches == 0)
		{
			return 0.0;
		} else
		{
			double demoninator = 2 * Math.log10(totalMatches);
			double numerator = Math.log10(nConceptsText1) + Math.log10(nConceptsText2);
			return (double) demoninator / numerator;
		}
	}

	/**
	 * Calculates the my conceptual measure
	 * 
	 * @param totalMatches
	 *                interception
	 * @param nConceptsText1
	 *                e1
	 * @param nConceptsText2
	 *                e2
	 * @return score
	 */
	private double calculateMyConceptualSimilarityMeasure(int totalMatches)
	{
		if (totalMatches == 1 || totalMatches == 0)
		{
			return 1.0;
		} else
			return ((double) 1 / (double) totalMatches * 3);

	}

	/**
	 * Calculates the conceptual Jaccard
	 * 
	 * @param totalMatches
	 *                interception
	 * @param nConceptsText1
	 *                e1
	 * @param nConceptsText2
	 *                e2
	 * @return Jaccard score
	 */
	private double calculateConceptualJaccard(int totalMatches, int nConceptsText1, int nConceptsText2)
	{
		if (totalMatches == 0)
			return 0.0;
		else
			return (totalMatches / ((double) nConceptsText1 + (double) nConceptsText2));
	}

	/**
	 * Counts the number of times the terms in the sentence X appear in the concepts in the sentence Y.
	 * 
	 * @param terms
	 *                tems in the sentence X
	 * @param concepts
	 *                concepts in sentence Y
	 * @return number of times the terms in the sentence X appear in the concepts in the sentence Y
	 */
	private int matchesConcepts(List<String> terms, List<String> concepts)
	{
		int matches = 0;
		String[] _concepts = null;
		for (String term : terms)
		{
			term = term.toLowerCase().trim();
			for (String concept : concepts)
			{
				_concepts = concept.split(", ");
				for (String c : _concepts)
				{
					c = c.toLowerCase().trim();
					if (term.equalsIgnoreCase(c))
					{
						// System.err.println("> " + term + " in [" + concept + "]");
						matches += 1;
					}
				}
			}
		}

		return matches;
	}

	// private int matchesInDefinitions(List<String> terms, List<String> definitions)
	// {
	// List<String> lemmas = null;
	// int matches = 0;
	// String[] _definitions = null;
	// for (String term : terms)
	// {
	// term = term.toLowerCase().trim();
	// if (myStopwordChecker.isStopWord(term))
	// {
	// System.err.println("\n######### >SW: " + term);
	// continue;
	// }
	// for (String definition : definitions)
	// {
	// // lemmatising the definition...
	// System.err.print(".");
	// nlpManager.getTaggedSentenceList(definition);
	// lemmas = nlpManager.getLemmatizedSentenceList(definition);
	// for (String l : lemmas)
	// {
	// l = l.toLowerCase().trim();
	// if (myStopwordChecker.isStopWord(l))
	// continue;
	// else if (term.equalsIgnoreCase(l))
	// {
	// System.err.println("\n>> " + term + " in [" + definition + "]");
	// matches += 1;
	// }
	// }
	// }
	// }
	//
	// return matches;
	// }

}
