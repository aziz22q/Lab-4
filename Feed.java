//import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;


public class Feed  implements Behavior {
	
	private LightSensor lSensor;
	long feedTime;
	boolean stop = false;
	
	public Feed(LightSensor lSensor)
	{
		this.lSensor = 	lSensor;
	}
	
	public boolean takeControl() {
		
		//lSensor.readValue();
		//lSensor.readNormalizedValue(); 
		return(lSensor.readValue() < 70);
	}


	public void action() {
		stop = false;
		Motor.A.backward();
		Motor.C.backward();
		
		try {
			if (!stop && (System.currentTimeMillis() >= feedTime + 5000) )
			{
				//LCD.drawString("FEED", 5, 0);
				//Robot.setSpeed(0);
				//Robot.move(100);
				//Robot.setSpeed(200);
				Thread.yield();
				Thread.sleep(3000);
				feedTime = System.currentTimeMillis();
			}
		} catch (Exception e) {
			
		}
		Motor.A.stop();
		Thread.yield();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		Motor.C.stop();
	}


	public void suppress() {
		stop = true;
		//Robot.stop();
		
	}

}