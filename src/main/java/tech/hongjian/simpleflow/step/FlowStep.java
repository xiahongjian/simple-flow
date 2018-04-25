package tech.hongjian.simpleflow.step;

import java.util.List;
import java.util.function.Function;

import tech.hongjian.simpleflow.exception.NoNextStepException;

/**
 * @author xiahongjian 
 * @time   2018-04-25 14:31:18
 *
 */
public abstract class FlowStep<T, R> {
	private FlowStep<T, R> parent;
	private List<FlowStep<T, R>> branches;
	private Function<T, R> func;
	private String keyword;
	
	
	public FlowStep(FlowStep<T, R> parent, List<FlowStep<T, R>> branches, String keyword, Function<T, R> func) {
		this.parent = parent;
		this.branches = branches;
		this.keyword = keyword;
		this.func = func;
	}
	
	public FlowStep(List<FlowStep<T, R>> branches) {
		this(null, branches, null, null);
	}
	
	public FlowStep<T, R> back() {
		return parent;
	}
	
	public FlowStep<T, R> go(String keyword) throws NoNextStepException {
		if (branches != null && !branches.isEmpty()) {
			for (FlowStep<T, R> step : branches) {
				if (step.getKeyword().equals(keyword))
					return step;
			}
		}
		throw new NoNextStepException();
	}
	
	public R excute(T t) {
		return func.apply(t);
	}
	
	public abstract String tip();
	
	public abstract String nextSteps();
	
	public abstract boolean needInput();
	
	public abstract boolean hasOutput();

	public FlowStep<T, R> getParent() {
		return parent;
	}

	public void setParent(FlowStep<T, R> parent) {
		this.parent = parent;
	}

	public List<FlowStep<T, R>> getBranches() {
		return branches;
	}

	public void setBranches(List<FlowStep<T, R>> branches) {
		this.branches = branches;
	}

	public Function<T, R> getFunc() {
		return func;
	}

	public void setFunc(Function<T, R> func) {
		this.func = func;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
