package dev.anil.splitwise.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class User extends  BaseModel{

    private String name;
    private String email;
    private String phone;

    @ManyToMany
    private List<Group> groups;
}
