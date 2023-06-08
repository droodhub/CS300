/**
 * 
 * @author Andrew McAvoy Class designed to compute data regarding arrays of covid-19 tests. Can add
 *         a test, remove an individual's data, get stats for one individual, and get stats for the
 *         entire dataset
 */

public class COVIDTracker {
  /**
   * Adds a test to the specified array if possible
   * 
   * @param posData        The array of positive tests
   * @param negData        The array of negative tests
   * @param userIdentifier The ID of the person whose test we will be adding to the array
   * @param isPos          indicates which array to add test to
   * @return whether or not the test was successfully added to the array. Returns false if the array
   *         was already full.
   */
  public static boolean addTest(String[] posData, String[] negData, String userIdentifier,
      boolean isPos) {
    if (isPos == true) { // attempt to add test to positive array
      int i = 0;
      while (i < posData.length) {
        if (posData[i] == null) { // adds test to the first open(null) space in the array
          posData[i] = userIdentifier;
          return true;
        } else
          i++;
      }

      return false;// returns false if unable to add to array
    }
    if (isPos == false) {// attempt to add to negative array
      int i = 0;
      while (i < negData.length)
        if (negData[i] == null) {// adds test to first open spot in the array
          negData[i] = userIdentifier;
          return true;
        } else
          i++;

      return false;// return false if unable to add to array
    }
    return false;
  }

  /**
   * Returns information about the population's testing data
   * 
   * @param posData The array of positive tests
   * @param negData The array of negative tests
   * @return The total number of positive and negative tests, the percentage of positive tests and
   *         positive individuals
   */
  public static String getPopStats(String[] posData, String[] negData) {
    String[] allNames = new String[posData.length + negData.length];
    int namesIndex = 0;
    int posTests = 0;
    int negTests = 0;
    int posIndividuals = 0;
    for (int i = 0; i < posData.length && posData[i] != null; i++) {
      posTests++;
      // before adding to array, check to see if array already has name held in it
      boolean containsName = alreadyHas(allNames, posData[i], namesIndex);
      if (containsName == false && posData[i] != null) {
        allNames[namesIndex] = posData[i]; // assign name to namesArray
        namesIndex++;
        posIndividuals++;
      }
    }
    for (int i = 0; i < negData.length && negData[i] != null; i++) {
      negTests++;
      // check to see if array already contains name before adding to it
      boolean containsName = alreadyHas(allNames, negData[i], namesIndex);
      if (containsName == false && negData[i] != null) {
        allNames[namesIndex] = negData[i];// assign name to namesArray
        namesIndex++;
      }
    }
    int totalTests = posTests + negTests;
    double percentPosTests = 0;
    double percentPosPeople = 0;
    if (totalTests > 0) // makes sure that we don't divide 0 by 0
      percentPosTests = ((double) posTests / (double) totalTests) * 100;
    // calculates percent of positive tests
    if (namesIndex > 0)
      percentPosPeople = ((double) posIndividuals / namesIndex) * 100;
    // calculates percent of positive individuals
    String summation = "Total tests: " + totalTests + "\nTotal individuals tested: " + namesIndex
        + "\nPercent positive tests: " + percentPosTests + "%\nPercent positive individuals: "
        + percentPosPeople + "%";
    return summation;
  }

  /**
   * Checks to see if the given array already contains the identifier passed through
   * 
   * @param nameSet    array of names
   * @param identifier the indivudal's ID that we are checking for
   * @param arrayIndex how many places the array has filled currently
   * @return true if nameSet already contains identifier, false if it doesn't
   */
  private static boolean alreadyHas(String[] nameSet, String identifier, int arrayIndex) {
    for (int i = 0; i < arrayIndex; i++) {
      if (nameSet[i] != null && nameSet[i].equals(identifier)) 
      //checks to see if the array contains the identifier already
        return true;
    }
    return false;
  }

  /**
   * Returns information about an individual's covid-19 testing data
   * 
   * @param posData    the array of positive covid-19 tests
   * @param negData    the array of negative covid-19 tests
   * @param identifier the individual whose information we are looking for
   * @return How many total tests the individual has had, and how many returned positive or negative
   */
  public static String getIndividualStats(String[] posData, String[] negData, String identifier) {
    int posTests = 0;
    int negTests = 0;
    for (int i = 0; i < posData.length && posData[i] != null; i++) {
      if (posData[i].equals(identifier))
        posTests++; //add to number of positive tests if we find the individual's name
    }
    for (int i = 0; i < negData.length && negData[i] != null; i++) {
      if (negData[i].equals(identifier))
        negTests++; //add to number of negative tests if we find the individual's name
    }
    String summation = "Total tests: " + (posTests + negTests) + "\nPositive: " + posTests
        + "\nNegative: " + negTests;
    return summation;
  }


  /**
   * Removes an individual's data from the arrays
   * 
   * @param posData    the array of positive tests
   * @param negData    the array of negative tests
   * @param identifier the individual whose data we are trying to remove
   * @return true if we removed any information regarding the individual, false if there was no data
   *         to remove
   */
  public static boolean removeIndividual(String[] posData, String[] negData, String identifier) {
    boolean removedData = false;

    String[] newPos = new String[posData.length];
    String[] newNeg = new String[negData.length];
    int newNegIndex = 0;
    int newPosIndex = 0;
    for (int i = 0; i < posData.length; i++) {//iterate through and remove all instance of the id
      if (posData[i] != null && posData[i].equals(identifier)) {
        posData[i] = null;
        removedData = true;
      }
    }
    for (int i = 0; i < posData.length; i++) {//assign values to newPos in the proper order

      if (posData[i] != null) {
        newPos[newPosIndex] = posData[i];
        newPosIndex++;
      }
    }
    for (int i = 0; i < posData.length; i++)
    //copy data from newPos back to posData so it is returned correctly
      posData[i] = newPos[i];
    for (int i = 0; i < negData.length; i++) {
      if (negData[i] != null && negData[i].equals(identifier)) {
        negData[i] = null;
        removedData = true;
      }
    }
    for (int i = 0; i < negData.length; i++) {//remove all instance of the id in negData

      if (negData[i] != null) {//assign values to newNeg in correct order
        newNeg[newNegIndex] = negData[i];
        newNegIndex++;
      }
    }
    for (int i = 0; i < negData.length; i++) //reassign values to negData so they can be returned
      //tried using negData = newNeg but for some reason that wasn't working
      negData[i] = newNeg[i];
    return removedData;
  }
}
