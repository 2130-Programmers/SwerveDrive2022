// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
/**
  public double driverJoyLeftX;
  private double driverJoyLeftY;
  private double driverJoyRightX;

  private double leftXOutput;
  private double leftYOutput;
  private double rightXOutput;
*/

  //defining swerve units as our SwerveMotor class
  public SwerveMotor motorFL;
  public SwerveMotor motorFR;
  public SwerveMotor motorRL;
  public SwerveMotor motorRR;

  public double leftXOutput = 0;
  public double leftYOutput = 0;
  public double rightXOutput = 0;

  //creating a variable which will be our angle
  public double FLAngle = 0;
  public double FRAngle = 0;
  public double RLAngle = 0;
  public double RRAngle = 0;
  public double desiredFLAngle = 0;

  //for the side length of the robot
  //it is undefined in constants rn
  public double l;
  //r is the hypotenuse and will be used for *vector math*
  public double r;


  //defining each swerve unit with the custom swerve class we made
  public DriveTrain() 
  {
  
    //we need to change the parameters, based off of Swolenoid
    motorFL = new SwerveMotor(1, 2, 9);
    motorFR = new SwerveMotor(3, 4, 10);
    motorRL = new SwerveMotor(5, 6, 11);
    motorRR = new SwerveMotor(7, 8, 12);

    //defining l by using constants, constants are good for these types of variables.
    //You can find constants in the editor right below the subsystems
    l = Constants.length;
    //this is a formula for hypotenuse with a 45 45 90 triangle
    r = (Math.sqrt(2)*l);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //for calculating which way the swerve units should point and how much power should be given
  public void moveSwerveAxis(double leftX, double leftY, double rightX)
  {
    double swivel = -rightX * (l / r);
//creates dead zones on the joystick
    if (Math.abs(leftX) < .05) {
      leftXOutput = 0;
    } else {
      leftXOutput = leftX;
    }
    if (Math.abs(leftY) < .05) {
      leftYOutput = 0;
    } else {
      leftYOutput = leftY;
    }
    if (Math.abs(rightX) < .05) {
      rightXOutput = 0;
    } else {
      rightXOutput = rightX;
    }

    // a b c and d are the sides of the robot, wheels are made from the combination of sides
    /**
     * this is the beginning of the vector based math, and in short we are calculating where each side wants to be
     * and then by combining the two sides into a triangle it gives us a swerve unit. For example The front left 
     * swerve unit is unit BC, because in a triangle made from the robots frames, the two sides are b and c.
     * This part is simply saying where each side wants to go by themselves.
     */
    double a = leftXOutput -swivel;
    double b = leftXOutput +swivel;
    double c = leftYOutput -swivel;
    double d = leftYOutput +swivel;
    //calculates the speed based on *vector math*
    /**
     */
    double FLDesiredSpeed = -Math.sqrt((b*b)+(c*c));
    double FRDesiredSpeed = -Math.sqrt((b*b)+(d*d));
    double RLDesiredSpeed = -Math.sqrt((a*a)+(c*c));
    double RRDesiredSpeed = -Math.sqrt((a*a)+(d*d));


    //notorious problem area, rework if possible, atan will return zero and not tell you
    if(leftX == 0 && leftY == 0 && swivel == 0)
    {
      //tries to avoid divide by zero error
      FLAngle = 0;
      FRAngle = 0;
      RLAngle = 0;
      RRAngle = 0;
    }else{
      //calculates the angle based of where each side wants to go
      FLAngle = Math.atan2(b, c)/Math.PI;
      FRAngle = Math.atan2(b, d)/Math.PI;
      RLAngle = Math.atan2(a, c)/Math.PI;
      RRAngle = Math.atan2(a, d)/Math.PI;

    }

    double FLOffset = 118;
    double FROffset = 1341;
    double RLOffset = 453;
    double RROffset = 3725;

    motorFL.drive(FLDesiredSpeed, FLAngle, FLOffset);
    motorFR.drive(FRDesiredSpeed, FRAngle, FROffset);
    motorRL.drive(RLDesiredSpeed, RLAngle, RLOffset);
    motorRR.drive(RRDesiredSpeed, RRAngle, RROffset);

  }
  //reaching into each motors "SwerveMotor" class to zero encoders
  public void zeroAllEncoders()
  {
    motorFL.zeroEncoder();
  }


  //Pressing Alt+left Click will duplicate your curser 
  //so you can type in multiple spots at once
  public void findZero()
  {
    motorFL.goToZero();
  }


}
