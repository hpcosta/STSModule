/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.dictionaries.babelnet;

import java.util.ArrayList;
import java.util.List;

import nlp.dictionaries.babelnet.auxobjects.Concept;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Parses the HTML for concepts and its definitions.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class ParseBabelNetWebPage
{

	private List<Concept> conceptsList = null;

	/**
	 * Default constructor.
	 */
	public ParseBabelNetWebPage()
	{
		conceptsList = new ArrayList<Concept>();
	}

	/**
	 * Parses the HTML for concepts and its definitions.
	 * 
	 * @param html
	 *                HTML page in a String format
	 */
	public void parseHTML(String html)
	{
		List<String> concepts = new ArrayList<String>();
		List<String> definitions = new ArrayList<String>();
		List<String> postaggs = new ArrayList<String>();
		List<String> types = new ArrayList<String>();

		conceptsList = new ArrayList<Concept>();

		Document doc = Jsoup.parse(html);
		Elements content = doc.getElementsByClass("word");

		// retrieving the concepts
		for (Element e : content)
		{
			concepts.add(e.text().trim());
			// System.err.println(e.text());
		}

		// retrieving the definitions
		Elements defs = doc.getElementsByClass("def");
		for (Element e : defs)
		{
			definitions.add(e.text().trim());
			// System.err.println(e.text());
		}

		// retrieving postaggs and types
		Elements ptaggs = doc.getElementsByClass("coor");
		// System.err.println(ptaggs.size());
		String _type = "";
		String _postagg = "";
		for (Element e : ptaggs)
		{
			_postagg = e.text().split(" ")[1].trim();
			_postagg = _postagg.charAt(_postagg.length() - 1) + "";
			_type = e.text().split(" ")[3].trim();

			postaggs.add(_postagg);
			types.add(_type);
			// System.err.println(e.text() + " " + _postagg + ":" + _type);
		}

		if (concepts.size() == definitions.size())
			for (int i = 0; i < concepts.size(); i++)
				conceptsList.add(new Concept(concepts.get(i), definitions.get(i), postaggs.get(i), types.get(i)));
		else
			System.err.println("Error the concepts and definitions do not match: " + concepts.size() + ":" + definitions.size());

	}

	/**
	 * @return the conceptsList
	 */
	public List<Concept> getConceptsList()
	{
		return conceptsList;
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		// ParseBabelNetWebPage babel = new ParseBabelNetWebPage();
	}

}
