package com.example.hw_13.dao;

import com.example.hw_13.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends
        JpaRepository<E, UUID>, JpaSpecificationExecutor<E> {
}
