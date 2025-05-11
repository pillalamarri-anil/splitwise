package dev.anil.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="group_splitwise")
@Getter
@Setter
public class Group extends BaseModel {

    private String name;
    private String description;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;

    @OneToMany(fetch = FetchType.EAGER,  mappedBy = "group")
    private List<Expense> expenses;
}
