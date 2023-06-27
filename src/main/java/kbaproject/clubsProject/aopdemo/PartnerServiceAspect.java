package kbaproject.clubsProject.aopdemo;//package kbaproject.clubsProject.aopdemo;

import kbaproject.clubsProject.entities.PlayersPartner;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect //bu class bir yerleri dinleyecek demek
@Component
@Order(1) //bu aop 1. sırada çalışacak demek
public class PartnerServiceAspect {
   // @Pointcut("execution(* *.getAll(..)") herhangi bir yerdeki getalla eşleşmek için bunu kullanırız
  //  veya bunu da kullanabiliriz @Pointcut("execution(* getAll(..))")
    //@Before methoda gitmeden önce çalıştırılmak için kullanılır
    //@AfterReturning methoda gidip başarılı bir sonuç verildikten sonra kullanılır

   //@Pointcut("execution(* *.get(..))") //herhangi bir yerdeki getle başlayan bir methodla eşleşmek için eşleşmek için bunu kullanırız

    // @Pointcut("execution(* *.getAll()") herhangi bir yerdeki getalla eşleşen ve parametre almayan method için bunu kullanırız
    // @Pointcut("execution(* *.add(kbaproject.clubsProject.Business.requests.CreatePlayerRequest)") sadece parametre olarak createplayerrequest nesnesini alan ve adı add olan method anlamı gelir. parantez içi paketin adını yazarız
    // @Pointcut("execution(* *.add(kbaproject.clubsProject.Business.requests.CreatePlayerRequest, ..)")parametre olarak createplayerrequest nesnesini ve sonra başka parametreleri alan veya almayan ve adı add olan method anlamı gelir. parantez içi paketin adını yazarız

/*
        @Before("aopMesajver()") //service paketinin altındaki herhangi bir classda herhangi bir methoda giden çağrında o method çalışmadan önce devreye gir demek
    public void mesajVerMethondundanOnce(JoinPoint joinPoint){ //mesaj ver methodu çalışmadan önce kendi yazdığım method çalışssın istiyoruz
     //   System.out.println("Mesaj ver metodundan önce parametre yakalandı: "+joinPoint.getArgs()[0]);
        //o paametrenin ilk argümantını verir
     //  System.out.println(joinPoint.getSignature());
       System.out.println("before method");

        //yetki kontrolü gibi işlemleri beferode ile yapmak daha manyıkldır

        //aynı pointcut aynı tip için(örneğin @before) 2 defa veya fazlaca kullanılabilir
    }

    @After("aopMesajver()")
    public void mesajVerMethodundanSonra(JoinPoint joinPoint){
       // System.out.println("Mesaj ver metodundan sonra parametre yakalandı: "+joinPoint.getArgs()[0]);
        System.out.println("after method");

    }
       */


    @Before("kbaproject.clubsProject.aopdemo.LuvAopExpressions.kombin()") //luvaopdeki  sınıfındaki kombine göre hareket et anlamındadır
    public void beforeAspect(JoinPoint joinPoint){
        System.out.println("\n =====> @Before PartnerAspect");
           MethodSignature methodSig= (MethodSignature) joinPoint.getSignature(); //method imzasını bu şekilde alırız
        System.out.println(methodSig);


        Object[] args= joinPoint.getArgs(); //methoda gönderilen parametreleri bu şekilde alırız
        //Arrays.stream(args).forEach(k -> System.out.println(k));
        for(Object tempArg: args){
            System.out.println(tempArg);

            if (tempArg instanceof PlayersPartner){
                PlayersPartner pP =(PlayersPartner) tempArg;
                System.out.println("partnersName:"+pP.getName());
            }

        }
    }

}