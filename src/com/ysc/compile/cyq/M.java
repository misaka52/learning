package com.ysc.compile.cyq;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class M {
    public static void main(String[] args) throws IOException{

        RandomAccessFile randomFile = new RandomAccessFile("D://output.txt", "rw");
        randomFile.setLength(0);//Çå¿ÕÉÏ´ÎÔËÐÐ³ÌÐòÊ±ÌîÈëµÄÊý¾Ý
        RandomAccessFile randomAccessFile=new RandomAccessFile("D://idlist.txt", "rw");
        randomAccessFile.setLength(0);
        RandomAccessFile randomAccessFile2=new RandomAccessFile("D://uintlist.txt", "rw");
        randomAccessFile2.setLength(0);
        randomAccessFile.close();
        randomAccessFile2.close();
        randomFile.close();
        String pathname = "D://input.txt"; //
        File filename = new File(pathname); // Òª¶ÁÈ¡ÒÔÉÏÂ·¾¶µÄinput.txtÎÄ¼þ
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(
                    new FileInputStream(filename));

            BufferedReader br = new BufferedReader(reader);
            Machine machine=new Machine();
            String text = "";

            String line = br.readLine();
            while(line != null) {
                text += line;
                line = br.readLine();
            }

            try {
                machine.analyseLex(text);
                machine.printResult();
                machine.printToScreen();
                machine.printId();
                machine.printNum();

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } // ½¨Á¢Ò»¸öÊäÈëÁ÷¶ÔÏóreader


            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}