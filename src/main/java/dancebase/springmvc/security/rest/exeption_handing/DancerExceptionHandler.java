package dancebase.springmvc.security.rest.exeption_handing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DancerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DancerIncorrectData>  hendlerException( NoSuchDancerException exception){
       DancerIncorrectData data = new DancerIncorrectData();
       data.setInfo(exception.getMessage());
       return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<DancerIncorrectData> allExceptions(Exception ex){
        DancerIncorrectData data = new DancerIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }
}
