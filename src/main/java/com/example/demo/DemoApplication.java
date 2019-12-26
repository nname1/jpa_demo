package com.example.demo;

import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.dao.StubTransDAO;
import com.example.demo.dto.EventTransDTO;
import com.example.demo.entity.Stub_trans;
import com.example.demo.util.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.criteria.Predicate;
import java.util.List;

@EnableJpaRepositories(basePackages={"com.example.demo.dao"})
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
    public CommandLineRunner demo3(StubTransDAO repository){
        return (args) -> {
            List<EventTransDTO> stub_transList = repository.findAllTransByEventId("104094671");
            for(EventTransDTO stub_trans : stub_transList) {
                System.out.println(stub_trans.getTransId());
            }
        };
    }*/

    @Bean
    public CommandLineRunner demo5(StubTransDAO repository){
        return (args) -> {
            List<Stub_trans> stList = repository.findAll((root,query,criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.equal(root.get("id"),"247651551");
                return predicate;
            });
            for(Stub_trans st : stList){
                System.out.println("id is "+st.getId());
                System.out.println("buyer id is "+st.getBuyerId());
            }
            Pageable pageable = PageRequest.of(0,10);
            Page<Stub_trans> stPage = repository.findAll((root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.between(root.get("id").as(Integer.class),247651551,247651651);
                return predicate;
            }, pageable);
            System.out.println(stPage.getContent()); //得到数据集合列表
            System.out.println(stPage.getTotalElements());//得到总条数
            System.out.println(stPage.getTotalPages());//得到总页数
        };
    }

    @Autowired
    private Environment env;

    @Autowired
    private TestBean testBean;

    @Bean
    public CommandLineRunner demo4(StubTransDAO repository){
        return (args) -> {
            System.out.println(env.getProperty("test.url"));
            EventLikeToGoDTO eventLikeToGoDTO = repository.findEventLikeToGo("104094671");
            System.out.println(eventLikeToGoDTO.getLikeToGoCount());
            System.out.println(eventLikeToGoDTO.getEventId());
            System.out.println(testBean.getApiUrl());
        };
    }
}
