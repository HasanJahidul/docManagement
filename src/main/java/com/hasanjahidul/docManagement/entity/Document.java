package com.hasanjahidul.docManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@ToString
@Table(name = "documents")
public class Document extends BaseEntity{
    private String name;
    private String content;
    @OneToMany(mappedBy = "documents", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<AccessControl> accessControls;
}
