package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.cs131.pa1.filter.Message;

/**
 * This class manages the parsing and execution of a command. It splits the raw
 * input into separated subcommands, creates subcommand filters, and links them
 * into a list.
 */
public class SequentialCommandBuilder {
	
	public static SQFilterData[] filters = {
		new SQFilterData(WorkingDirectoryFilter.class, "pwd", 0, 0, false, false, false, true),
		new SQFilterData(ListFilter.class, "ls", 0, 0, false, false, false, true),
		new SQFilterData(ChangeDirectoryFilter.class, "cd", 1, 1, false, false, false, false),
		new SQFilterData(CatFilter.class, "cat", 1, 1, false, false, false, true),
		new SQFilterData(HeadFilter.class, "head", 0, 0, true, false, true, true),
		new SQFilterData(TailFilter.class, "tail", 0, 0, true, false, true, true),
		new SQFilterData(GrepFilter.class, "grep", 1, 1, true, false, true, true),
		new SQFilterData(WordCountFilter.class, "wc", 0, 0, true, false, true, true),
		new SQFilterData(UniqFilter.class, "uniq", 0, 0, true, false, true, true)
	};

	public static List<SequentialFilter> createFiltersFromCommand(String command) {
		command = command.trim();
		List<SequentialFilter> sf = new ArrayList<SequentialFilter>();
		String[] subcommands = command.split("\\|");
		for (String c : subcommands) {
			c = c.strip();
			SequentialFilter filter = null;
			for(SQFilterData f : filters) {
				if(c.startsWith(">")) {
					SequentialREPL.printErr(Message.REQUIRES_INPUT, c);
					return null;
				}
				if(c.matches("^" + f.alias + "($|.+>\\s*\\S*|\\s\\S+)")) {
					try {
						if(c.matches("^" + f.alias + "(.+>\\s*\\S*)")) {
							if(!f.canOutputToPipe) {
								SequentialREPL.printErr(Message.CANNOT_HAVE_OUTPUT, c);
								return null;
							} else if(sf.size() + 1 < subcommands.length) {
								SequentialREPL.printErr(Message.CANNOT_HAVE_OUTPUT, ">" + c.replaceAll("^.+>", ""));
								return null;
							}
						}
						if(c.matches("^" + f.alias + " >$")) {
							SequentialREPL.printErr(Message.REQUIRES_PARAMETER, ">");
							return null;
						}
						String[] split = c.trim().split("\\s+");
						if(split.length > 1 && f.acceptedParameters > 0) {
							filter = f.c.getConstructor(String.class).newInstance(split[1]);
						} else if(split.length == 1 && f.requiredParameteres > 0) {
							SequentialREPL.printErr(Message.REQUIRES_PARAMETER, c);
							return null;
						} else {
							filter = f.c.getConstructor().newInstance();
						}
						if(sf.size() == 0 && f.requiresInputPipe) {
							SequentialREPL.printErr(Message.REQUIRES_INPUT, c);
							return null;
						}
						if(sf.size() > 0 && !f.acceptsPipeInput) {
							SequentialREPL.printErr(Message.CANNOT_HAVE_INPUT, c);
							return null;
						}
						if(sf.size() + 1 < subcommands.length && !f.canOutputToPipe) {
							SequentialREPL.printErr(Message.CANNOT_HAVE_OUTPUT, c);
							return null;
						}
					} catch(Exception ex) {
						ex.printStackTrace();
						return null;
					}
				}
			}
			if(filter == null) {
				SequentialREPL.printErr(Message.COMMAND_NOT_FOUND, c);
				return null;
			} else {
				sf.add(filter);
			}
		}
		
		String[] redirParse = command.split("\\s>\\s");
		if(redirParse.length == 2) {
			sf.add(new RedirectFilter(redirParse[1].strip()));
		} else {
			sf.add(new PrintFilter());
		}
		linkFilters(sf);
		return sf;
	}

	private static SequentialFilter constructFilterFromSubCommand(String subCommand) {
		return null;
	}

	private static void linkFilters(List<SequentialFilter> filters) {
		for(int i = 0; i < filters.size() - 1; i++) {
			SequentialFilter f = filters.get(i);
			f.setNextFilter(filters.get(i+1));
		}
	}
}
