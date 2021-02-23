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

	public void analyze(final String fileName, 
	                    final BankStatementParser bankStatementParser,
						final Exporter htmlExporter) throws IOException {

		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
		collectSummary(bankStatementProcessor);

		final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();
		System.out.println(htmlExporter.export(summaryStatistics));
	}

	private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {

		// Implicit API for summarize function
		double totalAmount = bankStatementProcessor.summarizeTransactions((acc, bankTransactions) -> acc + bankTransactions.getAmount());
		System.out.println("The total for all transaction is " + totalAmount);

		// Explicit API for summarize function
		System.out.println("The total for transaction in January is "
		       + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
		
		// Implement new class for transaction search
		class BankTransactionsInFebruary implements BankTransactionFilter {
			@Override
			public boolean test(final BankTransaction bankTransaction) {
				return bankTransaction.getDate().getMonth() == Month.FEBRUARY;
			}
		}
		
		final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(new BankTransactionsInFebruary());
		System.out.println("Transactions in February: " + transactions);

		// Utilize lambda expression for transaction search
		final List<BankTransaction> largeTransactions = bankStatementProcessor.findTransactions(bankTransactions -> bankTransactions.getAmount() >= 1000);
		System.out.println("Transaction larger than 1000: " + largeTransactions);
	}

}
