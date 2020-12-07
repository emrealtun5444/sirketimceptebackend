package com.aymer.sirketimceptebackend.common.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LazyLoadEvent {

    @NotNull
    private int first;

    @NotNull
    private int rows;

    private String sortField;

    private int sortOrder;

    @Valid
    private List<SortMeta> multiSortMeta;

    @Valid
    private Map<String, FilterMetadata> filters;

    @Valid
    private Object globalFilter;

}
