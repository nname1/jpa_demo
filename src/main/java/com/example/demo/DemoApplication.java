package com.example.demo;

import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.dto.EventTransDTO;
import com.example.demo.entity.Event;
import com.example.demo.repo.EventRepository;
import com.example.demo.repo.StubTransRepository;
import com.example.demo.util.PropertyUtils;
import com.example.demo.util.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;
@EnableJpaRepositories(basePackages={"com.example.demo.repo"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(TicketRepository repository){
	    return (args) -> {
            List<Ticket> ticketList = repository.findByEventId(104094671L).get();
            for(Ticket ticket : ticketList) {
                if(!StringUtils.isEmpty(ticket.getTransId())){
                    System.out.println(ticket.getId()+" -- "+ ticket.getTransId());
                }
            }
        };
    }*/
	/*@Bean
	public CommandLineRunner demo1(EventRepository repository){
	    return (args) -> {
            Event event = repository.findById(104094671L).get();
            Date date = event.getCreatedDate();
            System.out.println(date.toString());
        };
    }*/

    /*@Bean
    public CommandLineRunner demo2(StubTransRepository repository){
        return (args) -> {
            List<Stub_trans> stub_transList = repository.findByTicketId(1461074262L).get();
            for(Stub_trans stub_trans : stub_transList) {
                System.out.println(stub_trans.getId());
            }
        };
    }*/
    /*@Bean
    public CommandLineRunner demo3(StubTransRepository repository){
        return (args) -> {
            List<EventTransDTO> stub_transList = repository.findAllTransByEventId(104094671L);
            for(EventTransDTO stub_trans : stub_transList) {
                System.out.println(stub_trans.getTransId());
            }
        };
    }*/

    @Autowired
    private Environment env;

    @Autowired
    private TestBean testBean;

    @Bean
    public CommandLineRunner demo4(StubTransRepository repository){
        return (args) -> {
            System.out.println(env.getProperty("test.env"));
            EventLikeToGoDTO eventLikeToGoDTO = repository.findEventLikeToGo("104094671");
            System.out.println(eventLikeToGoDTO.getLikeToGoCount());
            System.out.println(eventLikeToGoDTO.getEventId());
            System.out.println(testBean.getApiUrl());
        };
    }
}
