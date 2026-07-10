package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.TestBench;
@Disabled
@TeleOp
public class TouchSensorPractice extends OpMode {
    TestBench bench = new TestBench();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    public void loop() {

        String touchSensorState = "not pressed!";
        if (bench.getTouchSensorState()) {
            touchSensorState = "pressed!";
        }
        telemetry.addData("Touch Sensor State:", touchSensorState);

    }
}
