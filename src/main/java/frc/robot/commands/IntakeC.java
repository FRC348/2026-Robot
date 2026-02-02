package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSS;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class IntakeC extends Command {
  /** Creates a new IntakeC. */
  public IntakeC(IntakeSS subsystem) {
    subsystem = RobotContainer.rc_intakeSS;
    addRequirements(subsystem);   
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.m_driverController.a().getAsBoolean()) {
      RobotContainer.rc_intakeSS.IntakeReverse();
    } else {
      RobotContainer.rc_intakeSS.IntakeForward();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.rc_intakeSS.IntakeStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}


