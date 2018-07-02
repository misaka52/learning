package com.ysc.thinkinginjava;

interface Destination {
    String readLabel();
}

public class Outer {
    public Destination get(String dest, float price) {
        return new Destination() {
            private int cost;
            {
                cost = Math.round(price);
                if(cost > 100)
                    System.out.println("Over budget!");
            }
            private String label = dest;
            public String readLabel() { return label; }
        };
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Destination d = outer.get("Ysc", 231.43F);
        System.out.println(d.readLabel());
    }
}
