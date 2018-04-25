package tech.hongjian.simpleflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import tech.hongjian.simpleflow.exception.NoNextStepException;
import tech.hongjian.simpleflow.flow.Flow;
import tech.hongjian.simpleflow.step.FlowStep;
import tech.hongjian.simpleflow.step.SimpleFlowStep;


/**
 * @author xiahongjian 
 * @time   2018-04-25 15:17:04
 *
 */
public class Test {
	public static void main(String[] args) {
		List<FlowStep<String, String>> steps = new ArrayList<>();
		SimpleFlowStep start = new SimpleFlowStep(steps, null, "Welcome to CommandServer. Please type 'a' or 'b' to choise service.");
		
		List<FlowStep<String, String>> aBranches = new ArrayList<>();
		List<FlowStep<String, String>> bBranches = new ArrayList<>();
		Function<String, String> func = (input) -> ("You input " + input); 
		SimpleFlowStep a = new SimpleFlowStep(start, aBranches, "a", func, "Now at step a. Type the param.", "Type a1 or a2 to choose service.", true, true);
		SimpleFlowStep b = new SimpleFlowStep(start, bBranches, "b", func, "Now at step b. Type the param.", null, true, true);
		steps.add(a);
		steps.add(b);
		
		List<FlowStep<String, String>> a1Branches = new ArrayList<>();
		SimpleFlowStep a1 = new SimpleFlowStep(a, a1Branches, "a1", func, "Now at step a1. Type the param.", "Type a11 to choose", true, true);
		SimpleFlowStep a2 = new SimpleFlowStep(a, null, "a2", func, "Now at step a2. Type the param.", null, true, true);
		aBranches.add(a1);
		aBranches.add(a2);
		
		SimpleFlowStep a11 = new SimpleFlowStep(a1, null, "a11", func, "Now at step a11. Type the param.", null, true, true);
		a1Branches.add(a11);
		
		Flow<String, String> flow = new Flow<>(start);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (flow.nextSteps()  != null)
				System.out.println(flow.nextSteps());
			String input = scanner.nextLine();
			if ("back".equals(input)) {
				flow.back();
				System.out.println("Back to up level.");
				continue;
			}
			if ("reset".equals(input)) {
				flow.reset();
				System.out.println("Reset to the top level.");
				continue;
			}
			try {
				flow.go(input);
				if (flow.tip() != null)
					System.out.println(flow.tip());
				String param = flow.needInput() ? scanner.nextLine() : null;
				String res = flow.excute(param);
				if (flow.hasOutput())
					System.out.println(res);
			} catch (NoNextStepException e) {
				System.out.println("You type incorrect command. Place try again.");
			}
		}
	}
}
