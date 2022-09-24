package com.moudjames23.sms;

import com.moudjames23.sms.entities.Customer;
import com.moudjames23.sms.entities.Template;
import com.moudjames23.sms.repositories.CustomerRepository;
import com.moudjames23.sms.services.CustomerService;
import com.moudjames23.sms.services.TemplateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }


    @Bean
    public CommandLineRunner seeder(final CustomerService customerService, final TemplateService templateService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {



                List<Customer> customers = new LinkedList<>();
                customers.add(
                        Customer.builder()
                                .name("Moud")
                                .phone("620029489")
                                .status(true)
                                .build()
                );
                customers.add(
                        Customer.builder()
                                .name("Yansané")
                                .phone("622095143")
                                .status(true)
                                .build()
                );

                customers.add(
                        Customer.builder()
                                .name("Maurel")
                                .phone("622101062")
                                .status(true)
                                .build()
                );

                customerService.saveAll(customers);


                Template template = Template.builder()
                        .title("Bienvenu")
                        .content("Message de bienvenu")
                        .build();

                templateService.create(template);
            }
        };
    }
}
