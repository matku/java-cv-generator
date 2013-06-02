package cz.muni.fi.jarvan.auth;

/**
 * Exception class extends from Exception
 * 
 */
public class CvException extends Throwable
{
    public CvException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public CvException(String message)
    {
        super(message);
    }
}