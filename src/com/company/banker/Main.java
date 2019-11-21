package com.company.banker;

import java.util.Scanner;

public class Main {
	
	static PCB p = new PCB();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		p.init();

		init();

		command();
	}

	public static void init() {

		System.out.println("1. 输入资源分配");

		System.out.println("2. 查看资源分配表");

		System.out.println("3. 请求资源");

		System.out.println("4. 退出");
		// PCB.init();
		System.out.println("请输入: ");
	}
	
	public static void command() {

		Scanner input = new Scanner(System.in);

		p.requestResource(input);

		p.show();

		while (input.hasNext()) {

			int commandCode = input.nextInt();
			
			switch (commandCode) {

			case 1:

				break;

			case 2:

				p.show();

				break;

			case 3:

				p.requestResource(input);

				p.show();

				break;

			case 4:

				return;

			default:

				break;

			}

			init();

		}

		input.close();

	}
}
