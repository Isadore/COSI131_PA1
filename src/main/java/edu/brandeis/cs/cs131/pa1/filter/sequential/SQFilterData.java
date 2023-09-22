package edu.brandeis.cs.cs131.pa1.filter.sequential;

public class SQFilterData {
	
	public final Class<SequentialFilter> c;
	public final String alias;
	public final int requiredParameteres;
	public final int acceptedParameters;
	public final boolean acceptsPipeInput;
	public final boolean canOutputToPipe;
	public final boolean requiresInputPipe;
	public final boolean requiresOutputPipe;
	
	SQFilterData(Class c, String alias, int preq, int pacc, boolean reqPipeIn, boolean reqPipeOut, boolean canAcceptPipe, boolean canOutputToPipe) {
		this.c = c;
		this.alias = alias;
		this.requiredParameteres = preq;
		this.acceptedParameters = pacc;
		this.acceptsPipeInput = canAcceptPipe;
		this.canOutputToPipe = canOutputToPipe;
		this.requiresInputPipe = reqPipeIn;
		this.requiresOutputPipe =  reqPipeOut;
	}

}
