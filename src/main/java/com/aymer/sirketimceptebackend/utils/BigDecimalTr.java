package com.aymer.sirketimceptebackend.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTr implements Serializable {

    public static int scale0 = 0;
    public static int scale2 = 2;
    public static int scale4 = 4;

    private BigDecimal value;

    public BigDecimalTr(BigDecimal value, int scale) {
        this.value = value.setScale(scale, RoundingMode.HALF_UP);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
        //return value.toString().replace(".",",");
    }

}
