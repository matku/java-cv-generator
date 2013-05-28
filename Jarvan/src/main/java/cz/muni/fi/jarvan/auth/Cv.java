package cz.muni.fi.jarvan.auth;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;






public class Cv
{
    //meta udaje Cv
    String name;            //format: name-given-by-user_user-email
    String lastEdit;
    
    //udaje
    String title;
    String titleAfter;
    String firstName;
    String lastName;
    String dateOfBirth;
    boolean male;
    
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
    Map<String, String> languages;              //key - language, value - level
    
    //others
    List<String> skills;

    public Cv() {
        this.name = null;
        this.lastEdit = null;
        this.title = null;
        this.firstName = null;
        this.lastName = null;
        this.dateOfBirth = null;
        this.email = null;
        this.homeNumber = null;
        this.mobileNumber = null;
        this.street = null;
        this.city = null;
        this.zip = null;
        this.state = null;
        this.highestEducation = null;
        this.highSchool = null;
        this.university = null;
        this.work = null;
        this.languages = null;
        this.skills = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitleAfter() {
        return title;
    }

    public void setTitleAfter(String titleAfter) {
        this.titleAfter = titleAfter;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws CvException {
        if (firstName.matches("[A-Z]([a-z])*"))
        {
            this.firstName = firstName;
        }
        else
        {
            throw new CvException("Wrong first name format");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws CvException {
        if (lastName.matches("[A-Z]([a-z])*"))
        {
            this.lastName = lastName;
        }
        else
        {
            throw new CvException("Wrong last name format");
        }
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws CvException {
        String[] split = dateOfBirth.split("\\.", 3);
        if (Integer.parseInt(split[0]) > 0 && Integer.parseInt(split[0]) <= 31 &&
            Integer.parseInt(split[1]) > 0 && Integer.parseInt(split[1]) <= 12 &&
            Integer.parseInt(split[2]) > 1900 && Integer.parseInt(split[2]) <= 2100)
        {
            if (Integer.parseInt(split[2]) > Calendar.getInstance().get(Calendar.YEAR))
            {
                throw new CvException("birth date not yet passed");
            }
            this.dateOfBirth = dateOfBirth;
        }
        else
        {
            throw new CvException("wrong birth date parameters");
        }
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) throws CvException {
        try
        {
            Integer.parseInt(homeNumber);
            this.homeNumber = homeNumber;
        } catch (NumberFormatException e)
        {
            throw new CvException("wrong home number format");
        }
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) throws CvException {
        try
        {
            Integer.parseInt(mobileNumber);
            this.mobileNumber = mobileNumber;
        } catch (NumberFormatException e)
        {
            throw new CvException("wrong mobile number format");
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws CvException {
        if (street.matches("[A-Z]([a-z])*( ([A-Z]|[a-z])([a-z])*)*( ([0-9])+){0,1}"))
        {
            this.street = street;
        }
        else
        {
            throw new CvException("wrong street format");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws CvException {
        if (city.matches("[A-Z]([a-z ])*"))
        {
            this.city = city;
        }
        else
        {
            throw new CvException("wrong city format");
        }
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) throws CvException {
        if (zip.matches("[0-9]{5}|([0-9]{3} [0-9]{2})"))
        {
            this.zip = zip;
        }
        else
        {
            throw new CvException("wrong zip code format");
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public List<Education> getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(List<Education> highSchool) {
        this.highSchool = highSchool;
    }

    public List<Education> getUniversity() {
        return university;
    }

    public void setUniversity(List<Education> university) {
        this.university = university;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    
    
    
    
}