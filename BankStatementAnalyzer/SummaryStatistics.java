package BankStatementAnalyzer;


public class SummaryStatistics {

	private final double sum;
	private final double max;
	private final double min;
	private final double avg;

	public SummaryStatistics(final double sum, final double max, final double min, final double avg) {
		this.sum = sum;
		this.max = max;
		this.min = min;
		this.avg = avg;
	}

	public double getSum() {
		return sum;
	}

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}
	
	public double getAverage() {
		return avg;
	}

}
