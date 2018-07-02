package com.ysc.compile;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static int[][] num = new int[1005][1005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger n;
        n = sc.nextBigInteger(2);
        BigInteger a, t;
        a = f(BigInteger.ZERO, n);
        //System.out.print(a + " ");
        if(n.compareTo(BigInteger.ONE) >= 0) {
            t = f(BigInteger.ONE, n);
            //System.out.print(t + " ");
            a = a.max(t);
            t = f(BigInteger.ZERO, n.subtract(BigInteger.ONE));
            //System.out.print(t + " ");
            a = a.max(t);
            t = f(BigInteger.ONE, n.subtract(BigInteger.ONE));
            //System.out.println(t);
            a = a.max(t);
        }
        System.out.println(a);
    }

    static BigInteger f(BigInteger l, BigInteger r) {
        if (l.compareTo(r) >= 0)
            return BigInteger.ZERO;
        BigInteger t = r.subtract(lowbit(r));
        if (t.compareTo(l) >= 0) {
            //return num[l][r - lowbit(r)] + 1;
            return f(l, t).add(BigInteger.ONE);
        }
        //return num[l][r - 1];
        return f(l, r.subtract(BigInteger.ONE));
    }

    static BigInteger lowbit(BigInteger r) {
        String str = r.toString(2);
        StringBuilder sb = new StringBuilder();
        sb.append('1');
        int i = str.length() - 1;
        for (int k = 1; i >= 0; ++k, --i) {
            if (str.charAt(i) == '1')
                return new BigInteger(sb.toString(), 2);
            sb.append('0');
        }
        return BigInteger.ZERO;
    }
}
