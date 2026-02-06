package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        if (RobotContainer.m_driverController.leftBumper().getAsBoolean() == false) {
            RobotContainer.rc_launcherSS.stop();
        }
        else {
            RobotContainer.rc_launcherSS.spin();
        }
              SmartDashboard.putNumber("Launcher Encoder Velocity", -RobotContainer.rc_launcherSS.launcher1.getEncoder().getVelocity());
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
