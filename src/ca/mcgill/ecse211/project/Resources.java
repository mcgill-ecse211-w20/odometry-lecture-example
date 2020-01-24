package ca.mcgill.ecse211.project;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * This class defines constants and allocates static resources.
 * 
 * @author Younes Boubekeur
 */
public class Resources {

  // Constants
  /** Period of sampling f (ms). */
  public static final int SAMPLING_INTERVAL = 25;
  
  /** Period of display update (ms). */
  public static final int DISPLAY_SLEEP_PERIOD = 100;
  
  /** Wheelbase (cm). */
  public static final double WHEEL_BASE = 16.0;
  
  /** Wheel radius (cm). */
  public static final double WHEEL_RADIUS = 2.7;
  
  /** Side of square (units = 0.1 mm). */
  public static final int SIDE = 5000;
  
  /** Straight line speed (deg/sec). */
  static final int FWD_SPEED = 180;
  
  /** Rotational speed (deg/sec). */
  static final int TURN_SPEED = 90;
  
  /** 360/(2xPixRw)  Rw=2.8cm    (20). */
  static final int DIST_TO_DEG = 21;
  
  /** (Rc/Rw)*100     Rc=7.94cm  (283). */
  static final int ORIENT_TO_DEG = 298;
  
  /** The number of degrees in one radian (180/PI). */
  public static final double DEG_PER_RAD = 57.2598;

  // Hardware resources
  /** The LCD. */
  public static final TextLCD lcd = LocalEV3.get().getTextLCD();
  
  /** The left motor. */
  public static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
  
  /** The right motor. */
  public static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.D);
  
}
