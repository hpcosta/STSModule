/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.stopwords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import constantsfilesfolders.Constants;
import constantsfilesfolders.FilesAndFolders;
import constantsfilesfolders.PublicFunctions;

/**
 * Loads stopwords from a file. Has functions to: 1) verify is a given word is a stopword or not; 2) get all stopwords; 3) remove special
 * characters from words;
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class MyStopwordChecker
{
	private String stopwordsFile = "";
	private String sourceEncoding = "";
	private List<String> stopwords = null;

	/**
	 * Default constructor. The language is used to load a stopword list.
	 * 
	 * @param language
	 *           language
	 */
	public MyStopwordChecker(String language)
	{
		// TODO Right now only stopword list for portuguese, english and spanish are available. Add stopword lists for RU, IT, FR,
		// DE
		this.stopwords = new ArrayList<String>();
		this.sourceEncoding = Constants.ENCODING_UTF8;

		System.err.println("Stopwords");
		if (language.equalsIgnoreCase(Constants.EN))
		{
			System.err.println("\t>Loading EN stopwords!");
			this.stopwordsFile = Constants.STOPWORDSFILE_EN;
		} else if (language.equalsIgnoreCase(Constants.ES))
		{
			System.err.println("\t>Loading ES stopwords!");
			this.stopwordsFile = Constants.STOPWORDSFILE_ES;
		} else if (language.equalsIgnoreCase(Constants.PT))
		{
			System.err.println("\t>Loading PT stopwords!");
			this.stopwordsFile = Constants.STOPWORDSFILE_PT;
		} else if (language.equalsIgnoreCase(Constants.IT))
		{
			System.err.println("\t>Loading IT stopwords!");
			this.stopwordsFile = Constants.STOPWORDSFILE_IT;
		} else if (language.equalsIgnoreCase(Constants.DE))
		{
			System.err.println("\t>Loading DE stopwords!");
			this.stopwordsFile = Constants.STOPWORDSFILE_DE;
		}

		else
		{
			System.err
					.println("MyStopwordChecker ERROR!! Does not exist a stopword list for the required language ("
							+ language
							+ ").Only Portuguese, Spanish and English available. Please use Constants.PT, Constants.ES or Constants.EN to define the language or use the other constructor.");
			System.err.println(">Loading english stopwaords by default!");
			this.stopwordsFile = Constants.STOPWORDSFILE_EN;
		}
		loadStopwordsFromFile();
	}

	/**
	 * Constructor. Receives a stopwords list from a file and the correspondent encoding.
	 * 
	 * @param stopwordsFile
	 *           path to the stopword file
	 * @param fileEncoding
	 *           file encoding
	 */
	public MyStopwordChecker(String stopwordsFile, String fileEncoding)
	{
		this.stopwordsFile = stopwordsFile;
		this.sourceEncoding = fileEncoding;
		this.stopwords = new ArrayList<String>();
		if (FilesAndFolders.txtFileExists(stopwordsFile))
		{
			loadStopwordsFromFile();
		} else
		{
			System.err.println("Error! The file does not exist in the given path:\n" + stopwordsFile + ".\nMake sure that is a .txt file!");
			return;
		}
	}

	/**
	 * Loads a list of stopwords from a file
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private void loadStopwordsFromFile()
	{
		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(stopwordsFile), sourceEncoding));

			String line = null;
			while ((line = reader.readLine()) != null)
			{
				line = line.toLowerCase().trim();
				stopwords.add(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		java.util.Collections.sort(stopwords);
	}

	/**
	 * @return the stopwords list
	 */
	public List<String> getStopwords()
	{
		return stopwords;
	}

	/**
	 * Check if a word is a stopword or not. Returns true if it is a stopword or has less than 2 characters, false if not.
	 * 
	 * @param word
	 *           the word to examine
	 * @return Returns true if it is a stopword, false if not.
	 */
	public boolean isStopWord(String word)
	{
		if (stopwords.contains(word.toLowerCase().trim()) || word.length() < 3) return true;

		return false;
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		String test = "(antepenúltima";
		String test2 = "• ";

		System.out.println("Path to the stopword list file: " + Constants.STOPWORDSFILE_ES);

		// MyStopwordChecker d = new MyStopwordChecker(
		// "/Users/hpcosta/Docs/imagens/relations/2014-Priscila/2014-08-minha_viagem_ao_brasil/2014-08-18 06.53.54.mov",
		// Constants.ENCODING_UTF8);
		// creating the StopwordChecker
		MyStopwordChecker filter = new MyStopwordChecker(Constants.STOPWORDSFILE_ES, Constants.ENCODING_UTF8);

		System.out.println("removing special characters from the word: " + test + " >> " + PublicFunctions.removeSpecialChars(test));
		System.out.println("removing special characters from the word: " + test2 + " >> " + PublicFunctions.removeSpecialChars(test2));
		System.out.println("verifying if the word is a stopword: " + test + " >> " + filter.isStopWord(test));

		test = PublicFunctions.removeSpecialChars(test);
		System.out.println("verifying if the word is a stopword: " + test + " >> " + filter.isStopWord(test));

		// printing to console all the stopwords
		// for (String str : filter.getStopwords())
		// System.out.println(str);
	}
}
