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

public class Ballot {
  private static ArrayList<Party> parties = new ArrayList<Party>();
  private static int counter = 0;
  private Candidate[] votes;
  final int ID;

  /**
   * @param p party to be added to the static list parties
   */
  public static void addParty(Party p) {
    for (int i = 0; i < parties.size(); i++) {
      if (parties.get(i).getName().equals(p.getName()))
        return;
    }
    parties.add(p);
  }

  /**
   * @param office the position we are trying to look at
   * @return list of all candidates running for the given office
   */
  public static ArrayList<Candidate> getCandidates(String office) {
    ArrayList<Candidate> returnList = new ArrayList<Candidate>();
    for (int i = 0; i < parties.size(); i++) {
      try {
        returnList.add(parties.get(i).getCandidate(office));
      } catch (NoSuchElementException e) {
        // expected behavior
      }
    }
    return returnList;
  }

  /**
   * Initialize instance variables for the ballot
   */
  public Ballot() {
    ID = counter;
    counter++;
    votes = new Candidate[Candidate.OFFICE.length];
  }

  /**
   * return the candidate that this ballot voted for for the given office
   * 
   * @param office the position we are looking for the information on
   * @return the candidate this ballot has voted for
   */
  public Candidate getVote(String office) {
    for (int i = 0; i < votes.length; i++) {
      if (votes[i] instanceof Candidate && votes[i].getOffice().equals(office)) {
        return votes[i];
      }
    }
    return null;
  }

  /**
   * Custom version of the default equals() method
   * 
   * @Override
   */
  public boolean equals(Object o) {
    if (o.getClass() != this.getClass()) {
      return false;
    }
    Ballot sub = (Ballot) o;
    if (this.ID - sub.ID == 0) {
      return true;
    }
    return false;
  }

  /**
   * @param c candidate that the ballot will vote for
   */
  public void vote(Candidate c) {
    for (int i = 0; i < votes.length; i++) {
      if (c.getOffice().equals(Candidate.OFFICE[i]))
        votes[i] = c;
    }
  }
}
