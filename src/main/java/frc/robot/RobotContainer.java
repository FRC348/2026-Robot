// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakeC;
import frc.robot.commands.LauncherC;
import frc.robot.subsystems.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final VisionSS rc_visionSS = new VisionSS();
  public static final IntakeSS rc_intakeSS = new IntakeSS();
  public static final LauncherSS rc_launcherSS = new LauncherSS();
  
  public static final IntakeC rc_intakeC = new IntakeC(rc_intakeSS);
  public static final LauncherC rc_launcherC = new LauncherC(rc_launcherSS);

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
    //Climb PID

    m_driverController.a().whileTrue(rc_launcherC);
    m_driverController.b().whileTrue(rc_launcherC);
    m_driverController.y().whileTrue(rc_launcherC);
    m_driverController.x().whileTrue(rc_launcherC);
    m_driverController.povUp().whileTrue(rc_launcherC);


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
