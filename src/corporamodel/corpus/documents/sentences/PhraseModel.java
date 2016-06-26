/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel.corpus.documents.sentences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import corporamodel.corpus.documents.sentences.tokens.TokenModel;

/**
 * This class wraps all the information related with a sentence, i.e. it has all the information related with all the words within the sentence (lemma, POS, stemm and token). 
 * A sentence is represented as a arraylist of @TokenModel list. This class also allows to retrieve both all the @TokenModel that are stopwords and all the @TokenModel that are whitewords.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2013
 */
public class PhraseModel
{
	private List<TokenModel> tokenModelObjectList = null;
	private List<TokenModel> stopwordList = null;
	private List<TokenModel> whitewordList = null;
	private int sentenceID = 0;

	/**
	 * Default constructor
	 */
	public PhraseModel()
	{
		this.tokenModelObjectList = new ArrayList<TokenModel>();
	}

	/**
	 * @return the sentenceID
	 */
	public int getSentenceID()
	{
		return sentenceID;
	}

	/**
	 * @param sentenceID
	 *           the sentenceID to set
	 */
	public void setSentenceID(int sentenceID)
	{
		this.sentenceID = sentenceID;
	}

	/**
	 * 
	 * @return the sentence ID
	 */
	public String getSentenceIDString()
	{
		return ">sentenceID: " + getSentenceID() + "\n";
	}

	/**
	 * Adds a new TokenModel to the tokenModelList
	 * 
	 * @param tokenModelObject
	 */
	public void addTokenModelObject(TokenModel tokenModelObject)
	{
		tokenModelObjectList.add(tokenModelObject);
	}

	/**
	 * Remove a TokenModel from the tokenModelList
	 * 
	 * @param TokenModel
	 *           to be removed
	 */
	public void removeTokenModelObject(TokenModel tokenModel)
	{
		tokenModelObjectList.remove(tokenModel);
	}

	/**
	 * Returns all the {@link TokenModel} in a list
	 * 
	 * @return the tokenModel List
	 */
	public List<TokenModel> getTokenModelObjectsList()
	{
		return tokenModelObjectList;
	}

	/**
	 * The PosTagged Sentence
	 * 
	 * @return the tagged sentence
	 */
	public String getPOSTaggedSentence()
	{
		StringBuffer posTaggedSentence = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			posTaggedSentence.append(tm.getPosTagObject().getPosTag() + " ");
		return posTaggedSentence.toString();
	}

	/**
	 * Returns the tags in a list format.
	 * 
	 * @return the tagged sentence in a List format
	 */
	public List<String> getPOSTaggedSentenceList()
	{
		List<String> posTaggedSentenceList = new ArrayList<String>();
		for (TokenModel tm : tokenModelObjectList)
			posTaggedSentenceList.add(tm.getPosTagObject().getPosTag());
		return posTaggedSentenceList;
	}

	/**
	 * Returns the tags in a hasmap format (Token, PosTag).
	 * 
	 * @return the tagged sentence in a HasMap format (Token, PosTag)
	 */
	public HashMap<String, String> getPOSTaggedSentenceHashMap()
	{
		HashMap<String, String> posTaggedSentenceMap = new HashMap<String, String>();

		for (TokenModel tm : tokenModelObjectList)
			posTaggedSentenceMap.put(tm.getTokenObject().getToken(), tm.getPosTagObject().getPosTag());
		return posTaggedSentenceMap;
	}

	/**
	 * Returns the lemmatised sentence in a list
	 * 
	 * @return the lemmatised sentence in a List format
	 */
	public List<String> getLemmatisedSentenceList()
	{
		List<String> lemmatisedSentenceList = new ArrayList<String>();

		for (TokenModel tm : tokenModelObjectList)
			lemmatisedSentenceList.add(tm.getLemmaObject().getLemma());
		return lemmatisedSentenceList;
	}

	/**
	 * Returns the lemmatised sentence in a HashMap (Token, Lemma)
	 * 
	 * @return the lemmatised sentence in a HashMap format (Token, Lemma)
	 */
	public HashMap<String, String> getLemmatisedSentenceHashMap()
	{
		HashMap<String, String> lemmatisedSentenceMap = new HashMap<String, String>();

		for (TokenModel tm : tokenModelObjectList)
			lemmatisedSentenceMap.put(tm.getTokenObject().getToken(), tm.getLemmaObject().getLemma());
		return lemmatisedSentenceMap;
	}

	/**
	 * The lemmatised sentence
	 * 
	 * @return the lemmatised sentence
	 */
	public String getLemmatisedSentence()
	{
		StringBuffer lemmaSentence = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			lemmaSentence.append(tm.getLemmaObject().getLemma() + " ");
		return lemmaSentence.toString();
	}

	/**
	 * Returns the tokenised sentence in a list
	 * 
	 * @return a list with all the tokens
	 */
	public List<String> getTokenisedSentenceList()
	{
		List<String> tokens = new ArrayList<String>();
		for (TokenModel tm : tokenModelObjectList)
			tokens.add(tm.getTokenObject().getToken());
		return tokens;
	}


	/**
	 * Returns the tokenised sentence in a array
	 * 
	 * @return a array with all the tokens
	 */
	public String[] getTokenisedSentenceArray()
	{
		List<String> tokens = new ArrayList<String>();
		for (TokenModel tm : tokenModelObjectList)
			tokens.add(tm.getTokenObject().getToken());
		return tokens.toArray(new String[tokens.size()]);
	}
	
	/**
	 * Returns the tokenised sentence
	 * 
	 * @return tokenised sentence
	 */
	public String getTokenisedSentence()
	{
		StringBuffer tokens = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			tokens.append(tm.getTokenObject().getToken() + " ");
		return tokens.toString();
	}

	/**
	 * The stemmed sentence
	 * 
	 * @return the stemm sentence
	 */
	public String getStemmedSentence()
	{
		StringBuffer lemmaSentence = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			lemmaSentence.append(tm.getStemmObject().getStemm() + " ");
		return lemmaSentence.toString();
	}

	/**
	 * The stemmed sentence in a list format
	 * 
	 * @return a list with all the stemms
	 */
	public List<String> getStemmedSentenceList()
	{
		List<String> lemmaSentence = new ArrayList<String>();
		for (TokenModel tm : tokenModelObjectList)
			lemmaSentence.add(tm.getStemmObject().getStemm());
		return lemmaSentence;
	}

	/**
	 * Returns a List of TokenModels that are stopwords.
	 * 
	 * @return a list of stopwords
	 */
	public List<TokenModel> getStopwordList()
	{
		if (stopwordList == null)
		{
			stopwordList = new ArrayList<TokenModel>();
			for (TokenModel tm : tokenModelObjectList)
				if (tm.isStopWord()) stopwordList.add(tm);
		}
		return stopwordList;
	}

	/**
	 * 
	 * @return the stopword sentence
	 */
	public String getStopwordSentence()
	{
		StringBuffer stopwordSentence = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			stopwordSentence.append(tm.isStopWord() + " ");
		return stopwordSentence.toString();
	}

	/**
	 * Returns a List of TokenModels that are not stopwords.
	 * 
	 * @return a list of white words
	 */
	public List<TokenModel> getWhitewordList()
	{
		if (whitewordList == null)
		{
			whitewordList = new ArrayList<TokenModel>();
			for (TokenModel tm : tokenModelObjectList)
				if (!tm.isStopWord()) whitewordList.add(tm);
		}
		return whitewordList;
	}

	public String toString()
	{
		StringBuffer builder = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			builder.append(tm.toString() + " ### ");
		return builder.toString();
	}

	public String toStringLinguamatics()
	{
		StringBuffer builder = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			builder.append(tm.toStringLinguamatics() + " ");
		return builder.toString();
	}
	
	/**
	 * Return all information separated by "|||"
	 * 
	 * @return the sentencePosTagLemmaStemm (token###posTag###lemma###stemm|||token###posTag###lemma###stemm)
	 */
	public String toStringSentencePosTagLemmaStemm()
	{
		StringBuffer all = new StringBuffer();
		for (TokenModel tm : tokenModelObjectList)
			all.append(tm.getAll() + "|||");
		return all.toString();
	}

}
