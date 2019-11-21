package com.company.ProcessScheduling;

import java.util.Comparator;

public class ProcessCompartor implements Comparator<Process> {
	public static final int PROCESSID = 0;
	public static final int COMETIME = 1;
	public static final int SERVERTIME = 2;
	public static final int PRIORITY = 3;
	private int type;

	public ProcessCompartor(int type) {
		this.type = type;
	}

	public int compare(Process p1, Process p2) {
		switch (type) {
		case PROCESSID:
			return p1.getProcessId() - p2.getProcessId();
		case COMETIME:
			return p1.getComeTime() - p2.getComeTime();
		case SERVERTIME:
			return p1.getServerTime() - p2.getServerTime();
		case PRIORITY:
			return p2.getPriority() - p1.getPriority();
		}
		return 0;
	}
}
