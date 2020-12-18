package com.aymer.sirketimceptebackend.system.user.mapper;


import com.aymer.sirketimceptebackend.common.model.abstructcommon.ReferenceMapper;
import com.aymer.sirketimceptebackend.system.user.dto.UserInput;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface UserMapper {
    UserInput toInput(User user);
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateFromInput(UserInput userInput, @MappingTarget User user);
    UserListItem toListItem(User user);
    List<UserListItem> toListItems(List<User> users);
}
