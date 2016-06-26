/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.ner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InvalidFormatException;
import constantsfilesfolders.Constants;

/**
 * This class loads all the necessary models for English.
 */

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class NEREnModelsLoader
{
	private InputStream isEnData = null;
	private InputStream isEnLocation = null;
	private InputStream isEnMoney = null;
	private InputStream isEnOrganization = null;
	private InputStream isEnPercentage = null;
	private InputStream isEnPerson = null;
	private InputStream isEnTime = null;

	private TokenNameFinderModel modelEnData = null;
	private TokenNameFinderModel modelEnLocation = null;
	private TokenNameFinderModel modelEnMoney = null;
	private TokenNameFinderModel modelEnOrganization = null;
	private TokenNameFinderModel modelEnPercentage = null;
	private TokenNameFinderModel modelEnPerson = null;
	private TokenNameFinderModel modelEnTime = null;

	private List<TokenNameFinderModel> enModelsList = null;

	/**
	 * Default constructor.
	 */
	public NEREnModelsLoader()
	{
		modelsLoader();
	}

	private void modelsLoader()
	{
		try
		{
			isEnData = new FileInputStream(Constants.EN_NER_DATE);
			isEnLocation = new FileInputStream(Constants.EN_NER_LOCATION);
			isEnMoney = new FileInputStream(Constants.EN_NER_MONEY);
			isEnOrganization = new FileInputStream(Constants.EN_NER_ORGANIZATION);
			isEnPercentage = new FileInputStream(Constants.EN_NER_PERCENTAGE);
			isEnPerson = new FileInputStream(Constants.EN_NER_PERSON);
			isEnTime = new FileInputStream(Constants.EN_NER_TIME);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			modelEnData = new TokenNameFinderModel(isEnData);
			modelEnLocation = new TokenNameFinderModel(isEnLocation);
			modelEnMoney = new TokenNameFinderModel(isEnMoney);
			modelEnOrganization = new TokenNameFinderModel(isEnOrganization);
			modelEnPercentage = new TokenNameFinderModel(isEnPercentage);
			modelEnPerson = new TokenNameFinderModel(isEnPerson);
			modelEnTime = new TokenNameFinderModel(isEnTime);

			closeInputStreams();

		} catch (InvalidFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		enModelsList = new ArrayList<TokenNameFinderModel>();
		enModelsList.add(modelEnData);
		enModelsList.add(modelEnLocation);
		enModelsList.add(modelEnMoney);
		enModelsList.add(modelEnOrganization);
		enModelsList.add(modelEnPercentage);
		enModelsList.add(modelEnPerson);
		enModelsList.add(modelEnTime);
	}

	/**
	 * Closes all InputStreams
	 */
	private void closeInputStreams()
	{
		try
		{
			isEnData.close();
			isEnLocation.close();
			isEnMoney.close();
			isEnOrganization.close();
			isEnPercentage.close();
			isEnPerson.close();
			isEnTime.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Returns a list of models for English
	 * 
	 * @return the esModelsList
	 */
	public List<TokenNameFinderModel> getEnModelsList()
	{
		return enModelsList;
	}
}
