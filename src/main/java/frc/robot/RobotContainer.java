package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // -----------------------------------------------------------------------------\\
  //                                                                              \\
  //                                joysticks                                     \\
  //                                                                              \\
  // -----------------------------------------------------------------------------\\
  public static Joystick driverJoy = new Joystick(0);
  public static Joystick operatorJoy = new Joystick(1);

  // -----------------------------------------------------------------------------\\
  //                                                                              \\
  //                                Buttons                                       \\
  //                                                                              \\
  // -----------------------------------------------------------------------------\\
  public static JoystickButton extendBut = new JoystickButton(driverJoy, Constants.driverButtonRB);
  public static JoystickButton retractBut = new JoystickButton(driverJoy, Constants.driverButtonLB);
  public static JoystickButton runIntakeBut = new JoystickButton(driverJoy, Constants.driverButtonA);
  public static JoystickButton runIntakeRevBut = new JoystickButton(driverJoy, Constants.driverButtonB);
  public static JoystickButton skyHookToggleBut = new JoystickButton(operatorJoy, Constants.operatorButtonLB);

  public static JoystickButton switchPipe = new JoystickButton(operatorJoy, Constants.operatorButtonStart);
  public static JoystickButton fire = new JoystickButton(operatorJoy, Constants.operatorButtonLB);

  // -----------------------------------------------------------------------------\\
  //                                                                              \\
  //                                subsystems                                    \\
  //                                                                              \\
  // -----------------------------------------------------------------------------\\
  public final static DriveTrain driveTrain = new DriveTrain();
  public final static LimelightSub limelightSub = new LimelightSub();
  public final static TurretSub turretSub = new TurretSub();
  public final static IntakeSub intakeSub = new IntakeSub();
  public final static IntakeRollerSub intakeRollerSub = new IntakeRollerSub();
  public final static ClimbSub climbSub = new ClimbSub();
  public final static PhotonSub photonSub = new PhotonSub();

  
  // -----------------------------------------------------------------------------\\
  //                                                                              \\
  //                                commands                                      \\
  //                                                                              \\
  // -----------------------------------------------------------------------------\\
  public final static SwitchPipelineCom switchPipelingCom = new SwitchPipelineCom(limelightSub);
  public final DriveCommand driveCommand = new DriveCommand(driveTrain);
  public final TurretCommand turretCommand = new TurretCommand(turretSub);
  public final BeltAndLauncher beltAndLauncher = new BeltAndLauncher(intakeSub);
  public final ExtendIntake extendIntake = new ExtendIntake(intakeSub);
  public final RetractIntake retractIntake = new RetractIntake(intakeSub);
  public final IntakeCom intakeCom = new IntakeCom(intakeRollerSub);
  public final SkyHookToggleCom skyHookToggleCom = new SkyHookToggleCom(climbSub);
  public final SkyHookExtendCom skyHookExtendCom = new SkyHookExtendCom(climbSub);

  // -----------------------------------------------------------------------------\\
  //                                                                              \\
  //                                auto Commands                                 \\
  //                                                                              \\
  // -----------------------------------------------------------------------------\\
  public final SwitchToBlue switchToBlue = new SwitchToBlue(photonSub);
  public final SwitchToRed switchToRed = new SwitchToRed(photonSub);
  public final SwitchToDriver switchToDriver = new SwitchToDriver(photonSub);
  public final static AutonTurnCom autonTurnLeft = new AutonTurnCom(driveTrain, 1);
  public final static AutonTurnCom autonStop = new AutonTurnCom(driveTrain, 2);
  public final static AutonTurnCom autonTurnRight = new AutonTurnCom(driveTrain, 3);
  public final AutonIntake autonIntake = new AutonIntake(intakeRollerSub);
  public final AutonTowardsBall towardsBall = new AutonTowardsBall(driveTrain);
  public final Taxi taxiOneBall = new Taxi();
  
  // -----------------------------------------------------------------------------\\
  //                                                                              \\
  //                                    other                                     \\
  //                                                                              \\
  // -----------------------------------------------------------------------------\\
  static SendableChooser<Command> whichColor = new SendableChooser<>();
  static SendableChooser<Command> whichPath = new SendableChooser<>();


  /** The container for the robot. Contains subsystems, OI devices, and commands. 
   *  Define the default commands here
  */
  public RobotContainer() {
    //runs the configure button binding method
    configureButtonBindings();
    driveTrain.setDefaultCommand(driveCommand);
    turretSub.setDefaultCommand(turretCommand);
    limelightSub.setDefaultCommand(switchPipelingCom);
    intakeRollerSub.setDefaultCommand(intakeCom);
    climbSub.setDefaultCommand(skyHookExtendCom);
    intakeSub.setDefaultCommand(beltAndLauncher);

    whichColor.setDefaultOption("blue", switchToBlue);
    whichColor.addOption("red", switchToRed);

    whichPath.setDefaultOption("Taxi one ball", taxiOneBall);
    whichPath.addOption("stop", autonStop);
    whichPath.addOption("right", autonTurnRight);
  }

  // use this to configure button bindings.
  private void configureButtonBindings() {
    switchPipe.whenPressed(switchPipelingCom);
    extendBut.whenPressed(extendIntake);
    retractBut.whenPressed(retractIntake);
    skyHookToggleBut.whenPressed(skyHookToggleCom);
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public static boolean firePressed(){
    return fire.get();
  }

  public static boolean intakeRevButValue(){
    return runIntakeRevBut.get();
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new ParallelCommandGroup(
      new SequentialCommandGroup(
        whichColor.getSelected(),
        whichPath.getSelected()
      ),
      new SequentialCommandGroup(
        extendIntake,
        autonIntake
      )
    
      );

  }

}
