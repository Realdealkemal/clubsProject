package kbaproject.clubsProject.aopdemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3) //bu aop 3. sırada çalışacak demek
public class MyCloudLogAsyncAspect {

    @Before("kbaproject.clubsProject.aopdemo.LuvAopExpressions.kombin()") ///luvaopdeki kombine göre hareket et anlamındadır
    public void performApiAnalytics(){
        System.out.println("\n =====> Logging to Cloud in asycnc fashion");
    }
}
