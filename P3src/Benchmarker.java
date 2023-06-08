//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Basic Benchmarking
// Course: CS 300 Fall 2020
//
// Author: Andrew McAvoy
// Email: apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Rojin Sangsari helped me figure out my try/catch structures
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.File;

public class Benchmarker {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    long[] sampleNums = new long[] {100000, 1000000, 10000000, 100000000, 1000000000};
    File writeTo = new File("compareResults.txt");
    createResultsFile(writeTo, sampleNums);

  }

  /**
   * tests the speed of constantTime() and bruteForce() and compares them to each other
   * 
   * @param n variable to pass to constantTime() and bruteForce() in order to compare efficiency
   * @return formatted string that compares the speed of both methods with respect to n
   * @throws NoSuchElementException if bruteForce() and constantTime() didn't return the same value
   */
  public static String compare(long n) throws NoSuchElementException {
    long startBruteForce = System.currentTimeMillis();
    long bruteResult = ComparisonMethods.bruteForce(n);
    long endBruteForce = System.currentTimeMillis();
    long startConstant = System.currentTimeMillis();
    long constantResult = ComparisonMethods.constantTime(n);
    long endConstant = System.currentTimeMillis();
    long bruteTime = endBruteForce - startBruteForce; // total time bruteForce took to run
    long constantTime = endConstant - startConstant; // total time constantTime took to run
    if (bruteResult != constantResult) { // need to calculate everything correctly or else
                                         // runtime comparison is pointless
      throw new NoSuchElementException("The methods did not return the same value for n = " + n);
    }
    String format = n + "\t" + bruteTime + "\t" + constantTime + "\n";
    return format;
  }

  /**
   * tests bruteForce() and constatTime() with an array of n values and writes the data to a file
   * 
   * @param f       file to write data to
   * @param queryNs array of values to test using the compare() method
   */
  public static void createResultsFile(File f, long[] queryNs) {
    FileWriter fileWriter = null;
    try {// need to catch errors from the fileWriter constructor
      fileWriter = new FileWriter("compareResults.txt");
      for (int i = 0; i < queryNs.length; i++) {
        try {
          fileWriter.write(compare(queryNs[i]));
        } catch (NoSuchElementException e) { // catch the exception if the methods don't return the
                                             // same value
          System.out.println("Exception encountered while writing for value N = " + queryNs[i]);
        }
      }
    } catch (IOException e) {
      System.out.println("Exception encountered, unable to complete method.");
    } finally {
      if (fileWriter != null)
        closeFileWriter(fileWriter);
    }
  }

  /**
   * closes a fileWriter object after the program has run
   * 
   * @param f fileWriter to close after program has run
   */
  private static void closeFileWriter(FileWriter f) { //helper method to close fileWriter
    try {
      if (f != null)
        f.close();
    } catch (IOException e) {
      System.out.println("Exception encountered while closing file.");
    }

  }
}
