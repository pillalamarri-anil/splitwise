package dev.anil.splitwise.Strategies;

import dev.anil.splitwise.customObjects.Transaction;
import dev.anil.splitwise.models.Expense;

import java.util.List;

public interface SettleUpStrategy {

    List<Transaction> settleUp(List<Expense> expenseList);

}
