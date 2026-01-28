package frc.robot.subsystems;

import java.time.format.TextStyle;
import java.util.List;

import org.photonvision.*;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;


import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSS extends SubsystemBase{
    public PhotonCamera camera = new PhotonCamera("Cam1");

    public void PrintTarget() {
        //Latest result from camera
        List<PhotonPipelineResult> result = camera.getAllUnreadResults();
        //Check for targets within latest result
        if (result.isEmpty()) {
            System.out.println("No targets");
            Boolean targetVisible = false;
            SmartDashboard.putBoolean("Vision Target Visible", targetVisible);
            return;
        }
        //boolean hasTargets = result.hasTargets();
        //Get the best target
        PhotonTrackedTarget target = result.get(0).getBestTarget();
        
        //Get location information from target
        if (target != null) {
            Boolean targetVisible = true;
            double yaw = target.getYaw();
            double pitch = target.getPitch();
            double area = target.getArea();
            double skew = target.getSkew();
            List<TargetCorner> corners = target.getDetectedCorners();
            //Get Apriltag data and more information
            int targetID = target.getFiducialId();
            double poseAmbiguity = target.getPoseAmbiguity();
            Transform3d bestCameraToTarget = target.getBestCameraToTarget();
            Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();

            System.out.println(targetID);
            SmartDashboard.putBoolean("Vision Target Visible", targetVisible);
            


            // Capture pre-process camera stream image
            camera.takeInputSnapshot();

            // Capture post-process camera stream image
            camera.takeOutputSnapshot();

        } 
            

        
    }
    
}
