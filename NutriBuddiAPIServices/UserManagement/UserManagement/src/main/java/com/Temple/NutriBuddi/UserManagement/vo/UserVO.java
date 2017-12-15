package com.Temple.NutriBuddi.UserManagement.vo;

import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;


public class UserVO {

    private String email;
    private String password;
    private String password2;
    private String userName;
    private String first;
    private String last;
    private String height;
    private String weight;
    private String age;
    private String gender;
    private UserRepository userRepository;

    public UserVO(String email, String password, String password2, String height, String weight, String age, String gender, UserRepository userRepository){
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.userName = userName;
        this.first = first;
        this.last = last;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.userRepository = userRepository;
    }

    public UserVO(String email, String password, String password2, String userName, String first, String last,
                  String height, String weight, String age, String gender, UserRepository userRepository){
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.userName = userName;
        this.first = first;
        this.last = last;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.userRepository = userRepository;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
