package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new JdbcMemberRepository(dataSource);
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}
