package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;
@TeleOp
public class DcMotorPractice extends OpMode {
    MertleSquadBot intake = new MertleSquadBot();
    MertleSquadBot shooterL = new MertleSquadBot();
    MertleSquadBot shooterR = new MertleSquadBot();
    @Override
    public void init() {
        intake.init(hardwareMap);
        shooterL.init(hardwareMap);
        shooterR.init(hardwareMap);
    }

    @Override
    public void loop() {

        boolean running = false;

        if (gamepad1.a) {
            intake.setMotorSpeed(1);
        } else if (!gamepad1.a) {
            intake.setMotorSpeed(0);
        }

        if (gamepad1.b) {
            shooterL.setShooterLSpeed(1);
            shooterR.setShooterRSpeed(1);
        } else if (!gamepad1.b) {
            shooterL.setShooterLSpeed(0);
            shooterR.setShooterRSpeed(0);
        }
    }
}
