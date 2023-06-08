import java.util.ArrayList;
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
public class Action {
  private InteractiveObject object;
  private String message; // message printed by this action (or null to do nothing)
  /**
   * @param message message to initialize
   */
  public Action(String message) {
    this.message = message;
  } // create and initialize this new action
  /**
   * @param object object to initialize
   */
  public Action(InteractiveObject object) {
    this.object = object;
  }
  /**
   * @param message message to display during act method
   * @param object object to active during act method
   */
  public Action(String message, InteractiveObject object) {
    this.message = message; 
    this.object = object;
  }
  /**
   * displays the action's message and activates an object if it has one
   * @param a arrayList to add the now-activated object to
   */
  public void act(ArrayList<InteractiveObject> a) { 
    if(object != null) {
      object.activate();
      a.add(object);
      object = null;
    }
    if(!message.equals(null))
      System.out.println(message);
  } // when message is not null, message is printed to System.out
}
