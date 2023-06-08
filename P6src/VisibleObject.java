import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileNotFoundException;
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
public class VisibleObject extends InteractiveObject {
  private PImage image; // the graphical representation of this object
  private int x; // the horizontal position (in pixels of this object's left side)
  private int y; // the vertical position (in pixels of this object's top side)

  /**
   * @param name of the image to search for
   * @param x    far left border of the image
   * @param y    topmost border of the image
   */
  public VisibleObject(String name, int x, int y) {
    super(name);
    // image = main.loadImage("images"+File.separator+ name +".png");
    this.image = getProcessing().loadImage("images" + File.separator + name + ".png");
    this.x = x;
    this.y = y;
  } // initialize this new VisibleObject
  // the image for this visible object should be loaded from :
  // "images"+File.separator+ name +".png"

  /**
   * Updates the image on the screen
   */
  @Override
  public Action update() {
    getProcessing().image(image, x, y);
    return null;
  } // draws image at its position before returning null

  /**
   * moves the image according to the given parameters
   * 
   * @param dx amount to move the image left/right
   * @param dy amount to move the image up/down
   */
  public void move(int dx, int dy) {
    x = x + dx;
    y = y + dy;
    return;
  } // changes x by adding dx to it (and y by dy)

  /**
   * checks if a given point is over the image
   * 
   * @param x x position to check
   * @param y y position to check
   * @return true if (x,y) is within the image
   */
  public boolean isOver(int x, int y) {
    if (this.x <= x && x <= (this.x + image.width))
      if ((this.y + image.height) >= y && y >= this.y)
        return true;
    return false;

  } // return true only when point x,y is over image


  /**
   * checks if two images overlap
   * 
   * @param other other image to reference
   * @return true if the two images overlap each other
   */
  public boolean isOver(VisibleObject other) {
    if (this.x >= (other.x + other.image.width) || other.x >= (this.x + image.width))
      return false;
    if (this.y <= (other.y - other.image.height) || other.y <= (this.y - image.height))
      return false;
    return true;
  } // return true only when other's image
  // overlaps this one's
}
