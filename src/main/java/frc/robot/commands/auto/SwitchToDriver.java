package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.PhotonSub;

public class SwitchToDriver extends InstantCommand {
  private PhotonSub photonSub;
  public SwitchToDriver(PhotonSub photonSub) {
    this.photonSub = photonSub;
    addRequirements(photonSub);
  }

  @Override
  public void initialize() {
    photonSub.driveMode();
  }
}
