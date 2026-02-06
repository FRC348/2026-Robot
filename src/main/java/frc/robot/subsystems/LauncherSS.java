package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSS extends SubsystemBase{

    public double speed = .5;

    public static final SparkMax launcher1 = new SparkMax(Constants.LauncherConstants.launcher1CANID, MotorType.kBrushless);
    public static final SparkMax launcher2 = new SparkMax(Constants.LauncherConstants.launcher2CANID, MotorType.kBrushless);


    // launcher at 0.675 gets it in from 170
    // Launcher at 0.625 gets it in at 130
    
    public Double changeSpeed(double change) {
      speed = speed + change;
      if (speed < 0) {
        speed = 0;
      }
      if (speed > 1) {
        speed = 1;
      }
      return speed;
    }

    public double getSpeed() {
        return speed;
    }
   
    public void spin() {
      launcher1.set(-speed);
      launcher2.set(speed);
    }

    public void stop(){
        launcher1.set(0);
        launcher2.set(0);
    }

}
