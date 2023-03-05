package com.base.project.domain.book.entity;

import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity {

    private String name;
    private String country;

    @OneToMany(mappedBy = "author")
    @Builder.Default
    @ToString.Exclude
    private List<AuthorBook> authorBooks = new ArrayList<>();
}
