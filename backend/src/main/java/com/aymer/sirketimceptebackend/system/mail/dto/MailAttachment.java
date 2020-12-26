package com.aymer.sirketimceptebackend.system.mail.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MailAttachment implements Serializable {

    private String fileName;
    private byte[] content;

    public MailAttachment(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

}

