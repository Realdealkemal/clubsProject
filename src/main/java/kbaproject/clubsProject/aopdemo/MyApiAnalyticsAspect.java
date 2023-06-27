package kbaproject.clubsProject.aopdemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2) //bu aop 2. sırada çalışacak demek
public class MyApiAnalyticsAspect {

    @Before("kbaproject.clubsProject.aopdemo.LuvAopExpressions.kombin()") //luvaopdeki kombine göre hareket et anlamındadır
    public void performApiAnalytics(){
        System.out.println("\n =====> performing API Analytics");
    }
}
