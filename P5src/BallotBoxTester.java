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

public class BallotBoxTester {

  public static void main(String[] args) {
Republican r = new Republican();
/**
    System.out.println(testCandidate());
    System.out.println(testPartyGetSize());
    System.out.println(testPartyConstructor());
    System.out.println(testGetCandidate());
    System.out.println(testBallotParties());
    System.out.println(testBallotVotes());
    System.out.println(testBallotBox()); 
    */
  }

  /**
   * tests the methods of the candidate class
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testCandidate() {
    Candidate c = new Candidate("Kyle Gregg", "Head Honcho");
    String expected = "Kyle Gregg (Head Honcho) ";
    if (!c.toString().equals(expected))
      return false;
    if (!c.getOffice().equals("Head Honcho"))
      return false;
    if (!c.getName().equals("Kyle Gregg"))
      return false;
    try {
      Candidate error = new Candidate("Biff", "Cat");
      System.out.println("Didn't throw an error for a nonexistent office");
      return false;
    } catch (IllegalArgumentException e) {

    }
    return true;
  }

  /**
   * tests the constructor for the party class
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testPartyConstructor() {
    Party p = new Party("Dogs");
    if (!p.getName().equals("Dogs"))
      return false;
    return true;
  }

  /**
   * tests the getCandidate method of the party class
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testGetCandidate() {
    Party p = new Party("Directors");
    Candidate c = new Candidate("Kyle Gregg", "Head Honcho");
    Candidate d = new Candidate("Paul Giamatti", "Big Boss");
    Candidate e = new Candidate("Burton Guster", "Head Honcho");
    try {

      p.addCandidate(c);
      p.addCandidate(d);
      if (!p.getCandidate("Big Boss").equals(d))
        return false;
      p.addCandidate(e);
      System.out.println("Added two candidates for the same office");
      return false;
    } catch (IllegalArgumentException f) {

    }
    try {
      p.getCandidate("Secretary");
      System.out.println("Should have thown error for sceretary");
      return false;

    } catch (NoSuchElementException g) {

    }
    return true;
  }

  /**
   * tests the getSize method of the party class
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testPartyGetSize() {
    Party p = new Party("Directors");
    Candidate c = new Candidate("Kyle Gregg", "Head Honcho");
    Candidate d = new Candidate("Paul Giamatti", "Big Boss");
    if (p.getSize() != 0)
      return false;
    p.addCandidate(c);
    p.addCandidate(d);;
    if (p.getSize() != 2)
      return false;
    return true;
  }

  /**
   * tests the voting portion of the ballot class
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testBallotVotes() {
    Party p = new Party("Directors");
    Candidate c = new Candidate("Kyle Gregg", "Head Honcho");
    Candidate d = new Candidate("Paul Giamatti", "Big Boss");
    p.addCandidate(c);
    p.addCandidate(d);
    Party q = new Party("Producers");
    Candidate e = new Candidate("Shawn Spencer", "Head Honcho");
    Candidate f = new Candidate("Carlton Lassiter", "Big Boss");
    q.addCandidate(e);
    q.addCandidate(f);
    Ballot.addParty(p);
    Ballot.addParty(q);
    Ballot b = new Ballot();
    b.vote(e);
    b.vote(d);
    Candidate r = b.getVote("Head Honcho");
    return true;
  }

  /**
   * tests the party portion of the Ballot class
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testBallotParties() {
    Party p = new Party("Directors");
    Candidate c = new Candidate("Kyle Gregg", "Head Honcho");
    Candidate d = new Candidate("Paul Giamatti", "Big Boss");
    p.addCandidate(c);
    p.addCandidate(d);
    Party q = new Party("Producers");
    Candidate e = new Candidate("Shawn Spencer", "Head Honcho");
    Candidate f = new Candidate("Carlton Lassiter", "Big Boss");
    q.addCandidate(e);
    q.addCandidate(f);
    Ballot.addParty(p);
    Ballot.addParty(q);
    ArrayList<Candidate> expected = new ArrayList<Candidate>();
    expected.add(c);
    expected.add(e);
    if (!expected.equals(Ballot.getCandidates("Head Honcho")))
      return false;
    return true;
  }

  /**
   * tests BallotBox class. Currently incomplete due to coding issues on gradescope.
   * 
   * @return whether or not the test ran properly
   */
  public static boolean testBallotBox() {
    Party p = new Party("Directors");
    Candidate c = new Candidate("Kyle Gregg", "Head Honcho");
    Candidate d = new Candidate("Paul Giamatti", "Big Boss");
    p.addCandidate(c);
    p.addCandidate(d);
    Party q = new Party("Producers");
    Candidate e = new Candidate("Shawn Spencer", "Head Honcho");
    Candidate f = new Candidate("Carlton Lassiter", "Big Boss");
    q.addCandidate(e);
    q.addCandidate(f);
    Ballot.addParty(p);
    Ballot.addParty(q);
    Ballot b = new Ballot();
    b.vote(e);
    Ballot a = new Ballot();
    a.vote(e);
    Ballot g = new Ballot();
    g.vote(e);
    Ballot h = new Ballot();
    h.vote(e);
    Ballot i = new Ballot();
    i.vote(c);
    Ballot j = new Ballot();
    j.vote(c);
    Ballot k = new Ballot();
    k.vote(e);
    BallotBox election = new BallotBox();
    election.submit(b);
    election.submit(a);
    election.submit(g);
    election.submit(h);
    election.submit(i);
    election.submit(j);
    election.submit(k);
   if(!election.getWinner("Head Honcho").getName().equals("Shawn Spencer"))
     return false;

    return true;
  }
}
