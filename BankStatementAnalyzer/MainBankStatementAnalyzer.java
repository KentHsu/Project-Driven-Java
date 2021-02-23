package BankStatementAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.time.Month;

public class MainBankStatementAnalyzer {

	private static final String RESOURCES = "./BankStatementAnalyzer/";

	public static void main(final String... args) throws IOException {

		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		collectSummary(bankStatementProcessor);
	}

	private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
		System.out.println("The total for all transaction is "
		       + bankStatementProcessor.calculateTotalAmount());
		System.out.println("The total for transaction in January is "
		       + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		System.out.println("The total salary recieved is "
		       + bankStatementProcessor.calculateTotalForCategory("Salary"));
	}

}
