package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.TestBench;
@Disabled
@TeleOp
public class ServoPractice extends OpMode {

    TestBench bench = new TestBench();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        double leftTrigger = gamepad1.left_trigger;
        double rightTrigger = gamepad1.right_trigger;
        bench.setServoPos(leftTrigger);
        bench.setServoRot(rightTrigger);

    }
}
