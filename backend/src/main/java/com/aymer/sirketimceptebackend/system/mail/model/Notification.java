package com.aymer.sirketimceptebackend.system.mail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Notification implements Serializable {
    // order
    WAITING_ORDER("notify.waiting.order");

    private String label;

}
