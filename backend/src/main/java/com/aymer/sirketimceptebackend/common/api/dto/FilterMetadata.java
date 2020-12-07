package com.aymer.sirketimceptebackend.common.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class FilterMetadata {

    @NotNull
    private Object value;

    @NotNull
    private String matchMode;
}
