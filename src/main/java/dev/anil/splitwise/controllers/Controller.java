package dev.anil.splitwise.controllers;

import dev.anil.splitwise.customObjects.Transaction;
import dev.anil.splitwise.dtos.*;
import dev.anil.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private SettleUpService settleUpService;

    @Autowired
    public Controller(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    @GetMapping("/SettleupUser")
    public UserSettleUpResponseDto SettleupUser(UserSettleUpRequestDto  requestDto) {

        UserSettleUpResponseDto responseDto = new UserSettleUpResponseDto();
        try
        {
           List<Transaction> transactionList = settleUpService.settleUpUser(requestDto.getUserId());
           responseDto.setTransactionList(transactionList);
           responseDto.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e)
        {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    };

    @GetMapping("/SettleupGroup")
    public GroupSettleUpResponseDto SettleupGroup(GroupSettleUpRequestDto requestDto) {

        GroupSettleUpResponseDto responseDto = new GroupSettleUpResponseDto();
        try
        {
            List<Transaction> transactionList = settleUpService.settleUpGroup(requestDto.getGroupId());
            responseDto.setTransactionList(transactionList);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e)
        {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
