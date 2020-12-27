package com.aymer.sirketimceptebackend.system.mail.service;


import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;

import java.util.Set;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface NotificationService {
    Set<User> findTargetList(Sirket sirket, Notification notification);
}
