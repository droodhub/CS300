//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 File Explorer
// Course: CS 300 Fall 2020
//
// Author: Andrew McAvoy
// Email: apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class FileExplorerTester {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    File f = new File("cs300");
    testListContents(f);
    testDeepListBaseCase(f);
    testDeepListRecursiveCase(f);
    testSearchByKeyBaseCase(f);
    testSearchByFileName(f);
  }

  /**
   * tests the listContents method for correctness
   * @param folder cs300 folder to test program with
   * @return if the method executed as expected
   */
  public static boolean testListContents(File folder) {
    try {
      ArrayList<String> listContents = FileExplorer.listContents(folder);
      String[] correct = new String[] {"grades", "lecture notes", "programs", "quizzes preparation",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expected = Arrays.asList(correct);
      if (listContents.size() != 7) {
        System.out.println("Error: List does not contain exactly 7 elements.");
        return false;
      }
      for (int i = 0; i < listContents.size(); i++) {
        if (!listContents.contains(expected.get(i))) {
          System.out.println("Error: list does not contain" + expected.get(i));
          return false;
        }
      }

    } catch (NotDirectoryException e) {
    }
    File f = new File(folder.getPath() + File.separator + "bananas.txt");
    try {
      ArrayList<String> notExist = FileExplorer.listContents(f);
      System.out
          .println("Error: file bananas.txt does not exist. An exception should have been caught");
      return false;
    } catch (NotDirectoryException e) {
      System.out.println("Successfully threw an exception for a nonexistent file");
    }
    f = new File(folder.getPath() + File.separator + "todo.txt");
    try {
      ArrayList<String> notExist = FileExplorer.listContents(f);
      System.out
          .println("Error: file todo.txt is not a directory. An exception should have been caught");
      return false;
    } catch (NotDirectoryException e) {
      System.out.println("Successfully threw an exception for a file that isn't a directory.");
    }
    return true;
  }

  /**
   * Calls deepListContents on a folder w/no sub-folders
   * 
   * @param folder file to Call deepListContents on
   * @return if the method returned the expected outcome
   */
  public static boolean testDeepListBaseCase(File folder) {
    try {
      File f = new File(folder + File.separator + "grades");
      if (FileExplorer.deepListContents(f).size() != 0) {
        System.out.println("Error: ArrayList should be of size 0 for folder w/no subfolders");
        System.out.println(FileExplorer.deepListContents(f));
        return false;
      }
    } catch (NotDirectoryException e) {
      System.out
          .println("Threw an exception for grades folder. Should have returned size 0 ArrayList");
      return false;
    }
    System.out.println("Successfully returned an empty array for a folder w/no subfolders");
    return true;
  }

  /**
   * tests deepListContents when it has to make a recursive call
   * @param folder cs300 file to call deepListContents on
   * @return if the method returned the expected outcome
   */
  public static boolean testDeepListRecursiveCase(File folder) {
    File f = new File(folder + File.separator + "lecture notes");
    try {
      ArrayList<String> results = FileExplorer.deepListContents(f);
      String[] expected = new String[] {"ExceptionHandling.txt", "proceduralProgramming.txt",
          "UsingObjectsAndArrayLists.txt", "AlgorithmAnalysis.txt", "Recursion.txt"};
      List<String> expectedResults = Arrays.asList(expected);
      for (int i = 0; i < results.size(); i++) {
        if (!results.contains(expectedResults.get(i))) {
          System.out.println(
              "Error: " + expectedResults.get(i) + " was not found in the returned ArrayList");
          return false;
        }
      }
    } catch (NotDirectoryException e) {

    }
    f = new File(folder + File.separator + "quizzes preparation");
    try {
      ArrayList<String> results = FileExplorer.deepListContents(f);
      String[] expected = new String[] {"codeSamples.java", "outline.txt"};
      List<String> expectedResults = Arrays.asList(expected);
      for (int i = 0; i < results.size(); i++) {
        if (!results.contains(expectedResults.get(i))) {
          System.out.println(
              "Error: " + expectedResults.get(i) + " was not found in the returned ArrayList");
          return false;
        }
      }
    } catch (NotDirectoryException e) {
    }
    System.out.println("Successfully listed contents of subfolders");
    return true;
  }

  /**
   * tests the base case of the searchByKey method
   * @param f cs300 folder to use for testing searchByKey
   * @return if the method executed correctly or not
   */
  public static boolean testSearchByKeyBaseCase(File f) {
    File searching = new File(f + File.separator + "grades");
    ArrayList<String> outcome = FileExplorer.searchByKey(searching, "Exam");
    if (outcome.size() > 0) {
      System.out.println("Error: ArrayList should have size 0 and no elements.");
      System.out.println(outcome);
      return false;
    }
    return true;
  }

  /**
   * tests the searchByName method for correctness
   * @param f cs300 file to test SearchByName on
   * @return if the method executed as expected
   */
  public static boolean testSearchByFileName(File f) {
    try {
      String tester = f.getName() + File.separator + "syllabus.txt";
      String path = FileExplorer.searchByName(f, "syllabus.txt");
      if (tester.equals(path)) {
        System.out.println("Successfully returned the path of syllabus.txt");
      } else {
        System.out.println("Path returned is incorrect");
        return false;
      }
    } catch (NoSuchElementException e) {
      System.out.print(e.getMessage());
    }
    try {
      FileExplorer.searchByName(f, null);
      System.out.println("Should have thrown error for null fileName");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Successfully threw an error for null fileName");
    }
    try {
      String path = FileExplorer.searchByName(f, "bananas.txt");
      System.out.println("Should have thrown error for bananas.txt");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Successfully threw error for bananas.txt");
      return true;
    }
  }
}
