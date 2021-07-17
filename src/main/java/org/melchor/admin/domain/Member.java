package org.melchor.admin.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String age;

    private String description;


}
