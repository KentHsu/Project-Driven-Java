package DocumentManagementSystem;

import java.io.IOException;
import static DocumentManagementSystem.Attributes.*;

public class client {
	public static void main(String... args) throws IOException {
		final MainApplication mainApplication = new MainApplication();
		mainApplication.importFile("DocumentManagementSystem/test.jpg");
		for (final Document doc: mainApplication.contents()) {
			System.out.println("Width: " + doc.getAttribute(WIDTH));
			System.out.println("Height: " + doc.getAttribute(HEIGHT));
		}
	}
}
