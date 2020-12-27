package com.aymer.sirketimceptebackend.system.mail.service;

import com.aymer.sirketimceptebackend.system.mail.dto.MailAttachment;
import com.aymer.sirketimceptebackend.system.mail.dto.MimeMessageWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Value(value = "${useFakeEMail}")
    private boolean useFakeEMail;

    @Value(value = "${emailSendEnable}")
    private boolean emailSendEnable;

    @Value(value = "${defaultMailAddress}")
    private String DEFAULT_FROM_ADDRESS;

    @Value(value = "${defaultMailReplyToAddress}")
    private String DEFAULT_REPLAYTO_ADDRESS;

    @Value(value = "${defaultToMailAddress}")
    private String DEFAULT_ADDRESS;

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    @Autowired
    private JavaMailSender mailSender;

    @Value("classpath:/mail-templates/mail-logo.png")
    private Resource resourceFile;

    @Override
    public void htmlMailGonder(String to, String subject, Map<String, Object> templateModel)  {
        Template freemarkerTemplate = null;
        try {
            freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("template-freemarker.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
            htmlMailGonder(to, subject, htmlBody);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void htmlMailGonder(String to, String subject, String msg) {
        MimeMessageWrapper mimeMessageWrapper = createMimeMessage(null, to, subject, msg, "", null, true);
        _sendMimeMail(mimeMessageWrapper);
    }

    public void ekliHtmlMailGonder(String to, String subject, String msg, String fileName, byte[] fileContent) {
        MimeMessageWrapper mimeMessageWrapper = createMimeMessage(null, to, subject, msg, fileName, fileContent, true);
        _sendMimeMail(mimeMessageWrapper);
    }

    public void ekliMailGonder(String to, String subject, String msg, String fileName, byte[] fileContent) {
        MimeMessageWrapper mimeMessageWrapper = createMimeMessage(null, to, subject, msg, fileName, fileContent, false);
        _sendMimeMail(mimeMessageWrapper);
    }

    public void ekliMailGonder(String from, String to, String subject, String msg, String fileName,
                               byte[] fileContent) {
        MimeMessageWrapper mimeMessageWrapper = createMimeMessage(from, to, subject, msg, fileName, fileContent, false);
        _sendMimeMail(mimeMessageWrapper);
    }


    public void ekliMailGonder(String to, String subject, String msg, MailAttachment[] mailAttachments) {
        MimeMessageWrapper mimeMessageWrapper = createMimeMessage(null, to, subject, msg, mailAttachments);
        _sendMimeMail(mimeMessageWrapper);
    }


    public void ekliMailGonder(String from, String to, String subject, String msg, MailAttachment[] mailAttachments) {
        MimeMessageWrapper mimeMessageWrapper = createMimeMessage(from, to, subject, msg, mailAttachments);
        _sendMimeMail(mimeMessageWrapper);
    }

    public void mailGonder(String to, String subject, String msg) {
        SimpleMailMessage message = createSimpleMailMessage(to, subject, msg);
        message.setFrom(DEFAULT_FROM_ADDRESS);
        _sendSimpleMail(message);
    }

    public void mailGonder(String from, String to, String subject, String msg) {
        SimpleMailMessage message = createSimpleMailMessage(to, subject, msg);
        from = checkMailPart("from", DEFAULT_FROM_ADDRESS, from);
        message.setFrom(from);

        _sendSimpleMail(message);
    }

    private SimpleMailMessage createSimpleMailMessage(String to, String subject, String msg) {
        to = checkMailPart("to", DEFAULT_FROM_ADDRESS, to);
        subject = checkMailPart("subject", "konu bilgisi yok", subject);
        msg = checkMailPart("text", "text bilgisi yok", msg);
        SimpleMailMessage message = new SimpleMailMessage();
        String[] toList = parseToAttribute(to);
        message.setTo(toList);
        message.setReplyTo(DEFAULT_REPLAYTO_ADDRESS);
        message.setSubject(subject);
        message.setText(msg);
        return message;
    }

    private MimeMessageWrapper createMimeMessage(String from, String to, String subject, String msg, String fileName,
                                                 byte[] fileContent, boolean html) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        to = checkMailPart("to", DEFAULT_FROM_ADDRESS, to);
        subject = checkMailPart("subject", "konu bilgisi yok", subject);
        msg = checkMailPart("text", "text bilgisi yok", msg);
        from = checkMailPart("from", DEFAULT_FROM_ADDRESS, from);
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            if (StringUtils.hasLength(fileName) && fileContent != null) {
                messageHelper.addAttachment(fileName, new ByteArrayResource(fileContent));
            } else {
                LOGGER.error("Ekli mail gonderilmek istendi fakat ek bilgileri bulunamadı");
            }
            String[] toList = parseToAttribute(to);
            messageHelper.setTo(toList);
            messageHelper.setSubject(subject);
            messageHelper.setText(msg, html);
            messageHelper.setFrom(from);
            messageHelper.setReplyTo(DEFAULT_REPLAYTO_ADDRESS);
            messageHelper.addInline("attachment.png", resourceFile);
        } catch (MessagingException exc) {
            LOGGER.error(exc.toString(), exc);
        }
        return new MimeMessageWrapper(mimeMessage, msg);
    }

    private MimeMessageWrapper createMimeMessage(String from, String to, String subject, String msg,
                                                 MailAttachment[] mailAttachments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        to = checkMailPart("to", DEFAULT_FROM_ADDRESS, to);
        subject = checkMailPart("subject", "konu bilgisi yok", subject);
        msg = checkMailPart("text", "text bilgisi yok", msg);
        from = checkMailPart("from", DEFAULT_FROM_ADDRESS, from);
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            if (mailAttachments != null) {
                for (MailAttachment mailAttachment : mailAttachments) {
                    String fileName = mailAttachment.getFileName();
                    byte[] fileContent = mailAttachment.getContent();
                    if (StringUtils.hasLength(fileName) && fileContent != null) {
                        messageHelper.addAttachment(fileName, new ByteArrayResource(fileContent));
                    } else {
                        LOGGER.error("Ekli mail gonderilmek istendi fakat ek bilgileri bulunamadı");
                    }
                }
            }
            String[] toList = parseToAttribute(to);
            messageHelper.setTo(toList);
            messageHelper.setSubject(subject);
            messageHelper.setText(msg);
            messageHelper.setFrom(from);
            messageHelper.addInline("attachment.png", resourceFile);
            messageHelper.setReplyTo(DEFAULT_REPLAYTO_ADDRESS);
        } catch (MessagingException exc) {
            LOGGER.error(exc.toString(), exc);
        }
        return new MimeMessageWrapper(mimeMessage, msg);
    }

    private void _sendSimpleMail(SimpleMailMessage message) {
        if (!emailSendEnable) {
            return;
        }
        try {
            log(message);
            checkFakeMail(message);
            new SimpleMailSender(message).start();
        } catch (Exception exc) {
            throw new RuntimeException("e-posta gönderilemedi", exc);
        }
    }

    private void checkFakeMail(SimpleMailMessage message) {
        boolean fakeMail = useFakeEMail;
        if (fakeMail) {
            try {
                String[] tos = message.getTo();
                if (tos != null && tos.length > 0) {
                    StringBuilder builder = new StringBuilder("Asıl e-posta alıcıları:");
                    for (String to : tos) {
                        builder.append(" ");
                        builder.append(to);
                    }
                    builder.append("\n\nİçerik: \n");
                    builder.append(message.getText());

                    message.setTo(DEFAULT_ADDRESS);
                    message.setText(builder.toString());
                }
            } catch (Exception exc) {
                LOGGER.error(exc.toString(), exc);
            }
        }
    }

    private void _sendMimeMail(MimeMessageWrapper messageWrapper) {
        if (!emailSendEnable) {
            return;
        }
        try {
            log(messageWrapper);
            checkFakeMail(messageWrapper);
            new MimeMailSender(messageWrapper.getMimeMessage()).start();
        } catch (Exception exc) {
            throw new RuntimeException("e-posta gönderilemedi", exc);
        }
    }

    private void checkFakeMail(MimeMessageWrapper messageWrapper) {
        MimeMessage message = messageWrapper.getMimeMessage();
        boolean fakeMail = useFakeEMail;
        if (fakeMail) {
            try {
                Address[] tos = message.getAllRecipients();
                if (tos != null && tos.length > 0) {
                    StringBuilder builder = new StringBuilder("Asıl e-posta alıcıları:");
                    for (Address to : tos) {
                        builder.append(" ");
                        builder.append(to);
                    }
                    builder.append("\n\nİçerik: \n");
                    builder.append(messageWrapper.getContent());

                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(DEFAULT_ADDRESS));
                    message.setText(builder.toString());
                }
            } catch (Exception exc) {
                LOGGER.error(exc.toString(), exc);
            }
        }
    }

    private void log(MimeMessageWrapper messageWrapper) {
        MimeMessage message = messageWrapper.getMimeMessage();
        try {
            StringBuilder recipientsBuilder = new StringBuilder();
            Address[] allRecipients = message.getAllRecipients();
            for (Address recipient : allRecipients) {
                recipientsBuilder.append(recipient);
                recipientsBuilder.append(" ");
            }
            String details = "Başlık= " + message.getSubject() + "\nİçerik= " + messageWrapper.getContent();
            String alicilar = recipientsBuilder.toString();
            LOGGER.debug("Alıcı= " + alicilar + "\n" + details);
        } catch (Exception exc) {
            LOGGER.error(exc.toString(), exc);
        }
    }

    private void log(SimpleMailMessage message) {
        try {
            StringBuilder recipientsBuilder = new StringBuilder();
            String[] allRecipients = message.getTo();
            for (String recipient : allRecipients) {
                recipientsBuilder.append(recipient);
                recipientsBuilder.append(" ");
            }

            String details = "Başlık= " + message.getSubject() + "\nİçerik= " + message.getText();
            String alicilar = recipientsBuilder.toString();
            LOGGER.debug("Alıcı= " + alicilar + "\n" + details);
        } catch (Exception exc) {
            LOGGER.error(exc.toString(), exc);
        }
    }

    private String checkMailPart(String identifier, String defaultValue, String mailPart) {
        if (mailPart == null) {
            LOGGER.error(
                    "[" + identifier + "] bilgisi set edilmemis, varsayılan olarak [" + defaultValue + "] set edildi");
            return defaultValue;
        }
        return mailPart;
    }

    private String[] parseToAttribute(String toList) {
       // if (StringUtils.isEmpty(toList)) {
         //   return new String[]{DEFAULT_FROM_ADDRESS};
        //}
        String[] mailParts = toList.split("[,;]");
        for (int i = 0, mailPartsLength = mailParts.length; i < mailPartsLength; i++) {
            mailParts[i] = mailParts[i].trim();
        }
        return mailParts;
    }

    private class SimpleMailSender extends Thread {
        private SimpleMailMessage message;

        private SimpleMailSender(SimpleMailMessage message) {
            this.message = message;
        }

        @Override
        public void run() {
            super.run();
            mailSender.send(message);
        }
    }

    private class MimeMailSender extends Thread {
        private MimeMessage message;

        private MimeMailSender(MimeMessage message) {
            this.message = message;
        }

        @Override
        public void run() {
            super.run();
            mailSender.send(message);
        }
    }

}

