package com.base.project.domain.book.entity;

import com.base.project.domain.review.entity.Review;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Book extends BaseEntity {
    private String title;

    @OneToMany(mappedBy = "book")
    @Builder.Default
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    @Builder.Default
    @ToString.Exclude
    private List<AuthorBook> authorBooks = new ArrayList<>();


}
