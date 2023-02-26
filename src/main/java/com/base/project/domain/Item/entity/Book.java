package com.base.project.domain.Item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="B")
@Getter
public class Book extends Item{
    private String author;
    private String isbn;
}
