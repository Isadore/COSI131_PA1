package edu.brandeis.cs.cs131.pa1.filter.sequential;

public class WordCountFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		if(line.isBlank()) {
			return "0 0 0";
		}
		int lc = line.split("\\n").length;
		int wc = line.trim().split("\\s+").length;
		int cc = line.replaceAll("\\s", "").length();
		return lc + " " + wc + " " + cc;
	}

}
