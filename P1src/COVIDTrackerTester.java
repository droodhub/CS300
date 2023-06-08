/**
 * 
 * @author Andrew McAvoy
 * Class to test the methods of COVIDTracker class
 */
public class COVIDTrackerTester {

  public static void main(String[] args) {

    System.out.println("testAddTest result: " + testAddTest());
    System.out.println("testRemoveIndividual result: " + testRemoveIndividual());
    System.out.println("testGetPopStats result: " + testGetPopStats());
    System.out.println("testGetIndividualStats result: " + testGetIndividualStats());
  }

  /**
   * Tests the CovidTracker class' addTest method
   * 
   * @return true if addTest's return matches the expected return value, false if it doesn't match
   */
  public static boolean testAddTest() {
  //create arrays to pass to method
    String[] pos = new String[20];
    String[] neg = new String[20];

    neg[0] = "A1";
    neg[1] = "B1";
    neg[2] = "C1";
    neg[3] = "A1";
    neg[4] = "F1";
    neg[5] = "D1";
    neg[6] = "K1";
    neg[7] = "K1";
    neg[8] = "K1";
    neg[9] = "A1";

    pos[0] = "L1";
    pos[1] = "B1";
    pos[2] = "C1";
    pos[3] = "Z1";
    pos[4] = "A1";
    String id = "Q1";
    boolean isPos = false;
    //call addTest and see if it correctly adds test
    boolean added = COVIDTracker.addTest(pos, neg, id, isPos); 
    return added;
  }

  /**
   * 
   * Tests the CovidTracker class' removeIndividual method
   * 
   * @return true if removeIndividual's return matches the expected return value, false if it
   *         doesn't match
   */
  public static boolean testRemoveIndividual() {
    //create arrays to pass to method
    String[] pos = new String[20];
    String[] neg = new String[20];

    neg[0] = "A1";
    neg[1] = "B1";
    neg[2] = "C1";
    neg[3] = "A1";
    neg[4] = "F1";
    neg[5] = "D1";
    neg[6] = "K1";
    neg[7] = "K1";
    neg[8] = "K1";
    neg[9] = "A1";

    pos[0] = "L1";
    pos[1] = "B1";
    pos[2] = "C1";
    pos[3] = "Z1";
    pos[4] = "A1";
    String id = "N1";
    //call removeIndividual and see if it returns the proper value
    boolean removed = COVIDTracker.removeIndividual(pos, neg, id);
    if (removed == true)
      return true;
    return false;
  }

  /**
   * 
   * Tests the CovidTracker class' getPopStats method
   * 
   * @return true if getPopStats' return matches the expected return value, false if it doesn't
   *         match
   */
  public static boolean testGetPopStats() {
  //create arrays to pass to method
    String[] pos = new String[20];
    String[] neg = new String[20];

    neg[0] = "A1";
    neg[1] = "B1";
    neg[2] = "C1";
    neg[3] = "A1";
    neg[4] = "F1";
    neg[5] = "D1";
    neg[6] = "K1";
    neg[7] = "K1";
    neg[8] = "K1";
    neg[9] = "A1";

    pos[0] = "L1";
    pos[1] = "B1";
    pos[2] = "C1";
    pos[3] = "Z1";
    pos[4] = "A1";
    String stats = COVIDTracker.getPopStats(pos, neg);
    //Create a string for the expected value the string will return
    String result = "Total tests: 15\nTotal individuals tested: 8\nPercent positive tests: "
        + 100 * (5.0 / 15.0) + "%\nPercent positive individuals: 62.5%";
    System.out.println(stats); //print the actual value returned
    if (stats.equals(result)) //compare the value returned to the expected result
      return true;
    return false;
  }

  /**
   * 
   * Tests the CovidTracker class' getIndividualStats method
   * 
   * @return true if getIndividualStats' return matches the expected return value, false if it
   *         doesn't match
   */
  public static boolean testGetIndividualStats() {
  //create arrays to pass to method
    String[] pos = new String[20];
    String[] neg = new String[20];
    /**
     * neg[0] = "A1"; neg[1] = "B1"; neg[2] = "C1"; neg[3] = "A1"; neg[4] = "F1"; neg[5] = "D1";
     * neg[6] = "K1"; neg[7] = "K1"; neg[8] = "K1"; neg[9] = "A1";
     * 
     * pos[0] = "L1"; pos[1] = "B1"; pos[2] = "C1"; pos[3] = "Z1"; pos[4] = "A1";
     */
    String id = "A1";
    String stats = COVIDTracker.getIndividualStats(pos, neg, id);
    String result = "Total tests: 0\nPositive: 0\nNegative: 0";//string of the expected value
    System.out.println(stats);
    if (stats.equals(result))//compare expected value to actual value returned
      return true;
    return false;
  }
}

