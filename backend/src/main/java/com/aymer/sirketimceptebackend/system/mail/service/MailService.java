package com.aymer.sirketimceptebackend.system.mail.service;

import com.aymer.sirketimceptebackend.system.mail.dto.MailAttachment;

import java.io.Serializable;

public interface MailService extends Serializable {
    void mailGonder(String from, String to, String subject, String msg);

    void mailGonder(String to, String subject, String msg);

    void htmlMailGonder(String to, String subject, String msg);

    void ekliHtmlMailGonder(String to, String subject, String msg, String fileName, byte[] fileContent);

    void ekliMailGonder(String to, String subject, String msg, String fileName, byte[] fileContent);

    void ekliMailGonder(String from, String to, String subject, String msg, String fileName, byte[] fileContent);

    void ekliMailGonder(String from, String to, String subject, String msg, MailAttachment[] mailAttachments);

    void ekliMailGonder(String to, String subject, String msg, MailAttachment[] mailAttachments);
}
