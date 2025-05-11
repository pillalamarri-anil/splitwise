package dev.anil.splitwise.dtos;

import dev.anil.splitwise.customObjects.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupSettleUpResponseDto {
    private List<Transaction> transactionList;
    private ResponseStatus status;
}
