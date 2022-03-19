// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonSub extends SubsystemBase {
  /** Creates a new PhotonSub. */
  public PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");

  public boolean hasTarget = false;
  public static double yaw = 0;
  public static double area = 0;

  public PhotonSub() { 

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //yaw = target.getYaw();
    var results = camera.getLatestResult();
    if (results.hasTargets()){
      area = results.getBestTarget().getArea();
    }
  }

  public void driveMode()
  {
    camera.setDriverMode(true);
  }
  public void cameraMode()
  {
    camera.setDriverMode(false);
  }
  public void BlueBalls()
  {
    camera.setPipelineIndex(0);
  }
  public void RedBalls()
  {
    camera.setPipelineIndex(1);
  }


  public void RunCamera()
  {
    double placeholder = 0;
    var results = camera.getLatestResult();
    
    if(results.hasTargets())
    {
      placeholder = results.getBestTarget().getYaw();
    }else{
      placeholder = 0;
    }
  yaw = placeholder;
  }
}
