/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nlp.ner.MyNER;
import nlp.ner.NamedEntities;
import nlp.postagger.MyPOSTagger;
import nlp.stemmer.MyStemmer;
import nlp.stopwords.MyStopwordChecker;
import nlp.tokenizer.MyTokenizer;
import constantsfilesfolders.Constants;
import corporamodel.corpus.documents.sentences.PhraseModel;

/**
 * This class was created to: 1) POS Tagger SP, EN and PT sentences - receives a string/sentence and returns a list of tags [@see
 * getTaggedSentenceList]; 2) Stemm a list of tokens - receives a list of tokens and returns a list of stemms [@see getStemmedTokensList];
 * 3) Check if a word is a stopword or not - receives a word and returns true or false [@see isStopWord].
 */

/**
 * @author Hernani Costa
 * 
 * @version 0.3/2015
 */
public class NLPManager
{

	private PhraseModel phraseModel = null;
	private MyPOSTagger mypostagger = null;
	private MyStemmer mystemmer = null;
	private MyStopwordChecker mystopwordchecker = null;
	private MyNER myner = null;
	private MyTokenizer myTokenizer = null;

	// private MyBabelNet = null;

	/**
	 * Default constructor. Loads the necessary modules for a given language.
	 * 
	 * @param language
	 *           - receives one of the languages defined in the {@see Constants} class (i.e. Constants.EN, Constants.SP or Constants.PT)
	 */
	public NLPManager(String language)
	{
		phraseModel = new PhraseModel();

		mypostagger = new MyPOSTagger(Constants.POSTAGGER_TREETAGGER, language);
		mystemmer = new MyStemmer(Constants.STEMMER_SNOWBALL, language);
		mystopwordchecker = new MyStopwordChecker(language);

		if ((language.equalsIgnoreCase(Constants.EN) || language.equalsIgnoreCase(Constants.ES) || language.equalsIgnoreCase(Constants.PT)))
		{
			myner = new MyNER(language);
		} else
		{
			System.err.println("MyNER only works for English, Spanish and Portuguese! (NLPManager)");
		}
		myTokenizer = new MyTokenizer(language);
	}

	/**
	 * Set stopword list
	 * 
	 * @param stopwordsFile
	 *           - path to the stopword file
	 * @param fileEncoding
	 *           - file encoding
	 */
	public void setStopwordList(String stopwordsFile, String fileEncoding)
	{
		mystopwordchecker = new MyStopwordChecker(stopwordsFile, fileEncoding);
	}

	/**
	 * Receives a sentence to be tagged and returns a list with tags.
	 * 
	 * @param rawSentence
	 *           - sentence to be tagged
	 * @return a list with tags
	 */
	public List<String> getTaggedSentenceList(String rawSentence)
	{
		mypostagger.taggSentence(rawSentence);
		phraseModel = mypostagger.getPhraseModel();
		return phraseModel.getPOSTaggedSentenceList();
	}

	/**
	 * Receives a sentence to be lemmatised and returns a list with lemmas. Make sure that you called the
	 * {@link #getTaggedSentenceList(String)} method first.
	 * 
	 * @param rawSentence
	 *           - sentence to be tagged
	 * @return a list with lemmas
	 */
	public List<String> getLemmatizedSentenceList(String rawSentence)
	{
		return phraseModel.getLemmatisedSentenceList();
	}

	/**
	 * Receives a list of tokens/words to be stemmed and returns a stemmed list of tokens.
	 * 
	 * @param rawTokensList
	 *           - list of tokens/words to be stemmed
	 * @return stemmed list of tokens
	 */
	public List<String> getStemmedTokensList(List<String> rawTokensList)
	{
		return mystemmer.getStemmedSentence(rawTokensList);
	}

	/**
	 * Receives a sentence to be stemmed and returns a list with stemms.
	 * 
	 * @param rawSentence
	 *           - sentence to be stemmed
	 * @return stemmed list of tokens
	 */
	public List<String> getStemmedSentenceList(String rawSentence)
	{
		// First the sentence is tokenized and then all the words are stemmed.
		List<String> tokens = myTokenizer.getTokenisedSentenceList(rawSentence);
		return getStemmedTokensList(tokens);
	}

	/**
	 * Receives a sentence to be tokenized and returns a list with tokens.
	 * 
	 * @param rawSentence
	 *           sentence to be tokenizer by default by the OpenNLP Tokenizer
	 * @return a tokenized list of tokens
	 */
	public List<String> getTokenisedSentenceList(String rawSentence)
	{
		return myTokenizer.getTokenisedSentenceList(rawSentence);
	}

	/**
	 * Receives a list of tokens/words and returns a boolean list with true's and false's (true means that the token/word is a stopword).
	 * 
	 * @param rawTokensList
	 *           - list of tokens/words to be analysed
	 * @return a boolean list with true's and false's (true means that the token/word is a stopword)
	 */
	public List<Boolean> getStopwordCheckerList(List<String> rawTokensList)
	{
		List<Boolean> stopwordsList = new ArrayList<Boolean>();
		for (String token : rawTokensList)
			stopwordsList.add(mystopwordchecker.isStopWord(token));

		return stopwordsList;
	}

	/**
	 * Extracts all the named entities from a sentence.
	 * 
	 * @param tokenizedSentence
	 *           - a tokenized sentence
	 * 
	 * @return {@link NamedEntities}
	 */
	public NamedEntities getNamedEntities(List<String> tokenizedSentence)
	{
		return myner.getNamedEntities(tokenizedSentence);
	}

	/**
	 * Receives two tokenised sentences in the same language and returns the intersection of the common named entities categories extracted
	 * from the two sentences.
	 * 
	 * @param tokenizedSentence1
	 *           - a tokenized sentence
	 * @param tokenizedSentence2
	 *           - a tokenized sentence
	 * @return the intersection of the common named entities categories extracted from the two sentences.
	 */
	public HashMap<String, Boolean> getCommonCategories(List<String> tokenizedSentence1, List<String> tokenizedSentence2)
	{
		return myner.getCommonCategories(tokenizedSentence1, tokenizedSentence2);
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		demo();

	}

	public static void demo()
	{
		String rawTextSP = "", rawTextEN = "", rawTextPT = "";
		rawTextSP = "En 1851 las aguas fueron declaradas de utilidad publica por Real Orden.";
		// String rawTextSP2 = "San Vicente del Raspeig (en valenciano y cooficialmente, Sant Vicent del Raspeig) es un municipio.";
		rawTextEN = "SPA HOTEL SERCOTEL VALLE DEL ESTE";
		rawTextPT = "Trabalhar em demasia, principalmente quando tal é sinónimo de horas extra, pode ter graves prejuízos para a saúde.";

		String rawTextES1 = "San Vicente del Raspeig (en valenciano y cooficialmente, Sant Vicent del Raspeig) es un municipio español situado en el noroeste del área metropolitana de Alicante, de la provincia de Alicante, en la Comunidad Valenciana (España). Cuenta con 55.781 habitantes (INE 2013).";
		String rawTextES2 = "En el término municipal de San Vicente del Raspeig se encuentra, desde su fundación en 1979, el campus de la Universidad de Alicante. ";
		String rawTextEN1 = "Thanks you for this Sanja Stajner , I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";
		String rawTextEN2 = "I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";

		ArrayList<String> tokens = null;
		ArrayList<Boolean> stopwords = null;
		MyTokenizer tokenizer = null;

		NLPManager sa = null;
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		/**************************** SPANISH ****************************/
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		System.out.println("SP: ");
		System.out.println(rawTextSP);

		sa = new NLPManager(Constants.ES);
		tokenizer = new MyTokenizer(Constants.ES);
		/*****************************************************************/
		/** How to get a pos tagged sentence in Spanish: */
		/*****************************************************************/
		List<String> posSP = sa.getTaggedSentenceList(rawTextSP);
		System.out.println("- Tagged sentence (" + posSP.size() + ")");
		for (String tag : posSP)
			System.out.print(tag + " ");
		System.out.println();

		/*****************************************************************/
		/** How to lemmatise a sentence in Spanish: */
		/*****************************************************************/
		List<String> lemmaSP = sa.getLemmatizedSentenceList(rawTextSP);
		List<String> lemmaSP2 = sa.getLemmatizedSentenceList(rawTextSP);
		System.out.println("- Lemmatised sentence (" + lemmaSP2.size() + ")");
		for (String tag : lemmaSP)
			System.out.print(tag + " ");
		System.out.println();

		tokens = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextSP);
		System.out.println("--> Used tokens: ");
		for (String t : tokens)
			System.out.print(t + " ");
		System.out.println();

		/*****************************************************************/
		/** How to stemm a sentence in Spanish: */
		/*****************************************************************/
		List<String> stemmsSP = sa.getStemmedTokensList(tokens);
		System.out.println("- Stemmed sentence:");
		for (String stemm : stemmsSP)
			System.out.print(stemm + " ");
		System.out.println();

		/*****************************************************************/
		/** How check if a word is a stopword in Spanish: */
		/*****************************************************************/
		System.out.println("- Stopwords checker:");
		stopwords = (ArrayList<Boolean>) sa.getStopwordCheckerList(tokens);
		for (int i = 0; i < tokens.size(); i++)
			System.out.print(stopwords.get(i) + " ");

		/*****************************************************************/
		/************ Finding Common Entities in Spanish *****************/
		/*****************************************************************/
		List<String> tokensES1 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextES1);
		List<String> tokensES2 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextES2);

		HashMap<String, Boolean> commonCatsES = sa.getCommonCategories(tokensES1, tokensES2);
		System.out.println("\nCommon Categories ES:");
		for (String s : commonCatsES.keySet())
			System.out.println(s + " \t" + commonCatsES.get(s));

		/*****************************************************************/
		/************ Finding Named Entities in Spanish ******************/
		/*****************************************************************/
		System.out.println("\nNamed Entities ES:");
		System.out.println(sa.getNamedEntities(tokensES1).toString());

		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		/**************************** ENGLISH ****************************/
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		System.out.println("\n\n\nEN: ");
		System.out.println(rawTextEN);

		sa = new NLPManager(Constants.EN);
		tokenizer = new MyTokenizer(Constants.EN);
		/*****************************************************************/
		/** How to get a pos tagged sentence in English: */
		/*****************************************************************/
		List<String> posEN = sa.getTaggedSentenceList(rawTextEN);
		System.out.println("- Tagged sentence (" + posEN.size() + ")");
		for (String tag : posEN)
			System.out.print(tag + " ");
		System.out.println();

		/*****************************************************************/
		/** How to lemmatise a sentence in English: */
		/*****************************************************************/
		List<String> lemmaEN = sa.getLemmatizedSentenceList(rawTextEN);
		System.out.println("- Lemmatised sentence (" + lemmaEN.size() + ")");
		for (String tag : lemmaEN)
			System.out.print(tag + " ");
		System.out.println();

		tokens = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextEN);
		System.out.println("--> Used tokens: ");
		for (String t : tokens)
			System.out.print(t + " ");
		System.out.println();

		/*****************************************************************/
		/** How to stemm a sentence in English: */
		/*****************************************************************/
		List<String> stemmsEN = sa.getStemmedTokensList(tokens);
		System.out.println("- Stemmed sentence:");
		for (String stemm : stemmsEN)
			System.out.print(stemm + " ");
		System.out.println();

		/*****************************************************************/
		/** How check if a word is a stopword in English: */
		/*****************************************************************/
		System.out.println("- Stopwords checker:");
		stopwords = (ArrayList<Boolean>) sa.getStopwordCheckerList(tokens);
		for (int i = 0; i < tokens.size(); i++)
			System.out.print(stopwords.get(i) + " ");

		/*****************************************************************/
		/************ Finding Common Entities in English *****************/
		/*****************************************************************/

		List<String> tokensEN1 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextEN1);
		List<String> tokensEN2 = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextEN2);

		HashMap<String, Boolean> commonCatsEN = sa.getCommonCategories(tokensEN1, tokensEN2);
		System.out.println("\nCommon Categories EN:");
		for (String s : commonCatsEN.keySet())
			System.out.println(s + " \t" + commonCatsEN.get(s));

		/*****************************************************************/
		/************ Finding Named Entities in English ******************/
		/*****************************************************************/
		System.out.println("\nNamed Entities EN:");
		System.out.println(sa.getNamedEntities(tokensEN1).toString());

		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		/************************ PORTUGUESE *****************************/
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		System.out.println("\n\n\nPT: ");
		System.out.println(rawTextPT);

		sa = new NLPManager(Constants.PT);
		tokenizer = new MyTokenizer(Constants.PT);
		/*****************************************************************/
		/** How to get a pos tagged sentence in Portuguese: */
		/*****************************************************************/
		List<String> posPT = sa.getTaggedSentenceList(rawTextPT);
		System.out.println("- Tagged sentence (" + posPT.size() + ")");
		for (String tag : posPT)
			System.out.print(tag + " ");
		System.out.println();

		/*****************************************************************/
		/** How to lemmatise a sentence in Portuguese: */
		/*****************************************************************/
		List<String> lemmaPT = sa.getLemmatizedSentenceList(rawTextPT);
		System.out.println("- Lemmatised sentence (" + lemmaPT.size() + ")");
		for (String tag : lemmaPT)
			System.out.print(tag + " ");
		System.out.println();

		tokens = (ArrayList<String>) tokenizer.getTokenisedSentenceList(rawTextPT);
		System.out.println("--> Used tokens: ");
		for (String t : tokens)
			System.out.print(t + " ");
		System.out.println();

		/*****************************************************************/
		/** How to stemm a sentence in Portuguese: */
		/*****************************************************************/
		List<String> stemmsPT = sa.getStemmedTokensList(tokens);
		System.out.println("- Stemmed sentence:");
		for (String stemm : stemmsPT)
			System.out.print(stemm + " ");
		System.out.println();

		/*****************************************************************/
		/** How check if a word is a stopword in Portuguese: */
		/*****************************************************************/
		System.out.println("- Stopwords checker:");
		stopwords = (ArrayList<Boolean>) sa.getStopwordCheckerList(tokens);
		for (int i = 0; i < tokens.size(); i++)
			System.out.print(stopwords.get(i) + " ");

	}
}
