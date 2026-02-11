//Apriltag detecting camera will be placed on shooter side to detect shooter tags and climber tags
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
import frc.robot.Constants;

public class VisionSS extends SubsystemBase{
    public PhotonCamera camera = new PhotonCamera("Camera_1");
    

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

        final double targetPitchRadians = target.getPitch();
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
            double targetHypotenuse = PhotonUtils.calculateDistanceToTargetMeters(Constants.VisionConstants.cameraHeightMeters, Constants.VisionConstants.targetHeightMeters, Constants.VisionConstants.cameraPitchRadians, targetPitchRadians);
            //camera height and target height must be changed at a later date
            double targetxdistance = Math.sqrt((targetHypotenuse*targetHypotenuse) - (Constants.VisionConstants.targetHeightMeters*Constants.VisionConstants.targetHeightMeters));

            System.out.println(targetID);
            System.out.println(targetHypotenuse);
            System.out.println(targetxdistance);
            SmartDashboard.putBoolean("Vision Target Visible", targetVisible);
            


            // Capture pre-process camera stream image
            camera.takeInputSnapshot();

            // Capture post-process camera stream image
            camera.takeOutputSnapshot();

        } 
            

        
    }
    
}
