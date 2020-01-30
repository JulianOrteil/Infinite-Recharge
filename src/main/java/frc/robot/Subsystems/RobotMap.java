package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Devices.Arduino;
import frc.robot.Devices.ColorSensor;

/**
 * RobotMap
 */
public class RobotMap {

    // Device IDs
    private final int FRONT_LEFT_ID, BACK_LEFT_ID, FRONT_RIGHT_ID, BACK_RIGHT_ID;
    private final int INTAKE_ID, DOOR_ID, SPINNER_ID, RACK_AND_PINION_ID;
    private final int RACK_AND_PINION_LOWER_SWITCH_ID, RACK_AND_PINION_READY_SWITCH_ID, RACK_AND_PINION_UPPER_SWITCH_ID;
    private final int SPINNER_READY_SWITCH_LEFT_ID, SPINNER_READY_SWITCH_RIGHT_ID;
    private final int BALL_SWITCH_ID;

    // Motors
    public WPI_TalonSRX frontLeft, backLeft, frontRight, backRight;
    public Spark intake, door, spinner, rackAndPinion;

    // Chassis and drive trains
    private SpeedControllerGroup leftDriveTrain, rightDriveTrain;
    public DifferentialDrive chassis;

    // Limit switches
    public DigitalInput rackAndPinionLowerSwitch, rackAndPinionUpperSwitch, rackAndPinionReadySwitch;
    public DigitalInput spinnerReadySwitchLeft, spinnerReadySwitchRight;
    public DigitalInput ballSwitch;

    // Arduino
    public Arduino arduino;

    // ColorSensor
    public ColorSensor colorSensor;

    public RobotMap() {

        this.FRONT_LEFT_ID = 0;
        this.BACK_LEFT_ID = 0;
        this.FRONT_RIGHT_ID = 0;
        this.BACK_RIGHT_ID = 0;

        this.INTAKE_ID = 0;
        this.DOOR_ID = 0;
        this.SPINNER_ID = 0;
        this.RACK_AND_PINION_ID = 0;

        this.RACK_AND_PINION_LOWER_SWITCH_ID = 0;
        this.RACK_AND_PINION_READY_SWITCH_ID = 0;
        this.RACK_AND_PINION_UPPER_SWITCH_ID = 0;

        this.SPINNER_READY_SWITCH_LEFT_ID = 0;
        this.SPINNER_READY_SWITCH_RIGHT_ID = 0;

        this.BALL_SWITCH_ID = 0;
        
        this.frontLeft = new WPI_TalonSRX(this.FRONT_LEFT_ID);
        this.backLeft = new WPI_TalonSRX(this.BACK_LEFT_ID);
        this.frontRight = new WPI_TalonSRX(this.FRONT_RIGHT_ID);
        this.backRight = new WPI_TalonSRX(this.BACK_RIGHT_ID);

        this.intake = new Spark(this.INTAKE_ID);
        this.door = new Spark(this.DOOR_ID);
        this.spinner = new Spark(this.SPINNER_ID);
        this.rackAndPinion = new Spark(this.RACK_AND_PINION_ID);

        this.leftDriveTrain = new SpeedControllerGroup(this.frontLeft, this.backLeft);
        this.rightDriveTrain = new SpeedControllerGroup(this.frontRight, this.backRight);

        this.chassis = new DifferentialDrive(this.leftDriveTrain, this.rightDriveTrain);

        this.rackAndPinionLowerSwitch = new DigitalInput(this.RACK_AND_PINION_LOWER_SWITCH_ID);
        this.rackAndPinionReadySwitch = new DigitalInput(this.RACK_AND_PINION_READY_SWITCH_ID);
        this.rackAndPinionUpperSwitch = new DigitalInput(this.RACK_AND_PINION_UPPER_SWITCH_ID);

        this.spinnerReadySwitchLeft = new DigitalInput(this.SPINNER_READY_SWITCH_LEFT_ID);
        this.spinnerReadySwitchRight = new DigitalInput(this.SPINNER_READY_SWITCH_RIGHT_ID);

        this.ballSwitch = new DigitalInput(this.BALL_SWITCH_ID);

        this.arduino = new Arduino();

        this.colorSensor = new ColorSensor();
    }

}