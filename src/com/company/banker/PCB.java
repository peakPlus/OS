package com.company.banker;

import java.util.Scanner;

public class PCB {

    private int resourceLen = 3;

    private int processLen = 5;

    private String[] Resource;

    private int[] Available;

    private int[][] Max;

    private int[][] Allocation;

    private int[][] Need;

    private boolean[] Finish;

    private boolean isPrintTableHead = true;

    public PCB() {

    }

    public void init() {

        String[] Resource = {"A", "B", "C", "D", "E"};

        int[] Available = {3, 3, 2};

        int[][] Max = {{7, 5, 3}, {3, 2, 2}, {9, 0, 2}, {2, 2, 2}, {4, 3, 3}};

        int[][] Allocation = {{0, 1, 0}, {2, 0, 0}, {3, 0, 2}, {2, 1, 1}, {0, 0, 2}};

        int[][] Need = {{7, 4, 3}, {1, 2, 2}, {6, 0, 0}, {0, 1, 1}, {4, 3, 1}};

        boolean[] Finish = {false, false, false, false, false};

        this.Resource = Resource;

        this.Available = Available;

        this.Max = Max;

        this.Allocation = Allocation;

        this.Need = Need;

        this.Finish = Finish;

    }

    public void show() {

        System.out.printf("%3s", "");

        System.out.printf("%10s", "Max|");

        System.out.printf("%10s", "Allo|");

        System.out.printf("%10s", "Need|");

        System.out.printf("%9s", "Aval");

        System.out.println();

        for (int i = 0; i < processLen; i++) {

            System.out.printf("%-3s", Resource[i]);

            for (int j = 0; j < resourceLen; j++) {

                System.out.printf("%3s", Max[i][j]);

            }
            System.out.printf("|");

            for (int j = 0; j < resourceLen; j++) {

                System.out.printf("%3s", Allocation[i][j]);

            }

            System.out.printf("|");

            for (int j = 0; j < resourceLen; j++) {

                System.out.printf("%3s", Need[i][j]);

            }

            System.out.printf("|");

            if (i == 0)

                for (int j = 0; j < resourceLen; j++) {

                    System.out.printf("%3s", Available[j]);

                }

            System.out.println();

        }

    }

    public void requestResource(Scanner input) {

//        int processN = 0;

//        int[] requestResArr = {1, 2, 2};

        System.out.println("请输入请求资源号：");

        int processN = input.nextInt();

        System.out.println("请输入请求资源数：");

        int[] requestResArr = {input.nextInt(), input.nextInt(), input.nextInt()};

        if (!isOverflowAval(requestResArr)) {

            System.out.println("请求资源数大于Available数量，分配失败");

        } else if (!isBiggerNeedRes(requestResArr, processN)) {

            System.out.println("请求资源数大于Need数量，分配失败");

        } else {

            for (int i = 0; i < resourceLen; i++) {

                Available[i] -= requestResArr[i];

                Need[processN][i] -= requestResArr[i];

                Allocation[processN][i] += requestResArr[i];

            }

            if (!isSafeAllocation()) {

                for (int i = 0; i < resourceLen; i++) {

                    Available[i] += requestResArr[i];

                    Need[processN][i] += requestResArr[i];

                    Allocation[processN][i] -= requestResArr[i];

                }

                System.out.println("分配后系统将进入不安全状态，分配失败");

            } else {

                System.out.println("资源分配成功");

            }

        }

    }

    public boolean isOverflowAval(int[] requestResArr) {

        boolean flag = true;

        for (int i = 0; i < resourceLen; i++) {

            if (requestResArr[i] > Available[i]) {

                flag = false;

                break;

            }

        }

        return flag;
    }

    public boolean isBiggerNeedRes(int[] requestResArr, int processN) {

        boolean flag = true;

        for (int i = 0; i < resourceLen; i++) {

            if (requestResArr[i] > Need[processN][i]) {

                flag = false;

                break;

            }

        }

        return flag;

    }

    public boolean isSafeAllocation() {

        int[] Work = new int[resourceLen];

        for (int i = 0; i < resourceLen; i++) {

            Work[i] = Available[i];

        }

        int n = 0;

        for (int k = 0; k < processLen; k++) {

            for (int i = 0; i < processLen; i++) {

                if (Finish[i] == false) {

                    if (isCanRecoverRes(Work, i)) {

                        n++;

                        Finish[i] = true;

                        printAvailableProcess(Work, i);

                        for (int j = 0; j < resourceLen; j++) {
                            Work[j] += Allocation[i][j];
                        }

                        break;

                    }

                }

            }

        }

        return n == processLen;

    }

    public boolean isCanRecoverRes(int[] Work, int processN) {

        boolean flag = true;

        for (int i = 0; i < resourceLen; i++) {

            if (Max[processN][i] > Work[i] + Allocation[processN][i]) {

                flag = false;

                break;

            }

        }

        return flag;
    }

    public void printAvailableProcess(int[] Work, int i) {

        if (isPrintTableHead) {

            System.out.printf("%3s", "");

            System.out.printf("%10s", "Work|");

            System.out.printf("%10s", "Need|");

            System.out.printf("%10s", "Allo|");

            System.out.printf("%10s", "W+A|");

            System.out.printf("%9s", "Finish");

            System.out.println();

            isPrintTableHead = false;

        }

        System.out.printf("%-3s", Resource[i]);

        for (int j = 0; j < resourceLen; j++) {

            System.out.printf("%3s", Work[j]);
        }

        System.out.printf("|");
        for (int j = 0; j < resourceLen; j++) {

            System.out.printf("%3s", Need[i][j]);
        }

        System.out.printf("|");
        for (int j = 0; j < resourceLen; j++) {

            System.out.printf("%3s", Allocation[i][j]);

        }

        System.out.printf("|");
        for (int j = 0; j < resourceLen; j++) {

            System.out.printf("%3s", Work[j] + Allocation[i][j]);

        }

        System.out.printf("|");

        System.out.printf("%9s", Finish[i]);

        System.out.println();
    }
}
