import java.util.Random;
import lejos.robotics.subsumption.*;
/*
 * This is the default behavior. The robot will move and turn randomly 
 * until an obstacle or a food source (dark spot on the ground) is found
 */
public class Wonder implements Behavior {

	boolean stop;
	
	// always returns true
	public boolean takeControl() {
		return true;
	}
	
	//move around
	public void action() {
		stop = false;
		try {

			while (!stop) {
				
				//moves forward
				Robot.move(200);
				
				Random rand = new Random();

				int number = rand.nextInt(10) + 1;
				//turns around at random
				if (number == 3) {
					Robot.turn(90);
				}
				Thread.yield();
				Thread.sleep(50);

			}

		} catch (Exception ex) {
		}

	}
	//stops the robot
	public void suppress() {
		stop = true;
		Robot.stop();

	}

}