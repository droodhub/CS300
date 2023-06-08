public class DroppableObject extends DraggableObject {
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P06 Treasure Hunt
//Course:   CS 300 Fall 2020
//
//Author:   Andrew McAvoy
//Email:    apmcavoy@wisc.edu
//Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:         none
//Online Sources:  GeeksForGeeks helped with isOver() method
//
///////////////////////////////////////////////////////////////////////////////
  private VisibleObject target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object
  // over target// initialize new object
  /**
   * @param name name of the image
   * @param x leftmost border of the image
   * @param y topmost border of the image
   * @param target object that will satisfy the drop method conditions
   * @param action to act when drop is successful
   */
  public DroppableObject(String name, int x, int y, VisibleObject target, Action action) {
    super(name, x, y);
    this.target = target;
    this.action = action;
  }
  /**
   * returns the action assigned to this object
   * when it is dropped on its target option
   */
  @Override
  protected Action drop() {
   if(this.isOver(target) && target.isActive()) {
     deactivate(); 
     target.deactivate();
     return action;
   }
   return null;
  } // returns action and deactivates objects
  // in response to successful drop
  // When this object is over its target and its target is active:
  // deactivate both this object and the target object, and return action,
  // otherwise return null
}

