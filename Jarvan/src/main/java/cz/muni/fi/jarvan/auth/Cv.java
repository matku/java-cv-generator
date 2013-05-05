package cz.muni.fi.jarvan.auth;

import java.util.List;
import java.util.Map;






public class Cv
{
    //meta udaje Cv
    String name;            //format: name-given-by-user_user-email
    String lastEdit;
    //udaje
    String firstName;
    String lastName;
    String dateOfBirth;
    boolean male;
    String nationality;
    //kontakt
    String email;
    String homeNumber;
    String mobileNumber;
    String street;
    String city;
    String zip;
    String state;
    //education
    String highestEducation;
    List<Education> highSchool;
    List<Education> university;
    //work
    List<Work> work;
    //language
    Map<String, String> languages;
    //others
    List<String> other;
    
    
    
    
}