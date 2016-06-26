/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.ner;

/**
 * This class extracts Named Entities from Spanish sentences.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nlp.tokenizer.MyTokenizer;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import constantsfilesfolders.Constants;
import constantsfilesfolders.PublicObjects;

public class NEREsFinder
{

	// Named entities HashMap template for Spanish
	private PublicObjects publicObjects = null;

	private NameFinderME nameFinder = null;

	// Named Entity Recogniser Models for Spanish
	private NEREsModelsLoader nerEsModelsLoader = null;
	private List<TokenNameFinderModel> esModelsList = null;

	// Spans
	private Span nameSpansSentence1[] = null;
	private Span nameSpansSentence2[] = null;

	// Named Entities Objects
	NamedEntities neListSentence1 = null;
	NamedEntities neListSentence2 = null;

	/**
	 * Default constructor. Loads the Spanish NER Models
	 */
	public NEREsFinder()
	{
		// allows to load the default Spanish named entity HashMap
		publicObjects = new PublicObjects();

		// loading Spanish models
		nerEsModelsLoader = new NEREsModelsLoader();
		esModelsList = nerEsModelsLoader.getEsModelsList();

	}

	/**
	 * This method extracts Named Entities from a spanish sentence.
	 * 
	 * @param tokenizedSentence
	 *           - tokenised sentence in spanish
	 * @return Named entities
	 */
	public NamedEntities getNamedEntities(List<String> tokenizedSentence)
	{
		String[] tokenized_sentence = tokenizedSentence.toArray(new String[tokenizedSentence.size()]);
		NamedEntities neListSentence = new NamedEntities();
		Span nameSpansSentence[] = null;
		NamedEntity ne = null;
		String entity = "";

		for (TokenNameFinderModel model : esModelsList)
		{
			nameFinder = new NameFinderME(model);

			/** Sentence 1 */
			nameSpansSentence = nameFinder.find(tokenized_sentence);
			for (Span s : nameSpansSentence)
			{
				entity = "";

				/** when dealing with an entity that has only one word */
				if (s.getStart() == (s.getEnd() - 1))
				{
					entity = tokenized_sentence[s.getStart()];
					ne = new NamedEntity(entity, s.getType());
					neListSentence.addNamedEntity(ne);

				}
				/** when dealing with multi-word entities */
				else
				{
					for (int i = s.getStart(); i < s.getEnd(); i++)
						entity += tokenized_sentence[i] + " ";

					ne = new NamedEntity(entity, s.getType());
					neListSentence.addNamedEntity(ne);
				}
			}
		}

		return neListSentence;
	}

	/**
	 * This method extracts Named Entities within two sentences at time.
	 * 
	 * @param tokenizedSentence1
	 *           - a tokenised sentence in spanish
	 * @param tokenizedSentence2
	 *           - a tokenised sentence in spanish
	 */
	private void findNamedEntities(List<String> tokenizedSentence1, List<String> tokenizedSentence2)
	{

		String[] tokenized_sentence1 = tokenizedSentence1.toArray(new String[tokenizedSentence1.size()]);
		String[] tokenized_sentence2 = tokenizedSentence2.toArray(new String[tokenizedSentence2.size()]);

		neListSentence1 = new NamedEntities();
		neListSentence2 = new NamedEntities();

		NamedEntity ne = null;
		String entity = "";

		for (TokenNameFinderModel model : esModelsList)
		{
			nameFinder = new NameFinderME(model);

			/** Sentence 1 */
			nameSpansSentence1 = nameFinder.find(tokenized_sentence1);
			for (Span s : nameSpansSentence1)
			{
				entity = "";

				/** when dealing with an entity that has only one word */
				if (s.getStart() == (s.getEnd() - 1))
				{
					entity = tokenized_sentence1[s.getStart()];
					// System.out.print(" 1TOKEN> " + s.getStart() + " " + tokenized_sentence1[s.getStart()] +" > ");
					ne = new NamedEntity(entity, s.getType());
					neListSentence1.addNamedEntity(ne);

				}
				/** when dealing with multi-word entities */
				else
				{
					for (int i = s.getStart(); i < s.getEnd(); i++)
						entity += tokenized_sentence1[i] + " ";

					// System.out.println(" >2TOKENS> "+tempStr);
					ne = new NamedEntity(entity, s.getType());
					neListSentence1.addNamedEntity(ne);
				}
			}

			/** Sentence 2 */
			nameSpansSentence2 = nameFinder.find(tokenized_sentence2);
			for (Span s : nameSpansSentence2)
			{
				entity = "";

				/** when dealing with an entity that has only one word */
				if (s.getStart() == (s.getEnd() - 1))
				{
					entity = tokenized_sentence2[s.getStart()];
					// System.out.print(" 1TOKEN> " + s.getStart() + " " + tokanizedSentence2[s.getStart()] +" > ");
					ne = new NamedEntity(entity, s.getType());
					neListSentence2.addNamedEntity(ne);

				}

				/** when dealing with multi-word entities */
				else
				{
					for (int i = s.getStart(); i < s.getEnd(); i++)
						entity += tokenized_sentence2[i] + " ";

					// System.out.println(" >2TOKENS> "+tempStr);
					ne = new NamedEntity(entity, s.getType());
					neListSentence2.addNamedEntity(ne);
				}
			}
		}

		// System.out.println("\nNamed Entities extracted from: ");
		//
		// System.out.println(">Sentence1");
		// System.out.println(neListSentence1.toString());
		//
		// System.out.println(">Sentence2");
		// System.out.println(neListSentence2.toString());
	}

	/**
	 * Extracts Named Entities within two sentences at same time. Matches the their categories and returns their intersection, i.e. common
	 * categories.
	 * @param tokenizedSentence1
	 *           - a tokenised sentence in spanish
	 * @param tokenizedSentence2
	 *           - a tokenised sentence in spanish
	 * @return common categories
	 */
	public HashMap<String, Boolean> getCommonCategories(List<String> tokenizedSentence1, List<String> tokenizedSentence2)
	{
		findNamedEntities(tokenizedSentence1, tokenizedSentence2);

		HashMap<String, Boolean> cats1 = neListSentence1.getExistingCategories();
		HashMap<String, Boolean> cats2 = neListSentence2.getExistingCategories();
		HashMap<String, Boolean> defaultEsNamedEntitiesHashMap = publicObjects.getDefaultEsNamedEntitiesHashMap();
		HashMap<String, Boolean> temp = publicObjects.getDefaultEsNamedEntitiesHashMap();

		for (String s : temp.keySet())
			if (cats1.containsKey(s) && cats2.containsKey(s))
			{
				defaultEsNamedEntitiesHashMap.remove(s);
				defaultEsNamedEntitiesHashMap.put(s, true);
			}

		return defaultEsNamedEntitiesHashMap;
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		String rawTextES1 = "San Vicente del Raspeig (en valenciano y cooficialmente, Sant Vicent del Raspeig) es un municipio español situado en el noroeste del área metropolitana de Alicante, de la provincia de Alicante, en la Comunidad Valenciana (España). Cuenta con 55.781 habitantes (INE 2013).";
		String rawTextES2 = "En el término municipal de San Vicente del Raspeig se encuentra, desde su fundación en 1979, el campus de la Universidad de Alicante. ";

		MyTokenizer tokenizer = new MyTokenizer(Constants.ES);

		List<String> tokensES1 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextES1);
		// String[] sentenceES1 = tokensES1.toArray(new String[tokensES1.size()]);

		List<String> tokensES2 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextES2);
		// String[] sentenceES2 = tokensES2.toArray(new String[tokensES2.size()]);

//		for (int i = 0; i < tokensES1.size(); i++)
//			System.out.println(i + " " + tokensES1.get(i));
//
//		System.out.println();
//		for (int i = 0; i < tokensES2.size(); i++)
//			System.out.println(i + " " + tokensES2.get(i));

		/** This is what really matters */
		/**
		 * Finding common entities between two sentences
		 */
		NEREsFinder ner = new NEREsFinder();
		System.out.println("\nCommon Categories:");
		HashMap<String, Boolean> commonCats = ner.getCommonCategories(tokensES1, tokensES2);
		for (String s : commonCats.keySet())
			System.out.println(s + " \t" + commonCats.get(s));

		/**
		 * Finding named entities in a sentence
		 */
		NamedEntities ne = ner.getNamedEntities(tokensES1);
		System.out.println("NER: \n"+ne.toString());

	}

}
