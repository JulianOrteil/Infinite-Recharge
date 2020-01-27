/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
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
    this.spinner = new Spinner();
    this.controller = new Xbox(this.CONTROLLER_ID);

  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
