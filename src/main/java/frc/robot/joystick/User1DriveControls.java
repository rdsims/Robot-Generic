package frc.robot.joystick;

import java.nio.ByteBuffer;
import java.util.Optional;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.taurus.joystick.XboxController;
import frc.taurus.messages.GenericQueue;
import frc.taurus.messages.JoystickStatusQueue;
import frc.taurus.messages.MessageQueueManager;

/**
 * This file defines the user controls / button mappings
 */

public class User1DriveControls extends ControlsBase implements IDriveControls {
    // singleton pattern
    private static User1DriveControls mInstance = null;
    public static User1DriveControls getInstance() {
        if (mInstance == null) {
             mInstance = new User1DriveControls();
        }
        return mInstance;
    }   

    // define the physical controllers that will be used
    private final XboxController mDriveController;

    private User1DriveControls() {
        // use ControlsBase.addController() to add controllers to this control method
        Joystick joystick = new Joystick(Constants.ControllerConstants.kDriveControllerPort);  
        Optional<JoystickStatusQueue> statusQueue = Optional.of(MessageQueueManager.getInstance().driveJoystickStatusQueue);
        Optional<GenericQueue<ByteBuffer>> goalQueue = Optional.of(MessageQueueManager.getInstance().driveJoystickGoalQueue);
        mDriveController = (XboxController)ControlsBase.addController(new XboxController(joystick, Constants.ControllerConstants.kDriveDeadband, statusQueue, goalQueue));      
    }

    public double getThrottle() { return mDriveController.getAxis(XboxController.Axis.L_STICK_Y_AXIS); };
    public double getSteering() { return mDriveController.getAxis(XboxController.Axis.L_STICK_X_AXIS); };
    public boolean getQuickTurn() { return false; };
    public boolean getLowGear() { return false; };

}