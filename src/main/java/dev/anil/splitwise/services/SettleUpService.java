package dev.anil.splitwise.services;

import dev.anil.splitwise.Strategies.SettleUpStrategy;
import dev.anil.splitwise.customObjects.Transaction;
import dev.anil.splitwise.exceptions.InvalidGroupException;
import dev.anil.splitwise.exceptions.InvalidUser;
import dev.anil.splitwise.models.Expense;
import dev.anil.splitwise.models.Group;
import dev.anil.splitwise.models.User;
import dev.anil.splitwise.models.UserExpense;
import dev.anil.splitwise.repositories.GroupRepository;
import dev.anil.splitwise.repositories.UserExpenseRepository;
import dev.anil.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SettleUpService {
    private UserExpenseRepository userExpenseRepository;
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpService(UserExpenseRepository userExpenseRepository,
                           GroupRepository groupRepository,
                           UserRepository userRepository,
                           SettleUpStrategy settleUpStrategy) {
        this.userExpenseRepository = userExpenseRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Transaction> settleUpGroup(long groupId)
    {
        Group group = groupRepository.findById(groupId).orElseThrow(()->new InvalidGroupException("Group not found"));
        List<Expense> ExpenseList = group.getExpenses();
        return settleUpStrategy.settleUp(ExpenseList);
    }

    public List<Transaction> settleUpUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new InvalidUser("User not found"));

        List<UserExpense> userExpenseList = userExpenseRepository.findAllByUser(user);
        Set<Expense> expenseSet = new HashSet<>();
        for(UserExpense userExpense : userExpenseList)
            expenseSet.add(userExpense.getExpense());

        return settleUpStrategy.settleUp(expenseSet.stream().toList());
    }
}
