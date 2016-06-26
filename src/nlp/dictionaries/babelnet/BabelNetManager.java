/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nlp.NLPManager;
import nlp.dictionaries.babelnet.auxobjects.Concept;
import nlp.dictionaries.babelnet.auxobjects.ConceptualSentence;
import nlp.dictionaries.babelnet.auxobjects.Term;
import nlp.dictionaries.babelnet.auxobjects.TermConcepts;
import constantsfilesfolders.Constants;

/**
 * Used to measure the conceptual similarity between two raw sentences (in English, Spanish and Portuguese).
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class BabelNetManager
{
	private NLPManager nlpManager = null;
	private String language = "";
	private BabelNet babelNet = null;
	private List<String> posText1 = null;
	private List<String> posText2 = null;
	private List<String> lemmaText1 = null;
	private List<String> lemmaText2 = null;
	private ConceptualSimilarity conceptualSimilarity = null;

	/**
	 * Default constructor
	 * 
	 * @param language
	 *                language (English, Spanish and Portuguese)
	 */
	public BabelNetManager(String language)
	{
		if (language.equalsIgnoreCase(Constants.EN) || language.equalsIgnoreCase(Constants.ES) || language.equalsIgnoreCase(Constants.PT))
		{
			this.language = language;
			nlpManager = new NLPManager(language);
			babelNet = new BabelNet(language);
			conceptualSimilarity = new ConceptualSimilarity(language);
		} else
		{
			System.err.println("Error BabelNet only works for English, Spanish and Portuguese");
			return;
		}
	}

	/**
	 * Receives two raw sentences and returns an hashMap with all the conceptual similarity scores (MeasureName - Score).
	 * 
	 * @param rawText1
	 *                raw text1
	 * @param rawText2
	 *                raw text1
	 * @return an HashMap with all the conceptual similarity scores (MeasureName - Score)
	 */
	public HashMap<String, Double> getConceptualSimilarity(String rawText1, String rawText2)
	{
		// 1) preprocessing the text
		preProcessingText(rawText1, rawText2);

		// System.err.println(lemmaText1.size() + ":" + posText1.size());
		// for (int i = 0; i < lemmaText1.size(); i++)
		// {
		// System.err.print(lemmaText1.get(i) + " " + posText1.get(i) + " | ");
		// }
		// System.err.println();
		// System.err.println(lemmaText2.size() + ":" + posText2.size());
		// for (int i = 0; i < lemmaText2.size(); i++)
		// {
		// System.err.print(lemmaText2.get(i) + " " + posText2.get(i) + " | ");
		// }

		// 2) extracting relevant terms in the texts/sentences
		List<Term> _termsText1 = extractRelevantWords(lemmaText1, posText1);
		List<Term> _termsText2 = extractRelevantWords(lemmaText2, posText2);

		// System.err.println();
		// System.err.println("\nRelevant terms in Sentence1");
		// for (Term t : _termsText1)
		// {
		// System.err.println(t.toString());
		// }
		// System.err.println("\nRelevant terms in Sentence2");
		// for (Term t : _termsText2)
		// {
		// System.err.println(t.toString());
		// }

		// 3) retrieves a list of concepts for each {@link Term} using BabelNet.
		ConceptualSentence conceptualSentence1 = retrievingConcepts(_termsText1);
		ConceptualSentence conceptualSentence2 = retrievingConcepts(_termsText2);

		// System.err.println("\n\n---------------------");
		// System.err.println("ConceptualSentence1:");
		// System.err.println("---------------------");
		// System.err.println(conceptualSentence1.toString());
		// System.err.println("\n\n---------------------");
		// System.err.println("ConceptualSentence2:");
		// System.err.println("---------------------");
		// System.err.println(conceptualSentence2.toString());

		return conceptualSimilarity.getConceptualSimilarityScores(conceptualSentence1, conceptualSentence2).getMeasures();
	}

	/**
	 * Pre-processes the texts/sentences, i.e. taggs and lemmatises both texts/sentences.
	 * 
	 * @param rawText1
	 *                raw text1
	 * @param rawText2
	 *                raw text2
	 */
	private void preProcessingText(String rawText1, String rawText2)
	{
		posText1 = nlpManager.getTaggedSentenceList(rawText1);
		lemmaText1 = nlpManager.getLemmatizedSentenceList(rawText1);

		posText2 = nlpManager.getTaggedSentenceList(rawText2);
		lemmaText2 = nlpManager.getLemmatizedSentenceList(rawText2);
	}

	/**
	 * Extracts the lemmas that are Nouns, Verbs, Adverbs and Adjectives from the input text.
	 * 
	 * @param lemmatisedText
	 *                lemmatised text
	 * @param postaggedText
	 *                postagged text
	 * @return list of {@link Term}s
	 */
	private List<Term> extractRelevantWords(List<String> lemmatisedText, List<String> postaggedText)
	{
		List<Term> _termsList = new ArrayList<Term>();
		String pos = "";

		for (int i = 0; i < lemmatisedText.size(); i++)
		{
			pos = postaggedText.get(i);
			pos = pos.toLowerCase();
			// System.err.print(pos + " ");
			if (pos.startsWith("n") || pos.startsWith("v") || pos.startsWith("ad"))
				if(lemmatisedText.get(i).equalsIgnoreCase("world") || 
						lemmatisedText.get(i).equalsIgnoreCase("%") 
						)
					continue;
				else
					_termsList.add(new Term(lemmatisedText.get(i), postaggedText.get(i)));
		}
		return _termsList;
	}

	/**
	 * Retrieves a list of concepts for each {@link Term} using BabelNet and returns a {@link ConceptualSentence}
	 * 
	 * @param terms
	 *                list of {@link Term}s
	 * @return {@link ConceptualSentence}
	 */
	private ConceptualSentence retrievingConcepts(List<Term> terms)
	{
		ConceptualSentence conceptualSentence = new ConceptualSentence();
		List<Concept> _conceptsList = null;
		for (Term t : terms)
		{
			// retrieving a list of concepts using the BabelNet website
			_conceptsList = babelNet.getConcepts(t.getTerm());
			conceptualSentence.addTermConcept(new TermConcepts(t, _conceptsList));
		}

		return conceptualSentence;
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 */
	public void demo()
	{
		// >0
		// String rawTextEN1 = "John went horse back riding at dawn with a whole group of friends.";
		// String rawTextEN2 = "Sunrise at dawn is a magnificent view to take in if you wake up early enough for it.";
		// String rawTextES1 = "Al amanecer, Juan se fue a montar a caballo con un grupo de amigos.";
		// String rawTextES2 =
		// "La salida del sol al amanecer es una magnífica vista que puede presenciar si usted se despierta lo suficientemente temprano para verla.";

		// >1
		// String rawTextEN1 = "The woman is playing the violin.";
		// String rawTextEN2 = "The young lady enjoys listening to the guitar.";
		// String rawTextES1 = "La mujer está tocando el violín.";
		// String rawTextES2 = "La joven disfruta escuchar la guitarra.";

		// >2
		// String rawTextEN1 = "They flew out of the nest in groups.";
		// String rawTextEN2 = "They flew into the nest together.";
		// String rawTextES1 = "Ellos volaron del nido en grupos.";
		// String rawTextES2 = "Volaron hacia el nido juntos.";

		// >3
		String rawTextEN1 = "John said he is considered a witness but not a suspect.";
		String rawTextEN2 = "\"He is not a suspect anymore.\" John said.";
		String rawTextES1 = "John dijo que él es considerado como testigo, y no como sospechoso.";
		String rawTextES2 = "\"Él ya no es un sospechoso,\" John dijo.";

		// >4
		// String rawTextES1 = "El pájaro se esta bañando en el lavabo.";
		// String rawTextES2 = "El pájaro se está lavando en el aguamanil.";
		// String rawTextEN1 = "The bird is bathing in the sink.";
		// String rawTextEN2 = "Birdie is washing itself in the water basin.";

		String rawText1 = "";
		String rawText2 = "";
		if (language.equalsIgnoreCase(Constants.EN))
		{
			rawText1 = rawTextEN1;
			rawText2 = rawTextEN2;
		} else if (language.equalsIgnoreCase(Constants.ES))
		{
			rawText1 = rawTextES1;
			rawText2 = rawTextES2;
		} else
		{
			System.out.println("Error! This demo only has examples for English and Spanish");
			return;
		}

		HashMap<String, Double> measures = getConceptualSimilarity(rawText1, rawText2);
		for (String measure : measures.keySet())
			System.out.println("CONCEPTUAL " + measure + ": " + measures.get(measure));

	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		BabelNetManager babelNetManager = new BabelNetManager(Constants.ES);
		babelNetManager.demo();
	}

}
