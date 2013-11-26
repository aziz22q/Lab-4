import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class Avoid implements Behavior, SensorPortListener{
	
	DifferentialPilot robot;
	TouchSensor bump;
	boolean stop;
	boolean state;
	
	public Avoid(DifferentialPilot robot, TouchSensor bump)
	{
		this.robot = robot;
		this.bump = bump;
		stop = false;
		
		//TouchSensor.addSensorListener(this);
	}

	public boolean takeControl() {
		
		state = bump.isPressed();
		return state;
		
	}


	public void action() {
		stop = false;
		try
		{
			if (!stop)
			{
				//Robot.move(-50);
				//Robot.move(10);
				Robot.turn(180);
				stop = true;
			}
			
		}
		catch(Exception ex)
		{
		}
		
	}


	public void suppress() {
		stop = true;
		//Robot.stop();
		
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		boolean currState = bump.isPressed();
        if (currState != state) {
           state = currState;
           if (state){
        	   try {
				//Robot.move(-200);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
           }
		
	}
	}
}