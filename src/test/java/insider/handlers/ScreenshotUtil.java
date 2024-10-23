package insider.handlers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil extends SeleniumHandler  {

    public static void getScreenShot(String file) {
        try {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(file));
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

}
