import java.io.File;
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
public class DraggableObject extends VisibleObject {
  private boolean mouseWasPressed; // similar to use in ClickableObject
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  /**
   * @param name name to set
   * @param x leftmost position of the image
   * @param y topmost position of the image
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y);
    isDragging = false;
    mouseWasPressed = false;

  } // initializes new draggable object

  /**
   * moves the object relative to the change
   * in the mouse if the mouse is over it and clicked
   * returns whatever is returned by the drop method
   */
  @Override
  public Action update() {
    super.update();

    if (!InteractiveObject.getProcessing().mousePressed) {
      mouseWasPressed = false;
    }
    if (isDragging && mouseWasPressed) {
      int dx = (getProcessing().mouseX - oldMouseX) ;
      int dy = (getProcessing().mouseY - oldMouseY) ;
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;
      move(dx, dy);
      return null;
    }
    if (super.isOver(InteractiveObject.getProcessing().mouseX,
        InteractiveObject.getProcessing().mouseY)
        && InteractiveObject.getProcessing().mousePressed) {
      mouseWasPressed = true;
      isDragging = true;
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;
      return null;
    }
    isDragging = false;
    mouseWasPressed = false;
    return drop();

  } // calls VisibleObject update() first, then moves
  // according to mouse drag
  // each time isDragging changes from true to false, the drop() method below will be
  // called once and any action objects returned from that method should then be
  // returned from update()

  /**
   * @return null, will be updated in subclasses
   */
  protected Action drop() {
    return null;
  }// this method returns null.
   // subclass types will override this drop() method to perform more interesting
}
