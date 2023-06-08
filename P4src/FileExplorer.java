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
import java.util.NoSuchElementException;

public class FileExplorer {
  /**
   * returns the contents of the given folder without recursion
   * @param currentFolder folder that we are attempting to list contents of
   * @return ArrayList containing the names of all files and directories within the folder
   * @throws NotDirectoryException if given file isnt a directory an error is thrown
   */
  public static ArrayList<String> listContents(File currentFolder) throws NotDirectoryException {
    // Returns a list of the names of all files and directories in
    // the the given folder.
    // Throws NotDirectoryException with a description error message if
    // the provided currentFolder does not exist or if it is not a directory
    if (!currentFolder.exists() || !currentFolder.isDirectory())
      throw new NotDirectoryException(
          "The provided folder either does not exist or isn't a directory");

    String[] names = currentFolder.list();
    ArrayList<String> fileNames = convertNames(names);
    return fileNames;
  }

  /**
   * recursive method to list the contents of a folder and all of its subfolders
   * @param currentFolder folder we are attempting to list contents of
   * @return ArrayList containing file names for this folder and all sub folders
   * @throws NotDirectoryException if current folder is not a directory or DNE error is thrown
   */
  public static ArrayList<String> deepListContents(File currentFolder)
      throws NotDirectoryException {
    ArrayList<String> returnList = new ArrayList<String>();
    // Recursive method that lists the names of all the files (not directories)
    // in the given folder and its sub-folders.
    // Throws NotDirectoryException with a description error message if
    // the provided currentFolder does not exist or if it is not a directory
    if (!currentFolder.exists() || !currentFolder.isDirectory())
      throw new NotDirectoryException(
          "The provided folder either does not exist or isn't a directory");
    else if (currentFolder.list().length == 0)
      return returnList;
    else {
      File[] nameChecks = currentFolder.listFiles();
      for (int i = 0; i < nameChecks.length; i++) {
        if (nameChecks[i].isDirectory())
          returnList.addAll(deepListContents(nameChecks[i]));
        else
          returnList.add(nameChecks[i].getName());
      }
    }
    return returnList;
  }

  /**
   * Find the path of the given fileName if it exists in the current folder
   * @param currentFolder folder we are searching through
   * @param fileName file we are trying to find the location of
   * @throws NotDirectoryException if the fileName is null or the folder DNE/isn't a directory
   * @return path of the file if discovered
   */
  public static String searchByName(File currentFolder, String fileName) {
    // Recursive method that searches the given folder and all of its subfolders
    // for an exact match to the provided fileName.
    // This method returns a path to the file, if it exists.
    // Throws NoSuchElementException with a descriptive error message if the
    // search operation returns with no results found (including the case if
    // fileName is null or currentFolder does not exist, or was not a directory
    if (fileName == null || !currentFolder.exists() || !currentFolder.isDirectory())
      throw new NoSuchElementException(
          "The file or file name given is incorrect. The method cannot execute properly");
    else {
      File[] names = currentFolder.listFiles();
      for (int i = 0; i < names.length; i++) {
        if (names[i].isDirectory())
          try {
            return searchByName(names[i], fileName);
          } catch (NoSuchElementException e) {
          }
        else if (names[i].getName().equals(fileName))
          return (names[i].getPath());
      }
    }
    throw new NoSuchElementException("The file with the specified name is not in the folder.");
  }

  /**
   * returns a list of all files with a name matching the given key
   * @param currentFolder folder we are searching through currently
   * @param key name that we are searching for
   * @return ArrayList containing all files with the key in their name
   */
  public static ArrayList<String> searchByKey(File currentFolder, String key) {
    ArrayList<String> fileNames = new ArrayList<String>();
    // Recursive method that searches the given folder and its subfolders
    // for ALL files that contain the given key in part of their name.
    // Returns An arraylist of all the names of files that match and an empty arraylist
    // when the operation returns with no results found (including the case where
    // currentFolder is not a directory).
    if (!currentFolder.exists() || !currentFolder.isDirectory()
        || currentFolder.list().length == 0) {
      return fileNames;
    } else {

      File[] names = currentFolder.listFiles();
      for (int i = 0; i < names.length; i++) {
        if (names[i].isDirectory())
          fileNames.addAll(searchByKey(names[i], key));
        else if (names[i].getName().contains(key)) {
          fileNames.add(names[i].getName());
        }
      }
      return fileNames;
    }
  }

  /**
   * finds all files in the currentFolder with size > sizeMin and < sizeMax
   * @param currentFolder folder we are searching through
   * @param sizeMin minimum size of files to add to the array
   * @param sizeMax maximum size of files to add to the array
   * @return List of all files within the range sizeMin < size < sizeMax
   */
  public static ArrayList<String> searchBySize(File currentFolder, long sizeMin, long sizeMax) {
    ArrayList<String> properSizeFiles = new ArrayList<String>();
    // Recursive method that searches the given folder and its subfolders for
    // ALL files whose size is within the given max and min values, inclusive.
    // Returns an array list of the names of all files whose size are within
    // the boundaries and an empty arraylist if the search operation returns
    // with no results found (including the case where currentFolder is not a directory)
    if (!currentFolder.exists() || !currentFolder.isDirectory()
        || currentFolder.list().length == 0) {
      return properSizeFiles;
    } else {
      File[] names = currentFolder.listFiles();
      for (int i = 0; i < names.length; i++) {
        if (names[i].isDirectory())
          properSizeFiles.addAll(searchBySize(names[i], sizeMin, sizeMax));
        else if (names[i].length() >= sizeMin && names[i].length() <= sizeMax)
          properSizeFiles.add(names[i].getName());
      }
      return properSizeFiles;
    }
  }

  /**
   * private helper method made to assist with listContents()
   * This was made very early on in the writing of code and I somewhat forgot what its purpose was
   * @param list list of string Names to be entered in the arrayList
   * @return arrayList with contents identical to list
   */
  private static ArrayList<String> convertNames(String[] list) {
    ArrayList<String> convertedList = new ArrayList<String>();
    for (int i = 0; i < list.length; i++)
      convertedList.add(list[i]);
    return convertedList;
  }
}
