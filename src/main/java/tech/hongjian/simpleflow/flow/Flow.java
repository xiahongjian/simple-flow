package tech.hongjian.simpleflow.flow;

import tech.hongjian.simpleflow.exception.NoNextStepException;
import tech.hongjian.simpleflow.step.FlowStep;

/**
 * @author xiahongjian 
 * @time   2018-04-25 15:04:47
 *
 */
public class Flow<T, R> {
	private FlowStep<T, R> head;
	private FlowStep<T, R> steps;
	private boolean init;
	
	
	public Flow(FlowStep<T, R> steps) {
		this.steps = steps;
		head = steps;
		init = true;
	}
	
	public Flow<T, R> back() {
		head = head.back();
		return this;
	}
	
	public Flow<T, R> reset() {
		head = steps;
		return this;
	}
	
	public Flow<T, R> go(String keyword) throws NoNextStepException {
		init = false;
		head = head.go(keyword);
		return this;
	}
	
	public R excute(T t) {
		R res = head.excute(t);
		// 流程执行完毕，重置step指针
		if (head.getBranches() == null || head.getBranches().isEmpty()) {
			head = steps;
			init = true;
		}
		return res;
	}
	
	public String tip() {
		return head.tip();
	}
	
	public String nextSteps() {
		return head.nextSteps();
	}
	
	public boolean needInput() {
		return head.needInput();
	}
	
	public boolean hasOutput() {
		return head.hasOutput();
	}

	public boolean isInit() {
		return init;
	}
}
