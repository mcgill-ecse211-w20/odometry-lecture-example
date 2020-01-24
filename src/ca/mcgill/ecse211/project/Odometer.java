package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.SAMPLING_INTERVAL;
import static ca.mcgill.ecse211.project.Resources.WHEEL_BASE;
import static ca.mcgill.ecse211.project.Resources.WHEEL_RADIUS;
import static ca.mcgill.ecse211.project.Resources.leftMotor;
import static ca.mcgill.ecse211.project.Resources.rightMotor;

import lejos.utility.Timer;
import lejos.utility.TimerListener;

/**
 * An implementation of an odometer as described in the class notes. Here we make use of the 
 * Lejos Timer class to sample the motors at a precise interval.
 * 
 * @author ferrie
 */
public class Odometer implements TimerListener {

  // Instance Variables
  private static double x; // x,y,theta = cart pose
  private static double y;
  private static double theta;
  private static int lastTachoL = leftMotor.getTachoCount(); // Last tacho counts for L and R motors
  private static int lastTachoR = rightMotor.getTachoCount();

  /**
   * The constructor sets up a timer interrupt using the Lejos Timer class which is delivered to
   * the timedOut() method implemented in this class. This is where the odometer code gets
   * executed.
   */
  public Odometer() {
    // Create and start the timer thread. `this` refers to this odometer.
    new Timer(SAMPLING_INTERVAL, this).start(); 
  }
  
  /*
   * The odometer is implemented in the timedOut() method (non Javadoc).
   * This method is called every SAMPLING_INTERVAL.
   */
  public void timedOut() {
    int nowTachoL = leftMotor.getTachoCount(); // get tacho counts
    int nowTachoR = rightMotor.getTachoCount();
    // compute L and R wheel displacements
    double distL = Math.PI * WHEEL_RADIUS * (nowTachoL - lastTachoL) / 180;
    double distR = Math.PI * WHEEL_RADIUS * (nowTachoR - lastTachoR) / 180;
    lastTachoL = nowTachoL; // save tacho counts for next iteration
    lastTachoR = nowTachoR;
    double deltaD = 0.5 * (distL + distR); // compute vehicle displacement
    double deltaT = (distL - distR) / WHEEL_BASE; // compute change in heading
    theta += deltaT; // update heading
    double dX = deltaD * Math.sin(theta); // compute x component of displacement
    double dY = deltaD * Math.cos(theta); // compute y component of displacement
    x = x + dX; // update estimates of x and y position
    y = y + dY;
  }

  /**
   * Returns x.
   * 
   * @return x
   */
  public double getX() {
    return x;
  }

  /**
   * Returns y.
   * 
   * @return y
   */
  public double getY() {
    return y;
  }

  /**
   * Returns theta.
   * 
   * @return theta
   */
  public double getTheta() {
    return theta;
  }

}
