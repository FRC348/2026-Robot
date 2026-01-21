package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSS extends SubsystemBase {
     public final SparkMax testmotor = new SparkMax(Constants.IntakeConstants.testmotorcanid,MotorType.kBrushless);

     public void start(double speed){
          testmotor.set(speed);
     }

     public void stop(){
          testmotor.set(0);
     }
}
