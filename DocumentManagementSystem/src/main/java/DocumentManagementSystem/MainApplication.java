package DocumentManagementSystem;

import java.io.File;
import java.io.IOException;
import java.util.*;
import static java.util.Collections.unmodifiableList;


public class MainApplication {

	private final List<Document> documents = new ArrayList<>();
	private final List<Document> documentsView = unmodifiableList(documents);
	private final Map<String, Importer> extensionToImporter = new HashMap<>();

	public MainApplication() {
		extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("letter", new LetterImporter());
	}

	public void importFile(String path) throws IOException {
		final File file = new File(path);
		final int seperateIndex = path.lastIndexOf('.');
		if (seperateIndex != -1) {
			final String extension = path.substring(seperateIndex + 1);
			final Importer importer = extensionToImporter.get(extension);
			final Document document = importer.importFile(file);
			documents.add(document);
		}
	}

	public List<Document> contents() {
		return documentsView;
	}

}
