
package cz.muni.fi.jarvan.auth.cv;

/**
 * XmlException class extend from exception
 * @author matuss
 */
public class XmlException extends Throwable
{
    public XmlException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public XmlException(String message)
    {
        super(message);
    }
}
