package com.example.davidgong.donation_tracker;

import java.util.HashMap;
import java.util.List;

public class Model {
    //Singleton Model
    private static final Model instance = new Model();
    private LocationManager locationManager;
    private HashMap<String, Account> accounts;
    private List<Location> locations;

    private Model() {
        accounts = new HashMap<>();
        locationManager = new LocationManager();
    }

    public static Model getInstance() {
        return instance;
    }

    public void addAccount(String username, String password) {
        accounts.put(username, new Account(username, password));
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocations(List<Location> locations) {
        this.locations = locations;
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
