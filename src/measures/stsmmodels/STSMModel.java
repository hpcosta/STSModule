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
package measures.stsmmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import measures.measureswrapper.MeasuresWrapper;
import constantsfilesfolders.Constants;

/**
 * Semantic Textual Similarity Measures Model.
 * Allows to store the resulted STSM from the comparison between two sentences/documents.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2015
 */
public class STSMModel
{
	private String _dis_weightedOverlap = Constants.MEASURE_DIS + Constants.MEASURE_WEIGHTEDOVERLAP;
	private String _dis_jaccard = Constants.MEASURE_DIS + Constants.MEASURE_JACCARD;
	private String _dis_cosine = Constants.MEASURE_DIS + Constants.MEASURE_COSINE;
	private String _dis_jensenShannon = Constants.MEASURE_DIS + Constants.MEASURE_JENSENSHANNON;
	private String _dis_klDivergence = Constants.MEASURE_DIS + Constants.MEASURE_KLDIVERGENCE;

	private String weightedOverlap = Constants.MEASURE_WEIGHTEDOVERLAP;
	private String jaccard = Constants.MEASURE_JACCARD;
	private String cosine = Constants.MEASURE_COSINE;
	private String jensenShannon = Constants.MEASURE_JENSENSHANNON;
	private String klDivergence = Constants.MEASURE_KLDIVERGENCE;

	private String stemm_chisquare = Constants.MEASURE_STEMMS + Constants.MEASURE_CHISQUARE;
	private String stemm_scc = Constants.MEASURE_STEMMS + Constants.MEASURE_SCC;
	private String lemma_chisquare = Constants.MEASURE_LEMMAS + Constants.MEASURE_CHISQUARE;
	private String lemma_scc = Constants.MEASURE_LEMMAS + Constants.MEASURE_SCC;
	private String token_chisquare = Constants.MEASURE_TOKENS + Constants.MEASURE_CHISQUARE;
	private String token_scc = Constants.MEASURE_TOKENS + Constants.MEASURE_SCC;
	private String ncommonlemmas = Constants.MEASURE_NUMBEROFCOMMONLEMMAS;
	private String ncommonsteems = Constants.MEASURE_NUMBEROFCOMMONSTEMMS;
	private String ncommontokens = Constants.MEASURE_NUMBEROFCOMMONTOKENS;

	private String pivotIdentifier = "pivotIdentifier";
	private String pivotIdentifierValue = "";
	private String nodeIdentifier = "nodeIdentifier";
	private String nodeIdentifierValue = "";

	private Map<String, Double> pairwiseMeasureValue = null;

	/**
	 * Constructor
	 */
	public STSMModel(String fileIdentifier)
	{
		this(fileIdentifier, fileIdentifier);
	}

	/**
	 * Constructor
	 */
	public STSMModel(String pivotIdentifier, String nodeIdentifier)
	{
		this.pivotIdentifierValue = pivotIdentifier;
		this.nodeIdentifierValue = nodeIdentifier;

		pairwiseMeasureValue = new HashMap<String, Double>();

		pairwiseMeasureValue.put(ncommontokens, 0.0d);
		pairwiseMeasureValue.put(token_scc, 0.0d);
		pairwiseMeasureValue.put(token_chisquare, 0.0d);

		pairwiseMeasureValue.put(ncommonsteems, 0.0d);
		pairwiseMeasureValue.put(stemm_scc, 0.0d);
		pairwiseMeasureValue.put(stemm_chisquare, 0.0d);

		pairwiseMeasureValue.put(ncommonlemmas, 0.0d);
		pairwiseMeasureValue.put(lemma_scc, 0.0d);
		pairwiseMeasureValue.put(lemma_chisquare, 0.0d);

		pairwiseMeasureValue.put(klDivergence, 0.0d);
		pairwiseMeasureValue.put(jensenShannon, 0.0d);
		pairwiseMeasureValue.put(cosine, 0.0d);
		pairwiseMeasureValue.put(jaccard, 0.0d);
		pairwiseMeasureValue.put(weightedOverlap, 0.0d);
		pairwiseMeasureValue.put(_dis_klDivergence, 0.0d);
		pairwiseMeasureValue.put(_dis_jensenShannon, 0.0d);
		pairwiseMeasureValue.put(_dis_cosine, 0.0d);
		pairwiseMeasureValue.put(_dis_jaccard, 0.0d);
		pairwiseMeasureValue.put(_dis_weightedOverlap, 0.0d);
	}

	public STSMModel(String pivotIdentifier, String nodeIdentifier, MeasuresWrapper measuresWrapper)
	{
		this(pivotIdentifier, nodeIdentifier);
		setSTSMeasures(measuresWrapper.getMeasures());
	}

	public STSMModel(String pivotIdentifier, String nodeIdentifier, Map<String, Double> stsMeasures)
	{
		this(pivotIdentifier, nodeIdentifier);
		setSTSMeasures(stsMeasures);
	}

	private void setSTSMeasures(Map<String, Double> stsMeasures)
	{
		if (stsMeasures.containsKey(ncommontokens)) setNcommontokens(stsMeasures.get(ncommontokens));
		if (stsMeasures.containsKey(token_scc)) setTokenScc(stsMeasures.get(token_scc));
		if (stsMeasures.containsKey(token_chisquare)) setTokenChisquare(stsMeasures.get(token_chisquare));

		if (stsMeasures.containsKey(ncommonsteems)) setNcommonsteems(stsMeasures.get(ncommonsteems));
		if (stsMeasures.containsKey(stemm_scc)) setStemmScc(stsMeasures.get(stemm_scc));
		if (stsMeasures.containsKey(stemm_chisquare)) setStemmChisquare(stsMeasures.get(stemm_chisquare));

		if (stsMeasures.containsKey(ncommonlemmas)) setNcommonlemmas(stsMeasures.get(ncommonlemmas));
		if (stsMeasures.containsKey(lemma_scc)) setLemmaScc(stsMeasures.get(lemma_scc));
		if (stsMeasures.containsKey(lemma_chisquare)) setLemmaChisquare(stsMeasures.get(lemma_chisquare));

		if (stsMeasures.containsKey(klDivergence)) setKlDivergence(stsMeasures.get(klDivergence));
		if (stsMeasures.containsKey(jensenShannon)) setJensenShannon(stsMeasures.get(jensenShannon));
		if (stsMeasures.containsKey(cosine)) setCosine(stsMeasures.get(cosine));
		if (stsMeasures.containsKey(jaccard)) setJaccard(stsMeasures.get(jaccard));
		if (stsMeasures.containsKey(weightedOverlap)) setWeightedOverlap(stsMeasures.get(weightedOverlap));

		if (stsMeasures.containsKey(_dis_klDivergence)) set_dis_klDivergence(stsMeasures.get(_dis_klDivergence));
		if (stsMeasures.containsKey(_dis_jensenShannon)) set_dis_jensenShannon(stsMeasures.get(_dis_jensenShannon));
		if (stsMeasures.containsKey(_dis_cosine)) set_dis_cosine(stsMeasures.get(_dis_cosine));
		if (stsMeasures.containsKey(_dis_jaccard)) set_dis_jaccard(stsMeasures.get(_dis_jaccard));
		if (stsMeasures.containsKey(_dis_weightedOverlap)) set_dis_weightedOverlap(stsMeasures.get(_dis_weightedOverlap));
	}

	/**
	 * Returns the STSM header
	 * 
	 * @return
	 */
	public String[] getSTSMHeader()
	{
		List<String> header = new ArrayList<String>();
		header.add(pivotIdentifier);
		header.add(nodeIdentifier);

		header.add(ncommontokens);
		header.add(token_scc);
		header.add(token_chisquare);

		header.add(ncommonsteems);
		header.add(stemm_scc);
		header.add(stemm_chisquare);

		header.add(ncommonlemmas);
		header.add(lemma_scc);
		header.add(lemma_chisquare);

		header.add(klDivergence);
		header.add(jensenShannon);
		header.add(cosine);
		header.add(jaccard);
		header.add(weightedOverlap);

		header.add(_dis_klDivergence);
		header.add(_dis_jensenShannon);
		header.add(_dis_cosine);
		header.add(_dis_jaccard);
		header.add(_dis_weightedOverlap);

		return header.toArray(new String[header.size()]);
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		String[] header = getSTSMHeader();
		String[] values = getStringArraySTSMValues();

		for (int i = 0; i < header.length; i++)
			buffer.append(header[i] + ":" + values[i] + "\n");

		return buffer.toString();
	}

	/**
	 * Returns the STS measures values
	 * 
	 * @return
	 */
	public String[] getStringArraySTSMValues()
	{
		List<String> stsValues = new ArrayList<String>();

		stsValues.add(getPivotIdentifierValue());
		stsValues.add(getNodeIdentifierValue());

		stsValues.add(String.valueOf(getNcommontokens()));
		stsValues.add(String.valueOf(getTokenScc()));
		stsValues.add(String.valueOf(getTokenChisquare()));

		stsValues.add(String.valueOf(getNcommonsteems()));
		stsValues.add(String.valueOf(getStemmScc()));
		stsValues.add(String.valueOf(getStemmChisquare()));

		stsValues.add(String.valueOf(getNcommonlemmas()));
		stsValues.add(String.valueOf(getLemmaScc()));
		stsValues.add(String.valueOf(getLemmaChisquare()));

		stsValues.add(String.valueOf(getKlDivergence()));
		stsValues.add(String.valueOf(getJensenShannon()));
		stsValues.add(String.valueOf(getCosine()));
		stsValues.add(String.valueOf(getJaccard()));
		stsValues.add(String.valueOf(getWeightedOverlap()));

		stsValues.add(String.valueOf(get_dis_klDivergence()));
		stsValues.add(String.valueOf(get_dis_jensenShannon()));
		stsValues.add(String.valueOf(get_dis_cosine()));
		stsValues.add(String.valueOf(get_dis_jaccard()));
		stsValues.add(String.valueOf(get_dis_weightedOverlap()));

		return stsValues.toArray(new String[stsValues.size()]);
	}

	/**
	 * Returns the STS measures values
	 * 
	 * @return
	 */
	public List<String> getListSTSMValues()
	{
		List<String> stsValues = new ArrayList<String>();
		stsValues.add(getPivotIdentifierValue());
		stsValues.add(getNodeIdentifierValue());

		stsValues.add(String.valueOf(getNcommontokens()));
		stsValues.add(String.valueOf(getTokenScc()));
		stsValues.add(String.valueOf(getTokenChisquare()));

		stsValues.add(String.valueOf(getNcommonsteems()));
		stsValues.add(String.valueOf(getStemmScc()));
		stsValues.add(String.valueOf(getStemmChisquare()));

		stsValues.add(String.valueOf(getNcommonlemmas()));
		stsValues.add(String.valueOf(getLemmaScc()));
		stsValues.add(String.valueOf(getLemmaChisquare()));

		stsValues.add(String.valueOf(getKlDivergence()));
		stsValues.add(String.valueOf(getJensenShannon()));
		stsValues.add(String.valueOf(getCosine()));
		stsValues.add(String.valueOf(getJaccard()));
		stsValues.add(String.valueOf(getWeightedOverlap()));
		stsValues.add(String.valueOf(get_dis_klDivergence()));
		stsValues.add(String.valueOf(get_dis_jensenShannon()));
		stsValues.add(String.valueOf(get_dis_cosine()));
		stsValues.add(String.valueOf(get_dis_jaccard()));
		stsValues.add(String.valueOf(get_dis_weightedOverlap()));

		return stsValues;
	}

	public String getPivotIdentifierValue()
	{
		return pivotIdentifierValue;
	}

	public String getNodeIdentifierValue()
	{
		return nodeIdentifierValue;
	}

	public double getTokenScc()
	{
		return pairwiseMeasureValue.get(token_scc);
	}

	public double getStemmScc()
	{
		return pairwiseMeasureValue.get(stemm_scc);
	}

	public double getLemmaScc()
	{
		return pairwiseMeasureValue.get(lemma_scc);
	}

	public double get_dis_weightedOverlap()
	{
		return pairwiseMeasureValue.get(_dis_weightedOverlap);
	}

	public double get_dis_jaccard()
	{
		return pairwiseMeasureValue.get(_dis_jaccard);
	}

	public double get_dis_cosine()
	{
		return pairwiseMeasureValue.get(_dis_cosine);
	}

	public double get_dis_jensenShannon()
	{
		return pairwiseMeasureValue.get(_dis_jensenShannon);
	}

	public double get_dis_klDivergence()
	{
		return pairwiseMeasureValue.get(_dis_klDivergence);
	}

	public double getWeightedOverlap()
	{
		return pairwiseMeasureValue.get(weightedOverlap);
	}

	public double getJaccard()
	{
		return pairwiseMeasureValue.get(jaccard);
	}

	public double getCosine()
	{
		return pairwiseMeasureValue.get(cosine);
	}

	public double getJensenShannon()
	{
		return pairwiseMeasureValue.get(jensenShannon);
	}

	public double getKlDivergence()
	{
		return pairwiseMeasureValue.get(klDivergence);
	}

	public double getTokenChisquare()
	{
		return pairwiseMeasureValue.get(token_chisquare);
	}

	public double getStemmChisquare()
	{
		return pairwiseMeasureValue.get(stemm_chisquare);
	}

	public double getLemmaChisquare()
	{
		return pairwiseMeasureValue.get(lemma_chisquare);
	}

	public double getNcommontokens()
	{
		return pairwiseMeasureValue.get(ncommontokens);
	}

	public double getNcommonlemmas()
	{
		return pairwiseMeasureValue.get(ncommonlemmas);
	}

	public double getNcommonsteems()
	{
		return pairwiseMeasureValue.get(ncommonsteems);
	}

	public void setPivotIdentifier(String pivotIdentifierValue)
	{
		this.pivotIdentifierValue = pivotIdentifierValue;
	}

	public void setNodeIdentifier(String nodeIdentifierValue)
	{
		this.nodeIdentifierValue = nodeIdentifierValue;
	}

	public void setTokenScc(double newTokenScc)
	{
		pairwiseMeasureValue.remove(token_scc);
		pairwiseMeasureValue.put(token_scc, newTokenScc);
	}

	public void setStemmScc(double newStemmScc)
	{
		pairwiseMeasureValue.remove(stemm_scc);
		pairwiseMeasureValue.put(stemm_scc, newStemmScc);
	}

	public void setLemmaScc(double newLemmaScc)
	{
		pairwiseMeasureValue.remove(lemma_scc);
		pairwiseMeasureValue.put(lemma_scc, newLemmaScc);
	}

	public void set_dis_weightedOverlap(double new_dis_weightedOverlap)
	{
		pairwiseMeasureValue.remove(_dis_weightedOverlap);
		pairwiseMeasureValue.put(_dis_weightedOverlap, new_dis_weightedOverlap);
	}

	public void set_dis_jaccard(double new_dis_jaccard)
	{
		pairwiseMeasureValue.remove(_dis_jaccard);
		pairwiseMeasureValue.put(_dis_jaccard, new_dis_jaccard);
	}

	public void set_dis_cosine(double new_dis_cosine)
	{
		pairwiseMeasureValue.remove(_dis_cosine);
		pairwiseMeasureValue.put(_dis_cosine, new_dis_cosine);
	}

	public void set_dis_jensenShannon(double new_dis_jensenShannon)
	{
		pairwiseMeasureValue.remove(_dis_jensenShannon);
		pairwiseMeasureValue.put(_dis_jensenShannon, new_dis_jensenShannon);
	}

	public void set_dis_klDivergence(double new_dis_klDivergence)
	{
		pairwiseMeasureValue.remove(_dis_klDivergence);
		pairwiseMeasureValue.put(_dis_klDivergence, new_dis_klDivergence);
	}

	public void setWeightedOverlap(double newWeightedOverlap)
	{
		pairwiseMeasureValue.remove(weightedOverlap);
		pairwiseMeasureValue.put(weightedOverlap, newWeightedOverlap);
	}

	public void setJaccard(double newJaccard)
	{
		pairwiseMeasureValue.remove(jaccard);
		pairwiseMeasureValue.put(jaccard, newJaccard);
	}

	public void setCosine(double newCosine)
	{
		pairwiseMeasureValue.remove(cosine);
		pairwiseMeasureValue.put(cosine, newCosine);
	}

	public void setJensenShannon(double newJensenShannon)
	{
		pairwiseMeasureValue.remove(jensenShannon);
		pairwiseMeasureValue.put(jensenShannon, newJensenShannon);
	}

	public void setKlDivergence(double newKlDivergence)
	{
		pairwiseMeasureValue.remove(klDivergence);
		pairwiseMeasureValue.put(klDivergence, newKlDivergence);
	}

	public void setTokenChisquare(double newTokenChisquare)
	{
		pairwiseMeasureValue.remove(token_chisquare);
		pairwiseMeasureValue.put(token_chisquare, newTokenChisquare);
	}

	public void setStemmChisquare(double newStemmChisquare)
	{
		pairwiseMeasureValue.remove(stemm_chisquare);
		pairwiseMeasureValue.put(stemm_chisquare, newStemmChisquare);
	}

	public void setLemmaChisquare(double newLemmaChisquare)
	{
		pairwiseMeasureValue.remove(lemma_chisquare);
		pairwiseMeasureValue.put(lemma_chisquare, newLemmaChisquare);
	}

	public void setNcommontokens(double newNcommontokens)
	{
		pairwiseMeasureValue.remove(ncommontokens);
		pairwiseMeasureValue.put(ncommontokens, newNcommontokens);
	}

	public void setNcommonlemmas(double newNcommonlemmas)
	{
		pairwiseMeasureValue.remove(ncommonlemmas);
		pairwiseMeasureValue.put(ncommonlemmas, newNcommonlemmas);
	}

	public void setNcommonsteems(double newNcommonsteems)
	{
		pairwiseMeasureValue.remove(ncommonsteems);
		pairwiseMeasureValue.put(ncommonsteems, newNcommonsteems);
	}

}
