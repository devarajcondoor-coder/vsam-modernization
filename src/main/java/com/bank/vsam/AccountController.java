package com.bank.vsam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @GetMapping("/accounts")
    public List<Account> getAccounts() throws Exception {

        VsamAccountService service =
                new VsamAccountService();

        return service.readAccounts();
    }
}
