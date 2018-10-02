package com.example.davidgong.donation_tracker;

import java.util.HashMap;

public class Model {
    //Singleton Model
    private static final Model instance = new Model();
    private HashMap<String, Account> accounts;

    private Model() {
        accounts = new HashMap<>();
    }

    public static Model getInstance() {
        return instance;
    }

    public void addAccount(String username, String password) {
        accounts.put(username, new Account(username, password));
    }

    public boolean containsUsername(String username) {
        return accounts.containsKey(username);
    }

    public boolean isAccount(String username, String password) {
        return containsUsername(username) && accounts.get(username).getPassword().equals(password);
    }

    public boolean validPassword(String password) {
        return Account.validPassword(password);
    }

    public boolean validUsername(String username) {
        return Account.validUsername(username);
    }
}
