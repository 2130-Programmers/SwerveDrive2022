package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

public class TaxiThreeBalls extends SequentialCommandGroup {
  
  public TaxiThreeBalls() {
    addCommands(
      new AutonTowardsBall(RobotContainer.driveTrain),
      new AutonTurnCom(RobotContainer.driveTrain, 3),
      new AutonTowardsBall(RobotContainer.driveTrain),
      new AutonDrive(RobotContainer.driveTrain, 0, 0, -.3, 1)
    );
  }
}
