package com.aymer.sirketimceptebackend.common.constants;

import java.util.Locale;

public interface IConstants {
    public static final Locale LOCALE_TR = new Locale("tr", "TR");
    public static final String UTF_8 = "UTF-8";

    // ODEME TIPI
    public static final String NAKIT = "NAKIT";
    public static final String CEK = "CEK";
    public static final String SENET = "SENET";
    public static final String KREDI_KARTI = "KREDI_KARTI";

    // file download
    public static final String FILE_DOWNLOAD_MANAGER_SERVLET_ID_PARAM			= "id";
    public static final String FILE_DOWNLOAD_MANAGER_SERVLET_CLASS_PARAM		= "class";
    public static final String CONTENT_TYPE		= "application/octet-stream";

}
