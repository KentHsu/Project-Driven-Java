package DocumentManagementSystem;

import javax.imageio.ImageIO;

import static DocumentManagementSystem.Attributes.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;


class ImageImporter implements Importer {

    @Override
    public Document importFile(final File file) throws IOException {

        final Map<String, String> attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());

        final BufferedImage image = ImageIO.read(file);
        attributes.put(WIDTH, String.valueOf(image.getWidth()));
        attributes.put(HEIGHT, String.valueOf(image.getHeight()));
        attributes.put(TYPE, "IMAGE");

        return new Document(attributes);
    }

}
