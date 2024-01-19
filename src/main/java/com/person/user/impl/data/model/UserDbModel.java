package com.person.user.impl.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "\"user\"")
public class UserDbModel {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    String description;

    public UserDbModel() {}

    public UserDbModel(Long id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }
}
