import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class Avoid implements Behavior{
	
	DifferentialPilot robot;
	TouchSensor bump;
	
	public Avoid(DifferentialPilot robot, TouchSensor bump)
	{
		this.robot = robot;
		this.bump = bump;
	}

	public boolean takeControl() {

		if(bump.isPressed())
		{
			return true;
		}
		return false;
	}


	public void action() {
		try
		{
			Robot.move(-40);
			Robot.turn(-90);
		}
		catch(Exception ex)
		{
		}
		
	}


	public void suppress() {
		
		Robot.stop();
		
	}

}
