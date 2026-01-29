package com.bank.vsam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VsamAccountService {

    public List<Account> readAccounts() throws Exception {

        List<Account> accounts = new ArrayList<>();

        // ✅ Load VSAM file from classpath (works in Docker)
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("data/account.vsam.dat");

        if (is == null) {
            throw new RuntimeException("VSAM data file not found in resources");
        }

        byte[] record = new byte[38];

        while (is.read(record) == 38) {

            String acctNo =
                    new String(record, 0, 10).trim();

            String name =
                    new String(record, 10, 20).trim();

            String type =
                    new String(record, 30, 2).trim();

            byte[] balBytes =
                    Arrays.copyOfRange(record, 32, 37);

            String status =
                    new String(record, 37, 1);

            BigDecimal balance =
                    unpackComp3(balBytes, 2);

            Account acc = new Account(
                    acctNo,
                    name,
                    type,
                    balance,
                    status
            );

            accounts.add(acc);
        }

        is.close();
        return accounts;
    }

    private BigDecimal unpackComp3(byte[] data, int scale) {

        StringBuilder digits = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            int b = data[i] & 0xFF;

            int high = (b & 0xF0) >> 4;
            int low  = b & 0x0F;

            if (i == data.length - 1) {
                digits.append(high);
                if (low == 0x0D) {
                    digits.insert(0, "-");
                }
            } else {
                digits.append(high);
                digits.append(low);
            }
        }

        BigDecimal val = new BigDecimal(digits.toString());
        return val.movePointLeft(scale);
    }
}
