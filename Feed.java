import lejos.nxt.LightSensor;
import lejos.robotics.subsumption.Behavior;


public class Feed  implements Behavior {
	
	private LightSensor lSensor;
	
	public Feed(LightSensor lSensor)
	{
		this.lSensor = 	lSensor;
	}
	
	public boolean takeControl() {
		
		lSensor.readValue();
		//lSensor.readNormalizedValue(); 
		return(lSensor.readValue() < 30);
	}


	public void action() {
		
		Robot.stop();
	}


	public void suppress() {

		Robot.stop();
		
	}

}
