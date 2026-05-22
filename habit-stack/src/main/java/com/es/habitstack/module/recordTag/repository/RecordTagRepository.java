package com.es.habitstack.module.recordTag.repository;

import com.es.habitstack.module.recordTag.entity.RecordTag;
import com.es.habitstack.module.recordTag.entity.RecordTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordTagRepository extends JpaRepository<RecordTag, RecordTagId> {
}
