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
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Party {
  private String name;
  private ArrayList<Candidate> candidates = new ArrayList<Candidate>();

  /**
   * @param name name of the party
   */
  public Party(String name) {
    this.name = name;
  }

  /**
   * @return name of the party
   */
  public String getName() {
    return name;
  }

  /**
   * @return number of candidates within the party
   */
  public int getSize() {
    return candidates.size();
  }

  /**
   * @param office position that we are interested in
   * @return candidate that is running for the office for the given party
   */
  public Candidate getCandidate(String office) {
    for (int i = 0; i < candidates.size(); i++) {
      if (candidates.get(i).getOffice().equals(office))
        return candidates.get(i);
    }
    throw new NoSuchElementException("There is no candidate for the given office");
  }

  /**
   * adds a candidate to the party's arrayList candidates if they are running for a position nobody
   * is currently running for
   * 
   * @param c candidate to be added to party
   */
  public void addCandidate(Candidate c) {
    for (int i = 0; i < candidates.size(); i++) {
      if (candidates.get(i).getOffice().equals(c.getOffice()))
        throw new IllegalArgumentException("Cannot have 2 candidates running for the same office");
    }
    candidates.add(c);
  }
}
