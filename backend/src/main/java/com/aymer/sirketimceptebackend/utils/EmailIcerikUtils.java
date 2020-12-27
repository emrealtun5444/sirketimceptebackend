package com.aymer.sirketimceptebackend.utils;

import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmailIcerikUtils {

    public static Map<String, Object> generateTemplateModel(User user, Sirket sirket, String text, String content) {
        Map<String, Object> templateModel = new LinkedHashMap<>();
        templateModel.put("recipientName", user.getAciklama());
        templateModel.put("senderName", sirket.getName());
        templateModel.put("senderAdress", sirket.getAddress() != null ? sirket.getAddress() : "");
        templateModel.put("senderMail", sirket.getAddress() != null ? sirket.getAddress() : "");

        templateModel.put("text", text);
        templateModel.put("content", content);
        return templateModel;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static Paragraf createParagraf(String text) {
        return new Paragraf(text);
    }

    public static KeyValueTableBuilder createKeyValueTableBuilder() {
        return new KeyValueTableBuilder();
    }

    public static TableBuilder createTableBuilder(RowBuilder baslik) {
        return new TableBuilder(baslik);
    }

    public static TableBuilder createTableBuilder(String style, RowBuilder baslik) {
        return new TableBuilder(style, baslik);
    }

    public static RowBuilder createRowBuilder() {
        return new RowBuilder();
    }

    public static RowBuilder createRowBuilder(String style) {
        return new RowBuilder(style);
    }

    public static class Builder {
        private final StringBuilder icerik;

        private Builder() {
            icerik = new StringBuilder();
        }

        public String build() {
            return icerik.toString();
        }

        public Builder add(Buildable buildable) {
            icerik.append("<p>");
            icerik.append(buildable.build());
            icerik.append("<p/>");
            return this;
        }
    }

    public interface Buildable {
        String build();
    }

    public static class Paragraf implements Buildable {
        private final String text;

        private Paragraf(String text) {
            this.text = text;
        }

        public String build() {
            return text;
        }
    }

    public static class KeyValueTableBuilder implements Buildable {
        private final StringBuilder icerik;

        private KeyValueTableBuilder() {
            icerik = new StringBuilder();
            initialize();
        }

        private void initialize() {
            icerik.append("<table>");
        }

        public String build() {
            end();
            return icerik.toString();
        }

        private void end() {
            icerik.append("</table>");
        }

        public KeyValueTableBuilder addRow(String key, String value) {
            icerik.append("<tr>");
            icerik.append("<th>");
            icerik.append(key);
            icerik.append("</th>");
            icerik.append("<td>");
            icerik.append(value);
            icerik.append("</td>");
            icerik.append("</tr>");
            return this;
        }
    }

    public static class TableBuilder implements Buildable {
        private final StringBuilder icerik;
        private final int kolonSayisi;
        private String style = "style=\"border:1px solid black;border-collapse:collapse;\"";

        private TableBuilder(RowBuilder baslik) {
            icerik = new StringBuilder();
            initialize();
            kolonSayisi = baslik.cellCount;
            addRow(baslik);
        }

        private TableBuilder(String style, RowBuilder baslik) {
            this(baslik);
            this.style = style;
        }


        private void initialize() {
            icerik.append("<table ").append(style).append(">");
        }

        public String build() {
            end();
            return icerik.toString();
        }

        private void end() {
            icerik.append("</table>");
        }

        public TableBuilder addRow(RowBuilder rowBuilder) {
            if (rowBuilder.cellCount != kolonSayisi) {
                throw new IllegalArgumentException("kolon sayıları her satırda aynı değil");
            }
            icerik.append(rowBuilder.build());
            return this;
        }
    }

    public static class RowBuilder {
        private final StringBuilder icerik;
        private int cellCount;
        private String style = "";

        private RowBuilder() {
            icerik = new StringBuilder();
            initialize();
        }

        private RowBuilder(String style) {
           this();
           this.style = style;
        }

        private void initialize() {
            icerik.append("<tr ").append(style).append(">");
        }

        private String build() {
            end();
            return icerik.toString();
        }

        private void end() {
            icerik.append("</tr>");
        }

        public RowBuilder addCell(String cell) {
            cellCount++;
            icerik.append("<td>");
            icerik.append(cell);
            icerik.append("</td>");
            return this;
        }
    }
}
