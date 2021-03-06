package DocumentManagementSystem;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;


public class MainApplication {

	private final List<Document> documents = new ArrayList<>();
	private final List<Document> documentsView = unmodifiableList(documents);
	private final Map<String, Importer> extensionToImporter = new HashMap<>();

	public MainApplication() {
		extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("invoice", new InvoiceImporter());
	}

	public void importFile(String path) throws IOException {
		final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

		final int separateIndex = path.lastIndexOf('.');
		if (separateIndex != -1 || separateIndex == path.length()) {
			final String extension = path.substring(separateIndex + 1);
			final Importer importer = extensionToImporter.get(extension);
            if (importer == null) {
                throw new UnknownFileTypeException("No file importer: " + path);
            }

			final Document document = importer.importFile(file);
			documents.add(document);
		} else {
            throw new UnknownFileTypeException("No extension found for file " + path);
        }
	}

	public List<Document> contents() {
		return documentsView;
	}

    public List<Document> search(final String query) {
        return documents.stream()
                        .filter(Query.parse(query))
                        .collect(Collectors.toList());
    }
}
