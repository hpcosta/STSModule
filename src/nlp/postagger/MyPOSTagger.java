/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.postagger;

import java.util.List;

import constantsfilesfolders.Constants;
import corporamodel.corpus.documents.sentences.PhraseModel;
import nlp.postagger.treetagger.TreeTaggerManager;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class MyPOSTagger
{
	private TreeTaggerManager treeTaggerManager = null;
	private String language = "";
	private PhraseModel phraseModel = null;

	/**
	 * 
	 */
	public MyPOSTagger(String language)
	{
		this.language = language;
		usingTreeTagger();
	}

	/**
	 * Default constructor.
	 * 
	 * @param posTaggerName
	 *                POS Tagger Name - Default TreeTagger
	 * @param language
	 *                language available for: TreeTagger (Spanish, English, Portuguese, Italian, German, Russian and French).
	 */
	public MyPOSTagger(String posTaggerName, String language)
	{
		this.language = language;

		if (posTaggerName.equalsIgnoreCase(Constants.POSTAGGER_TREETAGGER))
		{
			usingTreeTagger();
		} else
		{
			System.err.println("The only POS Tagger available right now is the TreeTagger! See Constants class.\n Using TreeTagger by default!!!");
			usingTreeTagger();
		}
	}

	private void usingTreeTagger()
	{
		treeTaggerManager = new TreeTaggerManager(language);
	}

	/**
	 * Receives a sentence to be tagged and returns a list with tags.
	 * 
	 * @param rawSentence
	 *                - sentence to be tagged
	 */
	public void taggSentence(String rawSentence)
	{
		treeTaggerManager.tagSentence(rawSentence);
		phraseModel = treeTaggerManager.getPhraseModel();

	}

	public List<String> getTaggedSentenceList()
	{
		return phraseModel.getPOSTaggedSentenceList();
	}

	public PhraseModel getPhraseModel()
	{
		return phraseModel;
	}

	/**
	 * ================== For test purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// System.out.println(System.getProperty("user.dir") + "/src/resources");
		String rawText = "En 1851 las aguas fueron declaradas de utilidad publica por Real Orden. Desde entonces, llegan enfermos de todas las latitudes a tomar estas aguas y sanar sus dolencias y males, obteniendo siempre un extraordinario resultado. El edificio del Balneario de Carratraca fue mandado construir por Fernando VII e inaugurado en el año 1855, siendo su estilo neoclásico y construido en piedra arenisca y marmol. ";
		// String documentName = "a001";
		MyPOSTagger myPosTagger = new MyPOSTagger(Constants.ES);
		myPosTagger.taggSentence(rawText);
		System.out.println("Sentences: ");
		System.out.println(myPosTagger.getPhraseModel().toStringLinguamatics());

		// System.out.println(System.getProperty("user.dir") + "/src/resources");
		String text = "SPA HOTEL SERCOTEL VALLE DEL ESTE";
		// String documentName = "a001";
		MyPOSTagger myPosTaggerEN = new MyPOSTagger(Constants.EN);
		myPosTaggerEN.taggSentence(text);
		System.out.println("Sentences: ");
		System.out.println(text);
		System.out.println(myPosTaggerEN.getPhraseModel().getPOSTaggedSentence());
		System.out.println(myPosTaggerEN.getPhraseModel().getLemmatisedSentence());
	}
}
