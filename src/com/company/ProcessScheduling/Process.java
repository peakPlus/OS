package com.company.ProcessScheduling;

class Process {
	private static int id = 0;

	private int processId;

	// 进程名
	private String name;
	// 到达时间
	private int comeTime;
	// 服务时间
	private int serverTime;
	// 完成时间
	private int endTime;
	// 周转时间
	private int turnoverTime;
	// 权
	private double weight;

	private int status = 0;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime() {
		this.waitTime++;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority() {
		this.priority = this.waitTime / this.serverTime;
	}
	// 等待时间
	private int waitTime = 0;
	// 优先权
	private int priority = 0;

	public Process() {

	}

	public Process(String name, int comeTime, int serverTime) {
		super();
		id++;
		this.name = name;
		this.comeTime = comeTime;
		this.serverTime = serverTime;
		this.processId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getComeTime() {
		return comeTime;
	}

	public void setComeTime(int comeTime) {
		this.comeTime = comeTime;
	}

	public int getServerTime() {
		return serverTime;
	}

	public void setServerTime(int serverTime) {
		this.serverTime = serverTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getTurnoverTime() {
		return turnoverTime;
	}

	public void setTurnoverTime() {
		this.turnoverTime = this.getEndTime() - this.getComeTime();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight() {
		this.weight = (double) this.turnoverTime / (double) this.serverTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProcessId() {
		return processId;
	}
}