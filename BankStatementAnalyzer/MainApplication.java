package BankStatementAnalyzer;

import java.io.IOException;

public class MainApplication {

	public static void main(final String... args) throws IOException {

		final MainBankStatementAnalyzer bankStatementAnalyzer = new MainBankStatementAnalyzer();
		final BankStatementParser bankStatementParser = new BankStatementCSVParser();
		bankStatementAnalyzer.analyze(args[0], bankStatementParser);
	}

}
