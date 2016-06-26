/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel.corpus;

/**
 * This class defines a Corpus Model, which has a list of documents, the name of the Corpus, its language and its id in the database
 */
import java.util.ArrayList;
import java.util.List;

import corporamodel.corpus.documents.DocumentModel;

/**
 * @author Hernani Costa
 * 
 * @version 0.2/2014
 */

public class CorpusModel
{
	private List<DocumentModel> documentModelList = null;
	private String corpusName = "";
	private String corpusLanguage = "";
	private int corpusId = 0;

	/**
	 * Constructor
	 */
	public CorpusModel(String corpusName, String corpusLanguage)
	{
		this.documentModelList = new ArrayList<DocumentModel>();
		this.corpusName = corpusName;
		this.corpusLanguage = corpusLanguage;
	}

	/**
	 * Constructor
	 */
	public CorpusModel(String corpusName, String corpusLanguage, int corpusId)
	{
		this(corpusName, corpusLanguage);
		this.corpusId = corpusId;
	}

	/**
	 * Adds a new DocumentModel to the documentModelList
	 * 
	 * @param documentModel
	 */
	public void addDocumentModel(DocumentModel documentModel)
	{
		documentModelList.add(documentModel);
	}

	/**
	 * @return the documentModelList
	 */
	public List<DocumentModel> getDocumentModelList()
	{
		return documentModelList;
	}

	/**
	 * @param documentModelList
	 *                the documentModelList to set
	 */
	public void setDocumentModelList(List<DocumentModel> documentModelList)
	{
		this.documentModelList = documentModelList;
	}

	/**
	 * @return the corpusName
	 */
	public String getCorpusName()
	{
		return corpusName;
	}

	/**
	 * @return the corpusId
	 */
	public int getCorpusId()
	{
		return corpusId;
	}

	/**
	 * @return the corpusLanguage
	 */
	public String getCorpusLanguage()
	{
		return corpusLanguage;
	}

	/**
	 * @return all the object fields in a String
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Corpus Name: " + corpusName);
		buffer.append("\nCorpus Language: " + corpusLanguage);
		buffer.append("\nCorpus Id: " + corpusId);
		buffer.append("\n");
		for (DocumentModel dm : documentModelList)
			buffer.append(dm.toString() + "\n");
		return buffer.toString();
	}

	/**
	 * @return all the object fields in a String
	 */
	public String toStringIDs()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Corpus Name: " + corpusName);
		buffer.append("\nCorpus Language: " + corpusLanguage);
		buffer.append("\nCorpus Id: " + corpusId);
		buffer.append("\n");
		for (DocumentModel dm : documentModelList)
			buffer.append(dm.getDocumentIDString() + "\n");
		return buffer.toString();
	}

}
