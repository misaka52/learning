package com.ysc.compile;

import java.util.Scanner;

public class test1 {
    static int[][] num = new int[10005][10005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
       // System.out.println(lowbit(8));
        while(sc.hasNext()) {
            for (int i = 0; i <= 1000; ++i)
                for (int j = 0; j <= 1000; ++j)
                    num[i][j] = 0;
            n = sc.nextInt(2);
            int Max, tx, ty;
            Max = 0;
            tx = ty = 0;
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= n; ++j) {
                    num[i][j] = f(i, j);
                    if (num[i][j] > Max) {
                        Max = num[i][j];
                        tx = i;
                        ty = j;
                    }
                    System.out.print(String.format("%3d", num[i][j]));
                }
                System.out.println();
            }
            System.out.println(tx + "," + ty);
        }
    }

    static int f(int l, int r) {
        if (l >= r)
            return 0;
        else if (r - lowbit(r) >= l) {
            return num[l][r - lowbit(r)] + 1;
            //return f(l, r - lowbit(r)) + 1;
        }
        else
            return num[l][r - 1];
        //return f(l, r - 1);
    }

    static int lowbit(int r) {
        return r & (-r);
    }
}
