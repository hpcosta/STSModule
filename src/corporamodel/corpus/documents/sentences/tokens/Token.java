/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package corporamodel.corpus.documents.sentences.tokens;

/**
 * @author Hernani Costa
 * 
 * @version 0.1/2013
 */
public class Token
{
	private String token = "";

	/**
	 * Default Constructor
	 */
	public Token()
	{
		super();
	}

	/**
	 * @param token
	 */
	public Token(String token)
	{
		super();
		this.token = token.trim();
	}

	/**
	 * @return the token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * @param token
	 *                the token to set
	 */
	public void setToken(String token)
	{
		this.token = token;
	}
}
