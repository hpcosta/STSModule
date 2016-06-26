/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel.corpus.documents.sentences.tokens;

/**
 * This class encapsulates the lemma, POS Tag, stemm and the token.
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2013
 */
public class TokenModel
{
	private Lemma lemmaObject = null;
	private POStag posTagObject = null;
	private Stemm stemmObject = null;
	private Token tokenObject = null;
	private boolean isStopWord = false;

	/**
	 * Default constructor
	 */
	public TokenModel()
	{
		super();
	}

	/**
	 * @param tokenObject
	 * @param posTagObject
	 * @param lemmaObject
	 */
	public TokenModel(Token tokenObject, POStag posTagObject, Lemma lemmaObject)
	{
		super();
		this.tokenObject = tokenObject;
		this.posTagObject = posTagObject;
		this.lemmaObject = lemmaObject;
	}

	/**
	 * @param token
	 * @param posTag
	 * @param lemma
	 */
	public TokenModel(String token, String posTag, String lemma)
	{
		super();
		this.tokenObject = new Token(token);
		this.posTagObject = new POStag(posTag);
		this.lemmaObject = new Lemma(lemma);
	}

	/**
	 * @param tokenObject
	 * @param posTagObject
	 * @param lemmaObject
	 * @param stemmObject
	 */
	public TokenModel(Token tokenObject, POStag posTagObject, Lemma lemmaObject, Stemm stemmObject)
	{
		super();
		this.tokenObject = tokenObject;
		this.posTagObject = posTagObject;
		this.lemmaObject = lemmaObject;
		this.stemmObject = stemmObject;

	}

	/**
	 * @param token
	 * @param posTag
	 * @param lemma
	 * @param stemm
	 */
	public TokenModel(String token, String posTag, String lemma, String stemm)
	{
		super();
		this.tokenObject = new Token(token);
		this.posTagObject = new POStag(posTag);
		this.lemmaObject = new Lemma(lemma);
		this.stemmObject = new Stemm(stemm);
	}

	/**
	 * @return the isStopWord
	 */
	public boolean isStopWord()
	{
		return isStopWord;
	}

	/**
	 * @param isStopWord
	 *           the isStopWord to set
	 */
	public void setIsStopWord(boolean isStopWord)
	{
		this.isStopWord = isStopWord;
	}

	/**
	 * @return the lemma
	 */
	public Lemma getLemmaObject()
	{
		return lemmaObject;
	}

	/**
	 * @param lemmaObject
	 *           the lemma to set
	 */
	public void setLemmaObject(Lemma lemmaObject)
	{
		this.lemmaObject = lemmaObject;
	}

	/**
	 * @return the posTag
	 */
	public POStag getPosTagObject()
	{
		return posTagObject;
	}

	/**
	 * @param posTagObject
	 *           the posTag to set
	 */
	public void setPosTagObject(POStag posTagObject)
	{
		this.posTagObject = posTagObject;
	}

	/**
	 * @return the stemm
	 */
	public Stemm getStemmObject()
	{
		return stemmObject;
	}

	/**
	 * @param stemmObject
	 *           the stemm to set
	 */
	public void setStemmObject(Stemm stemmObject)
	{
		this.stemmObject = stemmObject;
	}

	/**
	 * @return the token
	 */
	public Token getTokenObject()
	{
		return tokenObject;
	}

	/**
	 * @param token
	 *           the token to set
	 */
	public void setTokenObject(Token token)
	{
		this.tokenObject = token;
	}

	/**
	 * Return all information separated by "###". Useful to store in a database for example.
	 * 
	 * @return token###posTag###lemma###stemm
	 */
	public String getAll()
	{
		StringBuffer builder = new StringBuffer();
		builder.append(tokenObject.getToken() + "###" + posTagObject.getPosTag() + "###" + lemmaObject.getLemma() + "###" + stemmObject.getStemm());
		return builder.toString();
	}

	/**
	 * @return a string with a word information (Lemma, POS Tag, Token and Stemm)
	 */
	public String toString()
	{
		StringBuffer builder = new StringBuffer();
		if (tokenObject != null) if (!tokenObject.getToken().equalsIgnoreCase("")) builder.append(tokenObject.getToken() + " ");
		if (posTagObject != null) if (!posTagObject.getPosTag().equalsIgnoreCase("")) builder.append(posTagObject.getPosTag() + " ");
		if (lemmaObject != null) if (!lemmaObject.getLemma().equalsIgnoreCase("")) builder.append(lemmaObject.getLemma() + " ");
		if (stemmObject != null) if (!stemmObject.getStemm().equalsIgnoreCase("")) builder.append(stemmObject.getStemm() + " ");
		builder.append(isStopWord + " ");

		return builder.toString();
	}
	
	/**
	 * @return a string with a word information (word and POS Tag)
	 */
	public String toStringLinguamatics()
	{
		StringBuffer builder = new StringBuffer();
		if (tokenObject != null) if (!tokenObject.getToken().equalsIgnoreCase("")) builder.append(tokenObject.getToken() + "/");
		if (posTagObject != null) if (!posTagObject.getPosTag().equalsIgnoreCase("")) builder.append(posTagObject.getPosTag() + " ");
		return builder.toString();
	}
}
