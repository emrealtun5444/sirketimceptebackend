package com.aymer.sirketimceptebackend.siparis.service;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.siparis.model.SiparisDurumu;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisRepository;
import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.mail.service.MailService;
import com.aymer.sirketimceptebackend.system.mail.service.NotificationService;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import com.aymer.sirketimceptebackend.utils.EmailIcerikUtils;
import com.aymer.sirketimceptebackend.utils.LabelFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:44
 */
@Service
public class SiparisBildirimService implements Tasklet {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SirketRepository sirketRepository;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String subject = LabelFactory.getLabel("subject.bekleyen.siparis.listesi");
        String text = LabelFactory.getLabel("text.bekleyen.siparis.listesi", new Object[]{DateUtils.formatDateForHat(new Date())});

        List<Sirket> sirketList = sirketRepository.findAll();
        for (Sirket sirket : sirketList) {
            // hedef kitle tespit ediliyor
            final Set<User> targetUsers = notificationService.findTargetList(sirket, Notification.WAITING_ORDER);
            if (targetUsers.size() == 0) continue;

            String content = prepareMailContent(sirket);
            targetUsers.forEach(user -> {
                mailService.htmlMailGonder(user.getEmail(), subject, EmailIcerikUtils.generateTemplateModel(user, sirket, text, content));
            });
        }
        return RepeatStatus.FINISHED;
    }

    private String prepareMailContent(Sirket sirket) {
        final Map<CariKart, List<Siparis>> siparisMap = getSiparisMap(sirket);
        EmailIcerikUtils.Builder mailIcerigi = EmailIcerikUtils.createBuilder();

        for (Map.Entry<CariKart, List<Siparis>> entry : siparisMap.entrySet()) {
            mailIcerigi.add(EmailIcerikUtils.createParagraf("<strong>" + entry.getKey().getCariKodu() + " - " + entry.getKey().getCariAdi() + "</strong>"));

            EmailIcerikUtils.TableBuilder tableBuilder = EmailIcerikUtils.createTableBuilder(
                EmailIcerikUtils.createRowBuilder()
                    .addCell(LabelFactory.getLabel("label.siparis.no"))
                    .addCell(LabelFactory.getLabel("label.siparis.tarihi"))
                    .addCell(LabelFactory.getLabel("label.stok.kodu"))
                    .addCell(LabelFactory.getLabel("label.urun.adi"))
                    .addCell(LabelFactory.getLabel("label.miktar"))
                    .addCell(LabelFactory.getLabel("label.teslim.miktari"))
                    .addCell(LabelFactory.getLabel("label.kalan.miktari"))
                    .addCell(LabelFactory.getLabel("label.stok.miktari"))
                    .addCell(LabelFactory.getLabel("label.siparis.durumu"))
            );

            for (Siparis siparis : entry.getValue()) {
                tableBuilder.addRow(
                    EmailIcerikUtils.createRowBuilder()
                        .addCell(siparis.getSiparisNo())
                        .addCell(DateUtils.formatDateForHat(siparis.getIslemTarihi()))
                        .addCell(siparis.getStokKart().getStokKodu())
                        .addCell(siparis.getStokKart().getUrunAdi())
                        .addCell(siparis.getMiktar().toString())
                        .addCell(siparis.getTeslimMiktari().toString())
                        .addCell(siparis.getKalanMiktar().toString())
                        .addCell(siparis.getStokKart().getStokAdedi().toString())
                        .addCell(siparis.getSiparisDurumu().name())
                );
            }
            mailIcerigi.add(tableBuilder);
        }

        return mailIcerigi.build();
    }

    private Map<CariKart, List<Siparis>> getSiparisMap(Sirket sirket) {
        // tamamlanmamış tüm siparişler tespit ediliyor.
        List<Siparis> siparisList = siparisRepository.findAllBySiparisDurumuNotAndSirketOrderByIslemTarihiDesc(SiparisDurumu.TAMAMLANDI, sirket);
        // siparis listesini cari kart bazında gruplarız.
        return siparisList.stream().collect(Collectors.groupingBy(Siparis::getCariKart));
    }
}
