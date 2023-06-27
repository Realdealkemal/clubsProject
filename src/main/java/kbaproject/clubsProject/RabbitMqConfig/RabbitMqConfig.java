package kbaproject.clubsProject.RabbitMqConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMqConfig {
    @Value("default")
    String exchange;

    @Value("firstStepQueue")
    String queueName;

    @Value("firstRoute")
    String routingKey;

    @Bean
    DirectExchange exchange() {
        //Gelen mesajları bir anahtar(routing key) kullanarak kuyruğa yazmamız lazım exchange metodu sayesinde yaparız.

        return new DirectExchange(exchange);
    }

    @Bean
    Queue firstStepQueue(){
        return new Queue(queueName, false); //ilk queuemüzü proportiesda tanımladığımız queue olarak oluşturduk.
    }

    @Bean
    Queue secondStepQueue() {
        return new Queue("secondStepQueue", true);
    }


    @Bean
    Binding binding(Queue firstStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(firstStepQueue).to(exchange).with(routingKey);
        //kuyuruğumuzu exhange metoduna routing key ile burada bağlarız

    }
    @Bean
    Binding secondBinding(Queue secondStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(secondStepQueue).to(exchange).with("secondRoute");
    }


    @Bean
    public MessageConverter jsonMessageConverter(){
        //gelen mesajların Jsona dönüştürülmesini sağlar
        return new Jackson2JsonMessageConverter();
    }
}
