package com.aymer.sirketimceptebackend.common.model.abstructcommon;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SelectItemMapper {
    public static <T extends Idendifier> List<SelectItem> toComboItems(Collection<T> idendifiers) {
        return CollectionUtils.isEmpty(idendifiers) ? new ArrayList<>() :
                idendifiers.stream().map(SelectItemMapper::toComboItem).collect(Collectors.toList());
    }

    public static SelectItem toComboItem(Idendifier idendifier) {
        return idendifier != null ? new SelectItem(idendifier.getId(), idendifier.getAciklama()) : null;
    }
}
