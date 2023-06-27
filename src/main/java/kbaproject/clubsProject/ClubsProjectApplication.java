package kbaproject.clubsProject;

import kbaproject.clubsProject.core.utilities.exceptions.BusinessException;
import kbaproject.clubsProject.core.utilities.exceptions.ProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableAutoConfiguration
public class ClubsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubsProjectApplication.class, args);
	}




	@Bean//kod çalışmaya başlar başlamaz gelir bunu oluşturur ve IOCye atar. Çünkü modelmapper kendisi eklemez, normal bir sınıftır. MOdelMapper, @Service,@Component anatosyonu gibi bir anotasyon kullanmaz. Çünkü maven bağımlılığıyla eklediğimiz 3. parti koddur iç kodlarıyla işlem yapamayız
	public ModelMapper getModelMapper(){
		//uygulama açıldığında bir yer modelMapper dependecy enjeksiyonu isyecek. Bu yüzden bunu newlememiz lazım
		return new ModelMapper();
	}
}
