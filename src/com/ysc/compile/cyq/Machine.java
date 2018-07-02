package com.ysc.compile.cyq;

import java.awt.print.Printable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.util.*;

public class Machine {//输入一个单词，输出相应判断，如果必要的话加入相应表中

    public List<TableItem> resultList;
    public String token;
    public List<TableItem> reserveTable;//C语言的保留字表
    public Machine(){
        //只需要将保留字表初始化
        reserveTable=new ArrayList<TableItem>();
        reserveTable.add(new TableItem("res", "while"));
        reserveTable.add(new TableItem("res", "if"));
        reserveTable.add(new TableItem("res", "else"));
        reserveTable.add(new TableItem("res", "switch"));
        reserveTable.add(new TableItem("res", "case"));
        //reserveTable.add(new TableItem("id"));
        //reserveTable.add(new TableItem("num"));这两个以后用到了现加
        reserveTable.add(new TableItem("op", "+"));
        reserveTable.add(new TableItem("op", "-"));
        reserveTable.add(new TableItem("op", "*"));
        reserveTable.add(new TableItem("="));
        reserveTable.add(new TableItem(";"));
        reserveTable.add(new TableItem("rop", "<="));
        reserveTable.add(new TableItem("rop", "=="));
        reserveTable.add(new TableItem("rop", "<"));

    }
    public void printId() throws IOException{//将表中id保存成txt
        RandomAccessFile randomFile = new RandomAccessFile("D://idlist.txt", "rw");

        long fileLength = randomFile.length();
        HashSet<String> idList = new LinkedHashSet<>();
        for (TableItem tableItem : resultList) {
            if ("id".equals(tableItem.type)) {
                idList.add(tableItem.value);
            }
        }
        System.out.println("idList:---------------");
        for(String s : idList) {
            System.out.println(s);
            fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(s+"\r\n");
        }
    }
    public void printToScreen(){
        for (TableItem tableItem : resultList) {
            System.out.println("(" + tableItem.type+", "+tableItem.value+")\r\n");
        }
    }
    public void printNum() throws IOException
    {
        RandomAccessFile randomFile = new RandomAccessFile("D://uintlist.txt", "rw");

        long fileLength = randomFile.length();
        HashSet<String> numList = new LinkedHashSet<>();

        for (TableItem tableItem : resultList) {
            if ("num".equals(tableItem.type)) {
                numList.add(tableItem.value);
            }
        }
        for(String s : numList) {
            fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(s +"\r\n");
        }
    }
    public void printResult() throws IOException{
        RandomAccessFile randomFile = new RandomAccessFile("D://output.txt", "rw");
        // 文件长度，字节数
        long fileLength = randomFile.length();
        for (TableItem tableItem : resultList) {

            fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes("(" + tableItem.type+", "+tableItem.value+")\r\n");
        }
        randomFile.close();
    }
    public void error(){
        System.out.println("error");
    }
    public int reserve(){
        int i=0;
        for (TableItem tableItem : reserveTable) {



            String value= tableItem.value;
            String type=tableItem.type;
            i++;
            if (token.equals(value)&&"res".equals(type)) {
                return  i;
            }
        }
        return 0;
    }
    public boolean AlreadyHave()//表中有没有该项，不管是id还是res
    {

        for (TableItem tableItem : reserveTable) {



            String value= tableItem.value;

            if (token.equals(value)) {
                return  true;
            }
        }
        return false;
    }
    public void analyseLex(String string)//一行一行的分析字符串
    {

        resultList=new ArrayList<TableItem>();//存储分析结果的集合,每一项为一个二元组
        char character;
        token=null;
        int length= string.length();
        for (int i = 0; i < length; i++) {//一直循环到string结束
            token="";
            character=string.charAt(i);//将token和character重置
            switch (character) {
                case' ':
                    continue;//空格代表之前的识别已经结束，进入下一个单词的识别
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z'://标识符识别的入口
                    while (Character.isLetter(character)||Character.isDigit(character)) {
                        //把s中字符送入token数组
                        token+=character;
                        i++;
                        if (i>=length) {
                            break;
                        }
                        character=string.charAt(i);
                    }

                    i--;
                    character=string.charAt(i);//扫描指针回退一个字符
                    int isReserved=reserve();

                    if (isReserved==0) {//该单词为标识符
                        TableItem tableItem=new TableItem("id",token);
                        if (!AlreadyHave()) {
                            reserveTable.add(tableItem);
                            //把标识符登陆到符号表
                        }

                        resultList.add(tableItem);
                    }
                    else {
                        TableItem tableItem=new TableItem("res",token);
                        resultList.add(tableItem);
                    }

                    continue;//一个单词分析完了，循环继续分析后面的单词
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    while (Character.isDigit(character)) {
                        token+=character;
                        i++;
                        if (i>=length) {
                            break;
                        }
                        character=string.charAt(i);


                    }
                    if (Character.isLetter(character)) {
                        token+=character;
                        resultList.add(new TableItem("error",token));//数字后面不能紧跟字母
                        continue;
                    }
                    i--;
                    character=string.charAt(i);//确认紧跟的不是字母后扫描指针回退一个字符
                    TableItem tableItem=new TableItem("num",token);
                    reserveTable.add(tableItem);
                    //把常数登录到常数表
                    resultList.add(tableItem);
                    continue;
                case '+':
                    resultList.add(new TableItem("op","+"));
                    continue;
                case '-':
                    resultList.add(new TableItem("op","-"));
                    continue;
                case '*':
                    resultList.add(new TableItem("op","*"));
                    continue;
                case'<':
                    i++;//指针后移必须考虑下标越界
                    if (i>=length) {
                        i--;//<是最后一个字符，词法分析不用报错
                    }
                    character=string.charAt(i);
                    if (character=='=') {
                        resultList.add(new TableItem("relop","LE"));
                    }
                    else {
                        i--;
                        character=string.charAt(i);
                        resultList.add(new TableItem("relop","LT"));
                    }
                    continue;
                case'=':
                    i++;
                    if (i>=length) {
                        i--;
                        character=string.charAt(i);
                        resultList.add(new TableItem("="," "));
                        continue;
                    }
                    character=string.charAt(i);
                    if (character=='=') {
                        resultList.add(new TableItem("relop","EQ"));
                    }
                    else {
                        i--;
                        character=string.charAt(i);
                        resultList.add(new TableItem("="," "));
                    }
                    continue;
                case ';':
                    resultList.add(new TableItem(";"," "));
                    continue;
                case '(':
                    resultList.add(new TableItem("("," "));
                    continue;
                case ')':
                    resultList.add(new TableItem(")"," "));
                    continue;

                case '>':
                    resultList.add(new TableItem(">"," "));
                    continue;
                case '#':
                    resultList.add(new TableItem("#"," "));
                    continue;
                case '{':
                    resultList.add(new TableItem("{"," "));
                    continue;
                case '}':
                    resultList.add(new TableItem("}"," "));
                    continue;
                case '[':
                    resultList.add(new TableItem("(["," "));
                    continue;
                case ']':
                    resultList.add(new TableItem("]"," "));
                    continue;
                default:
                    resultList.add(new TableItem("error","token"));
            }
        }

    }
}
