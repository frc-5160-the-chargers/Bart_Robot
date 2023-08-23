// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot

import com.batterystaple.kmeasure.units.inches
import com.batterystaple.kmeasure.units.seconds
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import edu.wpi.first.wpilibj2.command.button.Trigger
import frc.chargers.commands.buildCommand
import frc.chargers.commands.setDefaultRunCommand
import frc.chargers.hardware.motorcontrol.EncoderMotorControllerGroup
import frc.chargers.hardware.motorcontrol.rev.ChargerSpark
import frc.chargers.hardware.motorcontrol.rev.neoSparkMax
import frc.chargers.hardware.sensors.encoders.Encoder
import frc.chargers.hardware.subsystems.drivetrain.BasicDifferentialDrivetrain
import frc.chargers.hardware.subsystems.drivetrain.sparkDrivetrain
import frc.robot.Constants.OperatorConstants
import frc.robot.commands.Autos
import frc.robot.commands.ExampleCommand
import frc.robot.subsystems.ExampleSubsystem

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the [Robot]
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
class RobotContainer {


    private val m_driverController = CommandXboxController(0)

    val left1  = ChargerSpark(channel = 0) // @Someone change this here
    val left2  = ChargerSpark(channel = 0) // and this
    val right1 = ChargerSpark(channel = 0)  // and this
    val right2 = ChargerSpark(channel = 0)
    private val drivetrain: BasicDifferentialDrivetrain = BasicDifferentialDrivetrain(
        leftMotors = MotorControllerGroup(left1, left2),
        rightMotors = MotorControllerGroup(right1,right2),
        invertMotors = false
    )

    /** The container for the robot. Contains subsystems, OI devices, and commands.  */
    init {
        // Configure the trigger bindings
        configureBindings()
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * [Trigger.Trigger] constructor with an arbitrary
     * predicate, or via the named factories in [ ]'s subclasses for [ ]/[ PS4][edu.wpi.first.wpilibj2.command.button.CommandPS4Controller] controllers or [Flight][edu.wpi.first.wpilibj2.command.button.CommandJoystick].
     */
    private fun configureBindings() {
        drivetrain.setDefaultRunCommand {
            drivetrain.curvatureDrive(m_driverController.leftY, m_driverController.rightX)
        }
    }

    val autonomousCommand: Command
        /**
         * Use this to pass the autonomous command to the main [Robot] class.
         *
         * @return the command to run in autonomous
         */
        get() = buildCommand{
            loopFor(5.seconds,drivetrain){
                drivetrain.curvatureDrive(0.3,0.3)
            }

        }

}
