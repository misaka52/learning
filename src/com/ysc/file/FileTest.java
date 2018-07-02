package com.ysc.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FileTest {
    public static void main(String[] args) {
        String[] suffixNames = new String[]{"defaul.java", "txt"};
        File path = new File("../");
        String[] listAll = path.list();
        System.out.println("------listAll------");
        if(listAll != null)
            for(String s : listAll)
                System.out.println(s);
        String[] list = path.list(new DirFilter(suffixNames[0]));
        System.out.println("--------list--------");
        if(list != null)
            for(String s : list)
                System.out.println(s);
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}