package com.es.habitstack.module.recordTag.entity;


import com.es.habitstack.module.record.entity.Record;
import com.es.habitstack.module.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RecordTag {

    //복합 PK
    @EmbeddedId
    private  RecordTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recordId")
    @JoinColumn(name = "record_id",nullable = false)
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id",nullable = false)
    private Tag tag;




    @Builder
    public RecordTag(Record record, Tag tag) {
        this.record = record;
        this.tag = tag;
        this.id = new RecordTagId(
                record.getRecordId(),
                tag.getTagId()
        );
    }



}
