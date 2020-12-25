package com.aymer.sirketimceptebackend.utils;

public class EmailIcerikUtils {

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

    public static RowBuilder createRowBuilder() {
        return new RowBuilder();
    }

    public static class Builder {
        private final StringBuilder icerik;

        private Builder() {
            icerik = new StringBuilder();
            initialize();
        }

        private void initialize() {
            icerik.append("<html>\n");
            icerik.append("<head>\n");
            icerik.append("    <style>\n");
            icerik.append("        table {\n");
            icerik.append("            font-family: Arial, Verdana, sans-serif;\n");
            icerik.append("            font-size: 12px;\n");
            icerik.append("            border-collapse: collapse;\n");
            icerik.append("            width: 100%;\n");
            icerik.append("        }\n");
            icerik.append("\n");
            icerik.append("        td, th {\n");
            icerik.append("            border: 1px solid #ddd;\n");
            icerik.append("            text-align: left;\n");
            icerik.append("            padding: 8px;\n");
            icerik.append("        }\n");
            icerik.append("\n");
            icerik.append("        tr:nth-child(even) {background-color: #f2f2f2;}\n");
            icerik.append("    </style>\n");
            icerik.append("</head>\n");
            icerik.append("<body>\n");
        }
        
        public String build() {
            end();
            return icerik.toString();
        }

        private void end() {
            icerik.append("</body></html>");
        }

        public Builder add(Buildable buildable) {
            icerik.append("<br/>");
            icerik.append(buildable.build());
            icerik.append("<br/>");
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

        private TableBuilder(RowBuilder baslik) {
            icerik = new StringBuilder();
            initialize();
            kolonSayisi = baslik.cellCount;
            addRow(baslik);
        }

        private void initialize() {
            icerik.append("<table>");
            icerik.append("<tr>");
        }

        public String build() {
            end();
            return icerik.toString();
        }

        private void end() {
            icerik.append("</tr>");
            icerik.append("</table>");
        }

        public TableBuilder addRow(RowBuilder rowBuilder) {
            if(rowBuilder.cellCount != kolonSayisi) {
                throw new IllegalArgumentException("kolon sayıları her satırda aynı değil");
            }
            icerik.append(rowBuilder.build());
            return this;
        }
    }

    public static class RowBuilder {
        private final StringBuilder icerik;
        private int cellCount;

        private RowBuilder() {
            icerik = new StringBuilder();
            initialize();
        }

        private void initialize() {
            icerik.append("<th>");
        }

        private String build() {
            end();
            return icerik.toString();
        }

        private void end() {
            icerik.append("</th>");
        }

        public RowBuilder addCell(String cell) {
            cellCount ++;
            icerik.append("<td>");
            icerik.append(cell);
            icerik.append("</td>");
            return this;
        }
    }
}
