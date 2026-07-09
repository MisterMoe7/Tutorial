package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanism.TestBench;
@TeleOp

public class ImuPractice extends OpMode {
    TestBench bench = new TestBench();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Heading", bench.getHeading(AngleUnit.DEGREES));

        if (bench.getHeading(AngleUnit.DEGREES) > 0.5) {
            bench.setMotorSpeed(-0.5);
        } else if (bench.getHeading(AngleUnit.DEGREES) < -0.5) {
            bench.setMotorSpeed(0.5);
        } else {
            bench.setMotorSpeed(0);
        }

    }
}
