package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;

@TeleOp
public class RobotDriveTest extends OpMode {
    MertleSquadBot bot = new MertleSquadBot();
    MertleSquadBot.DetectedColor detectedColor;
    boolean isShooterOn = false;

    @Override
    public void init() {
        bot.init(hardwareMap);
        bot.setDriveBehaviorAndMode(DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.setShooterBehaviorAndMode(DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_USING_ENCODER);
        bot.initShooterPIDF();

    }

    @Override
    public void loop() {
        double y = -1 * gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;
        double shooterSpeed = gamepad1.left_trigger * 0.5;

        if(!isShooterOn && gamepad2.bWasPressed()) { isShooterOn = true; } else if (isShooterOn && gamepad2.bWasPressed()) { isShooterOn = false; }

        if(isShooterOn) { bot.runShooterPIDF(1500); } else { bot.runShooterPIDF(0); }


        bot.setFLPower(0.5 * ((y + x) + rx));
        bot.setBLPower(0.5 * ((y - x) + rx));
        bot.setFRPower(-0.5 * ((y - x) - rx));
        bot.setBRPower(-0.5 * ((y + x) - rx));
        if (bot.touchSensorState()) {bot.setShooterSpeed(shooterSpeed);} else {bot.setShooterSpeed(0);}



        bot.setFeederPower(gamepad2.right_trigger);

        detectedColor = bot.getDetectedColor(telemetry);
        telemetry.addData("Detected Color", detectedColor);
    }
}
