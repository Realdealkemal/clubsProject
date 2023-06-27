package kbaproject.clubsProject.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class AfterAdviceType {
    //pointcutta method çalışsın veya hata alsın bu after yaptığımız zaman yine de döner ve sonuç verir

        //eğer bir hata yakalanmışşsa anında devreey girer
        @Pointcut("execution(* kbaproject.clubsProject.Business.concretes.ClubServiceImpl.*(..))")
        public void ClubdeThrowAt(){}

        @After(value="ClubdeThrowAt()")
        public void afterCalısıor(JoinPoint joinPoint){
            System.out.println("\n After çalışıyor");

        }
}
