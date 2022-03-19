// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class LimelightSub extends SubsystemBase {
  /** Creates a new LimelightSub. */
  private NetworkTable limelightTable;
  public NetworkTableEntry tx;
  private NetworkTableEntry ty;
  private NetworkTableEntry ta;
  private NetworkTableEntry tvert;
  private NetworkTableEntry thor;
  private NetworkTableEntry ts;
  private NetworkTableEntry pipe;
  private NetworkTableEntry tv;

  public boolean pipebool = true;

  public double x;
  public double y;
  public double area;
  public double linearEncoderValue;
  public double h;
  public double v;
  public double offset;
  public double xOffset = 0;
  public double pipeValue = 0;
  public double valid;
  public double zoom = 1;
  
  private static  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");


  public LimelightSub() 
  {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        tx = limelightTable.getEntry("tx");
        ty = limelightTable.getEntry("ty");
        ta = limelightTable.getEntry("ta");
        tvert = limelightTable.getEntry("tvert");
        thor = limelightTable.getEntry("thor");
        ts = limelightTable.getEntry("ts");
        pipe = table.getEntry("getpipe");
        
        tv = limelightTable.getEntry("tv");
        
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    x = tx.getDouble(0);
    y = ty.getDouble(0);
    area = ta.getDouble(0);
    h = tvert.getDouble(0);
    v = thor.getDouble(0);
    offset = ts.getDouble(0);
    pipeValue = pipe.getDouble(0);
    valid = tv.getDouble(0);
  }

  public void switchPipeline()
  {
      if(pipebool == true){
        NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
        pipelineEntry.setNumber(0);
          pipebool = false;
      }else{
        NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
        pipelineEntry.setNumber(1);
          pipebool = true;
      }
  }

  public void cameraMode()
  {
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
    pipelineEntry.setNumber(0);
    zoom = 1;
  }

  public void targetMode()
  {
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
    pipelineEntry.setNumber(1);
    zoom = 1;
  }

  public void targetZoomMode()
  {
    NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
    pipelineEntry.setNumber(2);
    zoom = 2;
  }

  public void targetSwitch()
  {
    int switchNum = (int) pipeValue;
    if(RobotContainer.operatorJoy.getRawAxis(2)<.9)
    {
      switch(switchNum){
        case 1:
          targetZoomMode();
          break;
        case 2:
          targetMode();
          break;
        default:
          targetMode();
      }
    }
    else
    {
    cameraMode();
    }
  }

  
}
