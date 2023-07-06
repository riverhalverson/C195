package halverson.c195;
import javafx.fxml.Initializable;

import java.io.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** This class is for the login page logger, it tracks successful and unsuccessful attempts and writes to a file */
public class LoginLogger{
    /** This method deletes an appointment object from the sql database
     * @param loginResult the login result, successful or not
     */
    public static void log(boolean loginResult){
        //create new file if does not exist
        try{
            PrintWriter logPrint = new PrintWriter(
                    new FileOutputStream(new File("login_activity.txt"), true));

            if(loginResult == true){
                LocalDate currentDate = LocalDate.now();
                LocalTime timestamp = LocalDateTime.now().toLocalTime();
                String result = "Successful login | Date:" + currentDate + " | Time:" + timestamp + "\n";

                logPrint.append(result);
            }
            else{
                LocalDate currentDate = LocalDate.now();
                LocalTime timestamp = LocalDateTime.now().toLocalTime();
                String result = "Unsuccessful login | Date:" + currentDate + " | Time:" + timestamp + "\n";

                logPrint.append(result);
            }

            logPrint.close();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
