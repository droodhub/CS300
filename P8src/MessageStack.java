import java.util.EmptyStackException;
import java.util.Iterator;
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
public class MessageStack implements StackADT<Message>, Iterable<Message>{
private LinkedNode<Message> top;
private int size;
  /**
   * adds an element to the top of the stack
   *@param element Message to add to the top of the stack
   */
  @Override
  public void push(Message element) {
    LinkedNode<Message> newTop = new LinkedNode<Message>(element, top);
    top = newTop;
    size++;
    
  }

  /**
   *return and remove the message at the top of the stack
   */
  @Override
  public Message pop() {
    if(size == 0)
      throw new EmptyStackException();
    Message toReturn = top.getData();
    top = top.getNext();
    size--;
    return toReturn;
  }

  /**
   *look at the top of the message stack without removing it
   */
  @Override
  public Message peek() {
    if(size == 0)
      throw new EmptyStackException();
    return top.getData();
  }

  /**
   *returns true if empty, false if the stack contains messages
   */
  @Override
  public boolean isEmpty() {
    if(size == 0 && top == null)
      return true;
    return false;
  }

  /**
   *returns the size of the stack
   */
  @Override
  public int size() {
    return size;
  }

  /**
   *Creates and returns an object to iterate through the MessageStack
   */
  @Override
  public Iterator<Message> iterator() {
    return new MessageStackIterator(top);
  }

}
