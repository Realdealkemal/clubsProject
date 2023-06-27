package kbaproject.clubsProject.aopdemo;

import jakarta.persistence.JoinColumn;
import kbaproject.clubsProject.entities.PlayersPartner;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class AfterReturningAdviceType {

    @Pointcut("execution(* kbaproject.clubsProject.Business.concretes.PlayersPartnerImpl.getAll(..))")
    public void afterReturningPartners() {
    }



    @AfterReturning(value = "afterReturningPartners()", returning = "result")
    public void afterReturningFindAll(JoinPoint joinPoint, List<PlayersPartner> result) {

        //burada returndan sonra işlemleri gerçekleştireceğiz
        //son güncellemelyi burada gerçekleştiririz ve buradan sonra kullanıcıya değer döndürürüz
        String methıd = joinPoint.getSignature().toShortString();
        System.out.println("------>>>> executing @After method" + methıd);

        result.get(0).setName("sabriii"); //ilk sıradaki sonucun adını sabri diye değiştirebiliriz ama görüntüde değişir. Veritabanına işlemez.

        System.out.println(result); //toString olduğu için çalışıyor

    }
}