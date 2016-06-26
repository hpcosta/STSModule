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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is only used to create a stopword list If you already have a stopword list and you want to add new stopwords but you are not
 * sure if they already exist you should: 1) add the new stopwords existent stopword file; 2) give the program the path to the 'source' file
 * (the file that contains the stopwords); 3) give the program the path to the 'target' file (new file that will contain all the stopwords,
 * ordered alphabetically and without repetitions); 4) run this program
 * 
 * See: http://members.unine.ch/jacques.savoy/clef/index.html //lists of stopwords
 * 
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class CreatingStopwordLists
{
	private final String source = System.getProperty("user.dir") + "/internalResources/stopwords/_DE-stopwords.txt"; // file containing the
																																							// old and the new
	// stopwords you want to add to the
	// current list
	private final String target = System.getProperty("user.dir") + "/internalResources/stopwords/DE-stopwords.txt"; // do not change the path
	private final String sourceEncoding = "UTF-8";
	private final String targetEncoding = "UTF-8";
	private List<String> stopwords = null;

	public CreatingStopwordLists()
	{
		// System.out.println(System.getProperty("user.dir"));
		// System.out.println(source);
		stopwords = new ArrayList<String>();

		try
		{
			loadStopWordList();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// list to colon delimited String
		StringBuilder sent = new StringBuilder();
		for (String s : stopwords)
			sent.append(s + "\n");

		System.out.println(stopwords.size());
		writeOutput(sent.toString());

	}

	/**
	 * Loads a list of stopwords from a file without duplications and in a alphabetic order
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void loadStopWordList() throws FileNotFoundException, IOException
	{
		String stopwordPath = source;

		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(stopwordPath), sourceEncoding));

		String line = null;

		while ((line = reader.readLine()) != null)
		{
			line = line.toLowerCase().trim();
			// necessary because I used a stopword list from the Internet and all the stopwords were separated by '|'
			if (line.startsWith("|")) continue;
			if (line.contains("|"))
			{
				int i = line.indexOf("|");
				line = line.substring(0, i).trim();
				// System.out.println(line);
			}
			if (!isStopWord(line)) stopwords.add(line);
		}
		Collections.sort(stopwords);

	}

	/**
	 * Creates a new file with all the stopwords
	 * 
	 * @param stopwordList
	 */
	private void writeOutput(String stopwordList)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(target);
			Writer out = new OutputStreamWriter(fos, targetEncoding);
			out.write(stopwordList);
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Check if a stopword already exists in the stopword list. Returns true if yes, false no.
	 * 
	 * @param word
	 *           the word to examine
	 * @return Returns true if it is a stopword, false if not.
	 */
	public boolean isStopWord(String word)
	{
		if (stopwords.contains(word))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		new CreatingStopwordLists();
	}

}
