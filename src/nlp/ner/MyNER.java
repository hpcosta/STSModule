/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.ner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nlp.tokenizer.MyTokenizer;
import constantsfilesfolders.Constants;

/**
 * This class extracts Named Entities from Spanish and English sentences. The method {@see MyNER.findCommonCategories} returns the
 * intersection of the common named entities categories extracted from the two sentences.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class MyNER
{
	private String language = "";
	private NEREnFinder nerEnFinder = null;
	private NEREsFinder nerEsFinder = null;

	/**
	 * Default constructor. Depending on the language, this class load the English or Spanish NER.
	 */
	public MyNER(String language)
	{
		this.language = language;

		if (language.equalsIgnoreCase(Constants.ES))
		{
			System.err.println("\t>Loading spanish NER model!");
			nerEsFinder = new NEREsFinder();
		}

		else if (language.equalsIgnoreCase(Constants.EN))
		{
			System.err.println("\t>Loading english NER model!");
			nerEnFinder = new NEREnFinder();
		} else
		{
			System.err.print("MyNER Warning! ");
			System.err.println("There is no NER models for \"" + language
					+ "\". Only Spanish and English models available!\n>Loading english NER model by default.");
			nerEnFinder = new NEREnFinder();
		}
	}

	/**
	 * Returns all the named entities in a sentence. It only works for English and Spanish.
	 * 
	 * @param tokenisedSentenceList
	 *           tokenised sentence list
	 * @return {@link NamedEntities}
	 */
	public NamedEntities getNamedEntities(List<String> tokenisedSentenceList)
	{
		if (language.equalsIgnoreCase(Constants.ES))
		{
			return nerEsFinder.getNamedEntities(tokenisedSentenceList);
		} else if (language.equalsIgnoreCase(Constants.EN))
		{
			return nerEnFinder.getNamedEntities(tokenisedSentenceList);
		} else
		{
			System.err.print("MyNER Warning! ");
			System.err.println("There is no NER models for \"" + language + "\". Returning NULL!");
			return null;
		}
	}

	/**
	 * This method returns the intersection of the common named entities categories extracted from the two sentences.
	 * 
	 * @param tokenizedSentence1
	 *           - a tokenized sentence
	 * @param tokenizedSentence2
	 *           - a tokenized sentence
	 * @return the intersection of the common named entities categories extracted from the two sentences.
	 */
	public HashMap<String, Boolean> getCommonCategories(List<String> tokenizedSentence1, List<String> tokenizedSentence2)
	{
		if (language.equalsIgnoreCase(Constants.ES))
			return nerEsFinder.getCommonCategories(tokenizedSentence1, tokenizedSentence2);
		else if (language.equalsIgnoreCase(Constants.EN))
			return nerEnFinder.getCommonCategories(tokenizedSentence1, tokenizedSentence2);
		else
		{
			System.err.println("It only has models for Spanish and English!");
			return null;
		}
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
		String rawTextEN1 = "Thanks you for this Sanja Stajner , I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";
		String rawTextEN2 = "I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";

		MyTokenizer tokenizer = new MyTokenizer();

		List<String> tokensES1 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextES1);
		List<String> tokensES2 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextES2);
		List<String> tokensEN1 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextEN1);
		List<String> tokensEN2 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextEN2);

		/** This is what really matters */
		MyNER myNer = null;

		myNer = new MyNER(Constants.ES);
		HashMap<String, Boolean> commonCatsES = myNer.getCommonCategories(tokensES1, tokensES2);
		System.out.println("\nCommon Categories ES:");
		for (String s : commonCatsES.keySet())
			System.out.println(s + " \t" + commonCatsES.get(s));

		System.out.println("\n" + myNer.getNamedEntities(tokensES1));

		myNer = new MyNER(Constants.EN);
		HashMap<String, Boolean> commonCatsEN = myNer.getCommonCategories(tokensEN1, tokensEN2);
		System.out.println("\nCommon Categories EN:");
		for (String s : commonCatsEN.keySet())
			System.out.println(s + " \t" + commonCatsEN.get(s));

		System.out.println("\n" + myNer.getNamedEntities(tokensEN1));
	}
}
