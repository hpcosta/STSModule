/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package constantsfilesfolders;

import java.nio.file.Paths;

/**
 * This class contains the constants for the project.
 * 
 * Please check the 'resources' directory. Additionally, make sure that the 'config' folder has the right paths to the semantic signatures
 * in the folder 'resources/adwResources' (I had put the folder outside the project folder due to the repository limits - it only allows to
 * push projects up to 2GB).
 *
 * 
 */
/**
 * @author Hernani Costa
 * 
 * @version 0.1/2014
 */
public class Constants
{
	public static final String INTERNAL_RESOURCES = System.getProperty("user.dir") + "/internalResources";

	public static final String EXTERNAL_RESOURCES = Paths.get(System.getProperty("user.dir")).getParent().toString() + "/externalResources";

	/**
	 * TAGS
	 */
	public static final String TAG_TOKEN = "<TOKEN>";
	public static final String TAG_TAG = "<TAGG>";
	public static final String TAG_LEMMA = "<LEMMA>";
	public static final String TAG_STEMM = "<STEMM>";
	public static final String TAG_STOP = "<STOP>";

	/**
	 * CSV
	 */
	public static final char CSV_COLUMN_SEP = ';';
	public static final char CSV_COLUMN_SEP_READER = ';';

	/**
	 * Name of the semantic measures used by the ADW (http://lcl.uniroma1.it/adw/)
	 */
	public static final String MEASURE_DIS = "_dis_";
	public static final String MEASURE_WEIGHTEDOVERLAP = "weightedOverlap"; // max 1; min 0
	public static final String MEASURE_JACCARD = "jaccard"; // max 1; min 0
	public static final String MEASURE_COSINE = "cosine"; // max 1; min 0
	public static final String MEASURE_JENSENSHANNON = "jensenShannon"; // max 0; min ?
	public static final String MEASURE_KLDIVERGENCE = "klDivergence"; // max 0; min ?

	/**
	 * Name of the similarity measures implemented by me
	 */
	public static final String MEASURE_NUMBEROFCOMMONLEMMAS = "ncommonlemmas"; // min 0; max ?
	public static final String MEASURE_NUMBEROFCOMMONSTEMMS = "ncommonsteems"; // min 0; max ?
	public static final String MEASURE_NUMBEROFCOMMONTOKENS = "ncommontokens"; // min 0; max ?
	public static final String MEASURE_LEMMAS = "lemmas_"; // min 0; max ?
	public static final String MEASURE_STEMMS = "steems_"; // min 0; max ?
	public static final String MEASURE_TOKENS = "tokens_"; // min 0; max ?

	public static final String MEASURE_CHISQUARE = "chisquare"; // max 0; min ?
	public static final String MEASURE_SCC = "scc"; // max 1; min 0

	/**
	 * OpenNLP Models for Named Entity Recognition in Spanish and English Models available here: http://opennlp.sourceforge.net/models-1.5/
	 */
	// english ner models for
	public static final String EN_NER_DATE = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-date.bin";
	public static final String EN_NER_LOCATION = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-location.bin";
	public static final String EN_NER_MONEY = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-money.bin";
	public static final String EN_NER_ORGANIZATION = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-organization.bin";
	public static final String EN_NER_PERCENTAGE = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-percentage.bin";
	public static final String EN_NER_PERSON = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-person.bin";
	public static final String EN_NER_TIME = INTERNAL_RESOURCES + "/opennlpmodels/en-ner-time.bin";
	// spanish ner models
	public static final String ES_NER_LOCATION = INTERNAL_RESOURCES + "/opennlpmodels/es-ner-location.bin";
	public static final String ES_NER_ORGANIZATION = INTERNAL_RESOURCES + "/opennlpmodels/es-ner-organization.bin";
	public static final String ES_NER_MISC = INTERNAL_RESOURCES + "/opennlpmodels/es-ner-misc.bin";
	public static final String ES_NER_PERSON = INTERNAL_RESOURCES + "/opennlpmodels/es-ner-person.bin";

	/**
	 * OpenNLP Models for Parsing English
	 */
	// english parser
	public static final String EN_PARSER = INTERNAL_RESOURCES + "/opennlpmodels/en-parser-chunking.bin";

	/**
	 * OpenNLP Models for Sentence Splitter and Tokenizer in English
	 */
	// model for sentence detection (this model needs to be inside the SRC folder, there is no other way!)
	public static final String EN_SENTENCE_DETECTOR = "/srcResources/openNLP/sentencesplitermodels/en-sent.bin";
	public static final String PT_SENTENCE_DETECTOR = "/srcResources/openNLP/sentencesplitermodels/pt-sent.bin";
	// model to tokenize sentences (this model needs to be inside the SRC folder, there is no other way!)
	public static final String EN_TOKENIZER = "/srcResources/openNLP/tokenizermodels/en-token.bin";
	public static final String PT_TOKENIZER = "/srcResources/openNLP/tokenizermodels/pt-token.bin";

	/**
	 * TREETAGGER main folder - directory in your computer
	 */
	// public static final String TREETAGGER_PATH = "/Users/hpcosta/TreeTagger";
	public static String TREETAGGER_PATH = INTERNAL_RESOURCES + "/TreeTagger";
	public static final String TREETAGGER_PROPERTIES = System.getProperty("user.dir") + "/config/treetagger.properties";

	/**
	 * TREETAGGER models - directory in your computer
	 */
	// POS Tagger Model for Portuguese
	public static String TREETAGGER_PT_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/pt.par:iso8859-1";
	// POS Tagger Model for Spanish
	public static String TREETAGGER_ES_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/spanish.par:iso8859-1";
	// POS Tagger Model for English
	public static String TREETAGGER_EN_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/english.par:iso8859-1";
	// POS Tagger Model for French
	public static String TREETAGGER_FR_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/french.par:iso8859-1";
	// POS Tagger Model for German
	public static String TREETAGGER_DE_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/german.par:iso8859-1";
	// POS Tagger Model for Italian
	public static String TREETAGGER_IT_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/italian-utf8.par:utf8";
	// POS Tagger Model for Russian
	public static String TREETAGGER_RU_POSTAGGERMODEL = Constants.TREETAGGER_PATH + "/models/russian.par:utf8";

	/**
	 * LANGUAGES
	 */
	// English language
	public static final String EN = "EN";
	// Spanish language
	public static final String ES = "ES";
	// Portuguese language
	public static final String PT = "PT";
	// French language
	public static final String FR = "FR";
	// German language
	public static final String DE = "DE";
	// Italian language
	public static final String IT = "IT";
	// Russian language
	public static final String RU = "RU";


	/**
	 * STOPWORDS LISTS
	 */
	// English language
	public static final String STOPWORDSFILE_EN = INTERNAL_RESOURCES + "/stopwords/EN-stopwords.txt";
	// Spanish language
	public static final String STOPWORDSFILE_ES = INTERNAL_RESOURCES + "/stopwords/SP-stopwords.txt";
	// Portuguese language
	public static final String STOPWORDSFILE_PT = INTERNAL_RESOURCES + "/stopwords/PT-stopwords.txt";
	// Italian language
	public static final String STOPWORDSFILE_IT = INTERNAL_RESOURCES + "/stopwords/IT-stopwords.txt";
	// German language
	public static final String STOPWORDSFILE_DE = INTERNAL_RESOURCES + "/stopwords/DE-stopwords.txt";

	/**
	 * STEMMERS, POSTAGGERS, TOKENIZER, SENTENCESPLITER
	 */
	public static final String STEMMER_SNOWBALL = "snowball";
	public static final String POSTAGGER_TREETAGGER = "treetagger";
	public static final String TOKENIZER_OPENNLP = "opennlptokenizer";
	public static final String SENTENCESPLITTER_OPENNLP = "opennlpsentencesplitter";

	/**
	 * ECODINGS: Supported Encodings: http://docs.oracle.com/javase/1.5.0/docs/guide/intl/encoding.doc.html
	 */
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_ISO88591 = "ISO-8859-1";
	public static final String ENCODING_MACROMAN = "MacRoman";
	// Windows encoding
	public static final String ENCODING_CP1252 = "Cp1252";

}
