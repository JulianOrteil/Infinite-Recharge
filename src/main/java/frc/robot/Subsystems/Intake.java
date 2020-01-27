package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.Devices.Arduino;

/**
 * Manages the intake system for the robot
 */
public class Intake {

    private Spark intake, door;
    private Arduino arduino;

    public Intake(Spark intake, Spark door, Arduino arduino) {

        this.intake = intake;
        this.door = door;
        this.arduino = arduino;
    }

    /**
     * Runs the intake based on the feedback from the Arduino.
     * If 5 or more balls are collected, then the intake will run in reverse
     * to prevent balls from the entering the intake.
     */
    public void run() {
        int ballCount = this.arduino.getBallCount();

        if (ballCount >= 5) {
            this.intake.set(-0.5);
        } else {
            this.intake.set(0.5);
        }
    }

    /**
     * Do not run the intake.
     */
    public void stop() {
        this.intake.set(0.0);
    }

    /**
     * Open the door, tell the Arduino all the balls are released, and stop the intake to save power.
     */
    public void openDoor() {
        this.door.set(0.3);
        this.stop();
        this.arduino.ballsReleased();
    }

    /**
     * Close the door.
     */
    public void closeDoor() {
        this.door.set(-0.3);
    }

    /**
     * Stops the door motor and save power.
     */
    public void stopDoor() {
        this.door.set(0.0);
    }
}