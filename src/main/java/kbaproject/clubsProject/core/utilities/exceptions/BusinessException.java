package kbaproject.clubsProject.core.utilities.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message); //runtime exceitpondaki süpere yolla hata ve hatayı yazsın
    }
}