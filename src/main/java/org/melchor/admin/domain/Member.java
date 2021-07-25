package org.melchor.admin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private String description;

    @Builder
    public Member(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

}
