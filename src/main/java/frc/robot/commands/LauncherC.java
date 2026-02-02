package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LauncherSS;

public class LauncherC extends Command {
    public double speed;

    public LauncherC(LauncherSS subsystem, double speed) {
        subsystem = RobotContainer.rc_launcherSS;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        RobotContainer.rc_launcherSS.spin(speed);
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
