import java.util.EmptyStackException;
import java.util.NoSuchElementException;

////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P08 LIFO Inbox Reader
//Course:   CS 300 Fall 2020
//
//Author:   Andrew McAvoy
//Email:    apmcavoy@wisc.edu
//Lecturer: (Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:         None
//Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 */
public class InboxReaderTester {

  /**
   * Calls your tester methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("Project testing returns " + runInboxReaderTestSuite());
  }

  // add the runInboxReaderTestSuite() method and your additional tester methods

  /**
   * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
   * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
   * consider at least the test scenarios provided in the detailed description of these javadocs.
   * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
   * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
   * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
   * use peek to verify that the correct item is at the top of the stack. Make sure also to check
   * that isEmpty() must return false and the size of the stack is one. The peek() method call
   * should not make any change to the contents of the stack. (4) You can further consider pushing
   * other elements onto the stack. Then, each call of peek() method should return the correct
   * Message object that should be at the top of the stack. After pushing a new message to the stack
   * double check that the size of the stack was incremented by one.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackConstructorIsEmptyPushPeek() {
    MessageStack m = new MessageStack();
    if (m.size() != 0)
      return false;
    try {
      m.peek();
      return false;
    } catch (EmptyStackException e) {
      System.out.println("Caught emptyStackException for peek() on an empty stack");
    }
    Message m1 = new Message("CS300", "I have no idea what I'm doing");
    m.push(m1);
    if (m.isEmpty() || m.peek() != m1 || m.size() != 1)
      return false;
    Message m2 =
        new Message("Covid-19 Response", "Our visitor policy has been re-updated(again(again))");
    Message m3 = new Message("Green Bay Packers", "We're doing pretty well right now!");
    m.push(m2);
    m.push(m3);
    if (m.peek() != m3 || m.size() != 3)
      return false;
    return true;
  } //


  /**
   * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
   * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
   * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
   * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
   * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
   * which contains only one element. Make sure that the pop() operation returned the expected
   * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
   * messages, then call pop method on the stack which contains 3 elements, the element at the top
   * of the stack must be returned and removed from the stack and its size must be decremented by
   * one. You can further keep popping the elements of the stack one by one until it becomes empty
   * and check each time that the pop operation performs appropriately.This test method must return
   * false if it detects at least one defect.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackPop() {
    MessageStack m = new MessageStack();
    if (m.size() != 0)
      return false;
    try {
      m.pop();
      return false;
    } catch (EmptyStackException e) {
      System.out.println("Caught emptyStackException for pop() on an empty stack");
    }
    Message m1 = new Message("CS300", "I have no idea what I'm doing");
    m.push(m1);
    if (m.pop() != m1 || m.size() != 0 || !m.isEmpty())
      return false;
    Message m2 =
        new Message("Covid-19 Response", "Our visitor policy has been re-updated(again(again))");
    Message m3 = new Message("Green Bay Packers", "We're doing pretty well right now!");
    Message m4 = new Message("New Message Ideas", "I'm starting to run out of them");
    m.push(m2);
    m.push(m3);
    m.push(m4);
    if (m.pop() != m4 || m.size() != 2)
      return false;
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.ReadMessage() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReadMessage() {
    Inbox outlook = new Inbox();
    Message m1 = new Message("CS300", "I have no idea what I'm doing");
    Message m2 =
        new Message("Covid-19 Response", "Our visitor policy has been re-updated(again(again))");
    Message m3 = new Message("Green Bay Packers", "We're doing pretty well right now!");
    Message m4 = new Message("New Message Ideas", "I'm starting to run out of them");
    if (!outlook.readMessage().equals("Nothing in Unread"))
      return false;
    if (!(outlook.getStatistics().equals("Unread 0\nRead 0")))
      return false;

    outlook.receiveMessage(m1);
    outlook.receiveMessage(m2);
    outlook.receiveMessage(m3);
    outlook.receiveMessage(m4);
    if (!(outlook.readMessage().equals(m4.toString()))
        || !(outlook.peekReadMessage().equals(m4.toString()))
        || !(outlook.getStatistics().equals("Unread 3\nRead 1")))
      return false;
    // Define your own test scenarios to check the correctness of Inbox.ReadMessage()
    // Your test method must return false if it detects at least one defect
    // Vary your test scenarios. Make sure to consider at least two test scenarios:
    // (1) when Inbox.unreadMessageBox is empty
    // (2) when Inbox.unreadMessageBox is not empty. You have to make sure the read message
    // has been popped off the Inbox.unreadMessageBox and pushed into the Inbox.readMessageBox
    // You can rely on Inbox.peekReadMessage() and Inbox.getStatistics() to check the method
    // behavior was as expected.
    return true;
  }


  /**
   * Checks for the correctness of the Inbox.ReceiveMessage() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReceiveMessage() {
    // Define your own test scenarios to check the correctness of Inbox.receiveMessage()
    // Your test method must return false if it detects at least one defect
    Inbox outlook = new Inbox();
    Message m1 = new Message("CS300", "I have no idea what I'm doing");
    Message m2 =
        new Message("Covid-19 Response", "Our visitor policy has been re-updated(again(again))");
    Message m3 = new Message("Green Bay Packers", "We're doing pretty well right now!");
    outlook.receiveMessage(m1);
    if (!outlook.readMessage().equals(m1.toString()))
      return false;
    outlook.receiveMessage(m2);
    outlook.receiveMessage(m3);
    if (!outlook.readMessage().equals(m3.toString()))
      return false;
    if (!outlook.getStatistics().equals("Unread 1\nRead 2"))
      return false;
    if (!outlook.peekReadMessage().equals(m3.toString()))
      return false;
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.markAllMessagesAsRead() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxMarkAllMessagesAsRead() {
    // Define your own test scenarios to check the correctness of Inbox.markAllMessagesAsRead()
    // Your test method must return false if it detects at least one defect
    Inbox outlook = new Inbox();
    Message m1 = new Message("CS300", "I have no idea what I'm doing");
    Message m2 =
        new Message("Covid-19 Response", "Our visitor policy has been re-updated(again(again))");
    Message m3 = new Message("Green Bay Packers", "We're doing pretty well right now!");
    Message m4 = new Message("New Message Ideas", "I'm starting to run out of them");
    if (outlook.markAllMessagesAsRead() != 0)
      return false;
    outlook.receiveMessage(m1);
    outlook.receiveMessage(m2);
    outlook.receiveMessage(m3);
    outlook.receiveMessage(m4);
    if (outlook.markAllMessagesAsRead() != 4)
      return false;
    return true;
  }

  /**
   * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
   * methods. You can rely on the constructor of the LinkedNode class which takes two input
   * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
   * least 3 linked nodes) which carry messages as data fields. Then, create a new
   * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
   * recommend that you consider at least the following test scenarios in this tester method. (1)
   * Try to call next() on the iterator. The first call of next() must return the message at the
   * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
   * true. (3) The second call of next() must return the message which has been initially at index 1
   * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
   * message initially at index 2 of your chain of linked nodes. (4) If you defined a chain of 3
   * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
   * the iterator must throw a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testMessageStackIterator() {
    Message m1 = new Message("CS300", "I have no idea what I'm doing");
    Message m2 =
        new Message("Covid-19 Response", "Our visitor policy has been re-updated(again(again))");
    Message m3 = new Message("Green Bay Packers", "We're doing pretty well right now!");
    Message m4 = new Message("New Message Ideas", "I'm starting to run out of them");
    LinkedNode<Message> m1Link = new LinkedNode<Message>(m1);
    LinkedNode<Message> m2Link = new LinkedNode<Message>(m2);
    LinkedNode<Message> m3Link = new LinkedNode<Message>(m3);
    LinkedNode<Message> m4Link = new LinkedNode<Message>(m4);
    m1Link.setNext(m2Link);
    m2Link.setNext(m3Link);
    m3Link.setNext(m4Link);
    MessageStackIterator m = new MessageStackIterator(m1Link);
    if (!m.hasNext())
      return false;
    if (!m.next().equals(m1))
      return false;
    if (!m.next().equals(m2))
      return false;
    if (!m.next().equals(m3))
      return false;
    if (!m.next().equals(m4))
      return false;
    if (m.hasNext())
      return false;
    try {
      m.next();
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Successfully caught NoSuchElementException e");
    }
    return true;
  }

  public static boolean runInboxReaderTestSuite() {
    if (testStackConstructorIsEmptyPushPeek() && testStackPop() && testInboxReadMessage()
        && testInboxReceiveMessage() && testInboxMarkAllMessagesAsRead()
        && testMessageStackIterator())
      return true;
    return false;
  }



}
