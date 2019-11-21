package com.company.ProcessScheduling;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private final static int LENGTH = 5;
	private static List<Process> PCB = new ArrayList<Process>();

	public static void main(String[] args) {
		listInit();
		init();
		command();

	}

	public static void command() {
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			int commandCode = input.nextInt();
			switch (commandCode) {
			case 1:
				inputProcessData(input);
				break;
			case 2:
				displayProcessData(PCB);
				break;
			case 3:
				new FCFS().run(PCB);
				break;
			case 4:
				new SJF().run(PCB);
				break;
			case 5:
				new HRRN().run(PCB);
				break;
			default:
				break;
			}
			init();
		}
		input.close();
	}

	public static void init() {
		// System.out.println("请输入");
		System.out.println("1. 输入进程");
		System.out.println("2. 显示进程");
		System.out.println("3. 先来先服务算法");
		System.out.println("4. 短作业优先算法");
		System.out.println("5. 高响应比算法");
		// PCB.init();
		System.out.println("请输入");
	}

	public static void inputProcessData(Scanner input) {
		String name = "";
		int comeTime = 0;
		int serverTime = 0;
		PCB.clear();
		for (int i = 0; i < LENGTH; i++) {
			System.out.println("进程" + i + "");
			System.out.println("进程名：");
			name = input.next();
			System.out.println("到达时间：");
			comeTime = input.nextInt();
			System.out.println("服务时间：");
			serverTime = input.nextInt();
			PCB.add(new Process(name, comeTime, serverTime));
		}
	}

	public static void displayProcessData(List<Process> PCB) {
		String name = "name";
		String comeTime = "comeTime";
		String serverTime = "serverTime";
		String endTime = "endTime";
		String turnoverTime = "turnoverTime";
		String weight = "weight";
		System.out.printf("%-14s", name);
		System.out.printf("%-14s", comeTime);
		System.out.printf("%-14s", serverTime);
		System.out.printf("%-14s", endTime);
		System.out.printf("%-14s", turnoverTime);
		System.out.printf("%-14s", weight);
		System.out.println();

		DecimalFormat df = new DecimalFormat("######0.00");
		for (Process p : PCB) {
			System.out.printf("%-14s", p.getName());
			System.out.printf("%-14s", p.getComeTime());
			System.out.printf("%-14s", p.getServerTime());
			System.out.printf("%-14s", p.getEndTime());
			System.out.printf("%-14s", p.getTurnoverTime());
			System.out.printf("%-14s", df.format(p.getWeight()));
			System.out.println();
		}

	}

	public static void listInit() {
		List<Process> _PCB = new ArrayList<Process>();
		_PCB.add(new Process("A", 0, 3));
		_PCB.add(new Process("B", 2, 6));
		_PCB.add(new Process("C", 4, 4));
		_PCB.add(new Process("D", 6, 5));
		_PCB.add(new Process("E", 8, 2));

		PCB.clear();
		for (Process p : _PCB) {
			PCB.add(p);
		}
	}

	public static void showAve(List<Process> PCB) {

		double aveTurnoverTime = 0;
		double aveWeight = 0;

		DecimalFormat df = new DecimalFormat("######0.00");
		
		for (Process p : PCB) {

			aveTurnoverTime += p.getTurnoverTime();
			aveWeight += p.getWeight();
		}
		System.out.println("平均周转时间: " + df.format(aveTurnoverTime / PCB.size())
				+ "\t平均带权周转时间: " + df.format(aveWeight / PCB.size()));

	}
}
