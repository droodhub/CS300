//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 BallotBox
// Course: CS 300 Fall 2020
//
// Author: Andrew McAvoy
// Email: apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////
public class Candidate {
  protected static final String[] OFFICE = new String[] {"Head Honcho", "Big Boss", "Secretary"};
  private String name;
  private String office;
public Candidate() {
  name = "Anon";
  System.out.println("Default Candidate constructor called");
}
  /**
   * @param name   name of the candidate
   * @param office position that the candidate is running for
   */
  public Candidate(String name, String office) {
    System.out.println("String constructor called");
    this.name = name;
    for (int i = 0; i < OFFICE.length; i++) {
      if (office.equals(OFFICE[i])) {
        this.office = office;
        return;
      }
    }
    throw new IllegalArgumentException("Given office name does not exist");
  }

  /**
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * @return
   */
  public String getOffice() {
    return office;
  }

  /**
   *
   */
  public String toString() {
    return name + " (" + office + ") ";
  }
}
