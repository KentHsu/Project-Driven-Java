package DocumentManagementSystem;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import static DocumentManagementSystem.Attributes.*;

public class DocumentManagementSystemTest {

    private static final String RESOURCES =
            "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String IMAGE = RESOURCES + "test.jpg";
	
	@Test
	public void importImagaAttributes() throws IOException {
		final MainApplication mainApplication = new MainApplication();
		mainApplication.importFile(IMAGE);
		final Document doc = mainApplication.contents().get(0);
		assertEquals(doc.getAttribute(WIDTH), "800");
		assertEquals(doc.getAttribute(HEIGHT), "455");
	}

}
