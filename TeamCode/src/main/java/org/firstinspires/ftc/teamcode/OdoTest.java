package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.mechanism.MertleSquadBot;

@Disabled
@TeleOp
public class OdoTest extends OpMode {

    MertleSquadBot bot = new MertleSquadBot();

    GoBildaPinpointDriver odo;
    private DcMotor FLDrive;
    private DcMotor BLDrive;
    private DcMotor BRDrive;
    private DcMotor FRDrive;

    @Override
    public void init() {
        bot.init(hardwareMap);

        FLDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");

        BLDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");

        BRDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        FRDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");

        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        odo.setOffsets(-146, 38, DistanceUnit.MM);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);


        odo.resetPosAndIMU();
        Pose2D startingPosition = new Pose2D(DistanceUnit.MM, 0,0, AngleUnit.DEGREES,0);
        odo.setPosition(startingPosition);

        telemetry.addData("X offset", odo.getXOffset(DistanceUnit.MM));
        telemetry.addData("Y offset", odo.getYOffset(DistanceUnit.MM));
        telemetry.addData("Heading", odo.getHeading(AngleUnit.DEGREES));
    }

    public void moveRobot() {
        double foreward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        Pose2D pos = odo.getPosition();
        double heading = pos.getHeading(AngleUnit.RADIANS);

        double cosAngle = Math.cos((Math.PI / 2)-heading);
        double sinAngle = Math.sin((Math.PI / 2)-heading);

        double globalStrafe = -foreward * sinAngle + strafe * cosAngle;
        double globalForeward = foreward * cosAngle + strafe * sinAngle;

        double[] newWheelSpeeds = new double[4];

        newWheelSpeeds[0] = globalForeward + globalStrafe + rotate;
        newWheelSpeeds[1] = globalForeward - globalStrafe - rotate;
        newWheelSpeeds[2] = globalForeward - globalStrafe + rotate;
        newWheelSpeeds[3] = globalForeward + globalStrafe - rotate;

        FLDrive.setPower(newWheelSpeeds[0]);
        FRDrive.setPower(-newWheelSpeeds[1]);
        BLDrive.setPower(newWheelSpeeds[2]);
        BRDrive.setPower(-newWheelSpeeds[3]);

        telemetry.addData("Robot XPos", pos.getX(DistanceUnit.MM));
        telemetry.addData("Robot YPos", pos.getY(DistanceUnit.MM));
        telemetry.addData("Robot Heading", heading);
        telemetry.addData("Foreward Speed", globalForeward);
        telemetry.addData("Strafe Speed", globalStrafe);
    }

    @Override
    public void loop() {
        moveRobot();

        Pose2D pos = odo.getPosition();
        telemetry.addData("Robot X", odo.getPosX(DistanceUnit.MM));
        telemetry.addData("Robot Y", odo.getPosY(DistanceUnit.MM));
        telemetry.addData("Robot Heading", odo.getHeading(AngleUnit.DEGREES));
        telemetry.update();

        odo.update();

    }
}
