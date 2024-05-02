package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository; // 스프링 컨테이너에서 memberRepository를 찾는다.

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
}
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // 순수 Jdbc와 연결
//        return new JdbcTemplateMemberRepository(dataSource) // JdbcTemplate와 연결
//        return new JpaMemberRepository(em);
//    }
//}
