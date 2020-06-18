package com.aymer.sirketimceptebackend.controller.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortMeta {

    @NotNull
    String field;

    @NotNull
    Long order;
}
