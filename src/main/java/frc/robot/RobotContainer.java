// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ClimbPIDC;
import frc.robot.commands.IntakeC;
import frc.robot.commands.IntakeTiltC;
import frc.robot.subsystems.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public static final VisionSS rc_visionSS = new VisionSS();
  public static final ClimbPIDSS rc_ClimbPIDSS = new ClimbPIDSS();
  public static final IntakeTiltSS rc_IntakeTiltSS = new IntakeTiltSS();
  public static final IntakeSS rc_intakeSS = new IntakeSS();
  /// ???? public static final ClimbPIDSS rc_pidSS = new ClimbPIDSS();
  
  public static final IntakeC rc_intakeC = new IntakeC(rc_intakeSS);

  public static final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    rc_visionSS.setDefaultCommand(
      new RunCommand(
          () ->
            rc_visionSS.PrintTarget(),
            rc_visionSS
            
      )
    );
    // Configure the trigger bindings
    configureBindings();
    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () ->
                m_robotDrive.drive(
                    -MathUtil.applyDeadband(
                        m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                    -MathUtil.applyDeadband(
                        m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                    -MathUtil.applyDeadband(
                        m_driverController.getRightX(), OIConstants.kDriveDeadband),
                    true),
            m_robotDrive));
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
