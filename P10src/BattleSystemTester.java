//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 Battle System
// Course:   CS 300 Fall 2020
//
// Author:   Andrew McAvoy
// Email:    apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         none
// Online Sources:  zybooks gave assistane with percolateUp/down and heapify algos
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

public class BattleSystemTester {

  public static void main(String[] args) {
    System.out.println(testEnqueueMoveQueue());
    System.out.println(testDequeueMoveQueue());
    System.out.println(testUpdateCharacter());
    System.out.println(testMiscellaneous());

  }

  /**
   * tests enqueue and percolateUp for moveQueue class
   * 
   * @return true if all tests pass
   */
  public static boolean testEnqueueMoveQueue() {
    MoveQueue tester1 = new MoveQueue();
    BattleCharacter char1 = new BattleCharacter("Chrom", new int[] {5, 5, 5, 5, 8});
    BattleCharacter char2 = new BattleCharacter("Roy", new int[] {7, 9, 2, 4, 9});
    BattleCharacter char3 = new BattleCharacter("Lucina", new int[] {5, 5, 5, 5, 7});
    BattleCharacter char4 = new BattleCharacter("Marth", new int[] {4, 6, 8, 1, 3});
    BattleCharacter char5 = new BattleCharacter("Robin", new int[] {5, 3, 7, 9, 5});
    BattleCharacter char6 = new BattleCharacter("Corrin", new int[] {8, 4, 7, 5, 4});
    BattleCharacter char7 = new BattleCharacter("Ganon", new int[] {9, 12, 7, 6, 1});
    BattleCharacter char8 = new BattleCharacter("Sonic", new int[] {4, 8, 1, 1, 10});
    BattleCharacter char9 = new BattleCharacter("Cloud", new int[] {5, 3, 3, 3, 5});
    BattleCharacter char10 = new BattleCharacter("Ike", new int[] {7, 7, 7, 3, 2});
    BattleCharacter char11 = new BattleCharacter("Incineroar", new int[] {8, 8, 5, 5, 1});
    tester1.enqueue(char1);
    if (!(tester1.toString().equals("[ Chrom(1, 8) | ]")))
      return false;
    try {
      tester1.enqueue(null);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Successfully threw exception trying to enqueue() a null object");
    }
    tester1.enqueue(char2);
    tester1.enqueue(char3);
    tester1.enqueue(char4);
    tester1.enqueue(char5);
    tester1.enqueue(char6);
    tester1.enqueue(char7);
    tester1.enqueue(char8);
    tester1.enqueue(char9);
    tester1.enqueue(char10);
    String expected =
        "[ Sonic(8, 10) | Roy(2, 9) | Lucina(3, 7) | Chrom(1, 8) | Robin(5, 5) | Corrin(6, 4) | Ganon(7, 1) | Marth(4, 3) | Cloud(9, 5) | Ike(10, 2) | ]";
    if (!tester1.toString().equals(expected))
      return false;
    try {
      tester1.enqueue(char11);
      return false;
    } catch (IllegalStateException e) {
      System.out.println("Successfully threw exception when trying to enqueue() a full array");
    }
    return true;
  }

  /**
   * tests dequeue and percolateDown for moveQueue class
   * 
   * @return true if all tests pass
   */
  public static boolean testDequeueMoveQueue() {
    MoveQueue tester2 = new MoveQueue();
    try {
      tester2.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception for calling dequeue on an empty array");
    }
    BattleCharacter char1 = new BattleCharacter("Chrom", new int[] {5, 5, 5, 5, 8});
    BattleCharacter char2 = new BattleCharacter("Roy", new int[] {7, 9, 2, 4, 9});
    BattleCharacter char3 = new BattleCharacter("Lucina", new int[] {5, 5, 5, 5, 7});
    BattleCharacter char4 = new BattleCharacter("Marth", new int[] {4, 6, 8, 1, 3});
    BattleCharacter char5 = new BattleCharacter("Robin", new int[] {5, 3, 7, 9, 5});
    BattleCharacter char6 = new BattleCharacter("Corrin", new int[] {8, 4, 7, 5, 4});
    BattleCharacter char7 = new BattleCharacter("Ganon", new int[] {9, 12, 7, 6, 1});
    BattleCharacter char8 = new BattleCharacter("Sonic", new int[] {4, 8, 1, 1, 10});
    BattleCharacter char9 = new BattleCharacter("Cloud", new int[] {5, 3, 3, 3, 5});
    BattleCharacter char10 = new BattleCharacter("Ike", new int[] {7, 7, 7, 3, 2});
    BattleCharacter char11 = new BattleCharacter("Incineroar", new int[] {8, 8, 5, 5, 1});
    tester2.enqueue(char1);
    if (!tester2.dequeue().equals(char1) || tester2.size() != 0)
      return false;
    tester2.enqueue(char2);
    tester2.enqueue(char3);
    tester2.enqueue(char4);
    tester2.enqueue(char5);
    tester2.enqueue(char6);
    tester2.enqueue(char7);
    tester2.enqueue(char8);
    tester2.enqueue(char9);
    tester2.enqueue(char10);
    tester2.enqueue(char11);
    if (!tester2.dequeue().equals(char8) || tester2.size() != 9)
      return false;
    return true;
  }

  /**
   * teste updateCharacter() method for a variety of situations
   * 
   * @return true if all tests pass
   */
  public static boolean testUpdateCharacter() {
    MoveQueue tester3 = new MoveQueue();
    BattleCharacter char1 = new BattleCharacter("Chrom", new int[] {5, 5, 5, 5, 8});
    tester3.enqueue(char1);
    char1.takeDamage(9000);
    tester3.updateCharacter(char1);
    if (tester3.size() != 0)
      return false;
    BattleCharacter char2 = new BattleCharacter("Roy", new int[] {7, 9, 2, 4, 9});
    BattleCharacter char3 = new BattleCharacter("Lucina", new int[] {5, 5, 5, 5, 7});
    BattleCharacter char4 = new BattleCharacter("Marth", new int[] {4, 6, 8, 1, 3});
    BattleCharacter char5 = new BattleCharacter("Robin", new int[] {5, 3, 7, 9, 5});
    BattleCharacter char6 = new BattleCharacter("Corrin", new int[] {8, 4, 7, 5, 4});
    BattleCharacter char7 = new BattleCharacter("Ganon", new int[] {9, 12, 7, 6, 1});
    BattleCharacter char8 = new BattleCharacter("Sonic", new int[] {4, 8, 1, 1, 10});
    BattleCharacter char9 = new BattleCharacter("Cloud", new int[] {5, 3, 3, 3, 5});
    BattleCharacter char10 = new BattleCharacter("Ike", new int[] {7, 7, 7, 3, 2});
    BattleCharacter char11 = new BattleCharacter("Incineroar", new int[] {8, 8, 5, 5, 1});
    tester3.enqueue(char2);
    tester3.enqueue(char3);
    tester3.enqueue(char4);
    tester3.enqueue(char5);
    tester3.enqueue(char6);
    tester3.enqueue(char7);
    tester3.enqueue(char8);
    tester3.enqueue(char9);
    tester3.enqueue(char10);
    tester3.enqueue(char11);
    char5.takeDamage(9000);
    tester3.updateCharacter(char5);
    String expected =
        "[ Sonic(30, 10) | Lucina(25, 7) | Roy(24, 9) | Cloud(31, 5) | Corrin(28, 4) | Ganon(29, 1) | Marth(26, 3) | Incineroar(33, 1) | Ike(32, 2) | ]";
    if (!tester3.toString().equals(expected) || tester3.size() != 9)
      return false;
    char10.takeDamage(2);
    tester3.updateCharacter(char10);
    if (!tester3.toString().equals(expected) | tester3.size() != 9)
      return false;
    return true;
  }

  /**
   * tests other methods not tested in previous tester methods
   * 
   * @return true if all tests pass
   */
  public static boolean testMiscellaneous() {
    MoveQueue tester4 = new MoveQueue();
    BattleCharacter char1 = new BattleCharacter("Chrom", new int[] {5, 5, 5, 5, 8});
    BattleCharacter char2 = new BattleCharacter("Roy", new int[] {7, 9, 2, 4, 9});
    BattleCharacter char3 = new BattleCharacter("Lucina", new int[] {5, 5, 5, 5, 7});
    BattleCharacter char4 = new BattleCharacter("Marth", new int[] {4, 6, 8, 1, 3});
    BattleCharacter char5 = new BattleCharacter("Robin", new int[] {5, 3, 7, 9, 5});
    BattleCharacter char6 = new BattleCharacter("Corrin", new int[] {8, 4, 7, 5, 4});
    BattleCharacter char7 = new BattleCharacter("Ganon", new int[] {9, 12, 7, 6, 1});
    BattleCharacter char8 = new BattleCharacter("Sonic", new int[] {4, 8, 1, 1, 10});
    BattleCharacter char9 = new BattleCharacter("Cloud", new int[] {5, 3, 3, 3, 5});
    BattleCharacter char10 = new BattleCharacter("Ike", new int[] {7, 7, 7, 3, 2});
    if (!tester4.isEmpty())
      return false;
    try {
      tester4.peekBest();
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Caught exception for peekBest() on empty heap");
    }
    tester4.enqueue(char1);
    tester4.enqueue(char2);
    tester4.enqueue(char3);
    tester4.enqueue(char4);
    tester4.enqueue(char5);
    tester4.enqueue(char6);
    tester4.enqueue(char7);
    tester4.enqueue(char8);
    tester4.enqueue(char9);
    tester4.enqueue(char10);
    if (!tester4.peekBest().equals(char8))
      return false;
    if (tester4.isEmpty())
      return false;
    tester4.clear();
    if (tester4.size() != 0)
      return false;
    return true;
  }
}
