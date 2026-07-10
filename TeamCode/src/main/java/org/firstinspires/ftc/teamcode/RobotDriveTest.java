package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanism.DriveTrain;
import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;
import org.firstinspires.ftc.teamcode.mechanism.Sensors;

@TeleOp
public class RobotDriveTest extends OpMode {
    MertleSquadBot bot = new MertleSquadBot();
    DriveTrain dTrain = new DriveTrain();
    Sensors sensors = new Sensors();
    Sensors.DetectedColor detectedColor;
    boolean isShooterOn = false;

    @Override
    public void init() {
        bot.init(hardwareMap);
        dTrain.init(hardwareMap);
        sensors.init(hardwareMap);
        dTrain.setDriveBehaviorAndMode(DcMotor.ZeroPowerBehavior.BRAKE, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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


        dTrain.setFLPower(0.5 * ((y + x) + rx));
        dTrain.setBLPower(0.5 * ((y - x) + rx));
        dTrain.setFRPower(-0.5 * ((y - x) - rx));
        dTrain.setBRPower(-0.5 * ((y + x) - rx));
        if (sensors.touchSensorState()) {bot.setShooterSpeed(shooterSpeed);} else {bot.setShooterSpeed(0);}
        //if (sensors.getDistance(DistanceUnit.CM) < 50) { dTrain.setFLPower(-0.25); dTrain.setFRPower(0.25); dTrain.setBLPower(-0.25); dTrain.setBRPower(0.25); }



        bot.setFeederPower(gamepad2.right_trigger);

        detectedColor = sensors.getDetectedColor(telemetry);
        telemetry.addData("Detected Color", detectedColor);
        telemetry.addData("Distance", sensors.getDistance(DistanceUnit.CM));
    }
}
