/**
 * @author Hernani Costa iCorpora EXPERT (EXPloiting Empirical appRoaches to Translation) ESR3 - Collection & preparation of multilingual
 *         data for multiple corpus-based approaches to translation Department of Translation and Interpreting Faculty of Philosophy and
 *         Humanities
 *
 *         Copyright (c) 2013-2016 University of Malaga, Spain All rights reserved.
 */
package constantsfilesfolders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains a set of general functions to deal with files and directories.
 * 
 * @author Hernani Costa
 * 
 * @version 0.1/2013
 * @version 0.2/2015
 */
public class FilesAndFolders
{

	/**
	 * Verifies if the path to a txt file exists.
	 * 
	 * @param pathToFile
	 *           - path to the file
	 * 
	 * @return true if exists, otherwise returns false
	 */
	public static boolean txtFileExists(String pathToFile)
	{
		boolean isTXT = pathToFile.toLowerCase().endsWith(".txt");
		Path path = Paths.get(pathToFile);
		// verifies that the path exists && is a file && and is a TXT file
		return (Files.exists(path) == true && isFileOrDirectory(pathToFile) == 1 && isTXT == true) ? true : false;
	}

	/**
	 * Verifies if the path is a file, a directory or does not exist.
	 * 
	 * @param pathString
	 *           to be analised
	 * @return 2, 1 or 0 if the path is a directory, file or error, respectively.
	 */
	public static int isFileOrDirectory(String pathString)
	{
		Path path = Paths.get(pathString);

		if (Files.isDirectory(path))
			return 2;
		else if (Files.isRegularFile(path))
			return 1;
		else return 0;

	}

	/**
	 * Get all files from a specific directory
	 * 
	 * @param pathToDirectory
	 *           - path to the directory
	 * @return - list with the names of the files
	 */
	public static String[] getAllFilesFromPath(String pathToDirectory)
	{
		File dir = new File(pathToDirectory);
		String[] files = dir.list();
		return (files == null) ? null : files;
	}

	/**
	 * Get all folder from a specific directory
	 * 
	 * @param pathToDirectory
	 *           - path to the directory
	 * @return - list with the names of the folders
	 */
	public static List<String> getAllFoldersFromPath(String pathToDirectory)
	{
		File f = null;
		File dir = new File(pathToDirectory);
		String[] directoryContent = dir.list();

		List<String> listOfDirectories = new ArrayList<String>();

		for (String item : directoryContent)
		{
			f = new File(pathToDirectory + item);
			if (f.isDirectory())
			{
				listOfDirectories.add(item);
				System.err.println(pathToDirectory + item);
			}
		}
		return (listOfDirectories == null) ? null : listOfDirectories;
	}

	/**
	 * Get PDF DOC RDF TXT DOCX ODT files from a specific directory
	 * 
	 * @param pathToDirectory
	 *           - path to the directory
	 * @return - list with the names of the files
	 */
	public static List<String> getPDFDOCRDFTXTDOXDOTFilesFromPath(String pathToDirectory)
	{
		File dir = new File(pathToDirectory);
		String[] temp = dir.list();
		List<String> files = new ArrayList<String>();

		for (String s : temp)
		{
			if (s.endsWith(".xls") || s.endsWith(".XLS") || s.endsWith(".txt") || s.endsWith(".TXT") || s.endsWith(".pdf") || s.endsWith(".PDF")
					|| s.endsWith(".doc") || s.endsWith(".DOC") || s.endsWith(".docx") || s.endsWith(".DOCX") || s.endsWith(".rdf") || s.endsWith(".RDF")
					|| s.endsWith(".odt") || s.endsWith(".ODT"))
			{
				files.add(s);
			}
		}
		return (files == null) ? null : files;
	}

	/**
	 * Returns a list with the name of the TXT files within a directory
	 * 
	 * @param pathToDirectory
	 *           - path to the directory
	 * @return - list with the names of txt files
	 */
	public static List<String> getTXTfilesFromPath(String pathToDirectory)
	{
		File dir = new File(pathToDirectory);
		String[] temp = dir.list();
		List<String> files = new ArrayList<String>();

		for (String s : temp)
			if (s.endsWith(".txt") || s.endsWith(".TXT")) files.add(s);

		return (files == null) ? null : files;
	}

	/**
	 * Returns a list with the names of the all files ended with a specified extension
	 * 
	 * @param pathToDirectory
	 *           - path to the directory
	 * @return - list with the names of files that end with a specific extension
	 */
	public static List<String> getFilesFromPath(String pathToDirectory, String extension)
	{
		File dir = new File(pathToDirectory);
		String[] temp = dir.list();
		List<String> files = new ArrayList<String>();

		for (String s : temp)
		{
			//System.err.println(s);
			if (s.endsWith(extension)) files.add(s);
		}

		return (files == null) ? null : files;
	}

	/**
	 * Loads a file to a StringBuffer
	 * 
	 * @param sourcePathToFile
	 *           - path to the file
	 * @param encoding
	 *           - file encoding
	 * @return - StringBuffer
	 */
	public static StringBuffer loadTextFromFile(String sourcePathToFile, String encoding)
	{
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePathToFile), encoding));
		} catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

		String line = "";
		try
		{
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line + "\n");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * This function writes to a file a specific content. If the file does not exist, this function creates a new one.
	 * 
	 * @param content
	 *           - data to be saved in a string format
	 * @param append
	 *           - true to written to the end of the file, false to overwrite the content
	 * @param filePath
	 *           - path to the file (if does not exist a new one will be created)
	 * @param encoding
	 *           - encoding
	 */
	public static void saveData(String content, boolean append, String filePath, String encoding)
	{
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, append), encoding));
			out.write(content);
			out.flush();
			out.close();
		} catch (IOException ex)
		{
			System.out.println("Error writing date to the file: " + filePath + ex.toString());
		}
	}

	/**
	 * This function permits to read a set of files from a directory in a specific format and convert them into another format.
	 * 
	 * @param sourceDirectory
	 *           source directory
	 * @param sourceEncoding
	 *           encoding in which the source files are encoded
	 * @param targetDirectory
	 *           target directory when the new files will be saved (null if you want to save in the source directory and consequently
	 *           overwrite to existing files)
	 * @param targetEncoding
	 *           encoding in which the target files will be encoded
	 */
	public static void decodingEncodingTXTFiles(String sourceDirectory, String sourceEncoding, String targetDirectory, String targetEncoding)
	{
		if (targetDirectory == null) targetDirectory = sourceDirectory;

		List<String> filesName = FilesAndFolders.getTXTfilesFromPath(sourceDirectory);
		String pathToSourceFile = "";
		String pathToTargetFile = "";
		StringBuffer content = null;
		for (String filename : filesName)
		{
			pathToSourceFile = sourceDirectory + filename;
			content = FilesAndFolders.loadTextFromFile(pathToSourceFile, sourceEncoding);
			pathToTargetFile = targetDirectory + filename;
			FilesAndFolders.saveData(content.toString(), false, pathToTargetFile, targetEncoding);
		}
	}

	/**
	 * This function permits to read one file from a specific path in a specific format and convert it into another format.
	 * 
	 * @param pathToSourceFile
	 *           source file path
	 * @param sourceEncoding
	 *           encoding in which the source files are encoded
	 * @param pathToTargetFile
	 *           target file path (null if you want to save in the source directory and consequently overwrite to existing file)
	 * @param targetEncoding
	 *           encoding in which the target files will be encoded
	 */
	public static void decodingEncodingTXTFile(String pathToSourceFile, String sourceEncoding, String pathToTargetFile, String targetEncoding)
	{
		if (pathToSourceFile == null) pathToTargetFile = pathToSourceFile;

		StringBuffer content = FilesAndFolders.loadTextFromFile(pathToSourceFile, sourceEncoding);
		FilesAndFolders.saveData(content.toString(), false, pathToTargetFile, targetEncoding);
	}

	// /////////////////////////////////////////////////////////
	// /////////////////////// DEPRECATED FUNCTIONS ///////////
	// ///////////////////////////////////////////////////////

	// http://www.rgagnon.com/javadetails/java-0014.html
	/**
	 * delete a specific file in windows
	 * 
	 * @param filee
	 */
	@Deprecated
	public void deleteAfile(String filee)
	{
		try
		{
			// File file = new File(filee);
			// file.delete();
			String cmd = "cmd /c del " + filee;
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();

			System.out.println(cmd);
		} catch (InterruptedException ex)
		{
			Logger.getLogger(FilesAndFolders.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex)
		{
			Logger.getLogger(FilesAndFolders.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * copy files from one directory to another
	 * 
	 * @param tgt
	 *           - targer
	 * @param src
	 *           - source
	 * @return true if success, false otherwise
	 */
	@Deprecated
	public boolean copy(String tgt, String src)
	{
		try
		{
			// Runtime run = Runtime.getRuntime();
			String cmd = "cmd /c copy ";
			if (src.indexOf(" ") != -1 && !src.startsWith("\n"))
			{
				src = "\"" + src + "\"";
			}
			if (tgt.indexOf(" ") != -1 && !tgt.startsWith("\n"))
			{
				tgt = "\"" + tgt + "\"";
			}
			cmd = cmd + src + " " + tgt;
			System.out.println(cmd);
			// run.exec(cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();

			return true;
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

}
