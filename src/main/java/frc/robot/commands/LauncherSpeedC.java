package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LauncherSS;

public class LauncherSpeedC extends InstantCommand {

    private double m_change;

    public LauncherSpeedC(LauncherSS subsystem, double change) {
        subsystem = RobotContainer.rc_launcherSS;
        m_change = change;
        addRequirements(subsystem);
    }    

    
    @Override
    public void execute() {
        RobotContainer.rc_launcherSS.changeSpeed(m_change);
    }
    

}
