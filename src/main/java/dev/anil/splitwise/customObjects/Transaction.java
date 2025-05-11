package dev.anil.splitwise.customObjects;

import dev.anil.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    User userFrom;
    User userTo;
    float amount;
}
