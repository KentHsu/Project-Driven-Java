package DocumentManagementSystem;

import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static DocumentManagementSystem.Attributes.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class DocumentManagementSystemTest {

    private static final String RESOURCES =
            "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String IMAGE = RESOURCES + "test.jpg";
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String INVOICE = RESOURCES + "patient.invoice";

    private MainApplication mainApplication = new MainApplication();
    
    @Test
    public void importImagaAttributes() throws Exception {
        mainApplication.importFile(IMAGE);
        final Document document = mainApplication.contents().get(0);
        assertAttributeEquals(document, TYPE, "IMAGE");
        assertAttributeEquals(document, WIDTH, "800");
        assertAttributeEquals(document, HEIGHT, "455");
    }

    @Test
    public void importLetterAttributes() throws Exception {
        mainApplication.importFile(LETTER);
        final Document document = mainApplication.contents().get(0);
        assertAttributeEquals(document, TYPE, "LETTER");
        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, ADDRESS, 
            "123 Fake Street\n" +
            "Westminster\n" +
            "London\n" +
            "United Kingdom");
        assertAttributeEquals(document, BODY,
            "We are writing to you to confirm the re-scheduling of your appointment\n" +
            "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
    }

    @Test
    public void importReportAttributes() throws Exception {
        mainApplication.importFile(REPORT);
        final Document document = mainApplication.contents().get(0);
        assertIsReport(document);
    }

    @Test
    public void importInvoiceAttributes() throws Exception {
        mainApplication.importFile(INVOICE);
        final Document document = mainApplication.contents().get(0);
        assertAttributeEquals(document, TYPE, "INVOICE");
        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, AMOUNT, "$100");
    }

    @Test
    public void ableToSearchFileByAttributes() throws Exception {
        mainApplication.importFile(LETTER);
        mainApplication.importFile(REPORT);
        mainApplication.importFile(IMAGE);
        
        final List<Document> documents = mainApplication.search("patient:Joe,body:Diet Coke");
        assertThat(documents, hasSize(1));
        assertIsReport(documents.get(0));
    }

    @Test(expected=FileNotFoundException.class)
    public void notImportMissFile() throws Exception {
        mainApplication.importFile("notExist.txt");
    }

    @Test(expected=UnknownFileTypeException.class)
    public void notImportUnknownFile() throws Exception {
        mainApplication.importFile(RESOURCES + "unknown.txt");
    }

    private void assertIsReport(final Document document) {
        assertAttributeEquals(document, TYPE, "REPORT");
        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, BODY,
            "On 5th January 2017 I examined Joe's teeth.\n" +
            "We discussed his switch from drinking Coke to Diet Coke.\n" +
            "No new problems were noted with his teeth.");
    }
    private void assertAttributeEquals(
        final Document document,
        final String attributeName,
        final String expectedValue) {

        assertEquals(
            "Document has the wrong value for " + attributeName,
            expectedValue, document.getAttribute(attributeName));
    }
}
