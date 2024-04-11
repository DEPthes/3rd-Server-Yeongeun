package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test가 끝날때 마다 repository를 지워주는 코드
    //test들끼리는 의존관계가 없기 때문
    @AfterEach //각 test가 끝날때마다 저장소를 지움
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        // db에서 꺼낸 result와 test를 위한 member가 같으면 참
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByfind(){
        // name이 Spring1인 회원등록
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        // name이 Spring2인 회원등록
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // DB에서 Spring1이라는 이름을 찾아 get()한 result와 member1이 동일하면 참
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        // name이 Spring1인 회원등록
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        // name이 Spring2인 회원등록
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
