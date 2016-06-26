/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * Responsible for retrieving the content from the BabelNet website.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class GetContent
{
	private String language = "";

	/**
	 * Default constructor.
	 */
	public GetContent(String language)
	{
		this.language = language;
	}

	/**
	 * Converts the HTML page to a String.
	 * 
	 * @param lemma
	 *                lemma
	 * @return String with the HTML
	 */
	public String retrievingContent(String lemma)
	{
		URL url = buildURL(lemma);
		System.err.println(url.toString());
		InputStream in = null;
		String page = "";
		try
		{
			in = url.openStream();
			page = convertInputStreamToString(in);
		} catch (IOException e)
		{
			System.err.println("Error opening the connection: " + url.toString());
			e.printStackTrace();
		}

		return page;
	}

	/**
	 * Converts the {@link InputStream} object to String
	 * 
	 * @param in
	 *                {@link InputStream}
	 * @return String with the HTML
	 */
	private String convertInputStreamToString(InputStream in)
	{
		String myString = "";
		try
		{
			myString = IOUtils.toString(in, "UTF-8");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// System.out.println(myString);
		return myString;
	}

	/**
	 * Automatically creates the URL to a given lemma
	 * 
	 * @param lemma
	 *                lemma
	 * @return the {@link URL}
	 */
	private URL buildURL(String lemma)
	{
		try
		{
			return new URL("http://babelnet.org/search?word=" + lemma + "&lang=" + language.toUpperCase());
			// System.out.println("http://babelnet.org/exploreResult?word=birth&lang=EN");
			// return new URL("http://babelnet.org/exploreResult?word=birth&lang=EN");

			// http://babelnet.org/exploreResult?word=wash&lang=EN
		} catch (MalformedURLException e)
		{
			System.err.println("Error building the URL: " + "http://babelnet.org/search?word=" + lemma + "&lang=" + language);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		GetContent getContent = new GetContent("ES");
		System.out.println(getContent.retrievingContent("bañar"));
	}

}
