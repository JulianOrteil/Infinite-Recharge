package frc.robot.Devices;

import edu.wpi.first.wpilibj.I2C;
/**
 * Arduino
 */
public class Arduino {

    private I2C Wire;
    private final int MAX_BYTES;

    private final int REGISTER_ADDRESS;

    public Arduino() {

        this.Wire = new I2C(I2C.Port.kOnboard, 0);
        this.MAX_BYTES = 32;
        this.REGISTER_ADDRESS = 4;

    }

    /**
     * Alert the Arduino that the balls were released and to set the count to 0.
     */
    public void ballsReleased() {
        byte[] writeData = new byte[1];
        writeData[0] = 1;
        this.Wire.transaction(writeData, writeData.length, null, 0);
    }

    /**
     * Gets the ball count from the Arduino.
     * @return The ball count
     */
    public int getBallCount() {

        byte[] data = new byte[this.MAX_BYTES];
        this.Wire.read(this.REGISTER_ADDRESS, this.MAX_BYTES, data);

        String output = new String(data);
        return Integer.valueOf(output);
    }
}