// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ClimbPIDC;
import frc.robot.commands.IntakeC;
import frc.robot.commands.IntakeTiltC;
import frc.robot.commands.LauncherC;
import frc.robot.commands.LauncherSpeedC;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  public static final VisionSS rc_visionSS = new VisionSS();
  public static final ClimbPIDSS rc_ClimbPIDSS = new ClimbPIDSS();
  public static final IntakeTiltSS rc_IntakeTiltSS = new IntakeTiltSS();
  public static final IntakeSS rc_intakeSS = new IntakeSS();
  public static final LauncherSS rc_launcherSS = new LauncherSS();
  public static final IntakeC rc_intakeC = new IntakeC(rc_intakeSS);
  public static final LauncherC rc_launcherC = new LauncherC(rc_launcherSS);
  public static final LauncherSpeedC rc_launcherspeedC = new LauncherSpeedC(rc_launcherSS, 0.5);

  public static final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Configure default commands

  }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  private void configureBindings() {
        // Driver controller button commands
    m_driverController.leftStick().whileTrue(m_robotDrive.setXCommand());
    m_driverController.start().onTrue(m_robotDrive.zeroHeadingCommand());
    //Climb PID
    m_driverController.povUp().onTrue(new ClimbPIDC(rc_ClimbPIDSS, () -> 20));
    m_driverController.povDown().onTrue(new ClimbPIDC(rc_ClimbPIDSS, () -> 15));

    m_driverController.povRight().onTrue(new IntakeTiltC(rc_IntakeTiltSS, () -> 10));
    m_driverController.povLeft().onTrue(new IntakeTiltC(rc_IntakeTiltSS, () -> 0));

    m_driverController.povRight().whileTrue(rc_intakeC);
    m_driverController.povLeft().whileTrue(rc_intakeC);

    m_driverController.a().whileTrue(rc_intakeC);
    m_driverController.b().whileTrue(rc_intakeC);
    //Climb PID

    // m_driverController.povUp().onTrue(new LauncherSpeedC(rc_launcherSS, 1));
    // m_driverController.povDown().onTrue(new LauncherSpeedC(rc_launcherSS, -1));
    // m_driverController.povRight().onTrue(new LauncherSpeedC(rc_launcherSS, 0.1));
    // m_driverController.povLeft().onTrue(new LauncherSpeedC(rc_launcherSS, -0.1));
    // m_driverController.y().onTrue(new LauncherSpeedC(rc_launcherSS, 0.01));
    // m_driverController.a().onTrue(new LauncherSpeedC(rc_launcherSS, -0.01));
    // m_driverController.b().onTrue(new LauncherSpeedC(rc_launcherSS, 0.001));
    // m_driverController.x().onTrue(new LauncherSpeedC(rc_launcherSS, -0.001));
    // rc_launcherSS.setDefaultCommand(rc_launcherC);
    //m_driverController.leftTrigger().onTrue(rc_launcherC);
    //m_driverController.rightTrigger().onTrue(rc_launcherC);
    //m_driverController.leftBumper().onTrue(rc_launcherC);
    //m_driverController.rightBumper().onTrue(rc_launcherC);


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
