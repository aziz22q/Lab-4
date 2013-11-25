import lejos.robotics.subsumption.*;


public class Wonder implements Behavior {
	
	boolean stop = false;


	public boolean takeControl() {
		return true;
	}


	public void action() {
		stop = false;
		try
		{
			while (!stop)
			{
				Robot.move(150);
				Robot.turn(90);
			}
		}
		catch(Exception ex)
		{
		}
		
	}


	public void suppress() {
		stop = true;
		Robot.stop();
		
	}

}
