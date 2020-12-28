package com.aymer.sirketimceptebackend.cariKart.service;

import com.aymer.sirketimceptebackend.cariKart.dto.CariKartDto;
import com.aymer.sirketimceptebackend.cariKart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:44
 */
@Service
public class BorcAlacakBildirimService implements Tasklet {

    @Autowired
    private CariKartRepository cariKartRepository;

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

            String content = prepareMailContent(sirket);
            targetUsers.forEach(user -> {
                mailService.htmlMailGonder(user.getEmail(), subject, EmailIcerikUtils.generateTemplateModel(user, sirket, text, content));
            });
        }
        return RepeatStatus.FINISHED;
    }

    private String prepareMailContent(Sirket sirket) {
        final Map<User, List<CariKart>> cariKartMap = getCariKarts(sirket);
        EmailIcerikUtils.Builder mailIcerigi = EmailIcerikUtils.createBuilder();

        for (Map.Entry<User, List<CariKart>> entry : cariKartMap.entrySet()) {
            String groupingValue = entry.getKey() != null ? entry.getKey().getAciklama() : LabelFactory.getLabel("label.other");
            mailIcerigi.add(EmailIcerikUtils.createParagraf("<strong>" + groupingValue + "</strong>"));

            EmailIcerikUtils.TableBuilder tableBuilder = EmailIcerikUtils.createTableBuilder(
                    EmailIcerikUtils.createRowBuilder()
                            .addCell(LabelFactory.getLabel("label.cari.kodu"))
                            .addCell(LabelFactory.getLabel("label.cari.adi"))
                            .addCell(LabelFactory.getLabel("label.toplam.borc"))
                            .addCell(LabelFactory.getLabel("label.toplam.alacak"))
                            .addCell(LabelFactory.getLabel("label.bakiye"))
            );

            List<CariKartDto> cariKartDtoList = cariKartMapper.carikartToDtoList(entry.getValue());
            cariKartDtoList.sort(Comparator.comparing(CariKartDto::getBakiye));
            for (CariKartDto cariKart : cariKartDtoList) {
                tableBuilder.addRow(
                        EmailIcerikUtils.createRowBuilder()
                                .addCell(cariKart.getCariKodu())
                                .addCell(cariKart.getCariAdi())
                                .addCell(MoneyUtils.currencyFormat(cariKart.getToplamBorc()))
                                .addCell(MoneyUtils.currencyFormat(cariKart.getToplamAlacak()))
                                .addCell(MoneyUtils.currencyFormat(cariKart.getBakiye()))
                );
            }
            mailIcerigi.add(tableBuilder);
        }

        return mailIcerigi.build();
    }

    private Map<User, List<CariKart>> getCariKarts(Sirket sirket) {
        // tamamlanmamış tüm siparişler tespit ediliyor.
        List<CariKart> cariKarts = cariKartRepository.findCariKartByDurumAndSirket(EDurum.AKTIF, sirket);
        // siparis listesini cari kart bazında gruplarız.
        return cariKarts.stream().collect(
                Collectors.toMap(
                        CariKart::getSorumluPersonel,
                        x -> {
                            List<CariKart> list = new ArrayList<>();
                            list.add(x);
                            return list;
                        },
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        },
                        HashMap::new

                )
        );
    }
}
