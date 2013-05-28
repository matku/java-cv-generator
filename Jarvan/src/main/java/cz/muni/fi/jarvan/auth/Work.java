package cz.muni.fi.jarvan.auth;


public class Work
{
    String employer;
    String position;
    String from;
    String to;

    public Work() {
        this.employer = null;
        this.position = null;
        this.from = null;
        this.to = null;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String Employer) {
        this.employer = Employer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
    
    
}