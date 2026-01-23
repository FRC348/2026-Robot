package frc.robot.subsystems;

import java.time.format.TextStyle;
import java.util.List;

import org.photonvision.*;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;


import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSS extends SubsystemBase{
    public PhotonCamera camera = new PhotonCamera("Test");

    //Latest result from camera
    PhotonPipelineResult result = camera.getLatestResult();
    //Check for targets within latest result
    boolean hasTargets = result.hasTargets();
    //Get a list of the targets
    List<PhotonTrackedTarget> targets = result.getTargets();
    //Get the best target
    PhotonTrackedTarget target = result.getBestTarget();
    //Get location information from target
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

    // Capture pre-process camera stream image
    //camera.takeInputSnapshot();

    // Capture post-process camera stream image
    //camera.takeOutputSnapshot();
}
