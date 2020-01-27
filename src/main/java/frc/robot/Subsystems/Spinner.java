package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Devices.ColorSensor;

/**
 * Controls the Spinner automatically for both rotation and position control.
 */
public class Spinner {

    private ColorSensor colorSensor;
    private Spark spinner;

    private String targetColor;
    private int targetRotations;

    private String startColor;

    private boolean rotationDebounce;
    private int rotations;

    public Spinner(Spark spinner) {
        this.colorSensor = new ColorSensor();
        this.spinner = spinner;

        this.targetColor = "";
        this.targetRotations = 0;

        this.startColor = "";
        
        this.rotationDebounce = false;
        this.rotations = 0;
    }

    /**
     * Runs once. Gets the target color from the field.
     * @return 1 indicates an error, or 0 on success.
     */
    public int colorControlInit() {
        this.targetColor = DriverStation.getInstance().getGameSpecificMessage();

        if (this.targetColor.length() > 0) {
            switch (this.targetColor.charAt(0)) {
                case 'R':
                    this.targetColor = "Red";
                    break;
                case 'G':
                    this.targetColor = "Green";
                    break;
                case 'B':
                    this.targetColor = "Blue";
                    break;
                case 'Y':
                    this.targetColor = "Yellow";
                    break;
                default:
                    this.targetColor = "";
                    return 1;
            }
            return 0;
        }
        this.targetColor = "";
        return 1;
    }

    /**
     * Runs the spinner until the correct color is detected.
     * @return 1 indicates the color is not detected, or 0 on completion.
     */
    public int colorControlPeriodic() {
        String detected = this.colorSensor.getNamedColor();

        if (this.colorSensor.DETECTED_2_TARGET.get(detected) == this.targetColor) {
            this.stop();
            return 0;
        } else {
            this.spinner.set(0.2);
        }
        return 1;
    }

    /**
     * Runs once. Gets the rotation from the SmartDashboard.
     * @return 1 indicates an out-of-bounds value was, so the target rotations was set to 3, or 0 on success.
     */
    public int rotationControlInit() {
        // The value for rotations must be doubled because
        // the color sensor will read both instances before
        // one full rotation of the control panel
        this.targetRotations = (int)SmartDashboard.getNumber("Control Panel Rotations", 3.0) * 2;
        this.startColor = this.colorSensor.getNamedColor();
        this.rotations = 0;

        if (this.targetRotations < 6 || this.targetRotations > 10) {
            this.targetRotations = 6;
            System.out.println("WARNING: Target rotations count is not in the valid range");
            return 1;
        }
        return 0;
    }

    /**
     * Runs the spinner until the specified amount of rotations are reached.
     * @return The amount of rotations already ran (note: increments once every full rotation of the wheel)
     */
    public int rotationControlPeriodic() {
        String detected = this.colorSensor.getNamedColor();

        if (detected == this.startColor) {
            if (!this.rotationDebounce) {
                this.rotationDebounce = true;
                this.rotations += 1;
            }
        } else if (!(detected == "Unknown")) {
            this.rotationDebounce = false;
        }

        if (this.rotations == this.targetRotations) {
            this.stop();
        } else if ((this.targetRotations - this.rotations) <= 2) {
            this.spinner.set(0.2);
        } else {
            this.spinner.set(0.5);
        }

        return (int)this.rotations / 2;
    }

    public void stop() {
        this.spinner.set(0.0);
    }

}