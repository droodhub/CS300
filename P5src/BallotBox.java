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

public class BallotBox {
  private ArrayList<Ballot> ballots;

  /**
   * initializes instance variables for ballotBox object
   */
  public BallotBox() {
    ballots = new ArrayList<Ballot>();
  }

  /**
   * @return number of ballots cast in this ballotBox
   */
  public int getNumBallots() {
    return ballots.size();
  }

  /**
   * determines the winner for the given office
   * 
   * @param office position we are trying to determine the winner of
   * @return candidate that won the office
   */
  public Candidate getWinner(String office) {
    ArrayList<Candidate> potential = Ballot.getCandidates(office);
    if(potential.size() == 0)
      return null;
    int[] votes = new int[potential.size()];
    for (int i = 0; i < ballots.size(); i++) {
      String name = ballots.get(i).getVote(office).getName();
      for(int j = 0; j < potential.size(); j++) {
        if(potential.get(j).getName().equals(name))
          votes[j]++;
      }
    }
    int maxPos = 0;
    for (int i = 0; i < votes.length; i++) {
      if (votes[i] > votes[maxPos])
        maxPos = i;
    }
    return potential.get(maxPos);
  }

  /**
   * adds a ballot to the ballotBox. Checks to make sure the ballot has not been added first
   * 
   * @param b ballot to be added to the ballotBox array of ballots
   */
  public void submit(Ballot b) {
    for (int i = 0; i < ballots.size(); i++) {
      if (ballots.get(i).equals(b))
        return;
    }
    ballots.add(b);
  }
}
