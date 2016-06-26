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
import java.util.List;

/**
 * The class stores all the STSMModels that result from the pairwise comparison between the pivot and the others documents/sentences.
 * It also gives the pivot STSMeasures average scores.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2015
 */
public class PivotSimilarityModel
{
	private String pivotFileName = "";
	private List<STSMModel> pivotSTSMModels = null;

	/**
	 * Constructor
	 */
	public PivotSimilarityModel(String pivotFileName)
	{
		this.pivotFileName = pivotFileName;
		pivotSTSMModels = new ArrayList<STSMModel>();
	}

	/**
	 * Adds a new pairwise similarity model to the list
	 * 
	 * @param stsmModel
	 *           - a pairwise similarity model
	 */
	public void addPairwiseSimilarityModel(STSMModel stsmModel)
	{
		pivotSTSMModels.add(stsmModel);
	}

	/**
	 * @return the pivotFileName
	 */
	public String getPivotFileName()
	{
		return pivotFileName;
	}

	/**
	 * @return the stsmModels
	 */
	public List<STSMModel> getPivotSTSMModels()
	{
		return pivotSTSMModels;
	}

	/**
	 * Calculates the Pivot Similarity Average
	 * 
	 * @return {@link STSMModel}
	 */
	public STSMModel getSTSPivotAverage()
	{
		STSMModel stsmAverage = new STSMModel(pivotFileName);
		double ncommontokens = 0.0d;
		double token_scc = 0.0d;
		double token_chisquare = 0.0d;
		
		double ncommonsteems = 0.0d;
		double stemm_scc = 0.0d;
		double stemm_chisquare = 0.0d;
		
		double ncommonlemmas = 0.0d;
		double lemma_scc = 0.0d;
		double lemma_chisquare = 0.0d;
		
		double klDivergence = 0.0d;
		double jensenShannon = 0.0d;
		double cosine = 0.0d;
		double jaccard = 0.0d;
		double weightedOverlap = 0.0d;
		
		double _dis_klDivergence = 0.0d;
		double _dis_jensenShannon = 0.0d;
		double _dis_cosine = 0.0d;
		double _dis_jaccard = 0.0d;
		double _dis_weightedOverlap = 0.0d;

		for (STSMModel stsmModel : pivotSTSMModels)
		{
			ncommontokens += stsmModel.getNcommontokens();
			token_scc += stsmModel.getTokenScc();
			token_chisquare += stsmModel.getTokenChisquare();
			
			ncommonsteems += stsmModel.getNcommonsteems();
			stemm_scc += stsmModel.getStemmScc();
			stemm_chisquare += stsmModel.getStemmChisquare();
			
			ncommonlemmas += stsmModel.getNcommonlemmas();
			lemma_scc += stsmModel.getLemmaScc();
			lemma_chisquare += stsmModel.getLemmaChisquare();
			
			klDivergence += stsmModel.getKlDivergence();
			jensenShannon += stsmModel.getJensenShannon();
			cosine += stsmModel.getCosine();
			jaccard += stsmModel.getJaccard();
			weightedOverlap += stsmModel.getWeightedOverlap();
			
			_dis_klDivergence += stsmModel.get_dis_klDivergence();
			_dis_jensenShannon += stsmModel.get_dis_jensenShannon();
			_dis_cosine += stsmModel.get_dis_cosine();
			_dis_jaccard += stsmModel.get_dis_jaccard();
			_dis_weightedOverlap += stsmModel.get_dis_weightedOverlap();
		}

		double totalModels = (double) pivotSTSMModels.size();
		ncommontokens /= totalModels;
		token_scc /= totalModels;
		token_chisquare /= totalModels;
		ncommonsteems /= totalModels;
		stemm_scc /= totalModels;
		stemm_chisquare /= totalModels;
		ncommonlemmas /= totalModels;
		lemma_scc /= totalModels;
		lemma_chisquare /= totalModels;
		klDivergence /= totalModels;
		jensenShannon /= totalModels;
		cosine /= totalModels;
		jaccard /= totalModels;
		weightedOverlap /= totalModels;
		_dis_klDivergence /= totalModels;
		_dis_jensenShannon /= totalModels;
		_dis_cosine /= totalModels;
		_dis_jaccard /= totalModels;
		_dis_weightedOverlap /= totalModels;

		stsmAverage.setNcommontokens(ncommontokens);
		stsmAverage.setTokenScc(token_scc);
		stsmAverage.setTokenChisquare(token_chisquare);
		
		stsmAverage.setNcommonsteems(ncommonsteems);
		stsmAverage.setStemmScc(stemm_scc);
		stsmAverage.setStemmChisquare(stemm_chisquare);
		
		stsmAverage.setNcommonlemmas(ncommonlemmas);		
		stsmAverage.setLemmaScc(lemma_scc);
		stsmAverage.setLemmaChisquare(lemma_chisquare);
		
		stsmAverage.setKlDivergence(klDivergence);
		stsmAverage.setJensenShannon(jensenShannon);
		stsmAverage.setCosine(cosine);
		stsmAverage.setJaccard(jaccard);
		stsmAverage.setWeightedOverlap(weightedOverlap);
		
		stsmAverage.set_dis_klDivergence(_dis_klDivergence);
		stsmAverage.set_dis_jensenShannon(_dis_jensenShannon);
		stsmAverage.set_dis_cosine(_dis_cosine);
		stsmAverage.set_dis_jaccard(_dis_jaccard);
		stsmAverage.set_dis_weightedOverlap(_dis_weightedOverlap);

		return stsmAverage;
	}
}
