// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TurretSub extends SubsystemBase {

  //placeholder not going to be a double//
  //Code the fingers into this subsystem -------------------------------------------------------
  public double turretEncoder = 0;

  private TalonSRX turret;

  public boolean directionBool = true;
  public boolean auto = true;

  public double limelightTX = 0;
  public double limelightTA = 0;



  /** Creates a new turretSub. */
  public TurretSub() {
    turret = new TalonSRX(10);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void turretRotation() 
  {
    if(RobotContainer.operatorJoy.getRawAxis(2)<.9)
    {
      auto = true;
      if(RobotContainer.limelightSub.valid == 1) 
      {
        setSusan(limelightTX);
      }
    }else{
      auto = false;
      setSusan(RobotContainer.operatorJoy.getRawAxis(4)*.5);
      RobotContainer.limelightSub.cameraMode();
    }
  }

  public void setSusan(double point){
    //change 90 and -90 when encoder goes in
    if(turretEncoder > 90 && point > 0 || turretEncoder < -90 && point < 0){
      turret.set(ControlMode.PercentOutput, 0);
    }else{
      turret.set(ControlMode.PercentOutput, point);
    }
  }

}
