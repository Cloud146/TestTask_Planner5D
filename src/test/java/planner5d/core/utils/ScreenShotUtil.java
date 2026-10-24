package planner5d.core.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ScreenShotUtil {

    public static BufferedImage takePageScreenshot(WebDriver driver) throws IOException {
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return ImageIO.read(new ByteArrayInputStream(screenshotBytes));
    }
}