package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
public class LogicStuff extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        if (!gamepad1.a) {
            double motorSpeed = -gamepad1.left_stick_y / 2.0;
            telemetry.addData("Motor Speed", motorSpeed);
        } else {
            double motorSpeed = -gamepad1.left_stick_y;
            telemetry.addData("Motor Speed", motorSpeed);
        }
    }
}
