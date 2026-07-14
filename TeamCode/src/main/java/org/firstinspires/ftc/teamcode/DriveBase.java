package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class DriveBase extends OpMode {

    private DcMotor FLDrive;
    private DcMotor BLDrive;
    private DcMotor BRDrive;
    private DcMotor FRDrive;

    @Override
    public void init() {
        FLDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");

        BLDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");

        BRDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        FRDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
    }

    @Override
    public void loop() {
        double y = -1 * gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        FLDrive.setPower(0.5 * ((y + x) + rx));
        BLDrive.setPower(0.5 * ((y - x) + rx));
        FRDrive.setPower(-0.5 * ((y - x) - rx));
        BRDrive.setPower(-0.5 * ((y + x) - rx));
    }
}
