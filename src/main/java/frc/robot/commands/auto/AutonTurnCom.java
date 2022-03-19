package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class AutonTurnCom extends CommandBase {
  /** Creates a new AutonTurnCom. */
  private DriveTrain driveTrain;
  private int direction;
  /**
   * 
   * 
   * @param driveTrain - drivetrain
   * @param direction - (int) left is 1, straight is 2, right is 3
   */
  public AutonTurnCom(DriveTrain driveTrain, int direction) {
    this.driveTrain = driveTrain;
    this.direction = direction;
    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.autonTurn(direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.photonSub.hasTarget;
  }
}
