package dev.anil.splitwise.models;

import dev.anil.splitwise.models.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Expense extends BaseModel {

    float amount;
    private String description;

    @ManyToOne
    private Group group;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "expense")
    private List<UserExpense> userExpenseList;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}
