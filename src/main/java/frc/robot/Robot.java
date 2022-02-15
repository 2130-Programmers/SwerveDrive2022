// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */

   double rawEncodedPosition;
   double encodedPosition;
  @Override
  public void robotPeriodic() {

    rawEncodedPosition = RobotContainer.driveTrain.motorFL.encoderMotor.getSelectedSensorPosition(0);

    if (rawEncodedPosition >= 0)
    {
      encodedPosition = RobotContainer.driveTrain.motorFL.encoderMotor.getSelectedSensorPosition(0) % 4096;
    }
      else
    {
      encodedPosition = (RobotContainer.driveTrain.motorFL.encoderMotor.getSelectedSensorPosition(0) % 4096) + 4096;
    }

    SmartDashboard.putNumber("encodedPosition", encodedPosition);
    
    SmartDashboard.putNumber("FLRAWFKINVALUE", RobotContainer.driveTrain.motorFL.encoderMotor.getSelectedSensorPosition(0) % 4096 + 4096);
    SmartDashboard.putNumber("FRRAWFKINVALUE", RobotContainer.driveTrain.motorFR.encoderMotor.getSelectedSensorPosition(0) % 4096 + 4096);
    SmartDashboard.putNumber("RLRAWFKINVALUE", RobotContainer.driveTrain.motorRL.encoderMotor.getSelectedSensorPosition(0) % 4096 + 4096);
    SmartDashboard.putNumber("RRRAWFKINVALUE", RobotContainer.driveTrain.motorRR.encoderMotor.getSelectedSensorPosition(0) % 4096 + 4096);

    SmartDashboard.putNumber("FL edited encoder value", RobotContainer.driveTrain.motorFL.encoderPosition);
    SmartDashboard.putNumber("FR edited encoder value", RobotContainer.driveTrain.motorFR.encoderPosition);
    SmartDashboard.putNumber("RL edited encoder value", RobotContainer.driveTrain.motorRL.encoderPosition);
    SmartDashboard.putNumber("RR edited encoder value", RobotContainer.driveTrain.motorRR.encoderPosition);

    SmartDashboard.putNumber("FL desired angle", RobotContainer.driveTrain.FLAngle);
    SmartDashboard.putNumber("FR desired angle", RobotContainer.driveTrain.FRAngle);
    SmartDashboard.putNumber("RL desired angle", RobotContainer.driveTrain.RLAngle);
    SmartDashboard.putNumber("RR desired angle", RobotContainer.driveTrain.RRAngle);

    SmartDashboard.putNumber("pointSet FL", RobotContainer.driveTrain.motorFL.pointSet);
    SmartDashboard.putNumber("pointSet FR", RobotContainer.driveTrain.motorFR.pointSet);
    SmartDashboard.putNumber("pointSet RL", RobotContainer.driveTrain.motorRL.pointSet);
    SmartDashboard.putNumber("pointSet RR", RobotContainer.driveTrain.motorRR.pointSet);

    SmartDashboard.putNumber("encoder remaining valueFL", RobotContainer.driveTrain.motorFL.encoderRemainingValue);
    SmartDashboard.putNumber("encoder remaining valueFR", RobotContainer.driveTrain.motorFR.encoderRemainingValue);
    SmartDashboard.putNumber("encoder remaining valueRL", RobotContainer.driveTrain.motorRL.encoderRemainingValue);
    SmartDashboard.putNumber("encoder remaining valueRR", RobotContainer.driveTrain.motorRR.encoderRemainingValue);

    SmartDashboard.putNumber("turnPowerRatio FL", RobotContainer.driveTrain.motorFL.turnPowerRatio);
    SmartDashboard.putNumber("FlipFL ", RobotContainer.driveTrain.motorFL.flip);
    SmartDashboard.putNumber("FlipFR ", RobotContainer.driveTrain.motorFR.flip);
    SmartDashboard.putNumber("FlipRL ", RobotContainer.driveTrain.motorRL.flip);
    SmartDashboard.putNumber("FlipRR ", RobotContainer.driveTrain.motorRR.flip);

    SmartDashboard.putNumber("Left Joystick X", RobotContainer.driverJoy.getRawAxis(0));
     SmartDashboard.putNumber("Left Joystick Y", RobotContainer.driverJoy.getRawAxis(1));
     SmartDashboard.putNumber("Right Joystick X", RobotContainer.driverJoy.getRawAxis(4));  

    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    
  }
  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
    
   }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
