package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;
import org.firstinspires.ftc.teamcode.mechanism.Sensors;
import org.firstinspires.ftc.teamcode.mechanism.TestBench;
@Disabled
@TeleOp
public class ColorSensorPractice extends OpMode {

    Sensors sensors = new Sensors();
    Sensors.DetectedColor detectedColor;

    @Override
    public void init() {
        sensors.init(hardwareMap);
    }

    @Override
    public void loop() {

        detectedColor = sensors.getDetectedColor(telemetry);
        telemetry.addData("Color Detected", detectedColor);
    }
}
