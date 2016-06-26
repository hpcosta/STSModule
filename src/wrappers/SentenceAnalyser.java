/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package wrappers;

import java.util.List;

import nlp.NLPManager;
import nlp.ner.MyNER;
import constantsfilesfolders.Constants;
import corporamodel.corpus.documents.sentences.PhraseModel;
import corporamodel.corpus.documents.sentences.tokens.TokenModel;

/**
 * This allows to process a sentence. It incorporates several NLP tasks, such as Postagging, Lemmatisation, Stemming and a stopword checker.
 * Right now, it only works for English, Spanish and Portuguese.
 * 
 * @author Hernani Costa
 *
 * @version 0.1/2014
 * @version 0.2/2015
 */
public class SentenceAnalyser
{
	private NLPManager nlpManager = null;

	/**
	 * Default constructor. Only works for Spanish, Portuguese and English!
	 */
	public SentenceAnalyser(String language)
	{
		if (!(language.equalsIgnoreCase(Constants.EN) || language.equalsIgnoreCase(Constants.IT) || language.equalsIgnoreCase(Constants.DE)
				|| language.equalsIgnoreCase(Constants.ES) || language.equalsIgnoreCase(Constants.PT)))
		{
			System.err.println("It only works for English, Spanish and Portuguese! (SentenceAnalyser)");
			return;
		} else
		{
			nlpManager = new NLPManager(language);
		}
	}

	/**
	 * Process a sentence and returns its PhraseModel. Automatically tokenises, postaggs, lemmatises, stemms and checks for stopwords.
	 * 
	 * @param rawSentence
	 *           - raw sentence
	 * @return a PhraseModel
	 */
	public PhraseModel getPhraseModel(String rawSentence)
	{
		PhraseModel phraseModel = new PhraseModel();

		List<String> token = nlpManager.getTokenisedSentenceList(rawSentence);
		List<String> pos = nlpManager.getTaggedSentenceList(rawSentence);
		List<String> lemma = nlpManager.getLemmatizedSentenceList(rawSentence);
		List<String> stemm = nlpManager.getStemmedSentenceList(rawSentence);
		List<Boolean> stopwords = nlpManager.getStopwordCheckerList(lemma);

		int n_pos = pos.size();
		int n_lemma = lemma.size();
		int n_stemm = stemm.size();
		double average = (double) (n_pos + n_lemma + n_stemm) / 3.0;

		if ((average - (double) n_pos) > 0.0000001)
		{
			System.err.println("ERROR in the SentenceAnalyser! ");
			System.err.println("\t" + pos.toString());
			System.err.println("\t" + lemma.toString());
			System.err.println("\t" + stemm.toString());
			System.err.println("\t > N_pos:" + n_pos + " N_lemmas:" + n_lemma + " N_stems:" + n_stemm);
			return null;
		} else
		{
			TokenModel t_model = null;
			for (int i = 0; i < pos.size(); i++)
			{
				t_model = new TokenModel(token.get(i), pos.get(i), lemma.get(i), stemm.get(i));
				t_model.setIsStopWord(stopwords.get(i));
				phraseModel.addTokenModelObject(t_model);
			}
			//System.err.println(phraseModel.toStringSentencePosTagLemmaStemm());
			return phraseModel;
		}
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		demoNLP();
	}

	/**
	 * ================== Testing SentenceAnalyser ==================
	 */
	public static void demoNLP()
	{
		String rawTextSP = "", rawTextEN = "", rawTextPT = "";
		rawTextSP = "En 1851 las aguas fueron declaradas de utilidad publica por Real Orden.";
		rawTextEN = "Thanks for this, I just tested this solution and this works fine!";
		rawTextPT = "Trabalhar em demasia, principalmente quando tal é sinónimo de horas extra, pode ter graves prejuízos para a saúde.";

		// String rawTextES1 =
		// "San Vicente del Raspeig (en valenciano y cooficialmente, Sant Vicent del Raspeig) es un municipio español situado en el noroeste del área metropolitana de Alicante, de la provincia de Alicante, en la Comunidad Valenciana (España). Cuenta con 55.781 habitantes (INE 2013).";
		// String rawTextES2 =
		// "En el término municipal de San Vicente del Raspeig se encuentra, desde su fundación en 1979, el campus de la Universidad de Alicante. ";
		// String rawTextEN1 =
		// "Thanks you for this Sanja Stajner , I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";
		// String rawTextEN2 =
		// "I just tested this solution and this works fine at London o! Michael Jordan is a professor at University of Malaga .";

		SentenceAnalyser sa = null;
		PhraseModel phraseModel = null;
		MyNER ner = null;

		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		/**************************** SPANISH ****************************/
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		System.out.println("\n>>>>>>>>>>>>>>>>>>> SP: ");
		System.out.println(rawTextSP);

		sa = new SentenceAnalyser(Constants.ES);
		phraseModel = sa.getPhraseModel(rawTextSP);

		/*****************************************************************/
		/** How to get a the sentence in Spanish: */
		/*****************************************************************/
		System.out.println("<TOKENS> " + phraseModel.getTokenisedSentence());

		/*****************************************************************/
		/** How to get a pos tag a sentence in Spanish: */
		/*****************************************************************/
		System.out.println("<POS> " + phraseModel.getPOSTaggedSentence());

		/*****************************************************************/
		/** How to lemmatise a sentence in Spanish: */
		/*****************************************************************/
		System.out.println("<LEMMA> " + phraseModel.getLemmatisedSentence());

		/*****************************************************************/
		/** How to stemm a sentence in Spanish: */
		/*****************************************************************/
		System.out.println("<STEMM> " + phraseModel.getStemmedSentence());

		/*****************************************************************/
		/** How check if a word is a stopword in Spanish: */
		/*****************************************************************/
		System.out.println("<SWORDS> " + phraseModel.getStopwordSentence());

		/*****************************************************************/
		/** How to find Common Entities in Spanish: */
		/*****************************************************************/
		ner = new MyNER(Constants.ES);
		System.out.println(ner.getNamedEntities(phraseModel.getTokenisedSentenceList()));

		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		/**************************** ENGLISH ****************************/
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		System.out.println("\n>>>>>>>>>>>>>>>>>>> EN: ");
		System.out.println(rawTextEN);

		sa = new SentenceAnalyser(Constants.EN);
		phraseModel = sa.getPhraseModel(rawTextEN);

		/*****************************************************************/
		/** How to get a the sentence in English: */
		/*****************************************************************/
		System.out.println("<TOKENS> " + phraseModel.getTokenisedSentence());

		/*****************************************************************/
		/** How to get a pos tag a sentence in English: */
		/*****************************************************************/
		System.out.println("<POS> " + phraseModel.getPOSTaggedSentence());

		/*****************************************************************/
		/** How to lemmatise a sentence in English: */
		/*****************************************************************/
		System.out.println("<LEMMA> " + phraseModel.getLemmatisedSentence());

		/*****************************************************************/
		/** How to stemm a sentence in English: */
		/*****************************************************************/
		System.out.println("<STEMM> " + phraseModel.getStemmedSentence());

		/*****************************************************************/
		/** How check if a word is a stopword in English: */
		/*****************************************************************/
		System.out.println("<SWORDS> " + phraseModel.getStopwordSentence());

		/*****************************************************************/
		/** How to find Common Entities in English: */
		/*****************************************************************/
		ner = new MyNER(Constants.EN);
		System.out.println(ner.getNamedEntities(phraseModel.getTokenisedSentenceList()));

		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		/**************************** PORTUGUESE *************************/
		/*****************************************************************/
		/*****************************************************************/
		/*****************************************************************/
		System.out.println("\n>>>>>>>>>>>>>>>>>>> PT: ");
		System.out.println(rawTextPT);

		sa = new SentenceAnalyser(Constants.PT);
		phraseModel = sa.getPhraseModel(rawTextPT);

		/*****************************************************************/
		/** How to get a the sentence in Portuguese: */
		/*****************************************************************/
		System.out.println("<TOKENS> " + phraseModel.getTokenisedSentence());

		/*****************************************************************/
		/** How to get a pos tag a sentence in Portuguese: */
		/*****************************************************************/
		System.out.println("<POS> " + phraseModel.getPOSTaggedSentence());

		/*****************************************************************/
		/** How to lemmatise a sentence in Portuguese: */
		/*****************************************************************/
		System.out.println("<LEMMA> " + phraseModel.getLemmatisedSentence());

		/*****************************************************************/
		/** How to stemm a sentence in Portuguese: */
		/*****************************************************************/
		System.out.println("<STEMM> " + phraseModel.getStemmedSentence());

		/*****************************************************************/
		/** How check if a word is a stopword in Portuguese: */
		/*****************************************************************/
		System.out.println("<SWORDS> " + phraseModel.getStopwordSentence());

	}
}
