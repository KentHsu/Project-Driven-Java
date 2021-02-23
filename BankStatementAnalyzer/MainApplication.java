package BankStatementAnalyzer;

import java.io.IOException;

public class MainApplication {

	public static void main(final String... args) throws IOException {

		final MainBankStatementAnalyzer bankStatementAnalyzer = new MainBankStatementAnalyzer();
		final BankStatementParser bankStatementParser = new BankStatementCSVParser();
		final Exporter htmlExporter = new HtmlExporter();
		bankStatementAnalyzer.analyze(args[0], bankStatementParser, htmlExporter);
	}

}
