package unittests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

public class ImageWriterTest {

    ImageWriter image = new ImageWriter("image", 1000, 1000, 500, 500);

    @Test
    public void renderTest() {
        for (int i = 0; i < image.getNx(); i++) {
            for (int j = 0; j < image.getNy(); j++)
                if (i % 50 == 0 || j % 50 == 0)
                    image.writePixel(j, i, Color.pink);
        }
        image.writeToImage();
    }
}
