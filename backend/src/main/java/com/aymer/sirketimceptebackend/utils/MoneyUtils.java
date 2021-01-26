package com.aymer.sirketimceptebackend.utils;

import com.aymer.sirketimceptebackend.common.constants.IConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtils {
    private static final int scale = 2;


    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getInstance(IConstants.LOCALE_TR).format(n);
    }

    public static Double getRoundedValue(Double d, int scale) {
        BigDecimal bd = new BigDecimal(d != null ? d : 0);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Double getRoundedValue(Double d) {
        return getRoundedValue(d, scale);

    }

    public static Double getRoundedValue(BigDecimal d) {
        return getRoundedValue(d, scale);
    }

    public static Double getRoundedValue(BigDecimal bd, int scale) {
        bd = new BigDecimal(String.valueOf(bd != null ? bd : 0));
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Long getLongValueFromDouble(Double d) {
        BigDecimal bd = new BigDecimal(d != null ? MoneyUtils.getRoundedValue(d) : 0);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.multiply(new BigDecimal(100)).longValue();
    }
}
