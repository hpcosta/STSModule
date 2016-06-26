/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 * 
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel.corpus.documents;

import java.util.ArrayList;
import java.util.List;

import corporamodel.corpus.documents.sentences.PhraseModel;

/**
 * This class represents a document and has all the associated information (id, name, source and isOriginal). A document is a collection of
 * {@link PhraseModel}s.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2013
 * @version 0.2/2015
 */
public class DocumentModel
{
	private List<PhraseModel> phraseModelList = null;
	private String documentName = "";
	private int documentID;
	private String documentSource = "";
	private boolean isOriginal = true;

	/**
	 * Default Constructor
	 */
	public DocumentModel()
	{
		this.phraseModelList = new ArrayList<PhraseModel>();
	}

	/**
	 * Default Constructor
	 */
	public DocumentModel(String documentName)
	{
		this.phraseModelList = new ArrayList<PhraseModel>();
		this.documentName = documentName;
	}

	/**
	 * Default Constructor
	 */
	public DocumentModel(String documentName, int documentID)
	{
		this.phraseModelList = new ArrayList<PhraseModel>();
		this.documentName = documentName;
		this.documentID = documentID;
	}

	/**
	 * Default Constructor
	 */
	public DocumentModel(String documentName, int documentID, String documentSource)
	{
		this.phraseModelList = new ArrayList<PhraseModel>();
		this.documentName = documentName;
		this.documentID = documentID;
		this.documentSource = documentSource;
	}

	/**
	 * Adds a new PhraseModel to the sentenceModelList
	 * 
	 * @param phraseModel
	 */
	public void addPhraseModel(PhraseModel phraseModel)
	{
		phraseModelList.add(phraseModel);
	}

	/**
	 * Returns all the {@link PhraseModel} in a list
	 * 
	 * @return the phraseModelList
	 */
	public List<PhraseModel> getPhraseModelList()
	{
		return phraseModelList;
	}

	/**
	 * @param documentName
	 *           the documentName to set
	 */
	public void setDocumentName(String documentName)
	{
		this.documentName = documentName;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName()
	{
		return documentName;
	}

	/**
	 * @return the documentID
	 */
	public int getDocumentID()
	{
		return documentID;
	}

	/**
	 * @param documentID
	 *           the documentID to set
	 */
	public void setDocumentID(int documentID)
	{
		this.documentID = documentID;
	}

	/**
	 * @return the isOriginal
	 */
	public boolean getIsOriginal()
	{
		return isOriginal;
	}

	/**
	 * @param isOriginal
	 *           the isOriginal to set
	 */
	public void setIsOriginal(boolean isOriginal)
	{
		this.isOriginal = isOriginal;
	}

	/**
	 * @return the documentSource
	 */
	public String getDocumentSource()
	{
		return documentSource;
	}

	/**
	 * @param documentSource
	 *          - the documentSource to set
	 */
	public void setDocumentSource(String documentSource)
	{
		this.documentSource = documentSource;
	}

	/**
	 * Returns the Document ID and all its sentences IDs
	 *
	 * @return all sentences' IDs in the document in a String
	 */
	public String getDocumentIDString()
	{
		StringBuffer builder = new StringBuffer();
		builder.append(">DocName:" + documentName + "<\n");
		for (PhraseModel pm : phraseModelList)
			builder.append(pm.getSentenceIDString() + "\n");
		return builder.toString();
	}

	/**
	 * Returns -- tokenised Document -- in -- Sentences --
	 * 
	 * @return tokenised sentences in a String
	 */
	public String getTokenisedDocument()
	{
		StringBuffer builder = new StringBuffer();
		for (PhraseModel pm : phraseModelList)
			builder.append(pm.getTokenisedSentence() + "\n");
		return builder.toString();
	}

	/**
	 * Returns -- tokenised Document -- in -- Sentences --
	 * 
	 * @return tokenised sentences in a List
	 */
	public List<String> getTokenisedDocumentList()
	{
		List<String> tokenisedSentencesList = new ArrayList<String>();
		for (PhraseModel pm : phraseModelList)
			tokenisedSentencesList.add(pm.getTokenisedSentence());
		return tokenisedSentencesList;
	}

	/**
	 * Returns -- tokenised Document -- in -- Tokens --
	 * 
	 * @return tokenised tokens in a List
	 */
	public List<String> getTokenisedTokensDocumentList()
	{
		List<String> builder = new ArrayList<String>();
		for (PhraseModel pm : phraseModelList)
			for (String s : pm.getTokenisedSentenceList())
				builder.add(s);
		return builder;
	}
	
	/**
	 * Returns -- Tagged Document -- in -- Sentences --
	 * 
	 * @return tagged sentences in a String
	 */
	public String getPOSTaggedDocument()
	{
		StringBuffer builder = new StringBuffer();
		for (PhraseModel pm : phraseModelList)
			builder.append(pm.getPOSTaggedSentence() + "\n");
		return builder.toString();
	}

	/**
	 * Returns -- Tagged Document -- in -- Sentences --
	 * 
	 * @return tagged sentences in a List
	 */
	public List<String> getPOSTaggedDocumentList()
	{
		List<String> builder = new ArrayList<String>();
		for (PhraseModel pm : phraseModelList)
			builder.add(pm.getPOSTaggedSentence());
		return builder;
	}

	/**
	 * Returns -- Tagged Document -- in -- Tokens --
	 * 
	 * @return tagged tokens in a List
	 */
	public List<String> getPOSTaggedTokensDocumentList()
	{
		List<String> builder = new ArrayList<String>();

		for (PhraseModel pm : phraseModelList)
			for (String s : pm.getPOSTaggedSentenceList())
				builder.add(s);
		return builder;
	}
	
	/**
	 * Returns -- lemmatised Document -- in -- Sentences --
	 * 
	 * @return lemmatised sentences in a String
	 */
	public String getLemmatisedSentencesDocument()
	{
		StringBuffer builder = new StringBuffer();
		for (PhraseModel pm : phraseModelList)
			builder.append(pm.getLemmatisedSentence() + "\n");
		return builder.toString();
	}

	/**
	 * Returns -- lemmatised Document -- in -- Sentences --
	 * 
	 * @return lemmatised sentences in a List
	 */
	public List<String> getLemmatisedSentencesDocumentList()
	{
		List<String> builder = new ArrayList<String>();

		for (PhraseModel pm : phraseModelList)
			builder.add(pm.getLemmatisedSentence());
		return builder;
	}

	/**
	 * Returns -- lemmatised Document -- in -- Tokens --
	 * 
	 * @return lemmatised tokens in a List
	 */
	public List<String> getLemmatisedTokensDocumentList()
	{
		List<String> builder = new ArrayList<String>();

		for (PhraseModel pm : phraseModelList)
			for (String lemma : pm.getLemmatisedSentenceList())
				builder.add(lemma);
		return builder;
	}

	/**
	 * Returns -- stemmed Document -- in -- Sentences --
	 * 
	 * @return stemmed sentences in a String
	 */
	public String getStemmedSentencesDocument()
	{
		StringBuffer builder = new StringBuffer();
		for (PhraseModel pm : phraseModelList)
			builder.append(pm.getStemmedSentence() + "\n");
		return builder.toString();
	}

	/**
	 * Returns -- stemmed Document -- in -- Sentences --
	 * 
	 * @return stemmed sentences in a List
	 */
	public List<String> getStemmedSentencesDocumentList()
	{
		List<String> builder = new ArrayList<String>();
		for (PhraseModel pm : phraseModelList)
			builder.add(pm.getStemmedSentence());
		return builder;
	}

	/**
	 * Returns -- stemmed Document -- in - Tokens --
	 * 
	 * @return stemmed tokens in a List
	 */
	public List<String> getStemmedTokensDocumentList()
	{
		List<String> builder = new ArrayList<String>();
		for (PhraseModel pm : phraseModelList)
			for (String stemm : pm.getStemmedSentenceList())
				builder.add(stemm);
		return builder;
	}

	/**
	 * @return String with all the sentences (Token, POS, Lemma, Stemm)
	 */
	public String toString()
	{
		StringBuffer builder = new StringBuffer();
		for (PhraseModel pm : phraseModelList)
			builder.append(pm.toString() + "\n");
		return builder.toString();
	}

	/**
	 * 
	 * @return String with all the sentences, one per line, with the tokens, taggs, lemmas, stemms and stopwords.
	 *
	 */
	public String getProcessedDocument()
	{
		StringBuffer builder = new StringBuffer();

		for (PhraseModel pm : phraseModelList)
		{
			if (verify(pm.getTokenisedSentenceList(), pm.getPOSTaggedSentenceList(), pm.getLemmatisedSentenceList(), pm.getStemmedSentenceList()))
			{
				builder.append("<TOKEN>" + pm.getTokenisedSentence() + "\n");
				builder.append("<TAGG>" + pm.getPOSTaggedSentence() + "\n");
				builder.append("<LEMMA>" + pm.getLemmatisedSentence() + "\n");
				builder.append("<STEMM>" + pm.getStemmedSentence() + "\n");
				builder.append("<STOP>" + pm.getStopwordSentence() + "\n");
			}
		}
		return builder.toString();
	}

	/**
	 * Verifies if everything is ok. It happened with the Europarl that in some sentences the sizes did not match.
	 * 
	 * @param tokens
	 * @param pos
	 * @param lemmas
	 * @param stemms
	 * @return true if everything is ok
	 */
	private boolean verify(List<String> tokens, List<String> pos, List<String> lemmas, List<String> stemms)
	{
		double average = (tokens.size() + pos.size() + lemmas.size() + stemms.size()) / 4.0;
		if (Math.abs(average - (double) tokens.size()) > 0.0001)
		{
			System.err.println("Error POSTagging! (class DocumentModel) ");
			System.err.println("\t"+tokens.size() + ":" + tokens.toString());
			System.err.println("\t"+pos.size() + ":" + pos.toString());
			System.err.println("\t"+lemmas.size() + ":" + lemmas.toString());
			System.err.println("\t"+stemms.size() + ":" + stemms.toString());
			return false;
		}
		return true;
	}
}
