import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Course: CS 300 Fall 2020
//
// Author: (Andrew McAvoy)
// Email: (apmcavoy@eisc.edu)
// Lecturer: (Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Rojin Sangsari helped me understand the gradescope
// feedback and fix my code accordingly
//
///////////////////////////////////////////////////////////////////////////////
public class WisconsinPrairie {
  private static PApplet processing; // PApplet object that represents the graphic// interface of
                                     // the WisconsinPrairie application
  private static PImage backgroundImage; // PImage object that represents the// background image
  private static Cow[] cows; // array storing the current cows present// in the Prairie
  private static Random randGen = new Random(); // Generator of random number

  public static void main(String[] args) {
    Utility.startApplication();

  }

  /***
   * Defines the initial environment properties of the application* @param processingObj represents
   * a reference to the graphical interface of* the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed
    // into the input argument parameter
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores// the width
    // [resp. height] of the display window.
    cows = new Cow[10];
  }

  /***
   * Draws and updates the application display window.* This callback method called in an infinite
   * loop.
   */
  public static void draw() {
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null)
        cows[i].draw();// draw any cows currently in the array
    }
  }



  /***
   * Checks if the mouse is over a given cow whose reference is provided* as input parameter**
   * 
   * @param cow reference to a given cow object
   * @return true if the mouse is over the given cow object (i.e. over the image of the cow), false
   *         otherwise
   */
  public static boolean isMouseOver(Cow cow) {
    PImage cowReference = cow.getImage();// get image object of cow
    // check if pointer is within the bounds of the cow image
    if ((cow.getPositionX() - cowReference.width / 2) <= processing.mouseX
        && processing.mouseX <= (cow.getPositionX() + cowReference.width / 2)) {
      if ((cow.getPositionY() - cowReference.height / 2) <= processing.mouseY
          && processing.mouseY <= (cow.getPositionY() + cowReference.height / 2)) {
        return true; // return true if pointer is within bounds of the image
      }
    }
    return false;
  }

  /*** Callback method called each time the user presses the mouse */
  public static void mousePressed() {
    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null && isMouseOver(cows[i])) {
        // cow obj exists and the mouse is over it, set dragging to true
        cows[i].setDragging(true);
        // can only drag one cow at a time, so break code here
        break;
      }

    }
  }

  /*** Callback method called each time the mouse is released */
  public static void mouseReleased() {
    if (!processing.mousePressed) {// makes sure that the mouse isn't pressed
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] != null) {
          cows[i].setDragging(false);// setDragging to false for all cows in the array
          cows[i].draw();
        }
      }
    }
  }

  /*** Callback method called each time the user presses a key */
  public static void keyPressed() {
    if (processing.key == 'c' || processing.key == 'C') {
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] == null) { // only add a cow to the array if there's room to add it
          float xPos = (float) randGen.nextInt(processing.width);
          float yPos = (float) randGen.nextInt(processing.height);
          cows[i] = new Cow(processing, xPos, yPos);
          // only need to add one cow, no point iterating through the rest of the array
          return;
        }
      }
    } else if (processing.key == 'd' || processing.key == 'D') {
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] != null && isMouseOver(cows[i])) {
          cows[i] = null;// set cow to null if mouse is over it and cow object exists
          return;
        }
      }

    }
  }
}

