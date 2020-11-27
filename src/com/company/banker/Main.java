package com.company.banker;

import java.util.Scanner;

public class Main {
	
	static PCB p = new PCB();
	
	public static void main(String[] args) {

		p.init();

		init();

		command();
	}

	public static void init() {

		System.out.println("1. 初始化资源分配");

		System.out.println("2. 查看资源分配表");

		System.out.println("3. 请求资源");

		System.out.println("4. 退出");

		System.out.println("请输入: ");
	}
	
	public static void command() {

		Scanner input = new Scanner(System.in);

		while (input.hasNext()) {

			int commandCode = input.nextInt();
			
			switch (commandCode) {

			case 1:

				p.init();

				p.show();

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
