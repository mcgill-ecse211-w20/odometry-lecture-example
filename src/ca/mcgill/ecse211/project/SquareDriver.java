package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.DIST_TO_DEG;
import static ca.mcgill.ecse211.project.Resources.FWD_SPEED;
import static ca.mcgill.ecse211.project.Resources.ORIENT_TO_DEG;
import static ca.mcgill.ecse211.project.Resources.SIDE;
import static ca.mcgill.ecse211.project.Resources.TURN_SPEED;
import static ca.mcgill.ecse211.project.Resources.leftMotor;
import static ca.mcgill.ecse211.project.Resources.rightMotor;

/**
 * This class implements an object that runs as a thread. It drives the two-wheeled cart in a
 * square of specified dimensions.
 * 
 * @author ferrie
 */
public class SquareDriver {

  /**
   * Main entry point here. Simply traces the sides of a square (non Javadoc).
   */
  public static void drive() {
    // spawn a new Thread to avoid this method blocking
    (new Thread() {
      public void run() {
        moveDistFwd(SIDE, FWD_SPEED); // Draw a SIDE
        rotate(90, TURN_SPEED); // Rotate 90° (repeat 4 times)
        moveDistFwd(SIDE, FWD_SPEED);
        rotate(90, TURN_SPEED);
        moveDistFwd(SIDE, FWD_SPEED);
        rotate(90, TURN_SPEED);
        moveDistFwd(SIDE, FWD_SPEED);
        rotate(90, TURN_SPEED);
      }
    }).start();
  }

  /**
   * Rotates forward in a straight line for specified distance.
   * 
   * @param distance the distance
   * @param speed the speed in deg/s
   */
  public static void moveDistFwd(int distance, int speed) {
    // Motor commands block by default (i.e. they return only when motion is complete).
    // To get both motors synchronized, use the non-blocking method for leftMotor
    // so that it returns immediately. The blocking form is used for rightMotor so
    // that this method returns when motion is complete.

    int rotationAngle = distance * DIST_TO_DEG / 100; // Convert linear distance to turns
    leftMotor.setSpeed(speed); // Roll both motors forward
    rightMotor.setSpeed(speed);
    leftMotor.rotate(rotationAngle, true); // Rotate left motor - DO NOT BLOCK
    rightMotor.rotate(rotationAngle); // Rotate right motor
  }

  /**
   * Identical to forward except for wheels rotating in opposite directions and a different
   * scale factor.
   * 
   * @param angle the angle in deg
   * @param speed the speed in deg/s
   */
  public static void rotate(int angle, int speed) {
    int rotationAngle = angle * ORIENT_TO_DEG / 100; // Convert rotation to equivalent turns
    leftMotor.setSpeed(speed); // Turn at a different speed from forward
    rightMotor.setSpeed(speed);
    leftMotor.rotate(rotationAngle, true); // Left motor clockwise, non-blocking
    rightMotor.rotate(-rotationAngle);; // Right motor counter clockwise, blocking
  }

}
