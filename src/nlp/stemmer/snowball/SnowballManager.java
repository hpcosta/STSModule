/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.stemmer.snowball;

import java.util.ArrayList;
import java.util.List;

import nlp.sentencespliter.MySentenceSplitter;
import nlp.stemmer.MyStemmer;
import nlp.tokenizer.MyTokenizer;
import constantsfilesfolders.Constants;

/**
 * Snowball Manager. It has stemmers for: Spanish, English, Portuguese, Italian, German, Russian and French. Allows to stem a word from its
 * derived forms.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class SnowballManager
{
	private String language = "";
	private SnowballSpanishStemmer sp = null;
	private SnowballPortugueseStemmer pt = null;
	private SnowballEnglishStemmer en = null;
	private SnowballFrenchStemmer fr = null;
	private SnowballItalianStemmer it = null;
	private SnowballGermanStemmer de = null;
	private SnowballRussianStemmer ru = null;

	/**
	 * @param language
	 */
	public SnowballManager(String language)
	{
		this.language = language;
		
		System.err.println("Stemmer");
		if (language.equalsIgnoreCase(Constants.EN))
		{
			System.err.println("\t>Loading EN Stemmer model!");
			en = new SnowballEnglishStemmer();
		} else if (language.equalsIgnoreCase(Constants.ES))
		{
			System.err.println("\t>Loading ES Stemmer model!");
			sp = new SnowballSpanishStemmer();
		} else if (language.equalsIgnoreCase(Constants.PT))
		{
			System.err.println("\t>Loading PT Stemmer model!");
			pt = new SnowballPortugueseStemmer();
		} else if (language.equalsIgnoreCase(Constants.RU))
		{
			System.err.println(">Loading RU Stemmer model!");
			ru = new SnowballRussianStemmer();
		} else if (language.equalsIgnoreCase(Constants.IT))
		{
			System.err.println("\t>Loading IT Stemmer model!");
			it = new SnowballItalianStemmer();
		} else if (language.equalsIgnoreCase(Constants.FR))
		{
			System.err.println("\t>Loading FR Stemmer model!");
			fr = new SnowballFrenchStemmer();
		} else if (language.equalsIgnoreCase(Constants.DE))
		{
			System.err.println("\t>Loading DE Stemmer model!");
			de = new SnowballGermanStemmer();
		} else
		{
			System.err.println("ERROR in the SnowballManager, the current language (i.e. "
							+ language
							+ ") is not supported. \nIt only has models to English, Spanish, Portuguese, Russian, Italian, French and German!\nSee the Constants class.");
		}
	}

	/**
	 * Obtains the stem of a word from its derived forms.
	 * 
	 * @param word
	 * @return stemm
	 */
	public String StemmWord(String word)
	{
		if (language.equalsIgnoreCase(Constants.EN))
		{
			return en.stemmWord(word);
		} else if (language.equalsIgnoreCase(Constants.ES))
		{
			return sp.stemmWord(word);
		} else if (language.equalsIgnoreCase(Constants.PT))
		{
			return pt.stemmWord(word);
		} else if (language.equalsIgnoreCase(Constants.IT))
		{
			return it.stemmWord(word);
		} else if (language.equalsIgnoreCase(Constants.RU))
		{
			return ru.stemmWord(word);
		} else if (language.equalsIgnoreCase(Constants.DE))
		{
			return de.stemmWord(word);
		} else if (language.equalsIgnoreCase(Constants.FR))
		{
			return fr.stemmWord(word);
		} else
		{
			System.err.println("Incorrect Stemmer for \"" + language + "\" language. Returned word: \"" + word + "\"");
			return word;
		}
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		MyStemmer stemmer = new MyStemmer(Constants.EN);
		System.out.println(stemmer.getStemmedWord("happiness"));

		stemmer = new MyStemmer(Constants.ES);
		System.out.println(stemmer.getStemmedWord("Regístrate"));

		stemmer = new MyStemmer(Constants.PT);
		System.out.println(stemmer.getStemmedWord("professorado"));

		stemmer = new MyStemmer("xi");
		System.out.println(stemmer.getStemmedWord("professorado"));

		String test = "En 1851 las aguas fueron declaradas de utilidad publica por Real Orden. Desde entonces, llegan enfermos de todas las latitudes a tomar estas aguas y sanar sus dolencias y males, obteniendo siempre un extraordinario resultado. El edificio del Balneario de Carratraca fue mandado construir por Fernando VII e inaugurado en el año 1855, siendo su estilo neoclásico y construido en piedra arenisca y marmol.";

		MySentenceSplitter splitter = new MySentenceSplitter();
		MyTokenizer tokenizer = new MyTokenizer();

		ArrayList<String> sentences = (ArrayList<String>) splitter.getSentenceSplitterList(test);
		stemmer = new MyStemmer(Constants.ES);

		for (String s : sentences)
		{
			List<String> tokens = tokenizer.getTokenisedSentenceList(s);
			for (String t : tokens)
				System.out.println(t + ":" + stemmer.getStemmedWord(t));
		}

	}
}
