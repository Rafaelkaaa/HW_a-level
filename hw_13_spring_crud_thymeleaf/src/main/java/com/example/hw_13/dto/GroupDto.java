package com.example.hw_13.dto;

import com.example.hw_13.entity.Group;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto extends BaseDto{
    public GroupDto(Group group) {
        setId(group.getId().toString());
        setName(group.getName());
    }

    String name;
}
