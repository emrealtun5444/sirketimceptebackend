package com.aymer.sirketimceptebackend.system.mail.repository;


import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NotificationRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join u.notifications n where n = :notification ")
    Set<User> findAllByNotification(@Param("notification") Notification notification);
}
