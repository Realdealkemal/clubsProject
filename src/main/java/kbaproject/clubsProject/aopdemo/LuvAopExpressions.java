package kbaproject.clubsProject.aopdemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class LuvAopExpressions {

    //Pointcut expressionları ayrı bir cclassda toplayarak diğer classlardan sırayla ulaşma konusunda yetkilendirebiliriz
    @Pointcut("execution(* kbaproject.clubsProject.Business.concretes.PlayersPartnerImpl.*(..))")  //genel method yazzarız içini boş bırakırız
    public void aopMesajver(){}
    //birden fazla pointcut birbiriyle birleştirilebilir. bu birleşimler if birleşimi gibi çalışır
    @Pointcut("execution(* kbaproject.clubsProject.Business.concretes.PlayersPartnerImpl.get*(..))")  //genel method yazzarız içini boş bırakırız
    public void getleBaslayanMethodlar(){}

    @Pointcut("aopMesajver() &&  ! getleBaslayanMethodlar()")
    public void kombin(){
    }
}
