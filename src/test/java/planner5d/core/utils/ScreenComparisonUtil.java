package planner5d.core.utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class ScreenComparisonUtil {

    private static final String BASELINE_DIR = "src/test/resources/standart_screenshotes";
    private static final String DIFF_DIR = "target/differences";

    @Step("Compare page screenshot with baseline: {expectedName}")
    public static void compareScreenshots(WebDriver driver, String expectedName, double allowedDifferencePercent) {
        log.info("Starting visual comparison for baseline: {} with allowed difference {}%", expectedName, allowedDifferencePercent);
        try {
            File expectedFile = getExpectedScreenshot(expectedName);
            BufferedImage actualImage = ScreenShotUtil.takePageScreenshot(driver);
            BufferedImage expectedImage = ImageIO.read(expectedFile);

            compareAndSaveResults(expectedImage, actualImage, expectedName, allowedDifferencePercent);
        } catch (IOException e) {
            log.error("Failed to perform visual comparison: {}", e.getMessage(), e);
            throw new RuntimeException("Error during visual comparison", e);
        }
    }

    private static File getExpectedScreenshot(String expectedName) throws IOException {
        Path expectedPath = Paths.get(BASELINE_DIR, expectedName);
        if (!Files.exists(expectedPath)) {
            log.error("Baseline screenshot not found at: {}", expectedPath.toAbsolutePath());
            throw new IOException("Baseline screenshot not found: " + expectedPath.toAbsolutePath());
        }
        return expectedPath.toFile();
    }

    private static void compareAndSaveResults(BufferedImage expected, BufferedImage actual, String fileName, double allowedDifferencePercent) throws IOException {
        ImageComparison imageComparison = new ImageComparison(expected, actual);

        imageComparison.setAllowingPercentOfDifferentPixels(allowedDifferencePercent);

        ImageComparisonResult result = imageComparison.compareImages();

        if (result.getImageComparisonState() == ImageComparisonState.MATCH) {
            log.info("Screenshots match perfectly or within the allowed difference.");
        } else {
            log.error("Screenshots differ. Difference percentage: {}%", result.getDifferencePercent());
            saveDifferenceImage(result, fileName);

            throw new AssertionError(String.format(
                    "Screenshots differ! Actual difference: %.2f%%, Allowed: %.2f%%",
                    result.getDifferencePercent(), allowedDifferencePercent
            ));
        }
    }

    private static void saveDifferenceImage(ImageComparisonResult result, String originalFileName) {
        try {
            BufferedImage differenceImage = result.getResult();
            Path diffDir = Paths.get(DIFF_DIR);
            Files.createDirectories(diffDir);

            String diffFileName = originalFileName.replace(".png", "_difference.png");
            Path diffPath = diffDir.resolve(diffFileName);
            ImageIO.write(differenceImage, "png", diffPath.toFile());

            log.info("Difference image saved to: {}", diffPath.toAbsolutePath());
        } catch (IOException e) {
            log.error("Error saving difference image: {}", e.getMessage(), e);
        }
    }
}