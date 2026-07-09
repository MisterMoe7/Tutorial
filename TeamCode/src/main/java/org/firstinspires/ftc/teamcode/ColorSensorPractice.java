package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;
import org.firstinspires.ftc.teamcode.mechanism.TestBench;

@TeleOp
public class ColorSensorPractice extends OpMode {

    MertleSquadBot bench = new MertleSquadBot();
    MertleSquadBot.DetectedColor detectedColor;

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {

        detectedColor = bench.getDetectedColor(telemetry);
        telemetry.addData("Color Detected", detectedColor);
    }
}
