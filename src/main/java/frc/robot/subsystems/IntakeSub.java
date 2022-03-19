// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeSub extends SubsystemBase {
  /** Creates a new IntakeSub. */

  private TalonSRX beltMotor;
  private Spark launcherMotor;

  private DoubleSolenoid extendSol;

  public boolean isExtended = false;
  public boolean toggleBool = false;

  public DigitalInput limitSwitch;


  public IntakeSub() {
    beltMotor = new TalonSRX(2);

    launcherMotor = new Spark(14);

    extendSol = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

    limitSwitch = new DigitalInput(9);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void extendIntake(){
    extendSol.set(Value.kForward);
    isExtended = true;
  }
  public void retractIntake(){
    extendSol.set(Value.kReverse);
    isExtended = false;
  }

  public void runLauncher()
  {
    launcherMotor.set(.9);
  }
  public void stopLauncher()
  {
    launcherMotor.set(0);
  }

  public void moveBalls()
  {
    if(isExtended)
      {
      if(limitSwitch.get() && RobotContainer.operatorJoy.getRawAxis(Constants.operatorRightAxisTrigger) < .9)
      {
        beltMotor.set(ControlMode.PercentOutput, 0);
      }else{
        beltMotor.set(ControlMode.PercentOutput, .6);
      }
    }else{
      beltMotor.set(ControlMode.PercentOutput, 0);
    }
  }
}
