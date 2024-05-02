package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //build.gradle에 data-jpa 라이브러리를 받으면, 스프링부터에서 자동으로 데이터베이스와 연결된 EntityManager를 생성한다. 우리는 EntityManager를 인젝션받으면 된다. 즉, DB와 통신하기 위해 사용.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //영구 저장한다. jpa가 insert query를 만들어서 DB에 질의하고, id까지 set해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//find(조회할 타입, 식별자pk)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { //jpql 객체지향쿼리 사용
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
