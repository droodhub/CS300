import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class InteractiveObject {
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
  private static PApplet processing = null;

  /**
   * @param processing field to set as the class' processing field
   */
  public static void setProcessing(PApplet processing) {
    InteractiveObject.processing = processing;
  }

  // initializes processing field
  /**
   * @return processing field for this application
   */
  protected static PApplet getProcessing() {
    return processing;
  } // accessor method to retrieve this static field

  private final String NAME; // the constant name identifying this interactive object
  private boolean isActive; // active means this interactive object is visible and
  // can be interacted with

  /**
   * initializes a new interactiveObject
   * @param name name of the object
   */
  public InteractiveObject(String name) {
    NAME = name;
    isActive = true;
  }
  // initializes the name of this object,
  // and sets isActive to true
  
  /**
   * checks if the object has the given name
   * @param name name to check against object's NAME
   * @return true if the two names are equal
   */
  public boolean hasName(String name) {
    if (name.equals(NAME))
      return true;
    return false;
  }

  // returns true only when contents of name equal NAME
  /**
   * @return true if the object is active
   */
  public boolean isActive() {
    if (isActive)
      return true;
    return false;
  }

  // returns true only when isActive is true
  /**
   * // changes isActive to true
   */
  public void activate() {
    isActive = true;
  } 

  /**
   * // changes isActive to false
   */
  public void deactivate() {
    isActive = false;
  } 

  /**
   * @return null, will change with subclasses
   */
  public Action update() {
    return null;
  } // this method returns null
  // subclass types will override this update() method to do more interesting things
}
