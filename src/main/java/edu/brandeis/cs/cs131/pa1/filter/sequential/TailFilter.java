package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.Collections;

public class TailFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		String[] lines = line.strip().split("\\n");
		ArrayList<String> tail = new ArrayList();
		for(int i = lines.length; i > 0 && i > lines.length - 10; i--) {
			tail.add(lines[i - 1]);
		}
		Collections.reverse(tail);
		return String.join("\n", tail);
	}
	
}
