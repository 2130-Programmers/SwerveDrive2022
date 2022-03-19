// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class AutonDrive extends CommandBase {
  /** Creates a new DriveCommand. */
  private DriveTrain driveTrain;
  private double x;
  private double y;
  private double twist;
  private double time;  

  private double timeAtStart = 0;
  /**
   * 
   * @param x - (double) value to go fowards or backwards
   * @param y - (double) value to strafe left or right
   * @param twist - (double) value to twist
   * @param time - (double) amount of time to run
   */

  public AutonDrive(DriveTrain driveTrain, double x, double y, double twist, double time) {
    this.driveTrain = driveTrain;
    this.x = x;
    this.y = y;
    this.twist = twist;
    this.time = time;    
    addRequirements(this.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeAtStart = Robot.timer.get();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //in previous versions axis 1 and 5 are always inverted
    driveTrain.moveSwerveAxis(x, y, twist);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.moveSwerveAxis(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timeAtStart+time < Robot.timer.get()){
      return true;
    }else{
      return false;
    }
  }
}
