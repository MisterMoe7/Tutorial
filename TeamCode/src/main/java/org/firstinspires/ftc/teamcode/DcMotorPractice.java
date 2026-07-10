package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;
import org.firstinspires.ftc.teamcode.mechanism.TestBench;
@Disabled
@TeleOp
public class DcMotorPractice extends OpMode {
    TestBench bench = new TestBench();

    @Override
    public void init() {

        bench.init(hardwareMap);


    }

    @Override
    public void loop() {


        if (gamepad1.a) {
            bench.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else if (gamepad1.b) {
            bench.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }


        if (bench.getTouchSensorState()) {
            bench.setMotorSpeed(0.5);
        } else {
            bench.setMotorSpeed(0);
        }
        telemetry.addData("Motor Revs", bench.getMotorRevs());




    }
}
