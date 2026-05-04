package com.library.library_management;

public class Members {

    private int memberId;      //Unique identifier for every member
    private String name;       //Full name of member
    private String email;      //email address
    private String phone;      //phone number

    public Members(int memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    //Getters

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    //Setters

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Print details of the member
    public void displayMember() {
        System.out.println("\nMember ID : " + memberId);
        System.out.println("Name      : " + name);
        System.out.println("Email     : " + email);
        System.out.println("Phone     : " + phone);
    }

    //Update information of member
    public void updateMemberInfo(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
