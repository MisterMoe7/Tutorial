package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class ControllerPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        double speedForeward = -gamepad1.left_stick_y / 2.0;
        double difference = gamepad1.left_stick_x - gamepad1.right_stick_x;
        double combTriggers = gamepad1.left_trigger + gamepad1.right_trigger;

        telemetry.addData("left x", gamepad1.left_stick_x);
        telemetry.addData("left y", speedForeward);
        telemetry.addData("right x", gamepad1.right_stick_x);
        telemetry.addData("right y", gamepad1.right_stick_y);
        telemetry.addData("a", gamepad1.a);
        telemetry.addData("b", gamepad1.b);
        telemetry.addData("difference in x", difference);
        telemetry.addData("both triggers", combTriggers);

    }
}
