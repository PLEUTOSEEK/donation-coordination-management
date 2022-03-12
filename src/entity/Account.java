/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public abstract class Account {

    protected String accountID;
    protected String name;
    protected char gender;
    protected String ic;
    protected String email;
    protected String phoneNo;
    protected String address;
    protected static String lastId;

    public Account() {

    }

    public Account(String accountID, String name, char gender, String ic, String email, String phoneNo, String address) {
        this.accountID = accountID;
        this.name = name;
        this.gender = gender;
        this.ic = ic;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // need to have auto generate ID
    public abstract String autoGenerateID();

    @Override
    public String toString() {
        return address + "\n" + phoneNo + "\n";
    }
}
