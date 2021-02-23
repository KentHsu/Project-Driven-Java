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

	public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {

		final Path path = Paths.get(RESOURCES + fileName);
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
