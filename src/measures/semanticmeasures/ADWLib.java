/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package measures.semanticmeasures;

import it.uniroma1.lcl.adw.ADW;
import it.uniroma1.lcl.adw.DisambiguationMethod;
import it.uniroma1.lcl.adw.LexicalItemType;
import it.uniroma1.lcl.adw.comparison.Cosine;
import it.uniroma1.lcl.adw.comparison.Jaccard;
import it.uniroma1.lcl.adw.comparison.JensenShannon;
import it.uniroma1.lcl.adw.comparison.KLDivergence;
import it.uniroma1.lcl.adw.comparison.SignatureComparison;
import it.uniroma1.lcl.adw.comparison.WeightedOverlap;
import measures.measureswrapper.MeasuresWrapper;
import constantsfilesfolders.Constants;

/**
 * Class that uses the Align, Disambiguate and Walk library. For more information about the library see http://lcl.uniroma1.it/adw/. It
 * requires the 'config' folder and the semantic signatures, see the README.md file for more information. Measures: {"weightedOverlap"; //
 * max 1; min 0}; {"jaccard"; // max 1; min 0}; {"cosine"; // max 1; min 0}; {"jensenShannon"; // max 0; min ?}; {"klDivergence"; // max 0;
 * min ?}
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class ADWLib
{
	private ADW pipeLine = null;

	private LexicalItemType sentence1Type = null;
	private LexicalItemType sentence2Type = null;

	private DisambiguationMethod disMethodAlignmentBased = null;
	private DisambiguationMethod disMethodNone = null;

	private SignatureComparison mWeightedOverlap = null;
	private SignatureComparison mJaccard = null;
	private SignatureComparison mCosine = null;
	private SignatureComparison mJensenShannon = null;
	private SignatureComparison mKLDivergence = null;

	private ADWMeasures adwMeasures = null;

	/**
	 * Default constructor. Only English available!
	 * 
	 * @param language
	 *           language
	 */
	public ADWLib(String language)
	{
		if (!language.equalsIgnoreCase(Constants.EN))
		{
			System.err.println("Error: Semantic Similarity Measures only work for English!!!");
			System.err.println("Please use Constants.EN as a parameter!\n");
			return;
		}

		this.pipeLine = new ADW();
		sentence1Type = LexicalItemType.SURFACE;
		sentence2Type = LexicalItemType.SURFACE;

		disMethodAlignmentBased = DisambiguationMethod.ALIGNMENT_BASED;
		disMethodNone = DisambiguationMethod.NONE;

		mWeightedOverlap = new WeightedOverlap();
		mJaccard = new Jaccard();
		mCosine = new Cosine();
		mJensenShannon = new JensenShannon();
		mKLDivergence = new KLDivergence();
	}

	/**
	 * 
	 * @return {@link ADWMeasures} with all the Semantic scores with and WITHOUT disambiguation
	 */
	public ADWMeasures getSemanticSimilarityValues()
	{
		return adwMeasures;
	}

	/**
	 * Calculates the Semantic Similarity between two raw English sentences.
	 * 
	 * @param rawSentence1
	 *           raw sentence 1
	 * @param rawSentence2
	 *           raw sentence 2
	 */
	public void calculatingSemanticSimilarityScores(String rawSentence1, String rawSentence2)
	{
		MeasuresWrapper measuresWithDesambiguation = new MeasuresWrapper();
		MeasuresWrapper measuresWithoutDesambiguation = new MeasuresWrapper();

		// mWeightedOverlap
		measuresWithDesambiguation.addMeasure(Constants.MEASURE_DIS + Constants.MEASURE_WEIGHTEDOVERLAP,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodAlignmentBased, mWeightedOverlap, sentence1Type, sentence2Type));
		measuresWithoutDesambiguation.addMeasure(Constants.MEASURE_WEIGHTEDOVERLAP,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodNone, mWeightedOverlap, sentence1Type, sentence2Type));

		// mJaccard
		measuresWithDesambiguation.addMeasure(Constants.MEASURE_DIS + Constants.MEASURE_JACCARD,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodAlignmentBased, mJaccard, sentence1Type, sentence2Type));
		measuresWithoutDesambiguation.addMeasure(Constants.MEASURE_JACCARD,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodNone, mJaccard, sentence1Type, sentence2Type));

		// mCosine
		measuresWithDesambiguation.addMeasure(Constants.MEASURE_DIS + Constants.MEASURE_COSINE,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodAlignmentBased, mCosine, sentence1Type, sentence2Type));
		measuresWithoutDesambiguation.addMeasure(Constants.MEASURE_COSINE,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodNone, mCosine, sentence1Type, sentence2Type));

		// mJensenShannon
		measuresWithDesambiguation.addMeasure(Constants.MEASURE_DIS + Constants.MEASURE_JENSENSHANNON,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodAlignmentBased, mJensenShannon, sentence1Type, sentence2Type));
		measuresWithoutDesambiguation.addMeasure(Constants.MEASURE_JENSENSHANNON,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodNone, mJensenShannon, sentence1Type, sentence2Type));

		// mKLDivergence
		measuresWithDesambiguation.addMeasure(Constants.MEASURE_DIS + Constants.MEASURE_KLDIVERGENCE,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodAlignmentBased, mKLDivergence, sentence1Type, sentence2Type));
		measuresWithoutDesambiguation.addMeasure(Constants.MEASURE_KLDIVERGENCE,
				pipeLine.getPairSimilarity(rawSentence1, rawSentence2, disMethodNone, mKLDivergence, sentence1Type, sentence2Type));

		adwMeasures = new ADWMeasures(measuresWithDesambiguation, measuresWithoutDesambiguation);
	}

	/**
	 * ================== For testing purposes ==================
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		// String sentence1 = "The bird is bathing in the sink.";
		// String sentence2 = "Birdie is washing itself in the water basin.";
		// String sentence1 = "This guy is a bad ass.";
		// String sentence2 = "These guys are bad ass.";
		String sentence1 = "The udders of a dairy cow that is standing in a pasture near a large building.";
		String sentence2 = "A cows ass and some buildings.";

		// String sentence1 =
		// "The report reflects this and urges the Commission to ensure that occupational diseases are correctly identified and remedied, with a particular focus on occupational cancers, with a view to setting targets for their reduction. We also need detailed action plans with financial and timing commitments. Besides the targets for a 25% reduction in accidents, there appear to be few ways in which progress can be monitored and measured. Priorities for action identified in my report include a carrot-and-stick approach to enforcement of existing legislation. I would like to see the Member States reward business for good health and safety with tax rebates and a preference in calls for tenders and the introduction of a bonus-malus system in insurance policies, as well as other financial incentives. However, I would also like to see tougher sanctions for those rogue employers who neglect the health and safety of their workforce, as well as more infringement proceedings against Member States who do not adequately implement and enforce the existing health and safety legislation.";
		// String sentence2 =
		// "Any health and safety strategy should naturally focus on those who are most at risk. Such vulnerable groups include migrant workers, who are often exploited, as well as young and ageing workers, who need special attention, and those with disabilities. It is essential that the 1989 Framework Directive be rigorously applied to these groups and other workers who are often ignored, such as agricultural and health care workers, when drawing up and implementing their strategies. The Member States need to take full account of these groups. We need a framework directive on musculoskeletal disorders to address a problem such as lower back pain - repetitive strain injuries, effectively - and lower back disorders.a";

		ADWLib adwLib = new ADWLib(Constants.EN);
		adwLib.calculatingSemanticSimilarityScores(sentence1, sentence2);

		System.out.println("with desambiguation: ");
		System.out.println(adwLib.getSemanticSimilarityValues().toStringWithDisambiguation());

		System.out.println("\nwithOUT desambiguation: ");
		System.out.println(adwLib.getSemanticSimilarityValues().toStringWithoutDisambiguation());
	}

}
