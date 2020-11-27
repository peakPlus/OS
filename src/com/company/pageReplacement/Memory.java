package com.company.pageReplacement;

import java.util.Scanner;

public class Memory {
	
	private static int pageSize = 1024;
	
	private static int jobSize = 1024 * 20;
	
	private static int block = 3;
	
	private static int[] memoryArr = {-1, -1, -1};

	public static void main(String[] args) {
		
		memoryInit();
		
		init();
		
		command();
	}
	
	 public static void init() {

	        System.out.println("1. 初始化");

	        System.out.println("2. FIFO算法");

	        System.out.println("3. LRU算法");

	        System.out.println("4. 退出");
	        // PCB.init();
	        System.out.println("请输入: ");
	    }

	    public static void command() {

	        Scanner input = new Scanner(System.in);

	        while (input.hasNext()) {

	            int commandCode = input.nextInt();

	            switch (commandCode) {

	                case 1:

						memoryInit();

	                    break;

	                case 2: 

	                    new FIFO(pageSize, jobSize, block,memoryArr, input);

	                    break;

	                case 3:

						new LRU(pageSize, jobSize, block,memoryArr, input);

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

	
	public static void memoryInit() {
		
		pageSize = 1024;
		
		jobSize = 1024 * 20;
		
		block = 3;
		
		int[] _memoryArr = {-1, -1, -1};
		
		memoryArr = _memoryArr;
	}

}
