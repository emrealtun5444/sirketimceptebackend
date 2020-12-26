package com.aymer.sirketimceptebackend.system.mail.dto;

import lombok.Getter;

import javax.mail.internet.MimeMessage;

@Getter
public class MimeMessageWrapper {
    private MimeMessage mimeMessage;
    private String content;

    public MimeMessageWrapper(MimeMessage mimeMessage, String content) {
        this.mimeMessage = mimeMessage;
        this.content = content;
    }
}
