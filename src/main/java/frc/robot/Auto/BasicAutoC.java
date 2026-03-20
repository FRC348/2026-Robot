package frc.robot.Auto;

import java.nio.ReadOnlyBufferException;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSS;
import frc.robot.subsystems.KickerSS;
import frc.robot.subsystems.LauncherSS;

public class BasicAutoC extends Command {

    public BasicAutoC(KickerSS subsystem, LauncherSS subsystem2, DriveSubsystem subsystem3) {
        subsystem = RobotContainer.rc_KickerSS;
        subsystem2 = RobotContainer.rc_launcherSS;
        subsystem3 = RobotContainer.m_robotDrive;
        addRequirements(subsystem);   
    }

    public double time;

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        time = 0;
        double launchspeed;
        
        while (time <= 2000) {
            RobotContainer.m_robotDrive.drive(0, -0.05, Math.PI / 100, false);
            time = time + 20;
        }
        if (time > 2000) {
            RobotContainer.m_robotDrive.drive(0, 0, 0, false);
            launchspeed = RobotContainer.rc_launcherSS.calculateLaunchSpeed(RobotContainer.rc_visionSS.getDistanceToHub());
            while (time <= 3000) {
                RobotContainer.rc_KickerSS.KickerForward();
                time = time + 20;
            }
            if (time > 3000) {
                while (time <= 11000) {
                    RobotContainer.rc_KickerSS.KickerForward();
                    RobotContainer.rc_launcherSS.spin(launchspeed);
                    time = time + 20;
                }
                if (time > 11000) {
                    RobotContainer.rc_KickerSS.KickerStop();
                    RobotContainer.rc_launcherSS.stop();
                    RobotContainer.m_robotDrive.drive(0, 0, 0, false);
                    time = time + 20;
                }
            }
        }

    
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.rc_KickerSS.KickerStop();
        RobotContainer.rc_launcherSS.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
