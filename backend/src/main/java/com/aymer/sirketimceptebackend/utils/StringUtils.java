package com.aymer.sirketimceptebackend.utils;

import java.text.Collator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    private StringUtils() {
    }

    public static String leftPad(String str, int size, String padStr) {
        return org.apache.commons.lang.StringUtils.leftPad(str, size, padStr);
    }

    public static boolean isEmpty(String str) {
        return org.apache.commons.lang.StringUtils.isEmpty(str);
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase(new Locale("tr"));
    }

    public static String removeChar(String s, char c) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur != c) {
                stringBuilder.append(cur);
            }
        }
        return stringBuilder.toString();
    }

    public static String getFirstnCharacters(String str, int n) {
        if (str != null && str.length() > n) {
            return str.substring(0, n);
        }
        return str;
    }

    public static List<Long> commaDelimitedStringToLongList(String s) {
        String str = "";
        if (!s.isEmpty()) {
            str = s.replace(" ", "");

            List<Long> longArray = new ArrayList<Long>();
            String delimiter = ",";

            String[] temp = str.split(delimiter);

            for (String aTemp : temp) {
                longArray.add(Long.valueOf(aTemp));
            }

            return longArray;
        } else {
            return null;
        }
    }

    public static List<String> commaDelimitedStringToList(String s) {
        String str = "";
        if (!s.isEmpty()) {
            str = s.replace(" ", "");

            List<String> stringArray = new ArrayList<String>();
            String delimiter = ",";

            String[] temp = str.split(delimiter);

            Collections.addAll(stringArray, temp);

            return stringArray;
        } else {
            return null;
        }
    }

    public static List<String> anahtarKelimeParser(String anahtarKelime) {
        String anahtarKelimeNew = anahtarKelime;
        List<String> stringList = new ArrayList<String>();
        if (!anahtarKelime.trim().isEmpty()) {
            String regex = "\"(.+?)\"";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(anahtarKelime);

            while (m.find() && m.groupCount() > 0) {
                String s = m.group(1);
                anahtarKelimeNew = anahtarKelimeNew.substring(0, anahtarKelimeNew.indexOf(s)) + anahtarKelimeNew.substring(anahtarKelimeNew.indexOf(s) + s.length());
                if (!s.trim().isEmpty()) {
                    stringList.add(s.trim());
                }
            }

            if (!anahtarKelimeNew.trim().isEmpty()) {
                StringTokenizer stringTokenizer = new StringTokenizer(anahtarKelimeNew, " \t\n\r\f\"\\");
                while (stringTokenizer.hasMoreTokens()) {
                    stringList.add(stringTokenizer.nextToken().trim());
                }
            }


        }
        return stringList;
    }

    public static boolean ozelKarakterlerVarMi(String string) {
        return string.contains("'") || string.contains("&") || string.contains("£") || string.contains("½") || string.contains("%") || string.contains("$") || string.contains("^") || string.contains("!");
    }

    public static String withoutDiacritics(String s) {
        String s2 = Normalizer.normalize(s, Normalizer.Form.NFD);
        return s2.replaceAll("(?s)\\p{InCombiningDiacriticalMarks}", "").replace((char) 304, (char) 73).replace((char) 305, (char) 105);
    }

    public static int compareWithLocale(String arg1, String arg2) {
        Collator usCollator = Collator.getInstance(new Locale("tr", "TR")); //Your locale here
        usCollator.setStrength(Collator.PRIMARY);
        return usCollator.compare(arg1, arg2);
    }

    public static String formatDoubleAsPara(Double tutar) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(otherSymbols);
        return df.format(tutar);
    }

    public static String quoteStringForSQL(String text) {

        return "'" + (text != null ? text.replace("'", "''") : "") + "'";

    }


    public static String mask(String str, int startIndex, int endIndex, char mask) {
        if (str.length() < startIndex || str.length() < endIndex) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = startIndex; i < endIndex; i++) {
            stringBuilder.setCharAt(i, mask);
        }
        return stringBuilder.toString();
    }


}
