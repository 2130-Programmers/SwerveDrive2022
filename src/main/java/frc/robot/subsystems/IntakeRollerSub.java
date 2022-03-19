// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeRollerSub extends SubsystemBase {
  private VictorSPX intakeMotor;
  /** Creates a new intakeRollerSub. */
  public IntakeRollerSub() {
    intakeMotor = new VictorSPX(3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runRollersIn(){
    intakeMotor.set(ControlMode.PercentOutput, .8);
  }
  public void runRollersOut(){
    intakeMotor.set(ControlMode.PercentOutput, -.8);
  }
  public void stopRollers(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }
  
  public int bIsTrue(){
    if(RobotContainer.intakeRevButValue())
    {
      return -1;
    }
    else
    {
      return 1;
    }
  }

  public void ReverseIntake(){
    if(RobotContainer.intakeSub.toggleBool)
    {
      intakeMotor.set(ControlMode.PercentOutput, .8 * bIsTrue());
    }
    else
    {
      intakeMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void autonBelt(){
    if(PhotonSub.area > 5){
      intakeMotor.set(ControlMode.PercentOutput, .8);
    }else{
      intakeMotor.set(ControlMode.PercentOutput, 0);
    }
  }

}
