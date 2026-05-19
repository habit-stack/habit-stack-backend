package com.es.habitstack.module.recordTag.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class RecordTagId implements Serializable {

    // record_tag.record_id
    private Long recordId;

    // record_tag.tag_id
    private Long tagId;
}