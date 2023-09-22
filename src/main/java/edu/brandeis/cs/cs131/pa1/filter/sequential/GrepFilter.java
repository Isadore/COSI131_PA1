package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;

/**
 * Filters pipe input based on user query.
 * @author Isadore H
 *
 */
public class GrepFilter extends SequentialFilter {
	
	private String query;

	/**
	 * Stores query for use by processLine
	 * @param q String query to be used by processLine
	 */
	public GrepFilter(String q) {
		this.query = q;
	}

	/**
	 * Searches line by line in "line" for lines containing query and returns only lines containing query,
	 * @param line Any string
	 */
	@Override
	protected String processLine(String line) {
		String[] split = line.split("\\n");
		ArrayList<String> filtered = new ArrayList();
		for(String l : split) {
			if(l.contains(query))
				filtered.add(l);
		}
		return String.join(System.lineSeparator(), filtered);
	}

}
