import java.util.Iterator;
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
public class MessageStackIterator implements Iterator<Message> {
  private LinkedNode<Message> next;

  /**
   * constructor for MessageStackIterator
   * @param top topmost message of the stack to iterate through
   */
  public MessageStackIterator(LinkedNode<Message> top) {
    next = top;
  }

  /**
   *returns true if the stack has another message in it, false if not
   */
  @Override
  public boolean hasNext() {
    if (next != null)
      return true;
    return false;
  }

  /**
   * returns the message currently at the top of the stack
   * reassigns next variable to the new top of the stack
   */
  @Override
  public Message next() {
    if (next == null)
      throw new NoSuchElementException();
    Message returning = next.getData();
    next = next.getNext();
    return returning;
  }

}
