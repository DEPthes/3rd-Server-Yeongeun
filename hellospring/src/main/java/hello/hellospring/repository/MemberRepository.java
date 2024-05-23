package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //Optional은 값이 없을때 null을 optional에 감싸서 반환한다.
    Member save(Membe r member);  //회원이 저장소에 저장됨
    Optional<Member> findById(Long id); // id로 회원을 찾음
    Optional<Member>  findByName(String name); //name으로 회원을 찾음
    List<Member> findAll(); // 지금까지 저장된 모든 회원 list를 다 반환함
}

