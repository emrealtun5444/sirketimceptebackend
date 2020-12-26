package com.aymer.sirketimceptebackend.system.mail.service;

import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.mail.repository.NotificationRepository;
import com.aymer.sirketimceptebackend.system.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<User> findTargetListByNotificationType(Notification notification) {
        return notificationRepository.findAllByNotification(notification);
    }

}
