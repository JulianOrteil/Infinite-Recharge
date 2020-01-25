package frc.robot.Subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * A class that controls the 2 connected cameras to the robot.
 */
public class CameraServer_ {

    private static UsbCamera primaryCamera;
    private static UsbCamera secondaryCamera;

    public CameraServer_() {

        this.primaryCamera = CameraServer.getInstance().startAutomaticCapture(0);
        this.secondaryCamera = CameraServer.getInstance().startAutomaticCapture(1);
    }
}