import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

/*
 * Uses the bump sensor. If the robot has bumped into something 
 * it makes it move back and turn
 */
public class Avoid implements Behavior {
	
	//the robot
	DifferentialPilot robot;
	//the bump sensor
	TouchSensor bump;

	//constructor
	public Avoid(DifferentialPilot robot, TouchSensor bump) {
		this.robot = robot;
		this.bump = bump;

	}

	//will take action only when the bump button 
	//is pressed
	public boolean takeControl() {

		return bump.isPressed();

	}

	//avoids the obstacle
	public void action() {

		try {
			//moves back
			Robot.move(-30);
			//turns
			Robot.turn(180);

			Thread.yield();
			Thread.sleep(50);

		} catch (Exception ex) {
		}

	}
	
	//stops
	public void suppress() {
		Robot.stop();
	}

}