/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Devices.Xbox;
import frc.robot.Subsystems.Autonomous;
import frc.robot.Subsystems.CameraServer_;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.RobotMap;
import frc.robot.Subsystems.Spinner;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private int CONTROLLER_ID;
  
  private RobotMap robotMap;
  private Autonomous autonomous;
  private CameraServer_ cameraServer;
  private Intake intake;
  private Spinner spinner;
  private Xbox controller;

  private int rumbleTime;
  private double rumbleStartTime;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    this.CONTROLLER_ID = 0;

    this.robotMap = new RobotMap();
    this.autonomous = new Autonomous();
    this.cameraServer = new CameraServer_();
    this.intake = new Intake(this.robotMap.intake, this.robotMap.door, this.robotMap.arduino);
    this.spinner = new Spinner(this.robotMap.spinner);
    this.controller = new Xbox(this.CONTROLLER_ID);

    this.rumbleTime = 1;
    this.rumbleStartTime = 0.0;

  }

  @Override
  public void autonomousInit() {
    this.robotMap.frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    this.robotMap.frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

    this.robotMap.frontLeft.setSelectedSensorPosition(0);
    this.robotMap.frontRight.setSelectedSensorPosition(0);
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    double turn = this.controller.getX(Hand.kRight);
    double velocity = this.controller.getY(Hand.kLeft);

    boolean lowerSwitchSet = this.robotMap.rackAndPinionLowerSwitch.get();
    boolean readySwitchSet = this.robotMap.rackAndPinionReadySwitch.get();
    boolean upperSwitchSet = this.robotMap.rackAndPinionUpperSwitch.get();

    boolean readyLeftSwitchSet = this.robotMap.spinnerReadySwitchLeft.get();
    boolean readyRightSwitchSet = this.robotMap.spinnerReadySwitchRight.get();

    boolean ballSwitchSet = this.robotMap.ballSwitch.get();

    boolean aButtonPressed = this.controller.getAButtonPressed();
    boolean bButtonPressed = this.controller.getBButtonPressed();
    boolean xButtonPressed = this.controller.getXButtonPressed();
    boolean yButtonPressed = this.controller.getYButtonPressed();

    boolean leftBumperPressed = this.controller.getBumperPressed(Hand.kLeft);
    boolean rightBumperPressed = this.controller.getBumperPressed(Hand.kRight);

    int dpadPressed = this.controller.getPOV();

    // CONTROLS:
    // A BUTTON: SPINNER TOGGLE (holding)
    // B BUTTON: REVERSE INTAKE
    // X BUTTON: NONE
    // Y BUTTON: NONE
    
    // D-PAD UP: SPINNER UP
    // D-PAD DOWN: SPINNER DOWN

    // RIGHT TRIGGER: SPINNER READY
    // MOVEMENT: LEFT STICK
    // MANUAL SPINNER: LEFT STICK (A-PRESSED)

    // START BUTTON HOLD: RUN SPINNER
    // BACK BUTTON: RESET SPINNER COUNTS

    // FEEDBACK RUMBLE:
    // Ball pickup, major spinner events (in position, 1 rotation), spinner positions, limit switches

    // Drive the robot
    this.robotMap.chassis.arcadeDrive(velocity, turn);

    // Run the rumble on the controller to 25% for both motors
    if ((System.currentTimeMillis() - this.rumbleStartTime) < this.rumbleTime) {
      this.controller.setRumble(RumbleType.kLeftRumble, 0.25);
      this.controller.setRumble(RumbleType.kRightRumble, 0.25);
    } else {
      this.rumbleStartTime = 0.0;
      this.controller.setRumble(RumbleType.kLeftRumble, 0.0);
      this.controller.setRumble(RumbleType.kRightRumble, 0.0);
    }

    SmartDashboard.putString("Spinner Left Switch", readyLeftSwitchSet ? "Ready" : "");
    SmartDashboard.putString("Spinner Right Switch", readyRightSwitchSet ? "Ready" : "");

    if (dpadPressed == 0) {
      if (!upperSwitchSet) {
        this.robotMap.spinner.set(0.25);
      } else {
        this.robotMap.spinner.set(0.0); // May need to have a non-zero value to maintain position
        this.rumbleStartTime = System.currentTimeMillis();
      }
    } else if (dpadPressed == 180) {
      if (!lowerSwitchSet) {
        this.robotMap.spinner.set(-0.25);
      } else {
        this.robotMap.spinner.set(0.0);
        this.rumbleStartTime = System.currentTimeMillis();
      }
    } else {
      this.robotMap.spinner.set(0.0);
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
