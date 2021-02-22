package BankStatementAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.time.Month;


public class MainBankStatementAnalyzer {
	private static final String RESOURCES = "./BankStatementAnalyzer/";

	public static void main(final String... args) throws IOException {

		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);

		System.out.println("The total amount is " + calculateTotalAmount(bankTransactions));
		System.out.println("Transaction in January: " + selectInMonth(bankTransactions, Month.JANUARY));
	}

	public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
		double total = 0d;
		for (final BankTransaction bankTransaction: bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}

	public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
		final List<BankTransaction> bankTransactionInMonth = new ArrayList<>();
		for (final BankTransaction bankTransaction: bankTransactions) {
			if (bankTransaction.getDate().getMonth() == month) {
				bankTransactionInMonth.add(bankTransaction);
			}
		}
		return bankTransactionInMonth;
	}

}
