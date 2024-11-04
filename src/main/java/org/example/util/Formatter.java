package org.example.util;

import com.sun.javafx.binding.StringFormatter;

public class Formatter {
    public static String normalizeId(String s){
        StringBuilder newStr = new StringBuilder();
        for( int i = 0; i < s.length(); i++){
            if(!Character.isSpaceChar(s.charAt(i))){
                newStr.append(s.charAt(i));
            }
        }
        return newStr.toString().toUpperCase();
    }

    public static String normalizeName(String s){
        String[] word = s.split("\\s+");
        StringBuilder newStr = new StringBuilder();
        for(String w : word){
            newStr.append((w.charAt(0)+"").toUpperCase());
            newStr.append(w.substring(1).toLowerCase() + " ");
        }
        return newStr.toString().trim();
    }

    public static String normalizeDate(String s){
        String[] part = s.split("[/\\\\-]");
        if(part.length == 3 ){
            String year = part[0].trim();
            String month = part[1].trim();
            String day = part[2].trim();
            StringBuilder date = new StringBuilder();
            date.append(year);
            date.append("-");
            date.append(String.format("%02d", Integer.parseInt(month)));
            date.append("-");
            date.append(String.format("%02d", Integer.parseInt(day)));
            return date.toString();
        }
        return "";
    }
}
