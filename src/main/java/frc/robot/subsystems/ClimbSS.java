package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSS extends SubsystemBase {

    public final SparkFlex climbmotor = new SparkFlex(0, MotorType.kBrushless);
    
    public void spin() {
        climbmotor.set(0.5);
    }

    public void reverse() {
        climbmotor.set(-0.5);   
   }

   public void stop() {
    climbmotor.set(0);
   }

}
