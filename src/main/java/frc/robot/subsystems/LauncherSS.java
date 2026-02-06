package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSS extends SubsystemBase{

    public static final SparkMax launcher1 = new SparkMax(Constants.LauncherConstants.launcher1CANID, MotorType.kBrushless);
    public static final SparkMax launcher2 = new SparkMax(Constants.LauncherConstants.launcher2CANID, MotorType.kBrushless);

    public void fastspin() {
        launcher1.set(-1);
        launcher2.set(1);
    }

    public void mediumspin() {
        launcher1.set(-.675);
        launcher2.set(.675);
        //170 in
    }

    public void mediumspin2() {
        launcher1.set(-.625);
        launcher2.set(.625);
        //130 in
    }

    public void slowspin() {
        launcher1.set(-.3);
        launcher2.set(.3);
    }

    public void slowspin2() {
        launcher1.set(-.1);
        launcher2.set(.1);
    }

    public void backwardspin() {
        launcher1.set(.2);
        launcher2.set(-.2);
    }

    public void stop(){
        launcher1.set(0);
        launcher2.set(0);
    }

}
