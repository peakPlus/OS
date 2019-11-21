package com.company.dynamicPartition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memory {

    private static List<Partition> pList = new ArrayList<Partition>();

    private static int maxAddress = 1024;

    private static int minPartitionSize = 2;

    public static void main(String[] args) {
        pListInit();
        init();
        command();
    }


    public static void init() {

        System.out.println("1. 分配内存");

        System.out.println("2. 回收内存");

        System.out.println("3. 显示内存使用情况");

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

                    System.out.println("作业名: ");
                    // 分配作业名
                    String allocatedName = input.next();

                    System.out.println("作业大小: ");
                    // 分配作业大小
                    int size = input.nextInt();

                    if (allocatedMemory(allocatedName, size))

                        System.out.println("分配成功");

                    else

                        System.out.println("分配失败");

                    printPartition();

                    break;

                case 2:

                    System.out.println("作业名: ");
                    // 回收作业名
                    String recoveryName = input.next();

                    if (recoveryMemory(recoveryName))

                        System.out.println("回收成功");

                    else

                        System.out.println("回收失败");

                    printPartition();

                    break;

                case 3:

                    printPartition();

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

    public static void pListInit() {

        pList.clear();

        pList.add(new Partition(null, 20, 0, false));

        pList.add(new Partition("A", 12, 20, true));

        pList.add(new Partition("B", 32, 32, true));

        pList.add(new Partition("C", 64, 64, true));

        pList.add(new Partition("D", 128, 128, true));

        pList.add(new Partition(null, maxAddress - 256, 256, false));

    }

    public static boolean allocatedMemory(String name, int partitionSize) {

        boolean flag = false;

        for (int i = 0; i < pList.size(); i++) {

            if (pList.get(i).isStatus() == false) {

                if (pList.get(i).getSize() >= partitionSize) {

                    if (pList.get(i).getSize() - partitionSize <= minPartitionSize) {

                        pList.get(i).setName(name);

                        pList.get(i).setStatus(true);

                    } else {

                        pList.add(i + 1, new Partition(null, pList.get(i).getSize() - partitionSize,
                                pList.get(i).getStartAddress() + partitionSize, false));

                        pList.get(i).setName(name);

                        pList.get(i).setSize(partitionSize);
                    }

                    flag = true;

                    break;

                }

            }

        }

        return flag;

    }

    public static boolean recoveryMemory(String name) {

        boolean flag = false;

        int index = -1;

        for (int i = 0; i < pList.size(); i++) {

            if (pList.get(i).isStatus() == true) {

                if (pList.get(i).getName().equals(name)) {

                    index = i;

                    pList.get(i).setName(null);

                    pList.get(i).setStatus(false);

                    break;

                }

            }

        }

        if (index != -1) {

            flag = true;

            boolean before = true;

            boolean after = true;

            if (index == 0)

                before = false;

            if (index == pList.size())

                after = false;

            if (before) {

                if (pList.get(index - 1).isStatus() == false) {

                    pList.get(index - 1).setSize(pList.get(index - 1).getSize() + pList.get(index).getSize());

                    pList.remove(index);

                    index--;

                }

            }

            if (after) {

                if (pList.get(index + 1).isStatus() == false) {

                    pList.get(index).setSize(pList.get(index + 1).getSize() + pList.get(index).getSize());

                    pList.remove(index + 1);

                }

            }

        }

        return flag;
    }

    public static void printPartition() {

        System.out.printf("%-10s", "id");

        System.out.printf("%-10s", "name");

        System.out.printf("%-14s", "startAddress");

        System.out.printf("%-10s", "size");

        System.out.printf("%-10s", "status");

        System.out.println();

        for (int i = 0; i < pList.size(); i++) {

            System.out.printf("%-10s", i + 1);

            System.out.printf("%-10s", pList.get(i).getName());

            System.out.printf("%-14s", pList.get(i).getStartAddress());

            System.out.printf("%-10s", pList.get(i).getSize());

            System.out.printf("%-10s", pList.get(i).isStatus());

            System.out.println();

        }
    }
}
