/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.stemmer.snowball;

import org.tartarus.snowball.ext.englishStemmer;

/**
 * This class is an abstraction to snowball. English Stemmer. Allows to stem a word from its derived forms. This class is used by
 * {@link SnowballManager}
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class SnowballEnglishStemmer
{
	private englishStemmer stemm = null;

	/**
	 * Default constructor
	 */
	public SnowballEnglishStemmer()
	{
		this.stemm = new englishStemmer();
	}

	/**
	 * Obtains the stem of a word from its derived forms.
	 * 
	 * @param word
	 * @return stemm
	 */
	public String stemmWord(String word)
	{
		stemm.setCurrent(word);
		if (stemm.stem())
			return stemm.getCurrent();
		else
			return word;
	}

}
