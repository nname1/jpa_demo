package com.example.demo;

import com.example.demo.entity.Stub_trans;
import com.example.demo.entity.Ticket;
import com.example.demo.repo.StubTransRepository;
import com.example.demo.repo.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TicketRepository repository){
	    return (args) -> {
            List<Ticket> ticketList = repository.findByEventId(104094671L).get();
            for(Ticket ticket : ticketList) {
                if(!StringUtils.isEmpty(ticket.getTransId())){
                    System.out.println(ticket.getId()+" -- "+ ticket.getTransId());
                }
            }
        };
    }
    @Bean
    public CommandLineRunner demo2(StubTransRepository repository){
        return (args) -> {
            List<Stub_trans> stub_transList = repository.findByTicketId(1461074262L).get();
            for(Stub_trans stub_trans : stub_transList) {
                System.out.println(stub_trans.getId());
            }
        };
    }
}
