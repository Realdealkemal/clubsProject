package kbaproject.clubsProject.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //servicede görülen hatayı kullanıcıya iletir
public class NoBılgıException {
    //hata bilgillerini dökmemek için bir nesne oluştur, onu döndür diyecek

    @ExceptionHandler//kullanıcıya ne göstermek isteiğimizi burada ayarlarız bu kısımlar olmazsa. hatayı saçma sapan kodlarla dödndürür
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //eğer hata yakalanırsa badrequest döndür
    public ProblemDetails handleBusinessException(BusinessException businessException){ //eğer hatan BusinessException olursa döndür
        ProblemDetails problemDetails=new ProblemDetails();
        problemDetails.setMessage(businessException.getMessage());

        return problemDetails; //problem details objesini döndürür.Json mantığı. String yerine obje döndürmek daha mantıklı
    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //eğer hata yakalanırsa badrequest döndür
    public ProblemDetails handleValidxception(MethodArgumentNotValidException methodArgumentNotValidException){ //eğer hatan BusinessException olursa döndür
        ProblemDetails problemDetails=new ProblemDetails();
        problemDetails.setMessage("Valisdation Problems");

        return problemDetails;
    }
    @ExceptionHandler
    @ResponseStatus(code=HttpStatus.BAD_GATEWAY)
    public String hataDeneme(HttpMessageNotReadableException generalException){
        return "Yaptığınız istek gönderilemedi";
    }

}
