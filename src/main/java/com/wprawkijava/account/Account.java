package com.wprawkijava.account;

public class Account {
    private boolean active;
    private Address defaultDeliveryAddress;
    private String email;

    public Account(){
        this.active = false;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if(defaultDeliveryAddress != null){
            activate();
        } else{
            this.active = false;

        }
    }

    public void activate(){
        this.active = true;
    }
    public boolean isActive(){
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
    public void setEmail(String email){
        if (email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")){
            this.email = email;

        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEmail() {
        return email;
    }
}
