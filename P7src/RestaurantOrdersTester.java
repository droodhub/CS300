//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Sustenance Boulevard
// Course: CS 300 Fall 2020
//
// Author: Andrew McAvoy
// Email: apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class implements unit test methods to check the correctness of LinkedOrders and
 * RestaurantOrders classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming
 * assignment.
 *
 */
public class RestaurantOrdersTester {

  /**
   * This method should test and make use of at least the LinkedOrders constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedOrders() {
    Order main = new Order("Lasagna", 2);
    LinkedOrder tester = new LinkedOrder(main);
    if (tester.getOrder() != main) {
      return false;
    }
    Order next = new Order("Cake", 4);
    LinkedOrder toAdd = new LinkedOrder(next);
    tester.setNext(toAdd);
    if (tester.getNext() != toAdd)
      return false;
    return true;
  }

  /**
   * This method checks for the correctness of both RestaurantOrders constructors and the instance
   * method isEmpty() when called on an empty restaurant orders object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorIsEmpty() {
    RestaurantOrders tester = new RestaurantOrders();
    if (!tester.isEmpty())
      return false;
    if (tester.size() != 0)
      return false;
    Order main = new Order("Pizza", 1);
    tester.placeOrder(main);
    if (tester.size() != 1)
      return false;
    if (tester.capacity() != 20)
      return false;
    RestaurantOrders tester2 = new RestaurantOrders(17);
    if (tester2.capacity() != 17)
      return false;
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
   * negative int value for the capacity.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorBadInput() {
    try {
      RestaurantOrders badTester = new RestaurantOrders(-13);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    }
  }


  /**
   * This method checks for the correctness of the RestaurantOrders.add() method when it is passed
   * bad inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding an order which
   * carries a negative timestamp. (3) Try adding an order with an existing timestamp to the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAddBadInput() {
    RestaurantOrders orders = new RestaurantOrders(10);
    Order order1 = new Order("Burger", 1);
    orders.placeOrder(order1);
    Order order2 = new Order("Sandwich", 3);
    orders.placeOrder(order2);
    Order order3 = new Order("Pizza", 2);
    orders.placeOrder(order3);
    Order order4 = new Order("Falafel", 4);
    orders.placeOrder(order4);
    Order order5 = new Order("Noodles", 5);
    orders.placeOrder(order5);
    try {
      Order badOrder = new Order("Bagel", 1);
      orders.placeOrder(badOrder);
    } catch (IllegalArgumentException e) {
      System.out.println("Successfully caught order with existing timestamp");
    }
    try {
      Order badOrder = new Order("Bagel", -1);
      orders.placeOrder(badOrder);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Successfully caught order with negative timestamp");
    }
    try {
      Order badOrder = null;
      orders.placeOrder(badOrder);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Successfully caught null order");
    }
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.add() considering at least the
   * test scenarios provided in the detailed description of these javadocs. (1) Try adding an order
   * to an empty list; (2) Try adding an order which is expected to be added at the head of a
   * non-empty restaurant list; (3) Try adding an order which is expected to be added at the end of
   * a non-empty restaurant list; (4) Try adding an order which is expected to be added at the
   * middle of a non-empty restaurant list. For each of those scenarios, make sure that the size of
   * the list is appropriately updated after a call without errors of the add() method, and that the
   * contents of the list is as expected whatever if list is read in the forward or backward
   * directions. You can also check the correctness of RestaurantOrders.get(int),
   * RestaurantOrders.indexOf(Order), and RestaurantOrders.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAdd() {
    RestaurantOrders orders = new RestaurantOrders(5);
    Order order1 = new Order("Burger", 2);
    orders.placeOrder(order1);
    Order order2 = new Order("Sandwich", 5);
    orders.placeOrder(order2);
    Order order3 = new Order("Pizza", 1);
    orders.placeOrder(order3);
    Order order4 = new Order("Falafel", 4);
    orders.placeOrder(order4);
    Order order5 = new Order("Noodles", 3);
    orders.placeOrder(order5);
    String expected = "The list contains 5 order(s): [ Pizza Burger Noodles Falafel Sandwich ]";
    if (!expected.equals(orders.readForward()))
      return false;
    if (orders.size() != 5)
      return false;
    if (orders.indexOf(order5) != 2)
      return false;
    if (orders.get(2) != order5)
      return false;
    return true;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.remove() considering at least
   * the test scenarios provided in the detailed description of these javadocs. (1) Try removing an
   * order from an empty list or pass a negative index to RestaurantOrders.remove() method; (2) Try
   * removing an order (at position index 0) from a list which contains only one order; (3) Try to
   * remove an order (position index 0) from a list which contains at least 2 orders; (4) Try to
   * remove an order from the middle of a non-empty list containing at least 3 orders; (5) Try to
   * remove the order at the end of a list containing at least two orders. For each of those
   * scenarios, make sure that the size of the list is appropriately updated after a call without
   * errors of the add() method, and that the contents of the list is as expected whatever if list
   * is read in the forward or backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersRemove() {
    RestaurantOrders orders = new RestaurantOrders(5);
    try {
      orders.removeOrder(1);
      return false;
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Successfully caught error for removing from an empty list");
    }
    Order order1 = new Order("Burger", 2);
    orders.placeOrder(order1);
    if (orders.removeOrder(0) != order1 && orders.size() != 0)
      return false;
    Order order2 = new Order("Sandwich", 5);
    orders.placeOrder(order2);
    Order order3 = new Order("Pizza", 1);
    orders.placeOrder(order3);
    Order order4 = new Order("Falafel", 4);
    orders.placeOrder(order4);
    Order order5 = new Order("Noodles", 3);
    orders.placeOrder(order5);
    String contents = "The list contains 3 order(s): [ Pizza Noodles Sandwich ]";
    if (orders.removeOrder(2) != order4 && !orders.readForward().equals(contents)
        && orders.size() != 3) // remove middle
      return false;
    contents = "The list contains 2 order(s): [ Noodles Sandwich ]";
    if (orders.removeOrder(0) != order3 && !orders.readForward().equals(contents)
        && orders.size() != 2) // remove head
      return false;
    contents = "The list contains 1 order(s): [ Noodles ]";
    if (orders.removeOrder(1) != order2 && !orders.readForward().equals(contents)
        && orders.size() != 1)
      return false;
    return true;
  }


  /**
   * This method calls all the test methods defined and implemented in your RestaurantOrdersTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (testRestaurantOrdersRemove() && testRestaurantOrdersAdd()
        && testRestaurantOrdersAddBadInput() && testRestaurantOrdersConstructorBadInput()
        && testRestaurantOrdersConstructorIsEmpty() && testLinkedOrders())
      return true;
    return false;
  }

  /**
   * Driver method defined in this RestaurantOrdersTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
