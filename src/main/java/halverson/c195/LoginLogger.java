package halverson.c195;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoginLogger{
    public static void log(boolean loginResult){
        Logger log = Logger.getLogger("loginlog.txt");

        try{
            FileHandler fileHandler = new FileHandler("loginlog.txt", true);
            SimpleFormatter simpleFormat = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormat);
            log.addHandler(fileHandler);
        }
        catch (IOException ex) {
            Logger.getLogger(LoginLogger.class.getName()).log(Level.SEVERE, null, ex);
        }

        log.setLevel(Level.INFO);

        if(loginResult = true){
            LocalDate currentDate = LocalDate.now();
            LocalTime timestamp = LocalDateTime.now().toLocalTime();
            String result = "Successful login | Date:" + currentDate + " | Time:" + timestamp + "\n";

            log.info(result);
        }
        else{
            LocalDate currentDate = LocalDate.now();
            LocalTime timestamp = LocalDateTime.now().toLocalTime();
            String result = "Unsuccessful login | Date:" + currentDate + " | Time:" + timestamp + "\n";

            log.info(result);

        }
    }

}
