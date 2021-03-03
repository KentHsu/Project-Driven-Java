package DocumentManagementSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static java.util.stream.Collectors.toList;
import static DocumentManagementSystem.Attributes.*;


class LetterImporter implements Importer {

    private final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(final File file) throws IOException {

        final Map<String, String> attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());

        final List<String> lines = Files.lines(file.toPath()).collect(toList());
        String line = lines.get(0);
        if (line.startsWith(NAME_PREFIX)) {
            attributes.put(PATIENT, line.substring(NAME_PREFIX.length()));
        }

        final StringBuilder accumulator = new StringBuilder();
        int lineNumber;
        for(lineNumber = 2; lineNumber < lines.size(); lineNumber++) {
            line = lines.get(lineNumber);
            if (line.isEmpty()) {
                break;
            }
            accumulator.append(line);
            accumulator.append('\n');
        }
        attributes.put(ADDRESS, accumulator.toString().trim());
        accumulator.setLength(0);

        for(lineNumber = lineNumber + 1; lineNumber < lines.size(); lineNumber++) {
            line = lines.get(lineNumber);
            if (line.startsWith("regards,")) {
                break;
            }
            accumulator.append(line);
            accumulator.append('\n');
        }
        attributes.put(BODY, accumulator.toString().trim());

        attributes.put(TYPE, "LETTER");

        return new Document(attributes);
    }

}
