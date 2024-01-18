package com.example.hw_13.service.impl;

import com.example.hw_13.dao.GroupRepository;
import com.example.hw_13.dto.DtoToEntityConvertorUtil;
import com.example.hw_13.dto.GroupDto;
import com.example.hw_13.dto.ClassDto;
import com.example.hw_13.entity.Group;
import com.example.hw_13.service.BaseCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService implements BaseCrudService<GroupDto> {
    private final GroupRepository groupRepository;
    @Override
    public void create(GroupDto value) {
        groupRepository.save(DtoToEntityConvertorUtil.convertGroupDto(value));
    }

    @Override
    public GroupDto read(String id) {
        Group group = groupRepository.findById((UUID.fromString(id))).get();
        return new GroupDto(group);
    }

    @Override
    public Collection<GroupDto> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void update(GroupDto newValue) {
        groupRepository.save(DtoToEntityConvertorUtil.convertGroupDto(newValue));
    }

    @Override
    public void delete(String id) {
        groupRepository.deleteById(UUID.fromString(id));
    }

    public List<ClassDto> findAllGroupsWithStudents(){
        List<Group> groups = groupRepository.findAll();
        return groups
                .stream()
                .map(ClassDto::new)
                .collect(Collectors.toList());
    }
}