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
 * The class stores all the PivotSimilarityModels that result from all the pivots (the comparison between all the objects).
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2015
 */
public class PivotsSimilarityModelWrapper
{
	private String csvFileName = "";
	private List<PivotSimilarityModel> pivotsModels = null;

	/**
	 * Constructor
	 */
	public PivotsSimilarityModelWrapper(String csvFileName)
	{
		this.csvFileName = csvFileName;
		pivotsModels = new ArrayList<PivotSimilarityModel>();
	}

	/**
	 * Adds a new pivot similarity model to the pivots models list
	 * 
	 * @param pivotSimilarityModel
	 *           - a pivot similarity model
	 */
	public void addPivotSimilarityModel(PivotSimilarityModel pivotSimilarityModel)
	{
		pivotsModels.add(pivotSimilarityModel);
	}

	/**
	 * @return the fileName
	 */
	public String getCSVFileName()
	{
		return csvFileName;
	}

	/**
	 * @return a list of {@link PivotSimilarityModel}
	 */
	public List<PivotSimilarityModel> getPivotsModels()
	{
		return pivotsModels;
	}

}
