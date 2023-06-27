package kbaproject.clubsProject.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowingAdviceType {
    //eğer bir hata yakalanmışşsa anında devreey girer
    @Pointcut("execution(* kbaproject.clubsProject.Business.concretes.PlayerServiceImpl.*(..))")
    public void PlayersdaThrowAt(){}

    @AfterThrowing(value="PlayersdaThrowAt()",throwing="theExc")
    public void afterThrowingPlayer(JoinPoint joinPoint, Throwable theExc){
        System.out.println("\n aspect hatayı yakaladı - The exception is:"+ theExc);

    }
}
