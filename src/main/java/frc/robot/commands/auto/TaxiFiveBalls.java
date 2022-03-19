package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

public class TaxiFiveBalls extends SequentialCommandGroup {
  
  public TaxiFiveBalls() {
    addCommands(
      new AutonTowardsBall(RobotContainer.driveTrain),
      new AutonDrive(RobotContainer.driveTrain, 0, 0, 0, 1),
      new AutonTurnCom(RobotContainer.driveTrain, 3),
      new AutonTowardsBall(RobotContainer.driveTrain),
      new AutonDrive(RobotContainer.driveTrain, 0, 0, 0, 1),
      new AutonTurnCom(RobotContainer.driveTrain, 1),
      new AutonTowardsBall(RobotContainer.driveTrain),
      new AutonDrive(RobotContainer.driveTrain, 0, 0, 0, 3)
      );
  }
}
