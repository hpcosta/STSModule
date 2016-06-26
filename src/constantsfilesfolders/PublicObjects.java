/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package constantsfilesfolders;

import java.util.HashMap;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class PublicObjects
{

	public static final String DATE = "date";
	public static final String LOCATION = "location";
	public static final String MONEY = "money";
	public static final String ORGANIZATION = "organization";
	public static final String PERCENTAGE = "pertentage";
	public static final String PERSON = "person";
	public static final String TIME = "time";
	public static final String MISC = "misc";

	/**
	 * 
	 * @return default HashMap for Named Entities in english
	 */
	public HashMap<String, Boolean> getDefaultEnNamedEntitiesHashMap()
	{
		return new HashMap<String, Boolean>()
		{
			/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			{
				put(DATE, false);
				put(LOCATION, false);
				put(MONEY, false);
				put(ORGANIZATION, false);
				put(PERCENTAGE, false);
				put(PERSON, false);
				put(TIME, false);

			}
		};
	}

	/**
	 * 
	 * @return default HashMap for Named Entities in spanish
	 */
	public HashMap<String, Boolean> getDefaultEsNamedEntitiesHashMap()
	{
		return new HashMap<String, Boolean>()
		{
			/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			{
				put(LOCATION, false);
				put(ORGANIZATION, false);
				put(PERSON, false);
				put(MISC, false);

			}
		};
	}

}
