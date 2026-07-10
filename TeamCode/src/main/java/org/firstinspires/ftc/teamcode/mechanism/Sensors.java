package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Sensors {

    private DigitalChannel touchSensor;
    private DistanceSensor distance;
    private NormalizedColorSensor colorSensor;
    public enum DetectedColor {
        RED,
        BLUE,
        YELLOW,
        UNKNOWN,
    }

    public void init(HardwareMap hwMap) {

        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");

        distance = hwMap.get(DistanceSensor.class, "distance_sensor");

        colorSensor = hwMap.get(NormalizedColorSensor.class, "color_sensor");
        colorSensor.setGain(15);

    }

    public boolean touchSensorState () { return !touchSensor.getState(); }

    public double getDistance(DistanceUnit distanceUnit) {
        return distance.getDistance(distanceUnit);
    }

    public Sensors.DetectedColor getDetectedColor(Telemetry telemetry) {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        if (normRed > 0.6 && normGreen < 0.45 && normBlue < 0.2) {
            return Sensors.DetectedColor.RED;
        } else if (normRed < 0.15 && normGreen > 0.2 && normBlue > 0.6) {
            return Sensors.DetectedColor.BLUE;
        } else if (normRed > 0.95 && normGreen > 0.95 && normBlue < 0.75) {
            return Sensors.DetectedColor.YELLOW;
        } else {
            return Sensors.DetectedColor.UNKNOWN;
        }
    }
}
