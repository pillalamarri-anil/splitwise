package dev.anil.splitwise.Strategies;

import dev.anil.splitwise.customObjects.Transaction;
import dev.anil.splitwise.models.Expense;
import dev.anil.splitwise.models.User;
import dev.anil.splitwise.models.UserExpense;
import dev.anil.splitwise.models.enums.UserExpenseType;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@Setter
class UserBalance
{
     private User user;
     private float balance;

    public UserBalance(User user, float balance) {
        this.user = user;
        this.balance = balance;
    }
}

@Component
public class GreedySettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(List<Expense> expenseList) {

        HashMap<User, Float> userToAmount = new HashMap<User, Float>();
        for(Expense expense : expenseList)
        {
            for(UserExpense userExpense : expense.getUserExpenseList())
            {
                float amount = userToAmount.getOrDefault(userExpense.getUser(), 0.0f);
                if(userExpense.getExpenseType().equals(UserExpenseType.LEND))
                    amount += userExpense.getAmount();
                else
                    amount -= userExpense.getAmount();
                userToAmount.put(userExpense.getUser(), amount);
            }
        }

        // max pq
        PriorityQueue<UserBalance> lenders = new PriorityQueue<UserBalance>((u1, u2)->Float.compare(u2.getBalance(), u1.getBalance()));
        // min pq
        PriorityQueue<UserBalance> borrowers = new PriorityQueue<UserBalance>((u1, u2)->Float.compare(u1.getBalance(), u2.getBalance()));

        for( User user : userToAmount.keySet())
        {
            if(userToAmount.get(user) > 0)
            {
                lenders.add(new UserBalance(user, userToAmount.get(user)));
            }
            else {
                borrowers.add(new UserBalance(user, -userToAmount.get(user)));
            }
        }

        List<Transaction> transactionList = new ArrayList<>();

        while (!lenders.isEmpty() && !borrowers.isEmpty())
        {
            UserBalance lender = lenders.poll();
            UserBalance borrower = borrowers.poll();

            Transaction transaction = new Transaction();
            transaction.setUserFrom(borrower.getUser());
            transaction.setUserTo(lender.getUser());
            transaction.setAmount(Math.min(lender.getBalance(), borrower.getBalance()));

            if(borrower.getBalance() >= lender.getBalance())
            {
                float newBalance = borrower.getBalance() - lender.getBalance();
                if(newBalance > 0) {
                    borrower.setBalance(newBalance);
                    borrowers.add(borrower);
                }
            }
            else {
                    lender.setBalance(lender.getBalance() - borrower.getBalance());
                    lenders.add(lender);
            }
        }
        return transactionList;
    }
}
