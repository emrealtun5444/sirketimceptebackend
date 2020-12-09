package com.aymer.sirketimceptebackend.common.model.abstructcommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectItem implements Serializable {
    private Long id;
    private String aciklama;
}