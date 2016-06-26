/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package nlp.ner;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class NamedEntities
{
	private Vector<NamedEntity> namedEntities = null;
	private Vector<NamedEntity> temp_namedEntities = null;
	private NamedEntity neTemp = null;

	/**
	 * Default constructor
	 */
	public NamedEntities()
	{
		namedEntities = new Vector<NamedEntity>();

	}

	/**
	 * Adds a new Named Entity to the list of Named Entities if it does not exist or increments its frequency if already exists in the
	 * list.
	 * 
	 * @param namedEntity
	 *                the new Named Entity
	 */
	@SuppressWarnings("unchecked")
	public void addNamedEntity(NamedEntity namedEntity)
	{
		boolean flag = true;

		/** if the list is empty lets just add the first element to the list */
		if (namedEntities.isEmpty())
		{
			namedEntities.add(namedEntity);
			flag = false;
		}
		/** if the list already has elements lets see if this Named Entity already exists. If yes, lets increment its frequency. */
		else
		{
			temp_namedEntities = (Vector<NamedEntity>) namedEntities.clone();

			for (int i = 0; i < temp_namedEntities.size(); i++)
			{
				neTemp = temp_namedEntities.get(i);

				if (neTemp.getNamedEntity().equalsIgnoreCase(namedEntity.getNamedEntity())
						&& neTemp.getCategory().equalsIgnoreCase(namedEntity.getCategory()))
				{
					neTemp.incrementFrequency();
					namedEntities.remove(i);
					flag = true;
					break;
				}
			}
		}
		if (flag)
			namedEntities.add(namedEntity);

	}

	public String toString()
	{
		StringBuilder temp = new StringBuilder();
		for (NamedEntity ne : namedEntities)
			temp.append(ne.getCategory() + "\t" + ne.getNamedEntity() + "\t" + ne.getFrequency() + "\n");

		return temp.toString();
	}

	/**
	 * 
	 * @return a HasMap with all the existing categories
	 */
	public HashMap<String, Boolean> getExistingCategories()
	{
		HashMap<String, Boolean> namedEntitiesPerCategory = new HashMap<String, Boolean>();
		String category = "";
		for (int i = 0; i < namedEntities.size(); i++)
		{
			category = namedEntities.get(i).getCategory();
			if (!namedEntitiesPerCategory.containsKey(category))
				namedEntitiesPerCategory.put(category, true);
		}
		return namedEntitiesPerCategory;
	}
}
