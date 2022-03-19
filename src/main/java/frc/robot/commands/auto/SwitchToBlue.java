package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.PhotonSub;

public class SwitchToBlue extends InstantCommand {
  private PhotonSub photonSub;
  public SwitchToBlue(PhotonSub photonSub) {
    this.photonSub = photonSub;
    addRequirements(photonSub);
  }

  @Override
  public void initialize() {
    photonSub.cameraMode();
    photonSub.BlueBalls();
  }
}
