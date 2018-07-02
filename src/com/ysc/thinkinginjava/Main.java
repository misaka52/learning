package com.ysc.thinkinginjava;

import com.ysc.thinkinginjava.util.Demo;
import com.ysc.thinkinginjava.util.CommonTool;
import java.util.*;

class comparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}

class A {

}
class subA extends A {}

public class Main {
    private static Random random = new Random(47);
    public <T> void f(T a) {
        System.out.println(a.getClass().getSimpleName());
    }
    public <A, B, C> void f(A t1, B t2, C t3) {
        System.out.println(t1.getClass().getSimpleName() + "," + t2.getClass().getSimpleName() + "," + t3.getClass().getSimpleName() );
    }
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("ttt", 1);
        map.put("fdsaf", 2);
        CommonTool.print(map);
        System.out.println(map);
    }

    private static void testHashSet() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i += 132)
            set.add(i);
        printSet(set);

        for (int i = 0; i < 10; ++i)
            set.add(i);
        printSet(set);
    }

    private static void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        ListIterator<String> listIt = list.listIterator();
        for (Integer i = 0; i < 3; ++i) {

            listIt.add(i.toString());
            listIt.previous();
        }
        printList(list);
    }

    private static void printList(List<String> list) {
        for (String s : list)
            System.out.println(s);
        System.out.println();
    }

    private static void printSet(Set<Integer> set) {
        for (Integer i : set)
            System.out.print(i);
        System.out.println();
    }

    static void func4() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("boy");
        list.add("cat");
        list.add("dog");
//        Iterator<String> it = list.iterator();
//        System.out.println(it);
//        while(it.hasNext()) {
//            System.out.println(it.next());
//            it.remove();
//        }
        ListIterator<String> listIt = list.listIterator();
        System.out.println(listIt);
        while(listIt.hasNext())
            System.out.println(listIt.next());
        System.out.println();
        System.out.println(listIt);

        while(listIt.hasPrevious())
            System.out.println(listIt.previous());
    }

    static void func3() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("boy");
        list.add("cat");
        list.add("dog");
        List<String> subList = list.subList(1, 2);
        for (String s : subList)
            System.out.println(s);
        System.out.println();
        list.removeAll(subList);
//        System.out.println(subList);
//        if(subList != null)
//            for (String s : subList)
//                System.out.println(s);

    }

    static void func2() {
        IdentityHashMap<String, Integer> hashMap = new IdentityHashMap<>();
        hashMap.put(new String("ysc"), 1);
        hashMap.put(new String("ysc"), 2);
        hashMap.put("f", 2);

        for (Map.Entry<String, Integer> entry : hashMap.entrySet())
            System.out.println(entry.getKey() + "," + entry.getValue());
        System.out.println(hashMap.containsKey("ysc") + ", " + hashMap.get("ysc"));
    }

    static void func1() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);

        List<Integer> list2 = new ArrayList<>(Arrays.asList(4));
//        Collections.addAll(list2, 5, 6);
        list2.addAll(list1);
        for (Integer i : list2)
            System.out.println(i);
        TreeSet<String> tm = new TreeSet<>(new comparator());
        tm.add("cat");
        tm.add("apple");
        tm.add("bo");
        tm.add("d");
        for (String s : tm)
            System.out.println(s);
    }
}
