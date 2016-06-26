/**
 * @author Hernani Costa
 * iCorpora
 * EXPERT (EXPloiting Empirical appRoaches to Translation)
 * ESR3 - Collection & preparation of multilingual data for multiple corpus-based approaches to translation
 * Department of Translation and Interpreting 
 * Faculty of Philosophy and Humanities 
 *
 * Copyright (c) 2013-2016 University of Malaga, Spain.
 * All rights reserved.
 */
package wrappers;

import java.util.List;

import nlp.sentencespliter.MySentenceSplitter;
import constantsfilesfolders.Constants;
import corporamodel.corpus.documents.DocumentModel;
import corporamodel.corpus.documents.sentences.PhraseModel;

/**
 * This class allows to process a document. It incorporates several NLP tasks, such as Postagging, Lemmatisation, Stemming and a stopword
 * checker. Right now, it only works for English, Spanish and Portuguese.
 * 
 * @author Hernani Costa
 *
 * @version 0.1/2015
 */
public class DocumentAnalyser
{
	private MySentenceSplitter sentenceSplitter = null;
	private SentenceAnalyser sentenceAnalyser = null;

	/**
	 * Constructor
	 */
	public DocumentAnalyser(String language)
	{
		if (!(language.equalsIgnoreCase(Constants.EN) || language.equalsIgnoreCase(Constants.IT) || language.equalsIgnoreCase(Constants.DE) || language.equalsIgnoreCase(Constants.ES) || language.equalsIgnoreCase(Constants.PT)))
		{
			System.err.println("It only works for English, Spanish and Portuguese! (DocumentAnalyser)");
			return;
		} else
		{
			sentenceSplitter = new MySentenceSplitter(language);
			sentenceAnalyser = new SentenceAnalyser(language);
		}
	}

	/**
	 * Process a document and returns its DocumentModel. Automatically splitts, tokenises, postaggs, lemmatises, stemms and checks for
	 * stopwords.
	 * 
	 * @param rawDocument
	 *           - raw text
	 * @return {@link DocumentModel}
	 */
	public DocumentModel getDocumentModel(String rawDocument)
	{
		DocumentModel documentModel = new DocumentModel();
		PhraseModel phraseModel = null;
		List<String> rawSentences = getSplittedDocument(rawDocument);

		for (String _rawSentence : rawSentences)
		{
			phraseModel = sentenceAnalyser.getPhraseModel(_rawSentence);
			documentModel.addPhraseModel(phraseModel);

		}

		return documentModel;
	}

	/**
	 * Splits a document (raw text) into sentences
	 * 
	 * @param rawDocument
	 *           - receives the entire document
	 * @return - a list with all the sentences
	 */
	public List<String> getSplittedDocument(String rawDocument)
	{
		return sentenceSplitter.getSentenceSplitterList(rawDocument);
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// demoMeasures();
		demoNLP();
	}

	public static void demoNLP()
	{
		DocumentAnalyser docAnalyser = null;

		/*****************************************************************/
		/************ Finding Common Entities in Spanish *****************/
		/*****************************************************************/
		String rawTextES1 = "San Vicente del Raspeig (en valenciano y cooficialmente, Sant Vicent del Raspeig) es un municipio español situado en el noroeste del área metropolitana de Alicante, de la provincia de Alicante, en la Comunidad Valenciana (España). Cuenta con 55.781 habitantes (INE 2013).";
		String rawTextES2 = "En el término municipal de San Vicente del Raspeig se encuentra, desde su fundación en 1979, el campus de la Universidad de Alicante. ";
		// String rawTextEN1 =
		// "Thanks you for this Sanja Stajner , I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";
		// String rawTextEN2 =
		// "I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";

		docAnalyser = new DocumentAnalyser(Constants.ES);

		DocumentModel docModel = docAnalyser.getDocumentModel(rawTextES1 + " " + rawTextES2 + " " + rawTextES1);
		// System.out.println(docModel.toString());
		for (PhraseModel pModel : docModel.getPhraseModelList())
		{
			System.out.println(pModel.getTokenisedSentence());
			System.out.println(pModel.getLemmatisedSentence());
			System.out.println(pModel.getStemmedSentence());
			System.out.println(pModel.getStopwordSentence());
		}

		/*****************************************************************/
		/** Finding Common Categories in Spanish between two sentences ***/
		/*****************************************************************/
		// see MyNER
	}
}
