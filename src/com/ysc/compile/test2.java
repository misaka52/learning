package com.ysc.compile;

import javafx.util.Pair;

import java.util.*;
/**
 * 实验要求：
 (1) 计算文法G[E]的FIRST集
 (2) 计算文法G[E]的FOLLOW集
 (3) 构造文法G[E]的LL(1)分析表
 这里用$代替空元素
 * 样例输入：
 E->TA
 A->+TA|$
 T->FB
 B->*FB|$
 F->(E)|i
 #
 */

public class test2 {
    static List<Pair<Character, String>> grammar = new ArrayList<>();
    static LinkedHashMap<Character, LinkedHashSet<Character>> first = new LinkedHashMap<>();
    static LinkedHashMap<Character, LinkedHashSet<Character>> follow = new LinkedHashMap<>();
    static String[][] LL1 = new String[50][50];
    static LinkedHashMap<Character, Integer> VtChars = new LinkedHashMap<>();
    static LinkedHashMap<Character, Integer> VnChars = new LinkedHashMap<>();
    public static void main(String[] args) {
        input();
        getFirst();
        getFollow();
        getLL1();
        print();
    }

    static void print() {
        printHashMap(first, "first");
        printHashMap(follow, "follow");
        printLL1();
    }

    static void printHashMap(LinkedHashMap<Character, LinkedHashSet<Character>> hashMap, String name) {
        System.out.println("--------" + name + "--------");
        for (Character ch : hashMap.keySet()) {
            System.out.print(ch.toString() + "=");
            Set<Character> tmp = hashMap.get(ch);
            for (Character ch2 : tmp)
                System.out.print(ch2.toString() + " ");
            System.out.println();
        }
    }

    static void printLL1() {
        System.out.println("--------LL1--------");
        StringBuilder row = new StringBuilder(solveString(null));
        for(Character ch : VtChars.keySet())
            row.append('|' + solveString(ch.toString()));
        System.out.println(row.toString());
        printStriping();
        int i = -1;
        for (Character ch : VnChars.keySet()) {
            ++i;
            row = new StringBuilder(solveString(ch.toString()));
            for (int j = 0; j < VtChars.size(); ++j) {
                row.append('|' + solveString(LL1[i][j]));
            }
            System.out.println(row.toString());
            printStriping();
        }
    }

    static String solveString(String string) {
        //将字符串处理成长度为10的，不足补空格
        StringBuilder output = new StringBuilder();
        if(string != null) {
            int diff = 10 - string.length();
            for (int i = 0; i < diff / 2; ++i)
                output.append(' ');
            output.append(string);
            for (int i = 0; i < (diff - diff / 2); ++i)
                output.append(' ');
        } else {
            for (int i = 0; i < 10; ++i)
                output.append(' ');
        }
        return output.toString();
    }

    static void printStriping() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= VtChars.size(); ++i) {
            for(int j = 0; j < 10; ++j)
                sb.append('-');
            if(i < VtChars.size())
                sb.append('|');
        }
        System.out.println(sb.toString());
    }

    static void input() {
        Scanner scanner = new Scanner(System.in);
        String str;
        Character key;
        String value;
        StringBuilder sb;
        while(scanner.hasNext()) {
            str = scanner.next();
            if(str.equals("#"))
                break;
            sb = new StringBuilder();
            int i;
            key = str.charAt(0);
            for(i = 0; i < str.length() && str.charAt(i) != '-'; ++i)
                sb.append(str.charAt(i));

            i += 2;
            while(i < str.length()) {
                sb = new StringBuilder();
                while(i < str.length() && str.charAt(i) != '|') {
                    sb.append(str.charAt(i++));
                }
                ++i;
                value = sb.toString();
                grammar.add(new Pair<>(key, value));
            }
        }
    }

    //两趟求出所有First集
    static void getFirst() {
        Pair<Character, String> pair;
        for(int k = 0; k < 10; ++k) {
            for (int i = 0; i < grammar.size(); ++i) {
                pair = grammar.get(i);
                addToFirst(pair.getKey(), pair.getValue());
            }
        }
    }

    static void addToFirst(Character key, String value) {
        LinkedHashSet<Character> valueSet = first.get(key);
        if(valueSet == null)
            valueSet = new LinkedHashSet<>();
        if(isVt(value.charAt(0)))        //首字符是终结符
            valueSet.add(value.charAt(0));
        else {
            int k = 0;
            LinkedHashSet<Character> tmpSet;
            while(k < value.length()) {
                if(isVt(value.charAt(k))) {
                    valueSet.add(value.charAt(k));
                    break;
                }
                if(!first.containsKey(value.charAt(k)))
                    break;
                tmpSet = first.get(value.charAt(k));
                addToSet(tmpSet, valueSet);
                if(!tmpSet.contains('$'))
                    break;
                ++k;
            }
            if(k == value.length())         //k到字符串尾说明value里的所以非终结符均有空元素$,此时应额外添加$
                valueSet.add('$');
        }
        first.put(key, valueSet);
    }

    static boolean isVt(Character ch) {
        return ch < 'A' || ch > 'Z';
    }

    static void addToSet(Set<Character> original, Set<Character> destination) {
        for(Character ch : original) {
            if(ch != '$')
                destination.add(ch);
        }
    }

    static void getFollow() {
        LinkedHashSet<Character> tmp = new LinkedHashSet<>();
        tmp.add('#');
        follow.put(grammar.get(0).getKey(), tmp);
        Pair<Character, String> pair;
        for(int k = 0; k < 10; ++k) {
            for (int i = 0; i < grammar.size(); ++i) {
                pair = grammar.get(i);
                addToFollow(pair.getKey(), pair.getValue());
            }
        }
    }

    static void addToFollow(Character key, String value) {
        LinkedHashSet<Character> valueSet = follow.get(key);
        if(valueSet == null)
            valueSet = new LinkedHashSet<>();
        for(int k = 0; k < value.length(); ++k) {
            if(isVt(value.charAt(k)))
                continue;
            LinkedHashSet<Character> tmp = follow.get(value.charAt(k));
            if(tmp == null)
                tmp = new LinkedHashSet<>();
            if(k < value.length() - 1) {
                //情况2, 将first(str[k + 1])加入到follow(str[k])中
                if(isVt(value.charAt(k + 1)))
                    tmp.add(value.charAt(k + 1));
                else {
                    LinkedHashSet<Character> tmp2 = first.get(value.charAt(k + 1));
                    addToSet(tmp2, tmp);
                }
            }
            if (k == value.length() - 1 ||
                    (k < value.length() - 1 && !isVt(value.charAt(k + 1)) && first.get(value.charAt(k + 1)).contains('$'))) {
                //情况3，将follow(key)加入到follow(str[k])中
                addToSet(valueSet, tmp);
            }
            follow.put(value.charAt(k), tmp);
        }
    }

    static void getLL1() {
        //先获取所有的终结符和非终结符即他们在表中对应的下标，存在
        int i = -1;
        int j = -1;
        for(Pair<Character, String> pair : grammar) {
            if(!VnChars.containsKey(pair.getKey()))
                VnChars.put(pair.getKey(), ++i);
            String str = pair.getValue();

            for(int k = 0; k < str.length(); ++k)
                if(isVt(str.charAt(k)) && str.charAt(k) != '$' && !VtChars.containsKey(str.charAt(k)))
                    VtChars.put(str.charAt(k), ++j);
        }
        VtChars.put('#', ++j);      //多处一个#

        for(Pair<Character, String> pair : grammar) {
            int x = VnChars.get(pair.getKey());
            String strVal;

            //公式2, 对所有的First(key)里的元素a, 往表中加入M[key, a] = str, 其中str为首字母是a的候选式或key能推出的唯一候选式
            LinkedHashSet<Character> tmp = first.get(pair.getKey());
            List<String> keyList = new ArrayList<>();           //keyList表示key对应的所以候选式的值
            for (Pair<Character, String> pair2 : grammar) {
                if (pair2.getKey().equals(pair.getKey())) {
                    keyList.add(pair2.getValue());
                }
            }

            if (keyList.size() == 1) {
                strVal = pair.getKey() + "->" + keyList.get(0);
                for (Character ch : tmp) {
                    int y = VtChars.get(ch);
                    LL1[x][y] = strVal;
                }
            } else {
                for (String s : keyList) {
                    strVal = pair.getKey() + "->" + s;
                    for (Character ch : tmp) {
                        if (s.charAt(0) == ch && ch != '$') {
//                            System.out.println(ch);
                            int y = VtChars.get(ch);
                            LL1[x][y] = strVal;
                            break;
                        }
                    }
                }
            }

            //公式3，若$属于First(key), 对于任何属于Follow(key)的终结符b, 将key->$填入M[key, b]中
            if(first.get(pair.getKey()).contains('$')) {
                strVal = pair.getKey() + "->$";
                Set<Character> setFollow = follow.get(pair.getKey());
                for (Character ch : setFollow) {
                    int y = VtChars.get(ch);
                    LL1[x][y] = strVal;
                }
            }
        }
    }
}