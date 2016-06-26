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
 * This class loads all the necessary models for Spanish.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class NEREsModelsLoader
{
	private InputStream isEsLocation = null;
	private InputStream isEsOrganization = null;
	private InputStream isEsPerson = null;
	private InputStream isEsMisc = null;

	private TokenNameFinderModel modelEsLocation = null;
	private TokenNameFinderModel modelEsOrganization = null;
	private TokenNameFinderModel modelEsPerson = null;
	private TokenNameFinderModel modelEsMisc = null;

	private List<TokenNameFinderModel> esModelsList = null;

	/**
	 * Default constructor.
	 */
	public NEREsModelsLoader()
	{
		modelsLoader();
	}

	private void modelsLoader()
	{
		try
		{
			isEsLocation = new FileInputStream(Constants.ES_NER_LOCATION);
			isEsOrganization = new FileInputStream(Constants.ES_NER_ORGANIZATION);
			isEsPerson = new FileInputStream(Constants.ES_NER_PERSON);
			isEsMisc = new FileInputStream(Constants.ES_NER_MISC);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			modelEsLocation = new TokenNameFinderModel(isEsLocation);
			modelEsOrganization = new TokenNameFinderModel(isEsOrganization);
			modelEsPerson = new TokenNameFinderModel(isEsPerson);
			modelEsMisc = new TokenNameFinderModel(isEsMisc);

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

		esModelsList = new ArrayList<TokenNameFinderModel>();
		esModelsList.add(modelEsLocation);
		esModelsList.add(modelEsOrganization);
		esModelsList.add(modelEsPerson);
		esModelsList.add(modelEsMisc);
	}

	/**
	 * Closes all InputStreams
	 */
	private void closeInputStreams()
	{
		try
		{
			isEsLocation.close();
			isEsOrganization.close();
			isEsPerson.close();
			isEsMisc.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Returns a list of models for Spanish
	 * 
	 * @return the esModelsList
	 */
	public List<TokenNameFinderModel> getEsModelsList()
	{
		return esModelsList;
	}

}
