package com.company.ProcessScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SJF {
	private int time = 0;
	private Process currentP = null;
	private List<Process> _PCB = new ArrayList<Process>();
	private List<Process> stack = new ArrayList<Process>();
	private List<Process> finalPCB = new ArrayList<Process>();

	public void run(List<Process> PCB) {
		_PCB.addAll(PCB);
		while (finalPCB.size() < PCB.size()) {
			for (int i = 0; i < _PCB.size(); i++) {
				if (_PCB.get(i).getComeTime() <= time) {
					System.out.println(time + " 进程" + _PCB.get(i).getName()
							+ "到达内存");
					stack.add(_PCB.get(i));
					_PCB.remove(i);
				}
			}

			if (currentP == null) {
				Collections.sort(stack, new ProcessCompartor(2));
				if (stack.get(0) != null) {
					currentP = stack.remove(0);
					currentP.setEndTime(time + currentP.getServerTime());
					currentP.setTurnoverTime();
					currentP.setWeight();
					System.out.println(time + " 进程" + currentP.getName()
							+ "开始执行");
				}
			} else if (time == currentP.getEndTime()) {
				finalPCB.add(currentP);
				System.out.println(time + " 进程" + currentP.getName() + "执行完毕");
				currentP = null;
				continue;
			}
			time++;
		}
		Collections.sort(finalPCB, new ProcessCompartor(0));
		Main.displayProcessData(finalPCB);
		Main.showAve(finalPCB);
	}
}