/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain. All rights reserved.
 */
package test.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import constantsfilesfolders.Constants;
import nlp.dictionaries.babelnet.MyBabelNet;

/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */
public class BabelScores
{
	private MyBabelNet myBabelNet = null;
	private String inputFile = "";
	private String outputFile = "";

	/**
	 * 
	 */
	public BabelScores(String language, String inputFile, String outputFile, String inputEncoding)
	{
		myBabelNet = new MyBabelNet(language);
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		processText(inputEncoding);
	}



	/**
	 * processing the document
	 */
	private void processText(String inputEncoding)
	{

		BufferedReader reader = null;

		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), inputEncoding));
		} catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		String str = "";
		String line1 = "";
		String line2 = "";
		int nline=0;
		try
		{
			while ((str = reader.readLine()) != null)
			{
				line1 = str.split("\t")[0].trim();
				line2 = str.split("\t")[1].trim();
				
				
				nline+=1;
				
				
				System.err.println(nline + " out 2000");
				if (nline < 198) //135//137//698
					continue;
				
				HashMap<String, Double> conceptualScores = myBabelNet.getConceptualSimilarity(line1, line2);
				saveData(conceptualScores, outputFile);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Saving the scores...
	 * 
	 * @param conceptualScores
	 * @param outputFile
	 */
	private void saveData(HashMap<String, Double> conceptualScores, String outputFile)
	{
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile, true), Constants.ENCODING_UTF8));
			for (String n : conceptualScores.keySet())
			{
				out.write(n + "\t" + conceptualScores.get(n) + "\t");
			}
			out.write("\n");
			out.flush();
			out.close();
		} catch (IOException ex)
		{
			System.out.println("Error writing date to the file: " + outputFile);
		}

	}
	
	
	
	/**
	 * 
	 * For testing purposes
	 */
	public static void main(String args[])
	{
		String inputFile = null;
		String outputFile = null;
		BabelScores babelScores = null;
		
		inputFile = "/Users/hpcosta/Docs/Workspace2016/SemEval2015/files/english/STS.input.answers-forums.txt";
		outputFile = "/Users/hpcosta/Docs/Workspace2016/SemEval2015/files/english/conceptualScores.STS.input.answers-forums.txt";
		// Constants.EN and Constants.ENCODING_UTF8 for ENGLISH
		// Constants.ES and Constants.ENCODING_ISO88591 for SPANISH
		babelScores = new BabelScores(Constants.EN, inputFile, outputFile, Constants.ENCODING_UTF8);

	}
}
