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
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "test.report";
    private static final String INVOICE = RESOURCES + "test.invoice";

    private MainApplication mainApplication = new MainApplication();
    
    @Test
    public void importImagaAttributes() throws IOException {
        mainApplication.importFile(IMAGE);
        final Document document = mainApplication.contents().get(0);
        assertAttributeEquals(document, TYPE, "IMAGE");
        assertAttributeEquals(document, WIDTH, "800");
        assertAttributeEquals(document, HEIGHT, "455");
    }

    @Test
    public void importLetterAttributes() throws IOException {
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
    public void importReportAttributes() throws IOException {
        mainApplication.importFile(REPORT);
        final Document document = mainApplication.contents().get(0);
        assertAttributeEquals(document, TYPE, "REPORT");
        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, BODY,
            "On 5th January 2017 I examined Joe's teeth.\n" +
            "We discussed his switch from drinking Coke to Diet Coke.\n" +
            "No new problems were noted with his teeth.");
    }

    @Test
    public void importInvoiceAttributes() throws IOException {
        mainApplication.importFile(INVOICE);
        final Document document = mainApplication.contents().get(0);
        assertAttributeEquals(document, TYPE, "INVOICE");
        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        //assertAttributeEquals(document, AMOUNT, "$100");
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
