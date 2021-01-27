package com.aymer.sirketimceptebackend.belge.model;


public interface Documentable {
    String getFileName();

    Long getDocumentSize();

    byte[] getDocumentContent();

    String getMinetype();
}
