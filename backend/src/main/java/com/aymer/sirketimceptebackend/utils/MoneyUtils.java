package com.aymer.sirketimceptebackend.utils;

import com.aymer.sirketimceptebackend.common.constants.IConstants;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtils {
    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getInstance(IConstants.LOCALE_TR).format(n);
    }
}
