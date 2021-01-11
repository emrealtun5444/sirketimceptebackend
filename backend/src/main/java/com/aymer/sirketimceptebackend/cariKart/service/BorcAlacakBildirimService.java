package com.aymer.sirketimceptebackend.cariKart.service;

import com.aymer.sirketimceptebackend.cariKart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.report.dto.HedefCariDto;
import com.aymer.sirketimceptebackend.report.service.ReportService;
import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.mail.service.MailService;
import com.aymer.sirketimceptebackend.system.mail.service.NotificationService;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import com.aymer.sirketimceptebackend.utils.EmailIcerikUtils;
import com.aymer.sirketimceptebackend.utils.LabelFactory;
import com.aymer.sirketimceptebackend.utils.MoneyUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:44
 */
@Service
public class BorcAlacakBildirimService implements Tasklet {

    @Autowired
    private ReportService reportService;

    @Autowired
    private MailService mailService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SirketRepository sirketRepository;

    @Autowired
    private CariKartMapper cariKartMapper;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String subject = LabelFactory.getLabel("subject.borc.alacak.listesi");
        String text = LabelFactory.getLabel("text.borc.alacak.listesi", new Object[]{DateUtils.formatDateForHat(new Date())});

        List<Sirket> sirketList = sirketRepository.findAll();
        for (Sirket sirket : sirketList) {
            // hedef kitle tespit ediliyor
            final Set<User> targetUsers = notificationService.findTargetList(sirket, Notification.BORC_ALACAK_ACCOUNT);
            if (targetUsers.size() == 0) continue;

            targetUsers.forEach(user -> {
                List<HedefCariDto> hedefCariDtos = reportService.donemeGoreHedefCariDagilimi(user, DateUtils.getYearFromDate(DateUtils.getToday()), sirket);
                if (!hedefCariDtos.isEmpty()) {
                    String content = prepareMailContent(hedefCariDtos, user, sirket);
                    mailService.htmlMailGonder(user.getEmail(), subject, EmailIcerikUtils.generateTemplateModel(user, sirket, text, content));
                }
            });
        }
        return RepeatStatus.FINISHED;
    }

    private String prepareMailContent(List<HedefCariDto> hedefCariDtos, User user, Sirket sirket) {
        EmailIcerikUtils.Builder mailIcerigi = EmailIcerikUtils.createBuilder();

        String groupingValue = user != null ? user.getAciklama() : LabelFactory.getLabel("label.other");
        mailIcerigi.add(EmailIcerikUtils.createParagraf("<strong>" + groupingValue + "</strong>"));

        EmailIcerikUtils.TableBuilder tableBuilder = EmailIcerikUtils.createTableBuilder(
                EmailIcerikUtils.createRowBuilder()
                        .addCell(LabelFactory.getLabel("label.cari.kodu"))
                        .addCell(LabelFactory.getLabel("label.cari.adi"))
                        .addCell(LabelFactory.getLabel("label.toplam.borc"))
                        .addCell(LabelFactory.getLabel("label.toplam.alacak"))
                        .addCell(LabelFactory.getLabel("label.bakiye"))
                        .addCell(LabelFactory.getLabel("label.toplam.ciro"))
                        .addCell(LabelFactory.getLabel("label.yillik.hedef"))
                        .addCell(LabelFactory.getLabel("label.gerceklesme.orani"))
        );

        hedefCariDtos.sort(Comparator.comparing(HedefCariDto::getBakiye));
        for (HedefCariDto entry : hedefCariDtos) {
            tableBuilder.addRow(
                    EmailIcerikUtils.createRowBuilder()
                            .addCell(entry.getCariKodu())
                            .addCell(entry.getCariAdi())
                            .addCell(MoneyUtils.currencyFormat(entry.getToplamBorc()))
                            .addCell(MoneyUtils.currencyFormat(entry.getToplamAlacak()))
                            .addCell(MoneyUtils.currencyFormat(entry.getBakiye()))
                            .addCell(MoneyUtils.currencyFormat(entry.getToplamCiro()))
                            .addCell(MoneyUtils.currencyFormat(entry.getYillikHedef()))
                            .addCell(MoneyUtils.currencyFormat(entry.getGerceklesmeYuzdesi()))
            );
        }
        return mailIcerigi.add(tableBuilder).build();
    }
}
