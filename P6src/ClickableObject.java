import processing.core.PApplet;
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
public class ClickableObject extends VisibleObject {
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed
  // during the last update()
  // initializes this new object

  /**
   * @param name name to initialize 
   * @param x left position of the image
   * @param y top position of the image
   * @param action action to display if the object is clicked
   */
  public ClickableObject(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action;
    mouseWasPressed = false;
  }

  /**
   * returns the action of the object if it is clicked
   */
  @Override
  public Action update() {
    super.update();
    if(!(InteractiveObject.getProcessing().mousePressed) && mouseWasPressed) {
      mouseWasPressed = false;
    }
    if(mouseWasPressed)
      return null;
    if (super.isOver(InteractiveObject.getProcessing().mouseX,
        InteractiveObject.getProcessing().mouseY)
        && InteractiveObject.getProcessing().mousePressed) {
      mouseWasPressed = true;
      return action;
    }
    mouseWasPressed = false;
    return null;
  } // calls VisibleObject update, then returns
  // action only when mouse is first clicked
  // on this object
}
