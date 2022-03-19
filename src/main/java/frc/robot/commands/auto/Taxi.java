package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

public class Taxi extends SequentialCommandGroup {
  /** Creates a new TaxiOneBall. */
  public Taxi() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutonDrive(RobotContainer.driveTrain, .5, 0, 0, 2)
    );
  }
}
