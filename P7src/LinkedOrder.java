//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Sustenance Boulevard
// Course:   CS 300 Fall 2020
//
// Author:   Andrew McAvoy
// Email:    apmcavoy@wisc.edu
// Lecturer: Mouna Kacem 
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
public class LinkedOrder {
  private final Order ORDER;
  private LinkedOrder previous;
  private LinkedOrder next;

  /**
   * @param order order to set as the central order of the class
   */
  public LinkedOrder(Order order) {
    Order tester = new Order("Test", 0);
    if (order.compareTo(tester) < 0)
      throw new IllegalArgumentException("timeStamp cannot be negative");
    ORDER = order;
    previous = null;
    next = null;
  }

  /**
   * constructor of the linkedorder class
   * @param order central order of the object
   * @param prev order that comes before order obj
   * @param next order that comes after order obj
   */
  public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next) {
    Order tester = new Order("Test", 0);
    if (order.compareTo(tester) < 0)
      throw new IllegalArgumentException("timeStamp cannot be negative");
    ORDER = order;
    previous = prev;
    this.next = next;
  }

  /**
   * @return order contained in the ORDER field
   */
  public Order getOrder() {
    return ORDER;
  }

  /**
   * @return order contained in the previous field
   */
  public LinkedOrder getPrevious() {
    return previous;
  }

  /**
   * @return order contained in the next field
   */
  public LinkedOrder getNext() {
    return next;
  }

  /**
   * @param previous order to assign to the object's previous field
   */
  public void setPrevious(LinkedOrder previous) {
    this.previous = previous;
  }

  /**
   * @param next order to assign to the objects next field
   */
  public void setNext(LinkedOrder next) {
    this.next = next;
  }

}
