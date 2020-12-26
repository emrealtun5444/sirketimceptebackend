package com.aymer.sirketimceptebackend.siparis.service;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.siparis.model.SiparisDurumu;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisRepository;
import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.mail.service.MailService;
import com.aymer.sirketimceptebackend.system.mail.service.NotificationService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // bildirim yapılacak kullanıcılar tespit ediliyor.
        final Set<User> targetUsers = notificationService.findTargetListByNotificationType(Notification.WAITING_ORDER);
        String subject = LabelFactory.getLabel("subject.bekleyen.siparis.listesi", new Object[]{DateUtils.formatDateForHat(new Date())});

        // siparis listesini cari kart bazında gruplarız.
        final Map<CariKart, List<Siparis>> siparisMap = getSiparisMap();
        targetUsers.forEach(user -> {
            // mail içeriği alınıyor.
            String content = prepareMailContent(user, siparisMap);
            mailService.mailGonder(user.getEmail(), subject, content);
        });


        return RepeatStatus.FINISHED;
    }


    private String prepareMailContent(User user, Map<CariKart, List<Siparis>> siparisMap) {
        EmailIcerikUtils.Builder mailIcerigi = EmailIcerikUtils.createBuilder().add(EmailIcerikUtils.createParagraf(LabelFactory.getLabel("label.sayin")));

        for (Map.Entry<CariKart, List<Siparis>> entry : siparisMap.entrySet()) {
            mailIcerigi.add(EmailIcerikUtils.createParagraf(entry.getKey().getCariKodu() + " - " + entry.getKey().getCariAdi()));

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

    private Map<CariKart, List<Siparis>> getSiparisMap() {
        // tamamlanmamış tüm siparişler tespit ediliyor.
        List<Siparis> siparisList = siparisRepository.findAllBySiparisDurumuNot(SiparisDurumu.TAMAMLANDI);
        // siparis listesini cari kart bazında gruplarız.
        return siparisList.stream().collect(Collectors.groupingBy(Siparis::getCariKart));
    }
}
