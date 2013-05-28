package cz.muni.fi.jarvan.auth;


public class Education
{
    String from;
    String to;
    String name;
    String city;
    String fieldOfStudy;

    public Education() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) throws CvException {
        if (Integer.parseInt(from) >= 1900 && Integer.parseInt(from) <= 2100)
        {
            this.from = from;
        }
        else
        {
            throw new CvException("wrong year");
        }
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) throws CvException {
        if (Integer.parseInt(to) >= 1900 && Integer.parseInt(to) <= 2100)
        {
            this.to = to;
        }
        else
        {
            throw new CvException("wrong year");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }
    
    
}