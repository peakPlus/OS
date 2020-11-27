package com.company.pageReplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FIFO {

    private int pageSize = 1024;

    private int jobSize = 1024 * 20;

    private int block = 3;

    private int[] memoryArr;

    private List<Integer> sortList = new ArrayList<>();

    private Scanner input = null;

    public FIFO(int pageSize, int jobSize, int block, int[] memoryArr, Scanner input) {

        this.pageSize = pageSize;

        this.jobSize = jobSize;

        this.block = block;

        this.memoryArr = memoryArr;

        this.input = input;

        init();
    }

    public void init() {

        System.out.println("输入要访问的逻辑地址<-1退出>：");

        while (input.hasNext()) {

            int address = input.nextInt();

            if (address == -1) return;

            else run(address);

            System.out.println("输入要访问的逻辑地址<-1退出>：");

        }

    }

    public void run(int address) {

        int pageNum = address / pageSize;

        int inMemoryIndex = -1;

        int nullMemoryIndex = -1;

        int blockNum;

        int _address;

        for (int i = 0; i < memoryArr.length; i++) {

            if (memoryArr[i] == pageNum) inMemoryIndex = i;

            if (memoryArr[i] == -1 && nullMemoryIndex == -1) nullMemoryIndex = i;

        }

        if (inMemoryIndex != -1) {

            System.out.println("页号：" + pageNum + "该页已在内存中！");

//            sortList.add(inMemoryIndex);

            blockNum = inMemoryIndex;

            _address = address % pageSize + blockNum * pageSize;

        } else {

            if (nullMemoryIndex != -1) {

                System.out.println("页号：" + pageNum + "该页不在内存中， 调入");

                memoryArr[nullMemoryIndex] = pageNum;

                sortList.add(nullMemoryIndex);

                blockNum = nullMemoryIndex;

                _address = address % pageSize + blockNum * pageSize;

            } else {

                System.out.println("页号：" + pageNum + "该页不在内存中， 调入");

                System.out.println("已无空闲物理块，置换！");

                memoryArr[sortList.get(sortList.size() - 1 - 2)] = pageNum;

                sortList.add(sortList.get(sortList.size() - 1 - 2));

                blockNum = sortList.get(sortList.size() - 1 - 2);

                _address = address % pageSize + blockNum * pageSize;

            }

        }

        System.out.printf("%-10s", "blockNum");

        System.out.printf("%-10s", "pageNum");

        System.out.println();

        for (int i = 0; i < memoryArr.length; i++) {

            System.out.printf("%-10s", i);

            System.out.printf("%-10s", memoryArr[i]);

            System.out.println();

        }

        System.out.println("块号是：" + blockNum + "物理地址是：" + _address);

    }

}
