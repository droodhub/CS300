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
public class RestaurantOrders implements SortedListADT<Order> {
  private LinkedOrder head;
  private LinkedOrder tail;
  private int size;
  private final int CAPACITY;

  /**
   * default constructor of the restaurantOrders class
   */
  public RestaurantOrders() {
    CAPACITY = 20;
  }

  /**
   * @param capacity maximum number of orders the list can take
   */
  public RestaurantOrders(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Capacity must be positive");
    CAPACITY = capacity;
  }

  /**
   * @return the capacity of the list
   */
  public int capacity() {
    return CAPACITY;
  }

  /**
   * @return a string with formatted contents of the list in sequential order
   */
  public String readForward() {
    LinkedOrder current = head;
    String composite = "The list contains " + size + " order(s)";
    if (size > 0)
      composite += ": [ ";
    for (int i = 0; i < size; i++) {
      composite += current.getOrder() + " ";
      if (current.getNext() != null)
        current = current.getNext();
    }
    if (size > 0)
      composite += "]";
    return composite;
  }

  /**
   * @return a string containing formatted contents of the list in reverse order
   */
  public String readBackward() {
    LinkedOrder current = tail;
    String composite = "The list contains " + size + " order(s)";
    if (size > 0)
      composite += ": [ ";
    for (int i = 0; i < size; i++) {
      composite += (current.getOrder() + " ");
      current = current.getPrevious();
    }
    if (size > 0)
      composite += "]";
    return composite;
  }

  /**
   * checks if the array is empty or if it contains any orders
   */
  @Override
  public boolean isEmpty() {
    if (head == null && tail == null && size == 0)
      return true;
    return false;
  }

  /**
   * adds a new LinkedOrder to the list with newOrder as its main order
   */
  @Override
  public void placeOrder(Order newOrder) {
    Order tester = new Order("Test", 0);
    if (newOrder == null || newOrder.compareTo(tester) < 0)
      throw new IllegalArgumentException("Timestamp cannot be negative");
    if (size == CAPACITY)
      return;
    if (size == 0) { // edge case for head
      LinkedOrder newNode = new LinkedOrder(newOrder, null, null);
      this.head = newNode;
      this.tail = newNode;
      size++;
      return;
    }
    LinkedOrder current = head;
    int i = 0;
    while (newOrder.compareTo(current.getOrder()) >= 0 && i < size) {
      if (newOrder.compareTo(current.getOrder()) == 0) {
        throw new IllegalArgumentException("Cannot have the same timestamp as a preexisting order");
      }
      current = current.getNext();
      i++;
      if (i == size) { // edge case for tail
        LinkedOrder newNode = new LinkedOrder(newOrder, tail, null);
        size++;
        tail.setNext(newNode);
        this.tail = newNode;
        return;
      }
    }

    LinkedOrder newNode = new LinkedOrder(newOrder, current.getPrevious(), current);
    if (current.getPrevious() == null) { //if current is the head and we are adding in front of the head
      current.setPrevious(newNode); 
      head = newNode; //reassign head val to new node
      size++;
      return;
    }
    current.getPrevious().setNext(newNode);
    current.setPrevious(newNode);
    size++;

  }

  /**
   *returns the size of the list
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return size;
  }

  /**
   * empties the list
   */
  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;

  }

  /**
   * returns WITHOUT REMOVING the order at given index
   */
  @Override
  public Order get(int index) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException();
    LinkedOrder current = head;
    for (int i = 0; i < size; i++) {
      current = current.getNext();
      if (i == index - 1)
        return current.getOrder();
    }
    return null;
  }

  /**
   * returns the index of the given order
   */
  @Override
  public int indexOf(Order findObject) {
    LinkedOrder current = head;
    for (int i = 0; i < size; i++) {
      if (current.getOrder().equals(findObject))
        return i;
      current = current.getNext();
    }
    return -1;
  }

  /**
   *removes and returns the order at the given index
   *@return the order found at index
   */
  @Override
  public Order removeOrder(int index) {
    if(size == 0)
      throw new IndexOutOfBoundsException("Cannot remove from an empty list");
    if(index > size)
     throw new IndexOutOfBoundsException("Index cannot be greater than size");
    if (index == 0) {
      Order returning = head.getOrder();
      this.head = head.getNext();
      size--;
      if(size == 0) 
        tail = null;
      return returning;
    }
    LinkedOrder current = head;
    for (int i = 0; i < size; i++) {
      if (i == index) {
        if(index == size-1) {
          current.getPrevious().setNext(null);
          tail = current.getPrevious();
          size--;
          return current.getOrder();
        }
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
        size--;
        if(size == 0) 
          tail = null;
        return current.getOrder();
      }
      current = current.getNext();
    }
    return null;
  }

}
