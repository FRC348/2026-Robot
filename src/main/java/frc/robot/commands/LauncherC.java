package frc.robot.commands;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LauncherSS;
import frc.robot.subsystems.VisionSS;
public class LauncherC extends Command {
    public double speed;

    public LauncherC(LauncherSS subsystem, VisionSS subsystem2) {
        subsystem = RobotContainer.rc_launcherSS;
        subsystem2 = RobotContainer.rc_visionSS;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double launchspeed = RobotContainer.rc_launcherSS.calculateLaunchSpeed(RobotContainer.rc_visionSS.getDistanceToHub());
        double distance = RobotContainer.rc_visionSS.getDistanceToHub();
        RobotContainer.rc_launcherSS.spin(launchspeed);
        SmartDashboard.putNumber("Launch Speed: ", launchspeed);
        SmartDashboard.putNumber("Distance to Hub in Launcher:  ", distance);
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
