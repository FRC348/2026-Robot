package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LauncherSS;

public class LauncherC extends Command {
    public double speed;

    public LauncherC(LauncherSS subsystem) {
        subsystem = RobotContainer.rc_launcherSS;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (RobotContainer.m_driverController.a().getAsBoolean() == true) {
            RobotContainer.rc_launcherSS.fastspin();
        }
        else if (RobotContainer.m_driverController.b().getAsBoolean() == true){
            RobotContainer.rc_launcherSS.mediumspin();
        }
        else if (RobotContainer.m_driverController.y().getAsBoolean()) {
            RobotContainer.rc_launcherSS.mediumspin2();
        }
        else if (RobotContainer.m_driverController.x().getAsBoolean()) {
            RobotContainer.rc_launcherSS.slowspin();
        }
        else if (RobotContainer.m_driverController.povUp().getAsBoolean()) {
            RobotContainer.rc_launcherSS.slowspin2();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.rc_launcherSS.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
