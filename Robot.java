import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Robot {

	final static int DISTANCE = 20;
	final static int TURN = 2;
	final static int SPEED = 200;

	static NXTRegulatedMotor leftMotor = Motor.C;
	static NXTRegulatedMotor rightMotor = Motor.A;

	static DifferentialPilot robot = new DifferentialPilot(5.6f, 11.0f, Motor.A, Motor.C, true);
	
	static TouchSensor tSensor = new TouchSensor(SensorPort.S1);
	static LightSensor lSensor = new LightSensor(SensorPort.S2);

	static boolean stop = false;

	public static void main(String args[]) {
		
		setSpeed(SPEED);
		
		Behavior move = new Wonder();
		Behavior avoid = new Avoid(robot, tSensor);
		Behavior feed = new Feed(lSensor);

		Behavior behaviors[] = { move, avoid, feed };

		Arbitrator arbitrator = new Arbitrator(behaviors);
		arbitrator.start();
	}

	/*
	 * Sets the speed
	 */
	public static void setSpeed(int speed) {
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}

	/*
	 * Stops moving
	 */
	public static void stop() {
		stop = true;
	}

	/*
	 * Turns
	 */
	public static void turn(int angle) throws Exception {
		
		stop = false;

		int degrees = angle * TURN;

		NXTRegulatedMotor forwardMotor = leftMotor;
		NXTRegulatedMotor backwardMotor = rightMotor;

		if (angle < 0) {
			forwardMotor = rightMotor;
			backwardMotor = leftMotor;
		}

		forwardMotor.resetTachoCount();
		backwardMotor.resetTachoCount();

		forwardMotor.forward();
		backwardMotor.backward();

		while (((forwardMotor.getTachoCount() < degrees) || (backwardMotor.getTachoCount() > -degrees)) && (!stop)) {
	
			if (forwardMotor.getTachoCount() > degrees)
			{
				forwardMotor.stop();
			}
			
			if (backwardMotor.getTachoCount() < -degrees)
			{
				backwardMotor.stop();
			}
			
		}

		forwardMotor.stop();
		backwardMotor.stop();
	}

	/*
	 * Move forward or backward
	 */
	public static void move(int distance) throws Exception {
		
		stop = false;
		int degrees = DISTANCE;

		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();

		if (distance > 0) {
			
			leftMotor.forward();
			rightMotor.forward();
		
		} else {
		
			leftMotor.backward();
			rightMotor.backward();
		
		}

		while ((leftMotor.getTachoCount() < degrees) || (rightMotor.getTachoCount() < degrees) && (!stop)) {
			
			if (leftMotor.getTachoCount() > degrees) {

				leftMotor.stop(); 
			}

			if (rightMotor.getTachoCount() > degrees) {

				rightMotor.stop();
			}
	
		}

		leftMotor.stop();
		rightMotor.stop();
	}

}
