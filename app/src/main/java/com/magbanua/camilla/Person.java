package com.magbanua.camilla;

public class Person {
    private String name;
    private Integer age;
    private String gender;

    public Person(){

    }

    public Person(String name, Integer age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public Integer getAge(){
        return age;
    }
}
