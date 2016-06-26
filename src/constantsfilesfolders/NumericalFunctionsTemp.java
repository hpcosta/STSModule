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
package constantsfilesfolders;

import java.util.*;
import java.text.*;
import java.io.*;
import java.math.*;

/**
 * Math and statistical static methods.
 */
/**
 * @author Hernani Costa
 *
 * @version 0.1/2014
 */

public class NumericalFunctionsTemp {
	public static final double log10 = Math.log(10);
	public static final double log2 = Math.log(2);
	private static final double sRConstant = Math.log(Math.PI) / 2.0;

	/**Using interbase coordinates so length = end - start.*/
	public static int calculateMiddleIntergenicCoordinates(int start, int end){
		if (start == end) return start;
		double length = end - start;
		double halfLength = length/2.0;
		return (int)Math.round(halfLength) + start;
	}


	/**@return number of trues in boolean[]*/
	public static int countNumberTrue (boolean[] b){
		int num =0;
		for (int i=0; i< b.length; i++) if (b[i]) num++;
		return num;
	}
	
	/**Scales the array to 1:1000*/
	public static void scale1To1000(float[] f){
		float[] minMax = NumericalFunctionsTemp.findMinMaxFloatValues(f);
		for (int i=0; i< f.length; i++) f[i] -= minMax[0];
		float range = minMax[1]-minMax[0];
		float multi = 999.0f/range;
		for (int i=0; i< f.length; i++) f[i] = Math.round((f[i]) * multi)+1;
	}

	/**Calculates the Poisson probablility for observed < 21.
	 * For observed > 20 call poissonProbabilityApproximation()
	 * @param rate - lambda, the average number of occurances
	 * @param observed - the actual number of occurances observed
	 * @return poissonProbability of observing the number of occurances by chance alone
	 * @author nix */
	public static double poissonProbablility (double rate, int observed){
		double a = Math.pow(rate, observed);
		double b = Math.pow(Math.E, -1*rate);
		double c = NumericalFunctionsTemp.factorial(observed);
		return (a*b)/c;
	}

	/**Calculates a natural log approximation of the Poisson probablility.
	 * @param mean - lambda, the average number of occurances
	 * @param observed - the actual number of occurances observed
	 * @return ln(poissonProbability) - the natural log of the poisson probability.*/
	public static double poissonProbabilityApproximation (double mean, int observed){
		double k = observed;
		double a = k * Math.log(mean);
		double b = -1.0 * mean * Math.log(Math.E);
		return a + b - NumericalFunctionsTemp.factorialApproximation(k);
	}
	/**Convert natural log to log 10.*/
	public static double ln2Log10 (double ln){
		return ln/ Math.log(10);
	}

	/**Convert natural log to log 10.*/
	public static double ln2Minus10Log10 (double ln){
		return -10* (ln/ Math.log(10));
	}
	/**Converter*/
	public static float[] relativeDifferenceToLog2Ratio (float[] relDiff){
		float[] log2Ratios = new float[relDiff.length];
		for (int i=0; i< relDiff.length; i++){
			double r = relDiff[i];
			Double log2Ratio = new Double(Math.log((r+2.0)/(2.0-r))/ log2);
			log2Ratios[i] = log2Ratio.floatValue();
		}
		return log2Ratios;
	}

	/**Converter*/
	public static double relativeDifferenceToLog2Ratio (double relDiff){
		double ratio = (relDiff+2)/(2-relDiff);
		return Math.log(ratio)/ log2;
	}


	/**Converts milliseconds to days.*/
	public static double millisecToDays (long ms){
		double current = (double)ms;
		current = current/1000;
		current = current/60;
		current = current/60;
		current = current/24;
		return current;
	}


	/**Writes a binary int[][2] array.
	 * @return true if sucessful, false if something bad happened.*/
	public static boolean writeBinaryInt2Array(int[][] c, File file){
		try {
			int num = c.length;
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			//write length
			dos.writeInt(num);
			//write value
			for (int i=0; i<num; i++) { 
				dos.writeInt(c[i][0]);
				dos.writeInt(c[i][1]);
			}
			dos.close();
			fos.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false; 
		}
	}

	/**Reads a binary int[][2] array file.
	 * @return null if something bad happened.*/
	public static int[][] readBinaryInt2Array(File file){
		try {
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream( fis );
			//read lenth
			int num = dis.readInt();
			//make array
			int[][] c = new int[num][2];
			for (int i=0; i< num; i++) {
				c[i][0] = dis.readInt();
				c[i][1] = dis.readInt();
			}
			dis.close();
			fis.close();
			return c;
		}
		catch (Exception ioe){
			ioe.printStackTrace();
			return null;   
		} 
	}

	/**Inverts the float[].*/
	public static void invertArray(float[] x){
		int lenthMinusOne = x.length -1;
		int stop = x.length/2;
		for (int i=0; i<stop; i++){
			int switchIndex = lenthMinusOne - i;
			float first = x[i];
			float last = x[switchIndex];
			x[i] = last;
			x[switchIndex] = first;

		}
	}

	/**Converts a double[] to float[] */
	public static float[] convertToFloat(double[] d){
		float[] f = new float[d.length];
		for (int i=0; i< d.length; i++) f[i] = (float)d[i];
		return f;
	}
	
	/**Converts a double[][] to float[][] */
	public static float[][] convertToFloat(double[][] d){
		int sizeMain = d.length;
		float[][] f = new float[sizeMain][];
		for (int i=0; i< sizeMain; i++) {
			int sizeMinor = d[i].length;
			f[i] = new float[sizeMinor];
			for (int j=0; j< sizeMinor; j++){
				f[i][j] = (float)d[i][j];
			}
		}
		return f;
	}

	/**Calculates N! modified from http://www.unix.org.ua/orelly/java-ent/jnut/ch01_03.htm
	 * Good for n < 21.*/
	public static double factorial(int n) {  
		if (n < 0) return 0;  
		if (n > 22 ) return -1;
		double fact = 1;                    
		while(n > 1) {                         
			fact = fact * n;                    
			n = n - 1;                          
		}                                      
		return fact;                           
	}  

	/**Srivivasa Ramanuja ln(n!) factorial estimation, better than Stirling's approximation.
	 * Good for larger values of n.
	 * @return ln(n!)*/
	public static double factorialApproximation(double n){
		if (n < 2) return 0;
		double a = (n * Math.log(n)) - n;
		double b = (Math.log(n * (1.0+ (4 * n * (1.0 + (2.0 * n)))))) / 6.0;
		return a + b + sRConstant;
	}


	/**Converts an ArrayList of int[]s to int[][].*/
	public static int[][] arrayList2IntArrayArray (ArrayList al){
		int length = al.size();
		int[][] intArray = new int[length][];
		for (int x= 0; x < length; x++){
			intArray[x] = (int[])al.get(x);
		}
		return intArray;
	}

	
	
	


	/**Converts an array of double using -10 * (Math.log(double)/log10).
	 * Any doubles <=0 are assigned the minimal double from the array >0 then transformed.*/
	public static void convertToNeg10Log10(double[] d){
		double min = 10000000000000.0;
		//find min
		for (int i=0; i< d.length; i++){
			if (d[i] < min && d[i] > 0.0) min = d[i];
		}
		min = -10 * (Math.log(min)/log10);
		//convert
		for (int i=0; i< d.length; i++){
			if (d[i] > 0.0) d[i] = -10 * (Math.log(d[i])/log10);
			else d[i] = min;
		}
	}
	
	/**Given a HashSet of Floats returns an array of float.*/
	public static float[] hashSetToFloat(HashSet<Float> hash){
		float[] f = new float[hash.size()];
		Iterator<Float> it = hash.iterator();
		int counter = 0;
		while (it.hasNext()) f[counter++] = it.next().floatValue();
		return f;
	}
	
	/**Given a HashSet of Integers returns an array of int.*/
	public static int[] hashSetToInt(HashSet<Integer> hash){
		int[] f = new int[hash.size()];
		Iterator<Integer> it = hash.iterator();
		int counter = 0;
		while (it.hasNext()) f[counter++] = it.next().intValue();
		return f;
	}

	/**Converts to unlogged value.*/
	public static double antiLog(double loggedValue, double base){
		return Math.pow(base, loggedValue);
	}
	
	/**Converts a -10Log10(val) back to the val.*/
	public static double antiNeg10log10(float fredScore){
		double s = -1.0 * fredScore/10.0;
		return Math.pow(10, s);
	}

	/**Converts an array float[] to unlogged values.*/
	public static float[] antiLog(float[] loggedValues, int base){
		float[] values = new float[loggedValues.length];
		for (int i=0; i< loggedValues.length; i++){
			values[i] = new Double(Math.pow(base, loggedValues[i])).floatValue();
		}
		return values;
	}

	/**Scans a float array for zeros, if found returns true, other wise returns false.*/
	public static boolean findZeros(float[] f){
		for (int i=0; i< f.length; i++){
			if (f[i] == 0) return true;
		}
		return false;
	}

	/**Rotates a square matrix 90 degrees CounterClockWise?*/
	public static float[][] rotateCounterClockwise(float [][] matrix){
		int numRows = matrix.length;
		float[][] rotated = new float[numRows][numRows];
		//for each row
		for (int i=0; i< numRows; i++){
			int rX = numRows - i -1;
			//for each column
			for (int j=0; j< numRows; j++){
				rotated[j][rX] = matrix[i][j];
			}
		}
		return rotated;
	}

	/**Rotates a square matrix 90 degrees Clock Wise?*/
	public static float[][] rotateClockwise(float [][] matrix){
		int numRows = matrix.length;
		float[][] rotated = new float[numRows][numRows];
		//for each row
		for (int i=0; i< numRows; i++){
			//for each column
			for (int j=0; j< numRows; j++){
				int rY = numRows - j -1;
				rotated[rY][i] = matrix[i][j];
			}
		}
		return rotated;
	}

	/**Returns the first index containing the maximum value. */
	public static int findMaxIntIndex(int[] ints) {
		int len = ints.length;
		int max = ints[0];
		int maxIndex = 0;
		for (int i = 1; i < len; i++) {
			if (ints[i]>max) {
				max=ints[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	/**Log2s every number in the array replacing the original values.*/
	public static void log2(float[][] f){
		double log2 = Math.log(2);
		int num = f.length;
		for (int i=0; i<num; i++){
			int num2 = f[i].length;
			for (int j=0; j<num2; j++){
				f[i][j] = new Double(Math.log(f[i][j])/log2).floatValue();
			}
		}
	}


	/**Log2s every number in the array returning the values, keep original intact.*/
	public static float[][] log2Return(float[][] f){
		double log2 = Math.log(2);
		int num = f.length;
		float[][] logged = new float[num][];
		for (int i=0; i<num; i++){
			int num2 = f[i].length;
			logged[i] = new float[num2];
			for (int j=0; j<num2; j++){
				logged[i][j] = new Double(Math.log(f[i][j])/log2).floatValue();
			}
		}
		return logged;
	}

	/**Log2s every number in the array replacing the original values.*/
	public static void log2(float[] f){
		double log2 = Math.log(2);
		int num = f.length;
		for (int i=0; i<num; i++){
			f[i] = new Double(Math.log(f[i])/log2).floatValue();
		}
	}

	/**Log2s every number in the array replacing the original values.*/
	public static void log2(double[] f){
		int num = f.length;
		for (int i=0; i<num; i++){
			f[i] = Math.log(f[i])/log2;
		}
	}

	/**Log2s every number in the array keeping original intact.*/
	public static float[] log2Return(float[] f){
		double log2 = Math.log(2);
		int num = f.length;
		float[] logged = new float[num];
		for (int i=0; i<num; i++){
			logged[i] = new Double(Math.log(f[i])/log2).floatValue();
		}
		return logged;
	}	

	/**Log2s every number in the array keeping original intact.*/
	public static double[] log2ReturnDouble(float[] f){
		double log2 = Math.log(2);
		int num = f.length;
		double[] logged = new double[num];
		for (int i=0; i<num; i++){
			logged[i] = Math.log(f[i])/log2;
		}
		return logged;
	}

	/**Loads columns of double into a HashMap, the first row is parsed as Strings and used as the key.
	 * The value is a double[].
	 * Can convert log10 values in the files to log2 (for Agilent data).
	 * Skips blank lines.*/
	public static LinkedHashMap loadDoubles(File dataFile1, boolean convertLog10ToLog2){
		LinkedHashMap idValues = new LinkedHashMap();
		String line = null;
		try {
			BufferedReader in = new BufferedReader ( new FileReader (dataFile1));
			//read first line
			line = in.readLine();
			String[] ids = line.split("\\t");
			ArrayList[] values = new ArrayList[ids.length];
			//instantiate ArrayLists
			for (int i=0; i< values.length; i++) values[i] = new ArrayList(20000);
			//read in values converting to log2
			while ((line = in.readLine())!=null){
				line = line.trim();
				if (line.length() == 0) continue;
				String[] cells = line.split("\\t");
				for (int i=0; i< ids.length; i++){
					double log = Double.parseDouble(cells[i]);
					if (convertLog10ToLog2){
						log = NumericalFunctionsTemp.antiLog(log, 10);
						log = NumericalFunctionsTemp.log2(log);
					}
					values[i].add(new Double(log));
				}
			}
			//add to hash
			for (int i=0; i<ids.length; i++){
				idValues.put(ids[i], NumericalFunctionsTemp.arrayListOfDoubleToArray(values[i]));
			}
			in.close();
		} catch (Exception e){
			System.out.println(dataFile1+ " Bad line "+line);
			e.printStackTrace();
		}
		return idValues;
	}

	/**Parses doubles from a String[] of doubles. Returns null if a parsing error is encountered.*/
	public static double[] parseDoubles(String[] doubles){
		int num = doubles.length;
		double[] d = new double[num];
		try {
			for (int i=0; i<num; i++){
				d[i] = Double.parseDouble(doubles[i]);
			}
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return d;
	}

	/**Parses ints from a String[] of ints. Returns null if a parsing error is encountered.*/
	public static int[] parseInts(String[] ints){
		int num = ints.length;
		int[] d = new int[num];
		try {
			for (int i=0; i<num; i++){
				d[i] = Integer.parseInt(ints[i]);
			}
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return d;
	}

	/**Converts an array of values to z scores given their mean and standard deviation.*/
	public static double[] convertToZScores(double[] values, double mean, double stndDev){
		int num = values.length;
		double[] z = new double[num];
		for (int i=0; i< num; i++){
			z[i] = (values[i]-mean)/stndDev;
		}
		return z;
	}
	/**Converts an array of values to z scores.*/
	public static double[] convertToZScores(double[] values){
		double mean = NumericalFunctionsTemp.mean(values);
		double stndDev = NumericalFunctionsTemp.standardDeviation(values,mean);
		return convertToZScores(values, mean, stndDev);
	}

	/**Converts an array of values to z scores using median as appose to mean.*/
	public static double[] convertToMedianZScores(double[] values){
		double median = NumericalFunctionsTemp.median(values);
		double stndDev = NumericalFunctionsTemp.standardDeviation(values,median);
		return convertToZScores(values, median, stndDev);
	}

	/**Returns a new array with the newValue appended on the end.*/
	public static double[] appendDouble (double[] d, double newValue){
		double[] added = new double[d.length+1];
		System.arraycopy(d,0,added,0,d.length);
		added[d.length] = newValue;
		return added;
	}

	/**Returns a new array with the newValue appended on the beginning.*/
	public static double[] prependDouble (double[] d, double newValue){
		double[] added = new double[d.length+1];
		System.arraycopy(d,0,added,1,d.length);
		added[0] = newValue;
		return added;
	}

	/**Calculates a one-step tukey biweight estimator using 
	 * 5 as the tuning constant and adding 0.0001 to the denominator
	 * to avoid dividing by zero, Affy's params.
	 * If all zeros are submitted then returns zero.*/
	public static double tukeyBiWeight(double[] x){
		int num = x.length;

		//find median
		Arrays.sort(x);
		double m = NumericalFunctionsTemp.median(x);		

		//find median diff
		double[] diffs = new double[num];
		for (int i=0; i< num; i++){
			diffs[i] = Math.abs(x[i]-m);
		}
		Arrays.sort(diffs);
		double mad = NumericalFunctionsTemp.median(diffs);	

		//calculate ts and bs
		double[] t = new double[num];
		double[] b = new double[num];
		double top = 0;
		double bottom = 0;
		for (int i=0; i<num; i++){
			//calc t
			t[i] = Math.abs((x[i] - m) / ((5 * mad)) + 0.0001);		
			//calc b
			if (t[i] <1){
				double w = t[i] * t[i];
				w = (1-w);
				b[i] = w * w;
			}
			else b[i] = 0;
			//increment sums
			top += (b[i] * x[i]);
			bottom += b[i];
		}
		if (bottom == 0) return 0;
		return top/bottom;
	}

	/**Geometric mean. Cannot have any zeros or negative numbers in the double[].*/
	public static double geometricMean(double[] x) {
		int num = x.length;
		//take logs
		double[] logs = new double[num];
		for (int i=0; i< num; i++){
			logs[i] = Math.log(x[i]);
		}
		//mean
		double mean = NumericalFunctionsTemp.mean(logs);
		//final
		return Math.pow(Math.E, mean);
	}

	/**Returns the value of a given percentile from a SORTED array.
	 * Percentile is from 0-1, ie 0.95 and is according to Lentner, 1982.*/
	public static float percentile(float[] sortedFloats, double percentile){
		//calculate index
		double index = ((percentile * (double)sortedFloats.length)) - 0.5;
		int trunkIndex = (int)index;
		//is it a whole number?
		double rnd = index%1;
		if (rnd<0.00001 || rnd> 0.99999){
			return sortedFloats[trunkIndex];
		}
		//otherwise average trunk and trunk +1
		else {
			return (sortedFloats[trunkIndex] + sortedFloats[trunkIndex+1])/2;
		}
	}


	/**Runs through float[][] setting any values that exceed the outlierThreshold
	 * to zero. Assumes float[i]s are equal length.*/
	public static void maskOutliers (float[][] f, float outlierThreshold){
		int numA = f.length;
		int numB = f[0].length;
		for (int i=0; i< numA; i++){
			for (int j=0; j< numB; j++){
				if (f[i][j]>outlierThreshold) f[i][j] = 0;
			}
		}
	}

	/**Returns the number of elements/ values in the array.*/
	public static int countValues(int[][] i){
		int num = i.length;
		int count = 0;
		for (int h=0; h<num; h++){
			count += i[h].length;
		}
		return count;
	}

	/**Returns the number of objects in the arrays.*/
	public static int countObjects(Object[][] i){
		int num = i.length;
		int count = 0;
		for (int h=0; h<num; h++){
			count += i[h].length;
		}
		return count;
	}

	/**Counts the number of values that are > or < threshold based on boolean.
	 * Returns - number of outliers > or < threshold
	 * Param - exceedsThreshold, boolean flag when true counts the number that
	 * exceed the threshold, when false the number less than the threshold.*/
	public static int countOutliers(float[] f, double threshold, boolean exceedsThreshold){
		int num = f.length;
		int count = 0;
		if (exceedsThreshold) {
			for (int i=0; i<num; i++) if (f[i]>threshold) count++;
		}
		else {
			for(int i=0; i<num; i++) if (f[i]<threshold) count++;
		}
		return count;
	}

	/**Loads a zeroed matrix with values that are > or < threshold based on boolean.
	 * Returns a loaded zeroed matrix.
	 * Param exceedsThreshold, boolean flag when true returns the values that
	 * exceed the threshold,  less than the threshold.*/
	public static float[][] identifyOutliers(float[][] f, int[][] controls, float threshold, boolean exceedsThreshold){
		//make a zeroed float[][] to load with outliers
		float[][] outliers = NumericalFunctionsTemp.zeroedFloatArray(f.length, f.length);
		//walk through control coordinates, fetch original matrix values and look for outliers, if found add to new
		int num = controls.length;
		if (exceedsThreshold) {
			for (int i=0; i<num; i++){
				int x = controls[i][0];
				int y = controls[i][1];
				if (f[x][y]> threshold)outliers[x][y] = f[x][y];
			}
		}
		else {
			for (int i=0; i<num; i++){
				int x = controls[i][0];
				int y = controls[i][1];
				if (f[x][y]< threshold)outliers[x][y] = f[x][y];
			}
		}
		return outliers;
	}

	/**Given a square table and a list of coordinates, will return the associated values.*/
	public static float[] fetchMatrixValues (float[][] matrix, int[][] xy){
		int numValues = xy.length;
		float[] floats = new float[numValues];
		for (int i=0; i<numValues; i++){
			floats[i]= matrix [xy[i][0]]  [xy[i][1]];
		}
		return floats;
	}



	/**Given a square table and a list of coordinates, will return the associated values 
	 * in a new zeroed square table.*/
	public static float[][] loadMatrixValues (float[][] matrix, int[][] xy){
		int numValues = xy.length;
		float[][] floats = zeroedFloatArray(matrix.length,matrix.length);
		for (int i=0; i<numValues; i++){
			floats[xy[i][0]][xy[i][1]] = matrix[xy[i][0]][xy[i][1]];
		}
		return floats;
	}

	/**Calculates the Q1,Q2,and Q3 (25th, 50th, and  75th percentile).
	 * Ignores the middle value in calculating Q1 and Q3 if the array length is odd.
	 * Don't forget to sort the array!*/
	public static double[] quartiles(float[] sortedFloatArray){
		double p25th;
		double p50th;
		double p75th;
		int middle = sortedFloatArray.length/2;  // subscript of middle element
		int middleLeft = middle/2;
		int middleRight = middle+middleLeft+1;
		//Odd number of elements -- return the middle one.
		if (sortedFloatArray.length%2 == 1) {
			p50th = sortedFloatArray[middle];
			p25th = ((double)sortedFloatArray[middleLeft-1] + (double)sortedFloatArray[middleLeft]) / 2.0;
			p75th = ((double)sortedFloatArray[middleRight-1] + (double)sortedFloatArray[middleRight]) / 2.0;
		}
		// Even number -- return average of middle two
		else {
			//average
			p50th = ((double)sortedFloatArray[middle-1] + (double)sortedFloatArray[middle]) / 2.0;
			p25th = ((double)sortedFloatArray[middleLeft-1] + (double)sortedFloatArray[middleLeft]) / 2.0;
			p75th = ((double)sortedFloatArray[middleRight-1] + (double)sortedFloatArray[middleRight]) / 2.0;
		}
		return new double[] {p25th, p50th, p75th};
	}


	/**Makes a float[x][y] and fills it with zeros.*/
	public static float[][] zeroedFloatArray (int x, int y){	
		//make filtering array, fill with zeros
		float[][] intensities = new float[x][y];
		for (int i= x-1; i>=0; i--){
			for (int j= y-1; j>=0; j--){
				intensities[i][j]=0;
			}
		}
		return intensities;
	}
	/**Makes a float[x][y] and fills it with the value.*/
	public static float[][] loadFloatArray (int x, int y, float value){	
		//make filtering array, fill with zeros
		float[][] intensities = new float[x][y];
		for (int i= x-1; i>=0; i--){
			for (int j= y-1; j>=0; j--){
				intensities[i][j]=value;
			}
		}
		return intensities;
	}

	/**Returns an array of float[numToSample] populated with randomly picked values from x.*/
	public static float[] randomSample(float[] x, int numToSample){
		float[] z = new float[numToSample];
		Random generator = new Random();
		for (int i=0; i<z.length; i++){
			z[i] = x[ generator.nextInt(x.length)];
		}
		return z;
	}

	/**Randomizes the array by permutating indexes and swapping. Fast.
	 * Can set seedMultiplier  or vary it to be sure and get diff*/
	public static void randomize(float[] f, long seed){
		int len = f.length;
		float current;
		float random;
		int index;
		Random generator = new Random(seed);
		for (int i=0; i<len; i++){
			index = generator.nextInt(len);
			current = f[i];
			random = f[index];
			f[i] = random;
			f[index] = current;
		}
	}


	/**Randomizes the first array by permutating indexes and swapping. Fast.
	 * Shuffles f[shuffle][].*/
	public static void randomizeFirstArray(float[][] f, long seed){
		int len = f.length;
		float[] current;
		float[] random;
		int index;
		Random generator = new Random(seed);
		for (int i=0; i<len; i++){
			index = generator.nextInt(len);
			current = f[i];
			random = f[index];
			f[i] = random;
			f[index] = current;
		}
	}

	/**Randomizes the intensities, not between replicas.
	 * @param float[replicas][intensities]*/
	public static void randomize (float[][] f){
		long seed = System.currentTimeMillis();
		for (int i=0; i<f.length; i++){
			seed +=i;
			randomize(f[i], seed);
		}
	}

	/**Given two float[replica][intensities] for treat and cont, for each oligo makes and returns all pairwise ratios 
	 * between treat and control replicas.
	 * @param float[replica][intensities] for treatment and control
	 * @return float[oligo index][all t/c ratios]*/
	public static float[][] layeredRatiosSeperate(float[][] treatment, float[][]control){
		int numOligos = treatment[0].length;
		int numTreat = treatment.length;
		int numCont = control.length;
		int totalRatiosPerOligo = numTreat*numCont;
		float[][] layers = new float[numOligos][];
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			float[] oligoRatios = new float[totalRatiosPerOligo];
			int counter = 0;
			//for each treatment
			for (int y=0; y< numTreat; y++){
				//for each control
				for (int z=0; z< numCont; z++){
					oligoRatios[counter++] = treatment[y][x]/control[z][x];
				}
			}
			layers[x] = oligoRatios;
		}
		return layers;
	}

	/**Given two float[replica][intensities] for treat and cont, for each oligo makes and returns all pairwise relative differences 
	 * between treat and control replicas.
	 * @param float[replica][intensities] for treatment and control
	 * @return float[oligo index][all relative differences]*/
	public static float[][] layeredRelativeDifferencesSeperate(float[][] treatment, float[][]control){
		int numOligos = treatment[0].length;
		int numTreat = treatment.length;
		int numCont = control.length;
		int totalScoresPerOligo = numTreat*numCont;
		float[][] layers = new float[numOligos][];
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			float[] oligoRelDiffs = new float[totalScoresPerOligo];
			int counter = 0;
			//for each treatment
			for (int y=0; y< numTreat; y++){
				//for each control
				for (int z=0; z< numCont; z++){
					oligoRelDiffs[counter++] = 2.0f *((treatment[y][x] - control[z][x]) / (treatment[y][x] + control[z][x]));
				}
			}
			layers[x] = oligoRelDiffs;
		}
		return layers;
	}


	/**Given two float[replica][values] for treat and cont, for each replica makes all pairwise relative differences 
	 * between treat and control. Returns as a big pool.*/
	public static double[] layeredRelativeDifferences(float[][] oligoTreat, float[][]oligoCont){
		int numOligos = oligoTreat[0].length;
		int numTreat = oligoTreat.length;
		int numCont = oligoCont.length;
		int totalRatios = numTreat*numCont*numOligos;
		double[] ratios = new double[totalRatios];
		int counter = 0;
		//for each oligo, calculate all relative differences between treatments and controls
		for (int x=0; x< numOligos; x++){
			for (int y=0; y< numTreat; y++){
				for (int z=0; z< numCont; z++){
					ratios[counter++] = NumericalFunctionsTemp.relativeDifference(oligoTreat[y][x], oligoCont[z][x]);
				}
			}
		}
		return ratios;
	}

	/**Given two float[replica][intensities] for treat and cont, for each oligo makes and returns all pairwise log2 ratios 
	 * between treat and control replicas.
	 * @param float[replica][intensities] for treatment and control
	 * @return float[oligo index][all log2(t/c) ratios]*/
	public static float[][] layeredLogRatiosSeperate(float[][] treatment, float[][]control){
		int numOligos = treatment[0].length;
		int numTreat = treatment.length;
		int numCont = control.length;
		int totalRatiosPerOligo = numTreat*numCont;
		double log2 = Math.log(2);
		float[][] layers = new float[numOligos][];
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			float[] oligoRatios = new float[totalRatiosPerOligo];
			int counter = 0;
			//for each treatment
			for (int y=0; y< numTreat; y++){
				//for each control
				for (int z=0; z< numCont; z++){
					oligoRatios[counter++] = new Double (Math.log(treatment[y][x]/control[z][x]) / log2).floatValue();
				}
			}
			layers[x] = oligoRatios;
		}
		return layers;
	}

	/**Given two float[replica][values] for treat and cont, for each replica makes all pairwise ratios 
	 * between treat and control.*/
	public static double[] layeredRatios(float[][] oligoTreat, float[][]oligoCont){
		int numOligos = oligoTreat[0].length;
		int numTreat = oligoTreat.length;
		int numCont = oligoCont.length;
		int totalRatios = numTreat*numCont*numOligos;
		double[] ratios = new double[totalRatios];
		int counter = 0;
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			for (int y=0; y< numTreat; y++){
				for (int z=0; z< numCont; z++){
					ratios[counter++] = oligoTreat[y][x]/oligoCont[z][x];
				}
			}
		}
		return ratios;
	}

	/**Given two float[replica][values] for treat and cont, for each replica makes matched pairwise ratios 
	 * between treat and control. Must have equal number of replicas for both T and C.*/
	public static double[] pairedRatios(float[][] treatments, float[][]controls){
		int numOligos = treatments[0].length;
		int numReplicas = treatments.length;
		int totalRatios = numReplicas*numOligos;
		double[] ratios = new double[totalRatios];
		int counter = 0;
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			//for each replica, make pair
			for (int y=0; y< numReplicas; y++){
				ratios[counter++] = treatments[y][x]/ controls[y][x];
			}
		}
		return ratios;
	}


	/**Given two float[replica][values] for treat and cont, for each replica makes matched pairwise relative differences 
	 * between treat and control. Must have equal number of replicas for both T and C.*/
	public static double[] pairedRelativeDifferences(float[][] treatments, float[][]controls){
		int numOligos = treatments[0].length;
		int numReplicas = treatments.length;
		int totalRatios = numReplicas*numOligos;
		double[] ratios = new double[totalRatios];
		int counter = 0;
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			//for each replica, make pair
			for (int y=0; y< numReplicas; y++){
				ratios[counter++] = NumericalFunctionsTemp.relativeDifference(treatments[y][x], controls[y][x]);
			}
		}
		return ratios;
	}


	/**Given two float[replica][values] for treat and cont, for each replica makes all pairwise 
	 * log2 ratios between treat and control.
	 * Zero ratios are assigned a log2 of 0.0000000001*/
	public static double[] layeredLogRatios(float[][] oligoTreat, float[][]oligoCont){
		int numOligos = oligoTreat[0].length;
		int numTreat = oligoTreat.length;
		int numCont = oligoCont.length;
		int totalRatios = numTreat*numCont*numOligos;
		double[] ratios = new double[totalRatios];
		int counter = 0;
		double log2 = Math.log(2);
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			for (int y=0; y< numTreat; y++){
				for (int z=0; z< numCont; z++){
					double r = oligoTreat[y][x]/oligoCont[z][x];
					if (r > 0 ) {
						ratios[counter] = Math.log(r) /log2;
					}
					else {
						ratios[counter] = 0.0000000001;
					}
					counter++;
				}
			}
		}
		return ratios;
	}

	/**Given two float[replica][values] for treat and cont, for each replica makes matched pairwise log ratios 
	 * between treat and control. Must have equal number of replicas for both T and C.*/
	public static double[] pairedLogRatios(float[][] treatments, float[][]controls){
		int numOligos = treatments[0].length;
		int numReplicas = treatments.length;
		int totalRatios = numReplicas*numOligos;
		double[] ratios = new double[totalRatios];
		int counter = 0;
		//for each oligo, calculate all ratios between treatments and controls
		for (int x=0; x< numOligos; x++){
			//for each replica, make pair
			for (int y=0; y< numReplicas; y++){
				double r = treatments[y][x]/ controls[y][x];
				if (r > 0 ) {
					ratios[counter] = Math.log(r) /log2;
				}
				else {
					ratios[counter] = 0.0000000001;
				}
				counter++;

			}
		}
		return ratios;
	}

	/**Takes unlogged MAT normalized t-values, sorts, logs and takes a variant of a number stabilized trimmed mean ratio.
	 * Returns 1 if trimmed mean will zero the number of values.*/
	public static double matScore(float[] t, float[] c){
		//proc T
		Arrays.sort(t);
		double[] logT = NumericalFunctionsTemp.log2ReturnDouble(t);
		int trimNumberT = (int)Math.round(((double)t.length) * 0.1);
		int numAfterTrimming = t.length-(2*trimNumberT);
		if (numAfterTrimming <= 0) return 1;
		double trMeanT = trimmedMean(logT, trimNumberT);

		//proc C
		Arrays.sort(c);
		double[] logC = NumericalFunctionsTemp.log2ReturnDouble(c);
		int trimNumberC = (int)Math.round(((double)c.length) * 0.1);
		numAfterTrimming = c.length-(2*trimNumberC);
		if (numAfterTrimming <= 0) return 1;

		double trMeanC = trimmedMean(logC, trimNumberC);
		return (Math.sqrt(trimNumberT) * trMeanT)  -  (Math.sqrt(trimNumberC) * trMeanC);
	}


	/**Returns a Hodges-Lehmann estimator, the median of all pairwise means.*/
	public static double pseudoMedian (double[] d){
		int len = d.length;
		int numPairs = (len*(len-1))/2;
		double[] means = new double[numPairs];
		int counter = 0;
		for (int i=0; i< len; i++){
			double one = d[i];
			for (int j=i+1; j< len; j++){
				double two = d[j];
				means[counter++] = (one+two)/2;
			}
		}
		Arrays.sort(means);
		return median(means);
	}

	/**Returns the integer square root from zero to num.*/
	public static float[] squareRoots(int num){
		float[] sqrs = new float[num];
		for (int i=1; i< num; i++){
			sqrs[i] = new Double(Math.sqrt(i)).floatValue();
		}
		return sqrs;
	}

	/**Returns all pairwise averages.*/
	public static float[] pairwiseMeans (float[] d){
		int len = d.length;
		int numPairs = (len*(len-1))/2;
		float[] means = new float[numPairs];
		int counter = 0;
		for (int i=0; i< len; i++){
			for (int j=i+1; j< len; j++){
				means[counter++] = (d[i]+d[j])/2.0f;
			}
		}
		return means;
	}

	/**Returns all pairwise averages.*/
	public static double[] pairwiseMeansDouble (float[] d){
		int len = d.length;
		int numPairs = (len*(len-1))/2;
		double[] means = new double[numPairs];
		int counter = 0;
		for (int i=0; i< len; i++){
			for (int j=i+1; j< len; j++){
				means[counter++] = (d[i]+d[j])/2.0f;
			}
		}
		return means;
	}

	/**Returns all pairwise averages between a and b but not self pairwise.*/
	public static double[] partialPairwiseMeans (float[] a, float[] b){
		int numPairs = a.length * b.length;
		double[] means = new double[numPairs];
		int counter = 0;
		for (int i=0; i< a.length; i++){
			for (int j=0; j< b.length; j++){
				means[counter++] = (a[i]+b[j])/2.0;
			}
		}
		return means;
	}

	/**Concatinates the float[]s, variable sizes OK.*/
	public static float[] concatinate(float[][] f){
		//calc size
		int size = 0;
		for (int i=0; i< f.length; i++){
			size+= f[i].length;
		}
		//make new
		float[] concat = new float[size];
		int index =0;
		for (int i=0; i< f.length; i++){
			System.arraycopy(f[i], 0, concat, index, f[i].length);
			index += f[i].length;
		}
		return concat;
	}

	/**Joins two float[]s using System.arraycopy().*/
	public static float[] concatinate(float[] one, float[] two){
		float[] merge = new float[one.length+two.length];
		System.arraycopy(one,0,merge,0,one.length);
		System.arraycopy(two,0,merge,one.length,two.length);
		return merge;
	}


	/** Builds a sub array from part of a float[replica][intensities].
	 * @param float[replicas][intensities]*/
	public static float[][] subArray(float[][] intensities, int startIndex, int stopIndex){
		int numIntensities = 1+stopIndex-startIndex;
		float[][] sub = new float[intensities.length][numIntensities];
		//for each intensity/ oligo
		int counter=0;
		for (int j=startIndex; j<=stopIndex; j++){
			//for each replica
			for (int i=0; i<intensities.length; i++) { 
				sub[i][counter] = intensities[i][j];
			}
			counter++;
		}
		return sub;
	}


	/**Calculates the pseudoMedian on each replica of intensities. If only one intensity,
	 * sets that as the pseudo median.
	 * @param repInt float[replica index][actual intensity values]*/
	public static double[] pseudoMedian(float[][] repInt){
		double[] sum = new double[repInt.length];
		//for each replica
		for (int i=0; i< repInt.length; i++){
			if (repInt[i].length == 1) sum[i] = repInt[i][0];
			else sum[i] = NumericalFunctionsTemp.pseudoMedian(repInt[i]);
		}
		return sum;
	}

	/**Returns a Hodges-Lehmann estimator, the median of all pairwise means.
	 * If only one pairwise mean, return the mean instead of the median on the means.*/
	public static double pseudoMedian (float[] d){
		int len = d.length;
		//only two values?
		if (len == 2) return mean(d);
		if (len == 1) return d[0];
		int numPairs = (len*(len-1))/2;
		double [] means = new double[numPairs];
		int counter = 0;
		for (int i=0; i< len; i++){
			double one = d[i];
			for (int j=i+1; j< len; j++){
				double two = d[j];
				means[counter++] = (one+two)/2;
			}
		}
		Arrays.sort(means);
		return median(means);
	}

	/**Removes zero values and their assoicated values from all arrays.
	 * Pass two float[numChips][oligoValues] representing treatment and control chips.
	 * The num oligoValues should be the same, the numChips can vary between t and c.
	 * Returns float[2][][] representing t[][] and c[][].*/
	public static float[][][] removeZeroValues (float[][] t, float[][] c){
		int windowSize = t[0].length;
		int numTreatments = t.length;
		int numControls = c.length;
		int numZeros = 0;
		float[][][] mod = new float[2][][];

		//first count the number of zeros found

		//for each oligo look for a zero
		for (int x=0; x< windowSize; x++){
			boolean zeroFound = false;
			//count number of zeros in treatment
			for (int y=0; y<numTreatments; y++){
				if (t[y][x] == 0){					
					numZeros++;
					zeroFound = true;
					break;
				}
			}
			//count number of zeros in control
			if (zeroFound == false){
				for (int y=0; y<numControls; y++){
					if (c[y][x] == 0){
						numZeros++;
						break;
					}
				}
			}	
		}

		//if any zeros present then make new arrays
		if (numZeros != 0){
			int newWindowSize = windowSize - numZeros;
			float[][] tMod = new float[numTreatments][newWindowSize];
			float[][] cMod = new float[numControls][newWindowSize];
			int counter = 0;
			for (int x=0; x< windowSize; x++){
				boolean zeroFound = false;
				//treatment
				for (int y=0; y<numTreatments; y++){
					if (t[y][x] == 0){
						zeroFound = true;
						break;
					}
					else{
						tMod[y][counter] = t[y][x];
					}
				}
				//count number of zeros in control
				if (zeroFound == false){
					for (int y=0; y<numControls; y++){
						if (c[y][x] == 0){
							zeroFound = true;
							break;
						}
						else{
							cMod[y][counter] = c[y][x];
						}
					}
				}	
				//only advance counter if no zeros found
				if (zeroFound == false) counter++;
				//watch out for zeros at the end of the array
				if (counter == newWindowSize) break;
			}

			//set final
			mod[0] = tMod;
			mod[1] = cMod;
		}
		else {
			mod[0] = t;
			mod[1] = c;
		}
		return mod;
	}

	/**Sets values exceeding the cutoff to the default.*/
	public static float[] trimOutliers(float[] f, float cutoff, float replacement){
		int num = f.length;
		for (int i=0; i< num; i++){
			if (f[i] > cutoff) {
				f[i] = replacement;
			}
		}
		return f;
	}

	/**For each f[replica][]Sets values exceeding the cutoff to the default.*/
	public static float[][] trimOutliers(float[][] f, float cutoff, float replacement){
		int num = f.length;
		for (int i=0; i< num; i++){
			f[i] = trimOutliers(f[i], cutoff, replacement);
		}
		return f;
	}



	/**Median normalize unsorted array to a given number.*/
	public static float[] medianNormalize (float[] f, float targetMedian){
		int num = f.length;
		//find median
		float[] sorted = new float[num];
		System.arraycopy(f,0,sorted,0,num);
		Arrays.sort(sorted);
		double median;
		median = median(sorted);
		//make scalar
		double scalar = targetMedian/median;
		//correct new
		System.arraycopy(f,0,sorted,0,num);
		for (int i=0; i<num; i++){
			Double scaled = new Double((double)sorted[i] * scalar);
			sorted[i] = scaled.floatValue();
		}
		return sorted;
	}

	/**Median normalize unsorted array to a given number.*/
	public static double[] medianNormalize (double[] f, double targetMedian){
		int num = f.length;
		//find median
		double[] sorted = new double[num];
		System.arraycopy(f,0,sorted,0,num);
		Arrays.sort(sorted);
		double median;
		median = median(sorted);
		//make scalar
		double scalar = targetMedian/median;
		//correct new
		System.arraycopy(f,0,sorted,0,num);
		for (int i=0; i<num; i++){
			sorted[i] = sorted[i] * scalar;
		}
		return sorted;
	}

	/**Median normalizes an unsorted float[][] array to a given target.*/
	public static float[][] medianNormalize (float[][] f, double targetMedian){
		//find median
		float[] sorted = collapseFloatArray(f);
		Arrays.sort(sorted);
		double median;
		median = median(sorted);
		//make scalar
		double scalar = targetMedian/median;
		//correct new
		int num = f.length;
		for (int i=0; i<num; i++){
			int num2= f[i].length;
			for (int j=0; j<num2; j++){
				Double scaled = new Double(((double)f[i][j]) * scalar);
				f[i][j] = scaled.floatValue();
			}
		}
		return f;
	}

	/**Calculates the median value of a sorted float[] ignoring leading zero values!*/
	public static double medianIgnoreZeros(float[] m) {
		//find start of actual values not zeros
		int start = 0;
		for (int i=0; i< m.length; i++){
			if (m[i]!= 0){
				start = i;
				break;
			}
		}
		int length = m.length-start;
		int middle = length/2;  // subscript of middle element
		//Odd number of elements -- return the middle one.
		if (m.length%2 == 1) return m[start+ middle];
		// Even number -- return average of middle two
		return ((double)m[start + middle-1] + (double)m[start+ middle]) / 2.0;
	}	

	/**Convert to int[]*/
	public static int[] convertToInt(double[] d){
		int num = d.length;
		int[] ints = new int[num];
		for (int i=0; i<num; i++){
			ints[i] = (int)Math.round(d[i]);
		}
		return ints;
	}
	/**Convert to double[]*/
	public static double[] convertToDouble(int[] d){
		int num = d.length;
		double[] ints = new double[num];
		for (int i=0; i<num; i++){
			ints[i] = d[i];
		}
		return ints;
	}	
	/**Convert to int[]*/
	public static int[] convertToInt(float[] d){
		int num = d.length;
		int[] ints = new int[num];
		for (int i=0; i<num; i++){
			ints[i] = Math.round(d[i]);
		}
		return ints;
	}
	/**Convert to float[]*/
	public static float[] convertToFloat(int[] d){
		int num = d.length;
		float[] floats = new float[num];
		for (int i=0; i<num; i++){
			floats[i] = d[i];
		}
		return floats;
	}

	/**Calculates relative differences. Uses 2 * ( (t-c)/(t+c) )  */
	public static double[] relativeDifferences(float[] t, float[] c){
		int num = t.length;
		double[] relDiffs = new double[num];
		for (int i=num-1; i>=0; i--){
			relDiffs[i] = 2 * ( (double)(t[i]-c[i]) / (double)(t[i]+c[i])   );
		}
		return relDiffs;
	}

	/**Calculates a relative difference. Uses 2 * ( (t-c)/(t+c) )  */
	public static double relativeDifference(double t, double c){
		return 2 * (  (t - c) / (t + c)   );
	}

	/**Calculates log base 2 ratios.*/
	public static double[] logRatios(double[] t, double[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=num-1; i>=0; i--){
			ratios[i] = log2(t[i]/c[i]);
		}
		return ratios;
	}
	/**Calculates log base 2 ratios.*/
	public static double[] logRatios(float[] t, float[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=num-1; i>=0; i--){
			ratios[i] = log2(t[i]/c[i]);
		}
		return ratios;
	}

	/**Rounds a double[] based on number of desired decimals, ie 1 = x.x, 2 = x.xx
	 * limited to 6*/
	public static double[] round(double[] d, int numDecimals ){
		if (numDecimals<0 || numDecimals>6) return null;
		int[] multipliers = {1,10,100,1000,10000,100000,1000000};
		double[] rounded = new double[d.length];
		for (int i=d.length-1; i>=0; i--) {
			double comp = d[i]*multipliers[numDecimals];
			comp = Math.round(comp);
			rounded[i] = comp/multipliers[numDecimals];
		}
		return rounded;
	}

	/**Rounds a double based on number of desired decimals, ie 1 = x.x, 2 = x.xx
	 * limited to 6*/
	public static double round(double d, int numDecimals ){
		if (numDecimals<0 || numDecimals>6) return d;
		int[] multipliers = {1,10,100,1000,10000,100000,1000000};
		double comp = d*multipliers[numDecimals];
		comp = Math.round(comp);
		return comp/multipliers[numDecimals];
	}

	/**Removes any NaN or Infinite*/
	public static double[] removeNaNInfinite(double[] d){
		int num = d.length;
		boolean ok = true;
		for (int i=0; i<num; i++){
			if (Double.isNaN(d[i]) || Double.isInfinite(d[i]) ){
				ok = false;
				break;
			}
		}
		if (ok) return d;
		//not ok clear bad ones
		ArrayList a = new ArrayList(num);
		for (int i=0; i<num; i++){
			if (Double.isNaN(d[i]) == false && Double.isInfinite(d[i]) == false ){
				a.add(new Double(d[i]));
			}
		}
		return arrayListOfDoubleToArray(a);
	}

	/**Returns the LOG base 10 of the number.*/
	public static double log10(double number){
		return Math.log(number)/log10;
	}

	/**Returns the LOG base 2 of the number.*/
	public static double log2(double number){
		return Math.log(number)/log2;
	}

	/**Returns the LOG base 2 of the number.*/
	public static float log2(float number){
		return (new Double (Math.log((double)number)/log2)).floatValue();
	}

	/**Returns the -10 * LOG base 10 of the number.*/
	public static double minus10log10(double pvalue){
		return -10 * (Math.log(pvalue)/log10);
	}

	/**Loads matrix of double[row number][double columns], white space delimited.
	 * # comment lines and blank lines ignored.
	 * */
	public static double[][] loadDoubleMatrix(File f){
		double[][] matrix = null;
		try{
			ArrayList al = new ArrayList();
			BufferedReader in = new BufferedReader ( new FileReader (f) );
			String line;
			String[] tokens;
			while ((line=in.readLine()) != null){
				line = line.trim();
				if (line.length() ==0 || line.startsWith("#"))continue;
				tokens = line.split("\\s+");
				double[] tds = new double[tokens.length];
				for (int i=0; i< tds.length; i++) tds[i] = Double.parseDouble(tokens[i]);
				al.add(tds);
			}
			//convert to array
			int size = al.size();
			matrix = new double[size][];
			for (int i=0; i< size; i++) matrix[i]= (double[]) al.get(i);

		} catch (Exception e){
			e.printStackTrace();
		}
		return matrix;
	}

	/**Loads a column of ints from a file into an array.
	 * Returns null if nothing found.
	 * Skips blank lines.*/
	public static int[] loadInts(File f){
		BufferedReader in ;
		int[] values = null;
		try{
			in = new BufferedReader (new FileReader(f));
			ArrayList al = new ArrayList();
			String line;
			while((line= in.readLine()) != null){
				line = line.trim();
				if (line.length() == 0) continue;
				al.add(Integer.valueOf(line));
			}
			values = NumericalFunctionsTemp.arrayListOfIntegerToInts(al);
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return values;
	}



	/**Loads a column of float from a file into an array.
	 * Returns null if nothing found.
	 * Skips blank lines.*/
	public static float[] loadFloats(File f){
		BufferedReader in ;
		float[] values = null;
		try{
			in = new BufferedReader (new FileReader(f));
			ArrayList al = new ArrayList();
			String line;
			while((line= in.readLine()) != null){
				line = line.trim();
				if (line.length() == 0) continue;
				al.add(Float.valueOf(line));
			}
			values = NumericalFunctionsTemp.arrayListOfFloatToArray(al);
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return values;
	}

	/**Writes a float array to file, one per line.*/
	public static boolean writeToFile (float[] floats, File floatFile){
		try{
			PrintWriter out = new PrintWriter (new FileWriter (floatFile));
			for (int i=0; i< floats.length; i++) out.println(floats[i]);
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**Writes a float array to file, one per line.*/
	public static boolean writeToFile (double[] doubles, File doubleFile){
		try{
			PrintWriter out = new PrintWriter (new FileWriter (doubleFile));
			for (int i=0; i< doubles.length; i++) out.println(doubles[i]);
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**ArrayList of Integer to int[]*/
	public static int[] arrayListOfIntegerToInts(ArrayList integers){
		int size = integers.size();
		int[] values = new int[size];
		for (int i=0; i< size; i++){
			values[i] = ((Integer)integers.get(i)).intValue();
		}
		return values;
	}

	/**ArrayList of Double to double[]*/
	public static double[] arrayListToDoubles(ArrayList doubles){
		int size = doubles.size();
		double[] values = new double[size];
		for (int i=0; i< size; i++){
			values[i] = ((Double)doubles.get(i)).doubleValue();
		}
		return values;
	}

	/**Attemps to parse an int, returns failNumber if it fails.*/
	public static int parseInt(String stringInt, int failNumber){
		try {
			return Integer.parseInt(stringInt);
		}catch(Exception e){
		}//leave empty
		return failNumber;
	}

	

	/**Remove zero values from float array.*/
	public static float[] removeZeroValues(float[] f){
		int num = f.length;
		ArrayList al = new ArrayList(num);
		for (int i=0; i<num; i++){
			if (f[i] != 0) al.add(new Float(f[i]));
		}
		return NumericalFunctionsTemp.arrayListOfFloatToArray(al);
	}

	/**Gets the average of the integers bracketed and including the start and end.
	 * (ie 3,6  returns the average of 3+4+5+6/4= 4.5)*/
	public static float getAverageInts (int start, int end){
		int endOne = end+1;
		int len = endOne-start;
		int sum =0;
		for (int i= start; i<endOne; i++) sum+=i;
		return (float)sum/(float)len;
	}

	/**Any length OK of the int[]s*/
	public static int[] collapseIntArray (int[][] ints){
		int total = NumericalFunctionsTemp.countValues(ints);
		int[] combine = new int[total];
		int k =0;
		for (int i=0; i< ints.length; i++){
			for (int j=0; j< ints[i].length; j++){
				combine[k++] = ints[i][j];
			}
		}
		return combine;
	}

	/**@param replicasIntensities - float[replicaNumber][associatedIntensities]
	 * @return comma delimited list of medians, one for each replica.*/
	public static String medianFloatArray (float[][] replicasIntensities){
		StringBuffer sb = new StringBuffer();
		Arrays.sort(replicasIntensities[0]);
		double median = NumericalFunctionsTemp.median(replicasIntensities[0]);
		sb.append(median);
		for (int i=1; i< replicasIntensities.length; i++){
			sb.append(",");
			Arrays.sort(replicasIntensities[i]);
			median = NumericalFunctionsTemp.median(replicasIntensities[i]);
			sb.append(median);
		}
		return sb.toString();
	}

	/**Assumes equal lengths of the float[]s*/
	public static float[] collapseFloatArray (float[][] fs){
		int numI = fs.length;
		int numJ = fs[0].length;
		float[] combine = new float[numI * numJ];
		int k =0;
		for (int i=0; i< numI; i++){
			for (int j=0; j< numJ; j++){
				combine[k++] = fs[i][j];
			}
		}
		return combine;
	}

	/**Assumes equal lengths of the float[]s*/
	public static float[] collapsePartOfAnArray (float[][] fs, int startIndex, int stopIndex){
		int numI = fs.length;
		int numJ = fs[0].length;
		float[] combine = new float[numI * numJ];
		int k =0;
		for (int i=0; i< numI; i++){
			for (int j=0; j< numJ; j++){
				combine[k++] = fs[i][j];
			}
		}
		return combine;
	}

	/**Assumes equal lengths of the double[]s*/
	public static double[] collapseDoubleArray (double[][] fs){
		int numI = fs.length;
		int numJ = fs[0].length;
		double[] combine = new double[numI * numJ];
		int k =0;
		for (int i=0; i< numI; i++){
			for (int j=0; j< numJ; j++){
				combine[k++] = fs[i][j];
			}
		}
		return combine;
	}

	/**Converts a float[] to float[][], must be certain about the number of rows and colmns!*/
	public static float[][] expandFloatArray(float[] f, int numRows, int numColumns){
		float[][] e = new float[numRows][numColumns];
		int counter = 0;
		for (int i=0; i<numRows; i++){
			for (int j=0; j<numColumns; j++){
				e[i][j]= f[counter++];
			}
		}
		return e;
	}

	/**Averages a int[]*/
	public static double averageIntArray(int[] f){
		double tTot = 0;
		int lenTreat = f.length;
		for (int i=0; i< lenTreat; i++) tTot+=f[i];
		return (double)tTot/(double)lenTreat;
	}	

	/**Averages a float[]*/
	public static double averageFloatArray(float[] f){
		double tTot = 0;
		int lenTreat = f.length;
		for (int i=0; i< lenTreat; i++) tTot+=f[i];
		return (double)tTot/(double)lenTreat;
	}

	/**Finds min and max values of a int array.*/
	public static int[] findMinMaxIntValues(int[] f){
		int min = f[0];
		int max = f[0];
		int num = f.length;
		for (int i=0; i<num; i++){
			if (f[i]< min) min=f[i];
			if (f[i]> max) max=f[i];
		}
		return new int[]{min,max};
	}


	/**Finds min and max values of an unsorted float array.*/
	public static float[] findMinMaxFloatValues(float[] f){
		float min = f[0];
		float max = f[0];
		int num = f.length;
		for (int i=0; i<num; i++){
			if (f[i]< min) min=f[i];
			if (f[i]> max) max=f[i];
		}
		return new float[]{min,max};
	}
	/**Finds min and max values of a float array.*/
	public static double[] findMinMaxDoubleValues(double[] f){
		double min = f[0];
		double max = f[0];
		int num = f.length;
		for (int i=0; i<num; i++){
			if (f[i]< min) min=f[i];
			if (f[i]> max) max=f[i];
		}
		return new double[]{min,max};
	}	

	/**Finds min and max values of a int array.*/
	public static int[] findMinMaxIntInt(int[][] f){
		int min = f[0][0];
		int max = f[0][0];
		int num = f.length;
		int[] minMax;
		for (int i=0; i<num; i++){
			minMax = findMinMaxIntValues(f[i]);
			if (minMax[0]<min) min = minMax[0];
			if (minMax[1]>max) max = minMax[1];
		}
		return new int[]{min,max};
	}

	/**Finds min and max values of a float array.*/
	public static float[] findMinMaxFloatArrays(float[][] f){
		float min = f[0][0];
		float max = f[0][0];
		int num = f.length;
		float[] minMax;
		for (int i=0; i<num; i++){
			minMax = findMinMaxFloatValues(f[i]);
			if (minMax[0]<min) min = minMax[0];
			if (minMax[1]>max) max = minMax[1];
		}
		return new float[]{min,max};
	}

	/**Finds the index of the key in the int[] but wont preceed the int[index]*/
	public static int findClosestStartIndex (int[] positions, int key){
		int index = Arrays.binarySearch(positions,key);
		if (index <0 ) {
			return (index+1) * -1;
		}
		return index;
	}

	/**Finds the index of the key in the int[] but wont exceed the int[index]*/
	public static int findClosestEndIndex (int[] positions, int key){
		int index = Arrays.binarySearch(positions,key);
		if (index <0 ) {
			return (index+2) * -1;
		}
		return index;
	}

	/**Returns the index of the closest values[index] to the key. Rounds up
	 * if the value is equi distant between two indexes.  Will not return an
	 * index <0 or > length-1 .
	 * Don't forget to SORT!.
	 * If identical values are present, returns the smallest index containing the key.*/
	public static int findClosestIndexToValue(int[] sortedValues, int key){
		int index = Arrays.binarySearch(sortedValues,key);
		//no exact match
		if (index<0){
			int indexAfter = (-1* index) -1;
			if (indexAfter >= sortedValues.length) return sortedValues.length-1;
			int indexBefore = indexAfter -1;
			if (indexBefore<0) return 0;
			int diffToAfter = Math.abs(sortedValues[indexAfter]-key);
			int diffToBefore = Math.abs(sortedValues[indexBefore]-key);
			if (diffToAfter >  diffToBefore) return indexBefore;
			else return indexAfter;
		}
		//exact match look for smaller indexes with same value
		else {
			float value = sortedValues[index];
			int testIndex = index;
			while (true){
				testIndex--;
				//look for negative index
				if (testIndex< 0) {
					index = 0;
					break;
				}
				//if different value break
				if (value != sortedValues[testIndex]) {
					index = ++testIndex;
					break;
				}
			}
			return index;
		}
	}

	/**Returns the index of the closest values[index] to the key. Rounds up
	 * if the value is equi distant between two indexes.  Will not return an
	 * index <0 or > length-1 .
	 * Don't forget to SORT!.
	 * If identical values are present, returns the smallest index containing the key.*/
	public static int findClosestIndexToValue(float[] sortedValues, float key){
		int index = Arrays.binarySearch(sortedValues,key);
		//no exact match
		if (index<0){
			int indexAfter = (-1* index) -1;
			if (indexAfter >= sortedValues.length) return sortedValues.length-1;
			int indexBefore = indexAfter -1;
			if (indexBefore<0) return 0;
			float diffToAfter = Math.abs(sortedValues[indexAfter]-key);
			float diffToBefore = Math.abs(sortedValues[indexBefore]-key);
			if (diffToAfter >  diffToBefore) return indexBefore;
			else return indexAfter;
		}
		//exact match look for smaller indexes with same value
		else {
			float value = sortedValues[index];
			int testIndex = index;
			while (true){
				testIndex--;
				//look for negative index
				if (testIndex< 0) {
					index = 0;
					break;
				}
				//if different value break
				if (value != sortedValues[testIndex]) {
					index = ++testIndex;
					break;
				}
			}
			return index;
		}
	}

	/**Finds the start and stop indexes given a sorted int[] of values and two values, one after the other, 
	 * close together, otherwise just use Arrays.binarySearch.  Good for huge int[] arrays. Set the notExact 
	 * boolean to true if the values are potentially not exact.*/
	public static int[] findStartStopIndexes(int[] positions, int startValue, int stopValue, boolean notExact){
		int startIndex;
		int stopIndex;
		if (notExact){
			startIndex = findClosestIndexToValue(positions, startValue);
			stopIndex = findClosestIndexToValue(positions, stopValue);
		}
		else {
			startIndex = Arrays.binarySearch(positions,startValue);
			stopIndex = startIndex;
			int length = positions.length-1;
			while (stopIndex!=length){
				if (stopValue == positions[stopIndex]) break;
				stopIndex++;
			}
		}
		return new int[]{startIndex, stopIndex};
	}

	/**Calculates the standard deviation of the difference between two int[]s 
	 * of the same length.*/
	public static double standardDeviationOfDifference(int[] t, int[] c){
		int num = t.length;
		int[] diff = new int[num];
		for (int i=0; i<num; i++){
			diff[i]= t[i]-c[i];
		}
		return standardDeviation(diff);
	}

	/**Average int[][] values to double[], averages repeats [numRepeats][values]*/ 
	public static double[] averageIntIntArray(int[][] ints){
		int num = ints.length;
		double[] ave = new double[num];
		double numSub;
		double sum;
		for (int i=0; i<num; i++){
			numSub = ints[i].length;
			sum =0;
			for (int j=0; j<numSub; j++){
				sum += ints[i][j];
			}
			ave[i] = sum/numSub;
		}
		return ave;
	}

	/**Average float[][] values to double[], averages repeats [values][numRepeats]*/ 
	public static double[] averageFloatArrays(float[][] ints){
		int num = ints.length;
		double[] ave = new double[num];
		double numSub;
		double sum;
		for (int i=0; i<num; i++){
			numSub = ints[i].length;
			sum =0;
			for (int j=0; j<numSub; j++){
				sum += ints[i][j];
			}
			ave[i] = sum/numSub;
		}
		return ave;
	}

	/**Average float[][] values to double[], averages repeats [numChips][OligoValues].
	 * Assumes equal number of oligos.*/ 
	public static double[] averageFloatArraysFlipped(float[][] ints){
		double numChips = ints.length;
		int numOligos = ints[0].length;
		double[] ave = new double[numOligos];
		double sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//total the repeats
			for (int j=0; j<numChips; j++){
				sum += ints[j][i];
			}
			ave[i] = sum/numChips;
		}
		return ave;
	}

	/**Average float[][] values to double[], averages repeats [numChips][OligoValues].
	 * Assumes equal number of oligos.*/ 
	public static float[] averageFloatArraysFlippedToFloat(float[][] ints){
		float numChips = ints.length;
		int numOligos = ints[0].length;
		float[] ave = new float[numOligos];
		float sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//total the repeats
			for (int j=0; j<numChips; j++){
				sum += ints[j][i];
			}
			ave[i] = sum/numChips;
		}
		return ave;
	}

	/**Takes the geometric mean of float[][] values to double[], [numChips][OligoValues], returns the values
	 * in log base 2.
	 * Assumes equal number of oligos.*/ 
	public static double[] geoMeanFloatArraysFlipped(float[][] ints){
		int numChips = ints.length;
		int numOligos = ints[0].length;
		double[] ave = new double[numOligos];
		double sum;
		//for each oligo
		double log2 = Math.log(2);
		for (int i=0; i<numOligos; i++){
			sum =0;
			//total the repeats
			for (int j=0; j<numChips; j++){
				sum += Math.log(ints[j][i])/log2;
			}
			ave[i] = sum/(double)numChips;
		}
		return ave;
	}

	/**Average float[][] values to float[], averages repeats [numChips][OligoValues].
	 * Assumes equal number of oligos.*/ 
	public static float[] averageFloatArraysFlippedToFloats(float[][] ints){
		float numChips = ints.length;
		int numOligos = ints[0].length;
		float[] ave = new float[numOligos];
		float sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//total the repeats
			for (int j=0; j<numChips; j++){
				sum += ints[j][i];
			}
			ave[i] = sum/numChips;
		}
		return ave;
	}

	/**Takes a geometric average of  float[][] values to double[], averages repeats [numChips][OligoValues].
	 * Assumes equal number of oligos. Skips zero values. Return in log base 10.*/ 
	public static double[] geometricMeanSkipZeros(float[][] ints){
		int numChips = ints.length;
		int numOligos = ints[0].length;
		double[] ave = new double[numOligos];
		//for each oligo
		for (int i=0; i<numOligos; i++){
			double sum =0;
			double numLogs = 0;
			//sum the non zero intensities, all intensities >0 unless masked
			for (int j=0; j<numChips; j++){
				if (ints[j][i]>0) {
					sum += Math.log(ints[j][i]);
					numLogs++;
				}
			}
			//find mean of logs
			if (numLogs !=0){
				double meanLogs = sum/numLogs;
				ave[i]= Math.pow(Math.E, meanLogs);
			}
			else ave[i] = 0;
		}
		return ave;
	}

	/**Takes a geometric average of float[][] values [numChips][OligoValues]. 
	 * Assumes no intensities <= 0.
	 * Returns log base 10 values.*/ 
	public static double[] geometricMean(float[][] ints){
		int numChips = ints.length;
		int numOligos = ints[0].length;
		double[] ave = new double[numOligos];
		//double zeroLog = Math.log(0.0000000001);
		//for each oligo
		for (int i=0; i<numOligos; i++){
			double sum =0;
			//sum the logged intensities
			for (int j=0; j<numChips; j++){
				//if (ints[j][i]<=0 ) sum+= zeroLog;
				//else sum += Math.log(ints[j][i]);
				sum += Math.log(ints[j][i]);
			}
			//find mean of logs
			double meanLogs = sum/numOligos;
			ave[i]= Math.pow(Math.E, meanLogs);

		}
		return ave;
	}




	/**Average int[][] values to double[], averages values [numRepeats][values]*/ 
	public static double[] averageIntIntArray2(int[][] ints){
		int numOligos = ints[0].length;
		double[] ave = new double[numOligos];
		double numIntensities = ints.length;
		double sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			//average the intensity values for the oligo
			sum =0;
			for (int j=0; j<numIntensities; j++){
				sum += ints[j][i];
			}
			ave[i] = sum/numIntensities;
		}
		return ave;
	}

	/**Uses a sliding window to smooth the scores applying a trimmed mean (drop 1 from ends) to the scores found within the window.
	 * The collection of values within the window is only an attempt to look windowSize/2 upstream and
	 * windowSize/2 downstream, not a requirement.  Thus at either end of the scores possibly only 1/2 of
	 * the scores are used in making the calculation that is associated with the original score.*/
	public static double[] smoothScores(double[] scores, int[] positions, int windowSize){
		int num = scores.length;
		double[] smoothedScores = new double[num];
		int halfWindow = (int)Math.round((int)windowSize/2);
		for (int i=0; i< num; i++){
			ArrayList vals = new ArrayList();
			vals.add(new Double(scores[i]));
			//double total = scores[i];
			//double numPts =1;
			int start = positions[i]-halfWindow;
			int stop = positions[i]+ halfWindow;
			//take start, attempt to look up stream xbp and downstream x bp adding scores
			//look upstream
			boolean go = true;
			int advanceIndex = i;
			while (go){
				//attempt to advance one
				advanceIndex++;
				if (advanceIndex<num){
					//check position
					if (positions[advanceIndex]<=stop){
						//not too far thus add val and increment numPts
						//total += scores[advanceIndex];
						//numPts++;
						vals.add(new Double(scores[advanceIndex]));
					}
					else{
						//too far bp wise
						go = false;
					}
				}
				else{
					//at end of interval cannot advance
					go = false;
				}
			}
			//look downstream
			go = true;
			advanceIndex = i;
			while (go){
				//attempt to back up one
				advanceIndex--;
				if (advanceIndex>-1){
					//check position
					if (positions[advanceIndex]>=start){
						//not too far thus add val and increment numPts
						//total += scores[advanceIndex];
						//numPts++;
						vals.add(new Double(scores[advanceIndex]));
					}
					else{
						//too far bp wise
						go = false;
					}
				}
				else{
					//at end of interval cannot advance
					go = false;
				}
			}

			//make average,
			//smoothedScores[i]= total/numPts; //mean

			//median
			double[] fin = arrayListOfDoubleToArray(vals);
			Arrays.sort(fin);
			//smoothedScores[i] = Num.median(fin);

			//trimmed mean, watch out for too few in array, occurs at ends.
			if (fin.length>2) smoothedScores[i] = NumericalFunctionsTemp.trimmedMean(fin,1);
			else smoothedScores[i]= NumericalFunctionsTemp.mean(fin);

		}
		return smoothedScores;
	}
	/**Calculates a mean on the non NaN and Infinity floats, return zero if no non zero values were found.*/
	public static double meanIgnoreNaNInfinity(float[] f){
		double numVals = 0;
		double total =0;
		for (int i=0; i< f.length; i++){
			if (Float.isNaN(f[i]) == false && Float.isInfinite(f[i]) == false) {
				total += f[i];
				numVals++;
			}
		}
		if (numVals>0) return total/numVals;
		return 0;
	}
	/** Slides a window along an array, one index at a time, averaging the contents. */
	public static double[] windowAverageScores(double[] scores, int windowSize) {
		double window = windowSize;
		int num = 1+ scores.length - windowSize;
		if (num < 1) return null;
		double[] means = new double[num];
		for (int i=0; i<num; i++){
			//sum window
			double stop = i+window;
			double total = 0;
			for (int j=i; j< stop; j++){
				total += scores[j];
			}
			means[i] = total/window;
		}
		return means;
	}

	/** Slides a window along an array, one index at a time, averaging the contents, ignores zero values. */
	public static double[] windowAverageScoresIgnoreZeros(double[] scores, int windowSize) {
		double window = windowSize;
		int num = 1+ scores.length - windowSize;
		if (num < 1) return null;
		double[] means = new double[num];
		for (int i=0; i<num; i++){
			//sum window
			double stop = i+window;
			double total = 0;
			double count = 0;
			for (int j=i; j< stop; j++){
				if (scores[j] !=0){
					total += scores[j];
					count ++;
				}
			}
			means[i] = total/count;
		}
		return means;
	}

	/** Slides a window along an array, one index at a time, averaging the contents, ignores zero values. */
	public static float[] windowAverageScoresIgnoreScore(float[][] scores, float scoreToIgnore, int windowSize) {
		float window = windowSize;
		int num = 1+ scores[0].length - windowSize;
		float[] means = new float[num];
		for (int i=0; i<num; i++){
			//sum window
			float stop = i+window;
			float total = 0;
			float count = 0;
			//for each window
			for (int j=i; j< stop; j++){
				//for each score index look for non scoresToIgnore and add
				for (int k = 0; k< scores.length; k++){
					if (scores[k][j] != scoreToIgnore){
						total += scores[k][j];
						count ++;
					}
				}
			}
			if (count == 0) means[i] = scoreToIgnore;
			else means[i] = total/count;
		}
		return means;
	}

	/** Slides a window along an array, one index at a time, averaging the contents. */
	public static float[] windowAverageScoresIgnoreScore(float[] scores, float scoreToIgnore, int windowSize) {
		float window = windowSize;
		int num = 1+ scores.length - windowSize;
		if (num < 1) return null;
		float[] means = new float[num];
		for (int i=0; i<num; i++){
			//sum window
			float stop = i+window;
			float total = 0;
			float numScores = 0;
			for (int j=i; j< stop; j++){
				if (scores[j] != scoreToIgnore) {
					total += scores[j];
					numScores++;
				}
			}
			if (numScores !=0) means[i] = total/numScores;
			else means[i] = scoreToIgnore;
		}
		return means;
	}	

	/** Slides a window along an array, one index at a time, averaging the contents. */
	public static float[] windowAverageScores(float[] scores, int windowSize) {
		double window = windowSize;
		int num = 1+ scores.length - windowSize;
		if (num < 1) return null;
		float[] means = new float[num];
		for (int i=0; i<num; i++){
			//sum window
			double stop = i+window;
			double total = 0;
			for (int j=i; j< stop; j++){
				total += scores[j];
			}
			means[i] = new Double(total/window).floatValue();
		}
		return means;
	}
	
	/** Slides a window along an array, one index at a time, averaging the contents. 
	 * Scans all windows including start and ends. Note, if windowSize is odd then the scan size is actually windowSize-1*/
	public static float[] windowAverageScoresNoShrink(float[] scores, int windowSize) {
		double window = windowSize;
		int halfWindow = (int)(window/2);
		float[] means = new float[scores.length];
		for (int i=0; i<scores.length; i++){
			//set start and stop
			int start = i-halfWindow;
			if (start < 0) start = 0;
			int stop = i+halfWindow;
			if (stop > scores.length) stop = scores.length;
			//sum window
			double total = 0;
			for (int j=start; j< stop; j++){
				total += scores[j];
			}
			double num = stop - start;
			means[i] = new Double(total/num).floatValue();
		}
		return means;
	}
	
	/** Slides a window along an array, one index at a time, averaging the contents. 
	 * Scans all windows including start and ends. Note, if windowSize is odd then the scan size is actually windowSize-1*/
	public static float[] windowAverageScoresNoShrinkIgnoreZeros(float[] scores, int windowSize) {
		double window = windowSize;
		int halfWindow = (int)(window/2);
		float[] means = new float[scores.length];
		for (int i=0; i<scores.length; i++){
			//set start and stop
			int start = i-halfWindow;
			if (start < 0) start = 0;
			int stop = i+halfWindow;
			if (stop > scores.length) stop = scores.length;
			//sum window
			double total = 0;
			double counter = 0;
			for (int j=start; j< stop; j++){
				if (scores[j] !=0){
					total += scores[j];
					counter++;
				}
			}
			if (counter !=0) means[i] = new Double(total/counter).floatValue();
		}
		return means;
	}

	/**Trims the fraction off the top and bottom, returns the mean of the remainder, rounds trimming # up.
	 * If trim number resolves to 0 will proceed returning mean.*/
	public static double trimmedMean(double[] sortedFloat, double fraction){
		int trimNumber = (int)Math.round(((double)sortedFloat.length) * fraction);
		return trimmedMean(sortedFloat, trimNumber);
	}

	/**Trims the fraction off the top and bottom, returns the mean of the remainder, rounds trimming # up.
	 * If trim number resolves to 0 will proceed returning mean.*/
	public static double trimmedMean(float[] sortedFloat, double fraction){
		int trimNumber = (int)Math.round(((double)sortedFloat.length) * fraction);
		return trimmedMean(sortedFloat, trimNumber);
	}

	/**Makes a trimmed mean, note the trim number is not a % but the number of values to drop
	 * from the beginning and end of the ordered set. Set appropriately. 
	 * Be sure to submit an ordered array!*/
	public static double trimmedMean(double[] sortedDouble, int trimNumber){
		double num = sortedDouble.length - trimNumber;
		double total = 0;
		for (int i=trimNumber; i<num; i++){
			total += sortedDouble[i];
		}
		return total/(sortedDouble.length-(2*trimNumber));
	}

	/**Makes a trimmed mean, note the trim number is not a % but the number of values to drop
	 * from the beginning and end of the ordered set. Set appropriately. 
	 * Be sure to submit an ordered array!*/
	public static double trimmedMean(float[] sortedFloat, int trimNumber){
		double num = sortedFloat.length - trimNumber;
		double total = 0;
		for (int i=trimNumber; i<num; i++){
			total += sortedFloat[i];
		}
		return total/(sortedFloat.length-(2*trimNumber));
	}

	/**ArrayList of Double to double[]*/
	public static double[] arrayListOfDoubleToArray(ArrayList dbl){
		int num = dbl.size();
		double[] d = new double[num];
		for (int i=0; i<num; i++)d[i]= ((Double)dbl.get(i)).doubleValue();
		return d;
	}
	/**ArrayList of Float to float[]*/
	public static float[] arrayListOfFloatToArray(ArrayList flt){
		int num = flt.size();
		float[] d = new float[num];
		for (int i=0; i<num; i++)d[i]= ((Float)flt.get(i)).floatValue();
		return d;
	}

	/**Calculates the average fold difference between two int[]s 
	 * of the same length.  Takes the mean of both arrays returns
	 * the ratio.*/
	public static double aveFoldDiffCombine(int[] t, int[] c){
		double tMean = mean(t);
		double cMean = mean(c);
		return tMean/cMean;
	}
	/**Calculates the average fold difference between two int[]s 
	 * of the same length. Calculates the ratio of each individually,
	 * then returns their average.*/
	public static double aveFoldDiffIndividual(int[] t, int[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=0; i<num; i++){
			ratios[i] = (double)t[i]/(double)c[i];
		}
		return mean(ratios);
	}

	/**Returns the ratio of each pair.*/
	public static double[] ratio(double[] t, double[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=0; i<num; i++){
			ratios[i] = t[i]/c[i];
		}
		return ratios;
	}

	/**Returns the ratio of each pair, averages replicas.
	 * @param float[replicas][intensities]*/
	public static float[] ratio(float[][] t, float[][] c){
		int numOligos = t[0].length;	
		float ratios[] = new float[numOligos];
		float numChipsT = t.length;
		float aveT;
		float numChipsC = c.length;
		float aveC;
		float sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//average treatments
			for (int j=0; j<numChipsT; j++){
				sum += t[j][i];
			}
			aveT = sum/numChipsT;
			sum = 0;
			//average controls
			for (int j=0; j<numChipsC; j++){
				sum += c[j][i];
			}
			aveC = sum/numChipsC;
			//calc ratio
			ratios[i] = aveT/aveC;
		}
		return ratios;
	}
	
	/**Returns the difference of each pair of average, aveT - aveC.
	 * @param float[replicas][intensities]*/
	public static float[] difference(float[][] t, float[][] c){
		int numOligos = t[0].length;	
		float ratios[] = new float[numOligos];
		float numChipsT = t.length;
		float aveT;
		float numChipsC = c.length;
		float aveC;
		float sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//average treatments
			for (int j=0; j<numChipsT; j++){
				sum += t[j][i];
			}
			aveT = sum/numChipsT;
			sum = 0;
			//average controls
			for (int j=0; j<numChipsC; j++){
				sum += c[j][i];
			}
			aveC = sum/numChipsC;
			//calc diff
			ratios[i] = aveT - aveC;
		}
		return ratios;
	}

	/**Returns the relative difference of each pair, averages replicas.
	 * @param float[replicas][intensities]*/
	public static float[] relativeDifferences(float[][] t, float[][] c){
		int numOligos = t[0].length;	
		float diffs[] = new float[numOligos];
		double numChipsT = t.length;
		double aveT;
		double numChipsC = c.length;
		double aveC;
		double sum;
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//average treatments
			for (int j=0; j<numChipsT; j++){
				sum += t[j][i];
			}
			aveT = sum/numChipsT;
			sum = 0;
			//average controls
			for (int j=0; j<numChipsC; j++){
				sum += c[j][i];
			}
			aveC = sum/numChipsC;
			//calc relative difference
			diffs[i] = new Double(2 * (  (aveT - aveC) / (aveT + aveC)   )).floatValue();
		}
		return diffs;
	}

	/**Returns the log2 ratio of each pair, takes a geometric mean of replicas.
	 * @param float[replicas][intensities]*/
	public static float[] geometricMeanRatio(float[][] t, float[][] c){
		int numOligos = t[0].length;	
		float ratios[] = new float[numOligos];
		double numChipsT = t.length;
		double aveT;
		double numChipsC = c.length;
		double aveC;
		double sum;
		double log2 = Math.log(2);
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//average treatments
			for (int j=0; j<numChipsT; j++){
				sum += Math.log(t[j][i])/log2;
			}
			aveT = sum/numChipsT;
			sum = 0;
			//average controls
			for (int j=0; j<numChipsC; j++){
				sum += Math.log(c[j][i])/log2;
			}
			aveC = sum/numChipsC;
			//calc ratio
			ratios[i] = new Double(aveT - aveC).floatValue();
		}
		return ratios;
	}

	/**Returns the log2 ratio of each pair, averaging replicas.
	 * @param float[replicas][intensities]*/
	public static float[] logRatios(float[][] t, float[][] c){
		int numOligos = t[0].length;	
		float ratios[] = new float[numOligos];
		float numChipsT = t.length;
		float aveT;
		float numChipsC = c.length;
		float aveC;
		float sum;
		double log2 = Math.log(2);
		//for each oligo
		for (int i=0; i<numOligos; i++){
			sum =0;
			//average treatments
			for (int j=0; j<numChipsT; j++){
				sum += t[j][i];
			}
			aveT = sum/numChipsT;
			sum = 0;
			//average controls
			for (int j=0; j<numChipsC; j++){
				sum += c[j][i];
			}
			aveC = sum/numChipsC;
			//calc ratio
			ratios[i] = new Double (Math.log(aveT/aveC) / log2).floatValue();
		}
		return ratios;
	}

	/**Returns the ratio of each pair.*/
	public static double[] ratio(float[] t, float[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=0; i<num; i++){
			ratios[i] = (double)t[i]/ (double)c[i];
		}
		return ratios;
	}

	/**Returns the ratio of each pair.*/
	public static double[] ratio(int[] t, double[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=0; i<num; i++){
			ratios[i] = ((double)t[i])/c[i];
		}
		return ratios;
	}
	/**Returns the ratio of each pair.*/
	public static double[] ratio(int[] t, int[] c){
		int num = t.length;
		double[] ratios = new double[num];
		for (int i=0; i<num; i++){
			ratios[i] = (double)t[i]/(double)c[i];
		}
		return ratios;
	}

	/**Returns the difference of each pair.*/
	public static double[] difference(int[] t, int[] c){
		int num = t.length;
		double[] diffs = new double[num];
		for (int i=0; i<num; i++){
			diffs[i] = t[i]-c[i];
		}
		return diffs;
	}
	/**Returns the difference of each pair.*/
	public static double[] difference(double[] t, double[] c){
		int num = t.length;
		double[] diffs = new double[num];
		for (int i=0; i<num; i++){
			diffs[i] = t[i]-c[i];
		}
		return diffs;
	}

	/** Builds a pooled from part of a float[replica][intensities] for treat and control.
	 * @param treatment and control float[replicas][intensities]
	 * @param start and stop indexes are inclusive
	 * @return float[treatment(0) or control(1)][pooled intensities]*/
	public static float[][] pooledSubArray(float[][] treatment, float[][] control, int startIndex, int stopIndex){
		int numIntensities = 1+stopIndex-startIndex;
		int numTreatments = treatment.length * numIntensities;
		float[] ts = new float[numTreatments];

		int numControls = control.length * numIntensities;
		float[] cs = new float[numControls];

		//for each intensity/ oligo
		int counter = 0;
		for (int j=startIndex; j<=stopIndex; j++){
			//make treatments
			for (int i=0; i<treatment.length; i++) { 
				ts[counter++] = treatment[i][j];
			}
			counter = 0;
			for (int i=0; i<control.length; i++) {
				cs[counter++] = control[i][j];
			}
		}
		float[][] tc = new float[2][];
		tc[0] = ts;
		tc[1] = cs;
		return tc;
	}

	/**Calculates the SAM d statistic, similar to a t-statistic with a bit of fudge added into the denominator to
	 * control for zero variance conditions. Set fudge to zero for standard unpaired T-Test.
	 * @return float[3] samScore, diff of means, variance (w/o fudge).*/
	public static float[] sam(float[] t, float[] c, double fudge){
		double meanT = mean(t);
		double meanC = mean(c);
		double top = meanT - meanC;

		double sT = Math.pow(standardDeviation(t, meanT), 2)/(double)t.length;
		double sC = Math.pow(standardDeviation(c, meanC), 2)/(double)c.length;
		double var = Math.sqrt(sT+sC);

		double sam = top/(var + fudge);
		float[] scores = new float[3];
		scores[0] = new Double(sam).floatValue();
		scores[1] = new Double(top).floatValue();
		scores[2] = new Double(var).floatValue();
		return scores;
	}

	/**Calculates standard deviation of an int[]*/
	public static double standardDeviation(int[] x){
		double mean = mean(x);
		return standardDeviation(x, mean);
	}
	/**Calculates standard deviation of an int[] and mean.*/
	public static double standardDeviation(int[] x, double mean){
		double N = x.length;
		double topTot =0;
		for (int i=0; i<N; i++){
			topTot += Math.pow(x[i]-mean,2);
		}
		return Math.sqrt( topTot/(N-1) );
	}

	/**Calculates standard deviation of an float[]*/
	public static double standardDeviation(float[] x){
		double mean = mean(x);
		return standardDeviation(x, mean);
	}
	/**Calculates standard deviation of an float[] given a mean.*/
	public static double standardDeviation(float[] x, double mean){
		double N = x.length;
		double topTot =0;
		for (int i=0; i<N; i++){
			topTot += Math.pow((double)x[i]-mean,2);
		}
		return Math.sqrt( topTot/(N-1) );
	}



	/**Calculates standard deviation of an double[]*/
	public static double standardDeviation(double[] x){
		double mean = mean(x);
		return standardDeviation(x, mean);
	}
	/**Calculates standard deviation of a double[] and mean.*/
	public static double standardDeviation(double[] x, double mean){
		double N = x.length;
		double topTot =0;
		for (int i=0; i<N; i++){
			topTot += Math.pow(x[i]-mean,2);
		}
		return Math.sqrt( topTot/(N-1) );
	}	

	/**Calculates standard error of a double[] given it's mean.
	 * Spead savings*/
	public static double standardError(double[] x, double mean){
		double N = x.length;
		double topTot =0;
		for (int i=0; i<N; i++){
			topTot += Math.pow(x[i]-mean,2);
		}
		return Math.sqrt( topTot/(N-1) )/ Math.sqrt(N);
	}

	/**Calculates the median absolute difference.*/
	public static double medianAbsoluteDifference(float[] x, float[] y){
		int num = x.length;
		float[] diff = new float[num];
		for (int i=0; i<num; i++){
			diff[i] = Math.abs(x[i]-y[i]);
		}
		Arrays.sort(diff);
		return median(diff);
	}

	/**Calculates the maximum median absolute difference between arrays of float where int[oligo index number][oligo intensity measurements]*/
	public static double calcMaxMedianAbsoluteDifference(float[][] oligosValues){
		//calculate medianAbsDiff for each pair
		double maxMAD = 0;
		double testMAD = 0;
		int numOligos = oligosValues.length;
		int numValues = oligosValues[0].length;
		float[] one = new float[numOligos];
		//get first array
		for (int k=0; k<numOligos; k++){
			one[k]= oligosValues[k][0];
		}
		//run thru remainder
		for (int j=1; j<numValues; j++){
			//get second array
			float[] two = new float[numOligos];
			for (int k=0; k<numOligos; k++){
				two[k]= oligosValues[k][j];
			}
			//calc medianAbsDiff
			testMAD = medianAbsoluteDifference(one, two);
			if (testMAD>maxMAD) maxMAD = testMAD;
			//reset one for next cycle
			one = two;
		}
		return maxMAD;
	}

	/**Calculates the mean of the median absolute differences between arrays of float where float[oligo index number][oligo intensity measurements]*/
	public static double calcMeanMedianAbsoluteDifference(float[][] oligosValues){
		//calculate medianAbsDiff for each pair
		double totMAD = 0;
		int numOligos = oligosValues.length;
		int numValues = oligosValues[0].length;
		float[] one = new float[numOligos];
		double mads = 0;
		//get first array
		for (int k=0; k<numOligos; k++){
			one[k]= oligosValues[k][0];
		}
		//run thru remainder
		for (int j=1; j<numValues; j++){
			//get second array
			float[] two = new float[numOligos];
			for (int k=0; k<numOligos; k++){
				two[k]= oligosValues[k][j];
			}
			//calc medianAbsDiff
			totMAD += medianAbsoluteDifference(one, two);
			mads++;
			//reset one for next cycle
			one = two;
		}
		return totMAD/mads;
	}

	/**Calculates Pearson correlation coefficient, r, from two int[]s. 
	 * Cannot have one int[] be uniform values, returns -2 if error.*/
	public static double correlationCoefficient (int[] x, int[] y){
		double N = x.length;
		double xTot=0;
		double yTot=0;
		double sqrXTot =0;
		double sqrYTot =0;
		double xYTot=0;
		for (int i=0; i<N; i++){
			xTot += x[i];
			yTot += y[i];
			sqrXTot += Math.pow(x[i],2);
			sqrYTot += Math.pow(y[i],2);
			xYTot += (x[i] * y[i]);
		}
		double top = (N * xYTot) - (xTot * yTot);
		double botLeft = Math.sqrt( (N * sqrXTot) - Math.pow(xTot,2) );
		double botRight = Math.sqrt( (N * sqrYTot) - Math.pow(yTot,2) );
		double bot = botLeft*botRight;
		if (bot == 0) {
			String message = "Warning: calc of corr coef error, Num.java";
			System.out.println(message);
			return -2;
		}
		return top/bot;
	}

	/**Provide a float[2][0], will remove zeros from both and there matched
	 * pair.
	 * Does a pairwise zero removal, if either of the float arrays are zero
	 * both values are removed.*/
	public static float[][] removeZeroValues(float[][] f){
		int num = f[0].length;
		ArrayList one = new ArrayList(num);
		ArrayList two = new ArrayList(num);
		for (int i=0; i<num; i++){
			if (f[0][i] != 0 && f[1][i] != 0) {
				one.add(new Float(f[0][i]));
				two.add(new Float(f[1][i]));
			}
		}
		float[][] str = new float[2][];
		str[0] = NumericalFunctionsTemp.arrayListOfFloatToArray(one);
		str[1] = NumericalFunctionsTemp.arrayListOfFloatToArray(two);
		return str;
	}



	/**Converts 0.345543 to 34.6% */
	public static String formatPercentOneFraction(double num){
		NumberFormat f = NumberFormat.getPercentInstance();
		f.setMaximumFractionDigits(1);
		return f.format(num);
	}

	/**Converts a double ddd.dddddddd to sss.s */
	public static String formatNumberOneFraction(double num){
		NumberFormat f = NumberFormat.getNumberInstance();
		f.setMaximumFractionDigits(1);
		return f.format(num);
	}
	/**Converts a double ddd.dddddddd to a user determined number of decimal places right of the .  */
	public static String formatNumber(double num, int numberOfDecimalPlaces){
		NumberFormat f = NumberFormat.getNumberInstance();
		f.setMaximumFractionDigits(numberOfDecimalPlaces);
		f.setMinimumFractionDigits(numberOfDecimalPlaces);
		return f.format(num);
	}

	/**Converts an array of double to a String with a defined number of decimal places and a delimiter.  */
	public static String doubleArrayToString(double[] d, int numberOfDecimalPlaces, String delimiter){
		NumberFormat f = NumberFormat.getNumberInstance();
		f.setMaximumFractionDigits(numberOfDecimalPlaces);
		f.setMinimumFractionDigits(numberOfDecimalPlaces);
		//return f.format();
		StringBuffer sb = new StringBuffer();
		int num = d.length;
		sb.append(f.format(d[0]));
		for (int i=1; i<num; i++){
			sb.append(delimiter);
			sb.append(f.format(d[i]));
		}
		return sb.toString();
	}

	/**Converts an array of double to a String with a maximal number of defined decimal places and a delimiter. Min isn't set.  */
	public static String doubleArrayToStringOnlyMax(double[] d, int maxNumDec, String delimiter){
		NumberFormat f = NumberFormat.getNumberInstance();
		f.setMaximumFractionDigits(maxNumDec);
		StringBuffer sb = new StringBuffer();
		int num = d.length;
		sb.append(f.format(d[0]));
		for (int i=1; i<num; i++){
			sb.append(delimiter);
			sb.append(f.format(d[i]));
		}
		return sb.toString();
	}

	/**Converts to String using delimiter.*/
	public static String floatArrayToString(float[] f, String delimiter){
		StringBuilder sb = new StringBuilder();
		sb.append(f[0]);
		for (int i=1; i< f.length; i++){
			sb.append(delimiter);
			sb.append(f[i]);
		}
		return sb.toString();
	}

	/**Converts an array of int to a String seperated by the delimiter.  */
	public static String intArrayToString(int[] d, String delimiter){
		StringBuffer sb = new StringBuffer();
		int num = d.length;
		sb.append(d[0]);
		for (int i=1; i<num; i++){
			sb.append(delimiter);
			sb.append(d[i]);
		}
		return sb.toString();
	}

	/**Given a String of ints delimited by something, will parse or return null.*/
	public static int[] stringArrayToInts(String s, String delimiter){
		String[] tokens = s.split(delimiter);
		int[] num = new int[tokens.length];
		try {
			for (int i=0; i< tokens.length; i++){
				num[i] = Integer.parseInt(tokens[i]);
			}
			return num;
		} catch (Exception e){
			return null;
		}
	}
	
	/**Given a String of floats delimited by something, will parse or return null.*/
	public static float[] stringArrayToFloat(String s, String delimiter){
		String[] tokens = s.split(delimiter);
		float[] num = new float[tokens.length];
		try {
			for (int i=0; i< tokens.length; i++){
				num[i] = Float.parseFloat(tokens[i]);
			}
			return num;
		} catch (Exception e){
			return null;
		}
	}
	
	/**Given a String of double delimited by something, will parse or return null.*/
	public static double[] stringArrayToDouble(String s, String delimiter){
		String[] tokens = s.split(delimiter);
		double[] num = new double[tokens.length];
		try {
			for (int i=0; i< tokens.length; i++){
				num[i] = Double.parseDouble(tokens[i]);
			}
			return num;
		} catch (Exception e){
			return null;
		}
	}

	/**Calculates Median of a sorted double[]. Copied code from WWW.*/
	public static double median(double[] sorted) {
		int middle = sorted.length/2;  // subscript of middle element
		//Odd number of elements -- return the middle one.
		if (sorted.length%2 == 1) return sorted[middle];
		// Even number -- return average of middle two
		return (sorted[middle-1] + sorted[middle]) / 2.0;
	}

	/**Calculates Median of a sorted int[].*/
	public static double median (int[] sorted) {
		int middle = sorted.length/2;  // subscript of middle element
		//Odd number of elements -- return the middle one.
		if (sorted.length%2 == 1) return sorted[middle];
		// Even number -- return average of middle two
		return ((double)(sorted[middle-1] + sorted[middle])) / 2.0; 
	}	


	/**Calculates Median of a sorted short[]. Copied code from WWW.*/
	public static double median(short[] sorted) {
		int middle = sorted.length/2;  // subscript of middle element
		//Odd number of elements -- return the middle one.
		if (sorted.length%2 == 1) return sorted[middle];
		// Even number -- return average of middle two
		return ((double)sorted[middle-1] + (double)sorted[middle]) / 2.0;
	}	

	/**Calculates Median of a sorted float[]. Copied code from WWW.*/
	public static double median(float[] sorted) {
		int middle = sorted.length/2;  // subscript of middle element
		//Odd number of elements -- return the middle one.
		if (sorted.length%2 == 1) return sorted[middle];
		// Even number -- return average of middle two
		return ((double)sorted[middle-1] + (double)sorted[middle]) / 2.0;
	}	
	/**Averages a float array.*/
	public static float mean(float[] t){
		float sumT=0;
		for (int i=t.length-1; i>=0; i--) sumT+= t[i];
		return sumT/(float)t.length;
	}
	/**Averages two float[]s .*/
	public static float[] mean(float[] one, float[] two){
		int len = one.length;
		if (len != two.length) return null;
		float[] ave = new float[len];
		for (int i=0; i<len; i++) ave[i] = (one[i]+two[i])/2.0F;
		return ave;
	}
	/**Averages two float[][]s, assumes a square.*/
	public static float[][] mean(float[][] one, float[][] two){
		int len = one.length;
		if (len != two.length) return null;
		float[][] ave = new float[len][len];
		for (int i=0; i<len; i++){
			for (int j=0; j<len; j++){
				ave[i][j] = (one[i][j]+two[i][j])/2.0F;
			}
		}
		return ave;
	}

	/**Averages a int array.*/
	public static double mean(int[] t){
		long sumT=0;
		for (int i=t.length-1; i>=0; i--) sumT+= t[i];
		return (double)sumT/(double)t.length;
	}
	/**Averages a double array.*/
	public static double mean(double[] t){
		double sumT=0;
		for (int i=t.length-1; i>=0; i--) sumT+= t[i];
		return sumT/(double)t.length;
	}
	/**Averages an ArrayList of Integer objects.*/
	public static int meanIntegers(ArrayList Integers){
		int len = Integers.size();
		if (len==0) return 0;
		long total = 0;
		for (int i=0; i<len; i++){
			int num = ((Integer)Integers.get(i)).intValue();
			total += num;
		}
		double ave = (double)total/(double)len;
		return (int)Math.round(ave);
	}

	/**Averages an ArrayList of Double objects.*/
	public static String meanDoubles(ArrayList Doubles){
		int len = Doubles.size();
		if (len==0) return "0";
		double total = 0;
		for (int i=0; i<len; i++){
			double num = ((Double)Doubles.get(i)).doubleValue();
			total += num;
		}
		double ave = total/(double)len;
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumFractionDigits(2);
		return formatter.format(ave);
	}	
	/**Finds and returns the biggest int in an int[].*/
	public static int findHighestInt(int[] ints) {
		int len = ints.length;
		int max = ints[0];
		for (int i = 1; i < len; i++) {
			if (ints[i]>max) max=ints[i];
		}
		return max;
	}
	/**Finds and returns the smallest int in an int[].*/
	public static int findSmallestInt(int[] ints) {
		int len = ints.length;
		int min = ints[0];
		for (int i = 1; i < len; i++) {
			if (ints[i]<min) min=ints[i];
		}
		return min;
	}
	/**Finds and returns the biggest float in an float[].*/
	public static float findHighestFloat(float[] ints) {
		int len = ints.length;
		float max = ints[0];
		for (int i = 1; i < len; i++) {
			if (ints[i]>max) max=ints[i];
		}
		return max;
	}
	/**Sums an int array*/
	public static int sumIntArray(int[] ints){
		int num = 0;
		for (int i= ints.length-1; i>=0; i--) num+= ints[i];
		return num;
	}
	/**Sums a float array*/
	public static float sumArray(float[] array){
		float num = 0;
		for (int i= array.length-1; i>=0; i--) num+= array[i];
		return num;
	}
	/**Sums a float array*/
	public static double sumArrayReturnDouble(float[] array){
		double num = 0;
		for (int i= array.length-1; i>=0; i--) num+= array[i];
		return num;
	}

	/**Finds mode of a int[] histogram array, assumes one peak, returns the index position, value/frequency.*/
	public static int[] modeOfHistogram(int[] ints) {
		int len = ints.length;
		int max = ints[0];
		int index = 0;
		for (int i = 1; i < len; i++) {
			if (ints[i]>max){
				max=ints[i];
				index = i;
			}
		}
		return new int[]{index,max};
	}
}