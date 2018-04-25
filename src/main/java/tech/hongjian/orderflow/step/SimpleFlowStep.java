package tech.hongjian.orderflow.step;

import java.util.List;
import java.util.function.Function;

/**
 * @author xiahongjian 
 * @time   2018-04-25 14:52:52
 *
 */
public class SimpleFlowStep extends FlowStep<String, String> {
	private String tip;
	private String nextSteps;
	private boolean needInput;
	private boolean hasOutput;

	public SimpleFlowStep(FlowStep<String, String> parent, List<FlowStep<String, String>> branches, String keyword,
			Function<String, String> func, String tip, String nextSteps, boolean needInput, boolean hasOutput) {
		super(parent, branches, keyword, func);
		this.tip = tip;
		this.nextSteps = nextSteps;
		this.needInput = needInput;
		this.hasOutput = hasOutput;
	}
	
	public SimpleFlowStep(List<FlowStep<String, String>> branches) {
		this(null, branches, null, null, null, null, false, false);
	}
	
	public SimpleFlowStep(List<FlowStep<String, String>> branches, String tip, String nextSteps) {
		this(branches);
		this.tip = tip;
		this.nextSteps = nextSteps;
	}

	@Override
	public String tip() {
		return tip;
	}

	@Override
	public boolean needInput() {
		return needInput;
	}

	@Override
	public boolean hasOutput() {
		return hasOutput;
	}

	@Override
	public String nextSteps() {
		return nextSteps;
	}

}
