import lejos.nxt.LightSensor;
import lejos.robotics.subsumption.Behavior;

/*
 * It uses the light sensor and stops the robot for 
 * 3 seconds if the robot has not done so using this behavior
 * in the last 5 sencods
 */
public class Feed implements Behavior {

	private LightSensor lSensor;
	//the last time robot has eaten
	long feedTime;
	boolean stop = false;
	
	//constructor
	public Feed(LightSensor lSensor) {
		this.lSensor = lSensor;
	}
	
	//method that decide if feed behavior wants to take action
	public boolean takeControl() {
		//if the light percentage is low enough and robot has not eaten in the last 5 second
		//returns true
		return ((lSensor.readValue() < 50) && (System.currentTimeMillis() >= feedTime + 5000));
	}
	
	//the feed action
	public void action() {

		try {
			//if robot has not eaten in the last 5 seconds
			if ((System.currentTimeMillis() >= feedTime + 5000)) {

				//stop for 3 second
				Thread.yield();
				Thread.sleep(3000);
				//remember the feed time
				feedTime = System.currentTimeMillis();
			}
		} catch (Exception e) {

		}

	}
	
	//stops the robot
	public void suppress() {
		Robot.stop();
	}

}