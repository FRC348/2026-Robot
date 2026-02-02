package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSS extends SubsystemBase{

    public static final SparkMax launcher1 = new SparkMax(Constants.LauncherConstants.launcher1CANID, MotorType.kBrushless);
    public static final SparkMax launcher2 = new SparkMax(Constants.LauncherConstants.launcher2CANID, MotorType.kBrushless);

    public void spin(double speed) {
        launcher1.set(speed);
        launcher2.set(-speed);
    }

    public void stop(){
        launcher1.set(0);
        launcher2.set(0);
    }

}
