/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel;

import java.util.ArrayList;
import java.util.List;

import corporamodel.corpus.CorpusModel;

/**
 * This class creates a model of a corpora, which contains several corpus, a type, a name and an id
 */
/**
 * @author Hernani Costa
 * 
 */
public class CorporaModel
{
	private List<CorpusModel> corpusModelList = null;
	private String corporaName = "";
	private String languages = "";
	private CorporaType corporaType = null;
	private int corporaId;

	/**
	 * Default constructor
	 */
	public CorporaModel(String corporaName, CorporaType corporaType, String languages)
	{
		this.corpusModelList = new ArrayList<CorpusModel>();
		this.corporaName = corporaName;
		this.corporaType = corporaType;
		this.languages = languages;
	}

	/**
	 * Default constructor
	 */
	public CorporaModel(String corporaName, CorporaType corporaType, String languages, int corporaId)
	{
		this(corporaName, corporaType, languages);
		this.corporaId = corporaId;
	}

	/**
	 * Adds a new CorpusModel to the corpusModelList
	 * 
	 * @param corpusModel
	 */
	public void addCorpusModel(CorpusModel corpusModel)
	{
		corpusModelList.add(corpusModel);
	}

	/**
	 * @return the corpusModelList
	 */
	public List<CorpusModel> getCorpusModelList()
	{
		return corpusModelList;
	}

	/**
	 * @return the corporaName
	 */
	public String getCorporaName()
	{
		return corporaName;
	}

	/**
	 * @return the corporaType
	 */
	public CorporaType getCorporaType()
	{
		return corporaType;
	}

	/**
	 * @return the corporaId
	 */
	public int getCorporaId()
	{
		return corporaId;
	}

	/**
	 * @return the languages
	 */
	public String getLanguages()
	{
		return languages;
	}

	/**
	 * Returns all the corpus in a given language
	 * 
	 * @param language
	 *           - language of the corpus
	 * @return all the corpus containing that language
	 */
	public List<CorpusModel> getCorpusByLanguage(String language)
	{
		List<CorpusModel> temp = new ArrayList<CorpusModel>();
		for (CorpusModel cm : (ArrayList<CorpusModel>) corpusModelList)
			if (cm.getCorpusLanguage().equalsIgnoreCase(language)) temp.add(cm);

		return temp;
	}

	/**
	 * @return all the object fields in a String
	 */
	public String toString()
	{
		StringBuffer builder = new StringBuffer();
		builder.append("Corpora Name: " + corporaName + "\n");
		builder.append("\nCorpora Type: " + corporaType + "\n");
		builder.append("\nCorpora Lang: " + languages + "\n");
		builder.append("\nCorpora Id: " + corporaId + "\n");
		builder.append("\nCorpus:\n" + "\n");
		for (CorpusModel cm : (ArrayList<CorpusModel>) corpusModelList)
			builder.append(cm.toString() + "\n");
		return builder.toString();
	}

}