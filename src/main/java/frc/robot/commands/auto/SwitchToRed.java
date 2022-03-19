package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.PhotonSub;

public class SwitchToRed extends InstantCommand {
  private PhotonSub photonSub;
  public SwitchToRed(PhotonSub photonSub) {
    this.photonSub = photonSub;
    addRequirements(photonSub);
  }

  @Override
  public void initialize() {
    photonSub.cameraMode();
    photonSub.RedBalls();
  }
}
