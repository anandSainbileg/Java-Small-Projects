import java.util.Scanner;
import java.lang.Math;
public class MarksRemapper {

	public MarksRemapper() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double desiredAverage = 60.0;
		final double desiredStandardDeviation = 20.0;
		Scanner input = new Scanner( System.in );
		double[] arrayOfNumbers = {};
		double[] arrayOfMarks = {};
		double total = 0.0;
		boolean finished = false;
		boolean number = false;
		boolean notNumber = false;
		System.out.print("Enter all percentages (separated by spaces): ");
		String error = " ";
		error = input.nextLine();
		Scanner inputLine = new Scanner(error);
		while(!finished) 
		{
			number = inputLine.hasNextDouble();
			notNumber = inputLine.hasNext();
			
			if(number) {
				double[] newNumbers = new double[(arrayOfMarks==null)? 1 :arrayOfMarks.length+1];
			
				if (arrayOfMarks != null) {
					
					System.arraycopy( arrayOfMarks,0,newNumbers,0,arrayOfMarks.length );
				}
				newNumbers[newNumbers.length-1] = inputLine.nextDouble();
				arrayOfMarks = newNumbers;
			}
			else if(!notNumber) {
				finished = true;
			}
			else {
				error = inputLine.next();
				System.out.println(" Error: " + error + " is not a valid number.");
			}
				
		
		}
		inputLine.close();
		arrayOfNumbers = arrayOfMarks;
		boolean result = modifyAllMarks(arrayOfMarks, desiredAverage, desiredStandardDeviation );
	}

	

	public static double determineAverage( double[] arrayOfNumbers ) {
		double total = 0.0;
		double oldAverage = 0.0;
		
		if (arrayOfNumbers != null && arrayOfNumbers.length != 0)
		{
			for(int i = 0; i<arrayOfNumbers.length; i++) {
				if(arrayOfNumbers[i] > 100.0) {
					arrayOfNumbers[i] = 100.0;
					}
				else if(arrayOfNumbers[i] < 0.0) {
					arrayOfNumbers[i] = 0.0;
					
				}
				total = total + arrayOfNumbers[i];
			}
			oldAverage = total/((int) arrayOfNumbers.length);
		}
		else {
			oldAverage = 0.0;
		}
		return(oldAverage);
	}
	public static double determineStandardDeviation( double[] arrayOfNumbers, double average ) {
		double standardDeviation = 0.0;
		if (arrayOfNumbers != null && arrayOfNumbers.length != 0)
		{
			average = determineAverage( arrayOfNumbers );
			
			double sumOfSquaredDifferences = 0.0;
		for (int index=0; index < arrayOfNumbers.length; index++)
		{
			sumOfSquaredDifferences += Math.pow(arrayOfNumbers[index]-average,2.0);
		}
	standardDeviation = Math.sqrt( sumOfSquaredDifferences/((double) arrayOfNumbers.length));
		
		}
		else {
			standardDeviation = 0.0;
		}
		return(standardDeviation);
	}
	
	public static boolean modifyAllMarks( double[] arrayOfMarks, double desiredAverage, double desiredStandardDeviation ) {
		double newAverage = 0.0;
		double newStandardDeviation = 0.0;
		double[] tempArray = new double[arrayOfMarks.length];
		double[] arrayOfNum = arrayOfMarks;
		double newMark = 0.0;
		boolean result = false;
		if(arrayOfMarks != null && arrayOfMarks.length != 0) {
		double oldAverage = determineAverage(arrayOfMarks);
		double oldStandardDeviation = determineStandardDeviation(arrayOfMarks, oldAverage );
		System.out.printf("The original average was %.1f and the standard deviation was %.1f\n", oldAverage, oldStandardDeviation);
		
		if (oldStandardDeviation == 0.0) {
			oldStandardDeviation = 1;
		}
		for(int i = 0; i<arrayOfMarks.length; i++) {
			newMark = desiredAverage + (arrayOfMarks[i] - oldAverage) * desiredStandardDeviation/oldStandardDeviation;
			if (newMark > 100.0) {
				newMark = 100.0;
			}
			if(newMark < 0.0) {
				newMark = 0.0;
			}
			
			if(arrayOfMarks.length == 1) 
			{
				newMark = 60.0;
			}
			tempArray[i] = newMark;
		}
		arrayOfMarks = tempArray;
		newAverage = determineAverage(arrayOfMarks);
		newStandardDeviation = determineStandardDeviation(arrayOfMarks, newAverage);
		System.out.printf("%.1f->%.1f", arrayOfNum[0], arrayOfMarks[0]);
		 for(int count = 1; count<arrayOfNum.length; count++) {
			 System.out.printf(", %.1f->%.1f", arrayOfNum[count], arrayOfMarks[count]);
		 }
		 System.out.printf("\nThe new average is %.1f and the new standard deviation is %.1f\n", newAverage, newStandardDeviation);
		 result = true;
		 }
		else {
			
			result = false;
			 
		}
		return(result);
	}
}