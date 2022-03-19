package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {

    
    public static double length = 30;

    public static double StickError = 0.05;

    public static int EncoderTicksInRevolution = 4096;
    public static int EncoderticksInThreeQuarters = 3072;
    public static int EncoderTicksInHalf = 2048;
    public static int EncoderTicksInQuadrant = 1024;
    
    public static double RotationSpeedCap = .3;

    // -----------------------------------------------------------------------------\\
    //                                                                              \\
    // Driver Controller Constants                                                  \\
    //                                                                              \\
    // -----------------------------------------------------------------------------\\

    public static int driverButtonA = 1;
    public static int driverButtonB = 2;
    public static int driverButtonX = 3;
    public static int driverButtonY = 4;
    public static int driverButtonLB = 5;
    public static int driverButtonRB = 6;
    public static int driverButtonBack = 7;
    public static int driverButtonStart = 8;
    public static int driverButtonLeftJoyClick = 9;
    public static int driverButtonRightJoyClick = 10;

    public static int driverLeftAxisX = 0;
    public static int driverLeftAxisY = 1;
    public static int driverRightAxisX = 4;
    public static int driverRightAxisY = 5;
    public static int driverLeftAxisTrigger = 2;
    public static int driverRightAxisTrigger = 3;

    // -----------------------------------------------------------------------------\\
    //                                                                              \\
    // Operator Controller Constants                                                \\
    //                                                                              \\
    // -----------------------------------------------------------------------------\\

    public static int operatorButtonA = 1;
    public static int operatorButtonB = 2;
    public static int operatorButtonX = 3;
    public static int operatorButtonY = 4;
    public static int operatorButtonLB = 5;
    public static int operatorButtonRB = 6;
    public static int operatorButtonBack = 7;
    public static int operatorButtonStart = 8;
    public static int operatorButtonLeftJoyClick = 9;
    public static int operatorButtonRightJoyClick = 10;

    public static int operatorLeftAxisX = 0;
    public static int operatorLeftAxisY = 1;
    public static int operatorRightAxisX = 4;
    public static int operatorRightAxisY = 5;
    public static int operatorLeftAxisTrigger = 2;
    public static int operatorRightAxisTrigger = 3;

}
