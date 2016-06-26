/**
 * @author Hernani Costa
 * iCorpora
 * EXPERT (EXPloiting Empirical appRoaches to Translation)
 * ESR3 - Collection & preparation of multilingual data for multiple corpus-based approaches to translation
 * Department of Translation and Interpreting 
 * Faculty of Philosophy and Humanities 
 *
 * Copyright (c) 2013-2016 University of Malaga, Spain
 * All rights reserved.
 */
package corporamodel.corpus.documents.sentences.tokens;

/**
 * @author Hernani Costa
 * 
 * @version 0.1/2013
 */
public class Lemma {
	private String lemma = "";

	/**
	 * Default Constructor
	 */
	public Lemma() {
		super();
	}

	/**
	 * @param lemma
	 */
	public Lemma(String lemma) {
		super();
		this.lemma = lemma.trim();
	}

	/**
	 * @return the lemma
	 */
	public String getLemma() {
		return lemma;
	}

	/**
	 * @param lemma
	 *            the lemma to set
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma.trim();
	}

	/**
	 * @return the lemma
	 */
	public String toString(){
		return lemma;
	}
}
