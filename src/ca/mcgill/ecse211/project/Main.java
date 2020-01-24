package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.DEG_PER_RAD;
import static ca.mcgill.ecse211.project.Resources.DISPLAY_SLEEP_PERIOD;
import static ca.mcgill.ecse211.project.Resources.lcd;

import lejos.hardware.Button;

/**
 * A demonstration of an odometer implementation. This class sets up the display, enables driver
 * and odometer threads, and updates the display.
 * 
 * @author ferrie
 */
public class Main {
  
  /**
   * The main entry point.
   * 
   * @param args not used
   * @throws InterruptedException because we call Thread.sleep()
   */
  public static void main(String[] args) throws InterruptedException {
    setupDisplay();

    // Start threads
    Odometer odo = new Odometer();
    SquareDriver.drive();

    // Enter display loop. Terminate on any button push.
    while (true) {
      int status = Button.readButtons();
      if (status == Button.ID_ENTER) {
        System.exit(0);
      }
      
      showOdoPosition(odo.getX(), odo.getY(), odo.getTheta());
      Thread.sleep(DISPLAY_SLEEP_PERIOD); // Put thread to sleep
    }
  }
  
  /**
   * Sets up the display.
   */
  public static void setupDisplay() {
    lcd.clear();
    lcd.drawString("Odometer Demo", 0, 0, false);
    lcd.drawString("Current X  ", 0, 4, false);
    lcd.drawString("Current Y  ", 0, 5, false);
    lcd.drawString("Current T  ", 0, 6, false);
  }
  
  /**
   * Shows the odometer position.
   * 
   * @param x current X estimate
   * @param y current Y estimate
   * @param theta current heading
   */
  public static void showOdoPosition(double x, double y, double theta) {
    lcd.drawInt((int) x, 4, 11, 4);
    lcd.drawInt((int) y, 4, 11, 5);
    lcd.drawInt((int) (theta * DEG_PER_RAD), 4, 11, 6);
  }

}
