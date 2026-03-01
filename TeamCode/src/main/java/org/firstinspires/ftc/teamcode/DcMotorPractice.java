package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;
@TeleOp
public class DcMotorPractice extends OpMode {
    MertleSquadBot intake = new MertleSquadBot();
    @Override
    public void init() {
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        intake.setMotorSpeed(0.5);
    }
}
