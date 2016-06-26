/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package nlp.postagger.treetagger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nlp.tokenizer.MyTokenizer;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

import constantsfilesfolders.Constants;
import corporamodel.corpus.documents.sentences.PhraseModel;
import corporamodel.corpus.documents.sentences.tokens.Lemma;
import corporamodel.corpus.documents.sentences.tokens.POStag;
import corporamodel.corpus.documents.sentences.tokens.Token;
import corporamodel.corpus.documents.sentences.tokens.TokenModel;

/**
 * @author Hernani Costa
 * 
 * @version 0.3/2014
 */
public class TreeTaggerManager
{
	private String language = "";
	private TreeTaggerLoadLanguageResources llr = null;
	private MyTokenizer myTokenizer = null;
	private PhraseModel phraseModel = null;

	/**
	 * Default constructor.
	 * 
	 * @param rawText
	 */
	public TreeTaggerManager(String language)
	{
		this.language = language;
		myTokenizer = new MyTokenizer(language);
		System.err.println("PosTagger");
		if (language.equalsIgnoreCase(Constants.EN) || language.equalsIgnoreCase(Constants.ES) || language.equalsIgnoreCase(Constants.PT)
				|| language.equalsIgnoreCase(Constants.RU) || language.equalsIgnoreCase(Constants.IT) || language.equalsIgnoreCase(Constants.FR)
				|| language.equalsIgnoreCase(Constants.DE))
		{
			System.err.println("\t>Loading " + language + " PosTagger model!");
			this.llr = new TreeTaggerLoadLanguageResources(language);
		} else
		{
			System.err
					.println("> Warning in the TreeTaggerManager, the TreeTagger only has models to EN, ES, PT, RU, IT, FR and DE! See the Constants class.\n\t>Loading PosTagger EN model by default!!!");
			this.llr = new TreeTaggerLoadLanguageResources(Constants.EN);
		}

	}

	/**
	 * Returns a PhraseModel, which contains the tokens, lemmas and posTaggs.
	 * 
	 * @param rawSentence
	 *           sentence to be tagged
	 * @return a {@link PhraseModel}
	 */
	public void tagSentence(String rawSentence)
	{
		try
		{
			phraseModel = sentencePOSTagger(rawSentence, language);
		} catch (IOException e)
		{
			System.err.println("Error in the TreeTaggerManager!");
			e.printStackTrace();
		} catch (TreeTaggerException e)
		{
			System.err.println("Error in the TreeTaggerManager!");
			e.printStackTrace();
		}

	}

	public PhraseModel getPhraseModel()
	{
		return phraseModel;
	}

	/**
	 * 
	 * @param sentence
	 *           - sentence to be tagged
	 * @param language
	 * @return a PhraseModel
	 * @throws IOException
	 * @throws TreeTaggerException
	 */
	@SuppressWarnings("finally")
	private PhraseModel sentencePOSTagger(String sentence, String language) throws IOException, TreeTaggerException
	{
		PhraseModel phraseModel = new PhraseModel();
		// System.out.println(sentence + " " + language);
		/*
		 * TokenModel tokenModel = null; Point TT4J to the TreeTagger installation directory. The executable is expected in the "bin"
		 * subdirectory - in this example at "/opt/treetagger/bin/tree-tagger"
		 */
		TreeTaggerWrapper<String> treeTaggerWrapper = new TreeTaggerWrapper<String>();
		final List<String> tokenList = new ArrayList<String>();
		final List<String> tagList = new ArrayList<String>();
		final List<String> lemmaList = new ArrayList<String>();

		try
		{
			treeTaggerWrapper.setModel(llr.getPosTaggerModelIn());
			treeTaggerWrapper.setHandler(new TokenHandler<String>()
			{
				public void token(String _token, String _posTag, String _lemma)
				{
					/** test purposes */
					// System.out.println("-->" + _token + " " + _posTag + " " + _lemma);
					// tagLemme.add(_token + "_" + _posTag + "_" + _lemma);
					tokenList.add(_token);
					tagList.add(_posTag);
					lemmaList.add(_lemma);
					// System.out.println(token + "\t" + pos + "\t" + lemma);
				}
			});
			// String str =
			// "La expresi�n latina Salutem per aquam (salud por el agua). La utilizaci�n como sigla conllevar�a el uso de may�scula en todas las letras (SPA), pero la RAE recomienda utilizar las siglas convertidas en acr�nimos (las m�s usuales) como cualquier otra palabra, en min�scula.";
			// String str = "ol� o meu nome � Hernani e j� fui a Viseu.";
			// Tokens t = new Tokens();
			// String s[] = tokensDetector(sentence);

			// tt.process(asList(s));
			treeTaggerWrapper.process((ArrayList<String>) myTokenizer.getTokenisedSentenceList(sentence));

			Token token = null;
			POStag posTag = null;
			Lemma lemma = null;
			TokenModel tokenModel = null;

			// System.out.println("token Model:");

			for (int i = 0; i < tokenList.size(); i++)
			{
				// String lem = lemmaList.get(i);
				// String ta = tagList.get(i);
				// String to = tokenList.get(i);
				// System.out.println(to+"-"+ta+"-"+lem);

				token = new Token(tokenList.get(i).trim());
				posTag = new POStag(tagList.get(i).trim());
				lemma = new Lemma(lemmaList.get(i).trim());
				tokenModel = new TokenModel(token, posTag, lemma);
				phraseModel.addTokenModelObject(tokenModel);
			}

		} finally
		{
			treeTaggerWrapper.destroy();
			return phraseModel;
		}
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
		TreeTaggerManager da = new TreeTaggerManager(Constants.ES);
		da.tagSentence(rawText);
		System.out.println("Sentences: ");
		System.out.println(da.getPhraseModel().toString());
	}

}
