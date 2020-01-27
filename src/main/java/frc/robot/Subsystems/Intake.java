package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.Devices.Arduino;

/**
 * Intake
 */
public class Intake {

    private Spark intake, door;
    private Arduino arduino;

    public Intake(Spark intake, Spark door, Arduino arduino) {

        this.intake = intake;
        this.door = door;
        this.arduino = arduino;
    }
}