/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.stemmer;

import java.util.ArrayList;
import java.util.List;

import nlp.sentencespliter.MySentenceSplitter;
import nlp.stemmer.snowball.SnowballManager;
import nlp.tokenizer.MyTokenizer;
import constantsfilesfolders.Constants;

/**
 * This class is an abstraction to all the available stemmers (in this case we only have Snowball implemented). Snowball: Spanish, English,
 * Portuguese, Italian, German, Russian and French.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */

public class MyStemmer
{

	private String language = "";
	private SnowballManager snowballManager = null;

	/**
	 * Default Stemmer is Snowball.
	 * 
	 * @param language
	 *                language
	 */
	public MyStemmer(String language)
	{
		this.language = language;
		this.snowballManager = new SnowballManager(language);
	}

	/**
	 * Constructor.
	 * 
	 * @param stemmerName
	 *                see available stemmers in the {@link Constants}
	 * @param language
	 *                language
	 */
	public MyStemmer(String stemmerName, String language)
	{
		this.language = language;
		if (stemmerName.equalsIgnoreCase(Constants.STEMMER_SNOWBALL))
		{
			usingSnowball();
		} else
		{
			System.err.println("The only Stemmer available right now is the Snowball! See Constants class.\n Using Snowball as default!!!");
			usingSnowball();
		}
	}

	private void usingSnowball()
	{
		this.snowballManager = new SnowballManager(language);
	}

	/**
	 * Obtains the stem of a word from its derived forms.
	 * 
	 * @param word
	 * @return stemm
	 */
	public String getStemmedWord(String word)
	{
		return snowballManager.StemmWord(word);
	}

	/**
	 * Returns a list a Stemmed sentence.
	 * 
	 * @param tokenisedSentence
	 * @return a list of Stems
	 */
	public List<String> getStemmedSentence(List<String> tokenisedSentence)
	{
		List<String> stemmedSentence = new ArrayList<String>();

		for (String word : tokenisedSentence)
			stemmedSentence.add(getStemmedWord(word));
		return stemmedSentence;
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		MyStemmer stemmer = new MyStemmer(Constants.STEMMER_SNOWBALL, Constants.EN);
		System.out.println(stemmer.getStemmedWord("happiness"));

		stemmer = new MyStemmer(Constants.STEMMER_SNOWBALL, Constants.ES);
		System.out.println(stemmer.getStemmedWord("Regístrate"));

		stemmer = new MyStemmer(Constants.STEMMER_SNOWBALL, Constants.PT);
		System.out.println(stemmer.getStemmedWord("professorado"));

		stemmer = new MyStemmer("grego");
		System.out.println(stemmer.getStemmedWord("professorado"));

		String test = "En 1851 las aguas fueron declaradas de utilidad publica por Real Orden. Desde entonces, llegan enfermos de todas las latitudes a tomar estas aguas y sanar sus dolencias y males, obteniendo siempre un extraordinario resultado. El edificio del Balneario de Carratraca fue mandado construir por Fernando VII e inaugurado en el año 1855, siendo su estilo neoclásico y construido en piedra arenisca y marmol.";
		MySentenceSplitter mySentenceSplitter = new MySentenceSplitter();
		MyTokenizer myTokenizer = new MyTokenizer();
		
		ArrayList<String> sentences = (ArrayList<String>) mySentenceSplitter.getSentenceSplitterList(test);
		
		stemmer = new MyStemmer(Constants.STEMMER_SNOWBALL, Constants.ES);
		
		
		for (String sentence : sentences)
		{
			List<String> tokens = myTokenizer.getTokenisedSentenceList(sentence);

			for (String stemm : stemmer.getStemmedSentence(tokens))
				System.out.print(stemm + " ");
			System.out.println();

		}

	}
}
