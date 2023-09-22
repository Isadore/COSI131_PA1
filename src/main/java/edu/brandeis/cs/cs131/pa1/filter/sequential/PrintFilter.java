package edu.brandeis.cs.cs131.pa1.filter.sequential;

public class PrintFilter extends SequentialFilter {
	@Override
	protected String processLine(String line) {
		if(line != null && !line.isEmpty()) {
			System.out.println(line.stripTrailing());
		}
		return null;
	}

}
