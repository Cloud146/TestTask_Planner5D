package planner5d.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
public class ScreenShotUtil {

    public static BufferedImage takePageScreenshot(WebDriver driver) throws IOException {
        log.info("Capturing full page screenshot");
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return ImageIO.read(new ByteArrayInputStream(screenshotBytes));
    }
}