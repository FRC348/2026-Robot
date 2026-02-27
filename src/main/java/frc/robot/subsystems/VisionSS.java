//Apriltag detecting camera will be placed on shooter side to detect shooter tags and climber tags
package frc.robot.subsystems;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Optional;
import java.util.Optional;
import java.util.Optional;
import java.util.Optional;
import java.util.Optional;

import org.photonvision.*;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.apriltag.*;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class VisionSS extends SubsystemBase{
    public PhotonCamera camera = new PhotonCamera("Camera_1");
    public static final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);
    public static final Transform3d kRobotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0, 0, 0));
    PhotonPoseEstimator photonEstimator = new PhotonPoseEstimator(kTagLayout, kRobotToCam);
    public Optional<EstimatedRobotPose> robotPose = Optional.empty();

    public Optional<EstimatedRobotPose> estimateCoprocMultiTagPose(PhotonPipelineResult result) {
        if (!result.hasTargets()) {
            return Optional.empty();
        }

        return photonEstimator.estimateCoprocMultiTagPose(result);
    }

    public void PrintTarget() {
        //Latest result from camera
        List<PhotonPipelineResult> result = camera.getAllUnreadResults();
        System.out.println(result.size());

        //Check for targets within latest result
        if (result.isEmpty() || !result.get(0).hasTargets()) {
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
            //System.out.println(targetHypotenuse);
            //System.out.println(targetxdistance);
            System.out.println(robotPose);
            SmartDashboard.putBoolean("Vision Target Visible", targetVisible);

            // Capture pre-process camera stream image
            camera.takeInputSnapshot();

            // Capture post-process camera stream image
            camera.takeOutputSnapshot();
        } 
    }

    public Optional<EstimatedRobotPose> getRobotPose() {
        return robotPose;
    }
}
     