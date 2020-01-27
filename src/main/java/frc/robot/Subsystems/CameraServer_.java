package frc.robot.Subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * A class that controls the 2 connected cameras to the robot.
 */
public class CameraServer_ {

    private UsbCamera primaryCamera;
    private UsbCamera secondaryCamera;

    public CameraServer_() {

        this.primaryCamera = CameraServer.getInstance().startAutomaticCapture(0);
        this.secondaryCamera = CameraServer.getInstance().startAutomaticCapture(1);
    }

    /**
     * Closes the stream for the cameras
     */
    public void stop() {
        this.primaryCamera.close();
        this.secondaryCamera.close();
    }
}