import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Bump {
	
	public static void main(String[] args) {
	
		TouchSensor leftBump = new TouchSensor(SensorPort.S1);
		TouchSensor rightBump = new TouchSensor(SensorPort.S2);
		
		while (Button.ESCAPE.isDown() == false) {
		
			if (leftBump.isPressed() == true) {
				Motor.B.stop();
				Motor.C.forward();
			}
			
			if (rightBump.isPressed() == true) {
				Motor.B.forward();
				Motor.C.stop();
			}
		}
	}
}
