package BankStatementAnalyzer;

import java.util.List;
import java.util.ArrayList;
import java.time.Month;


public class BankStatementProcessor {

	private final List<BankTransaction> bankTransactions;

	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
		double result = 0;
		for (final BankTransaction bankTransaction: bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);
		}
		return result;
	}
	
	public double calculateTotalInMonth(final Month month) {
		return summarizeTransactions((acc, bankTransactions) -> bankTransactions.getDate().getMonth() == month ? acc + bankTransactions.getAmount() : acc);
	}

	public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
		final List<BankTransaction> result = new ArrayList<>();
		for (final BankTransaction bankTransaction: bankTransactions) {
			if (bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		return result;
	}

}
