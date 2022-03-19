package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeRollerSub;

public class AutonIntake extends CommandBase {

  private IntakeRollerSub intakeRollerSub;
  public AutonIntake(IntakeRollerSub intakeRollerSub) {
    this.intakeRollerSub = intakeRollerSub;
    addRequirements(this.intakeRollerSub);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakeRollerSub.autonBelt();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeRollerSub.stopRollers();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !Robot.inAuto;
  }
}
