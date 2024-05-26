package dancebase.springmvc.security.rest.exeption_handing;

public class NoSuchDancerException extends RuntimeException{
    public NoSuchDancerException(String message){
        super(message);
    }
}
