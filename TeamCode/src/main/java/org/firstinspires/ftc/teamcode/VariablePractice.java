package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class VariablePractice extends OpMode {

    public void init() {
        int teamNumber = 26070;
        double motorSpeed = 0.56;
        boolean shooterOn = false;
        String name = "Mertle Squad";
        int motorAngle = 59;

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Shooter State", shooterOn);
        telemetry.addData("Name", name);
        telemetry.addData("Motor Angle", motorAngle);
    }

    @Override
    public void loop() {

    }
}
