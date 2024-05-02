package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //memberService의 join() 메서드를 사용하여 회원을 등록할 때, 동일한 회원을 memberService의 findOne() 메서드를 사용하여 동일한 이름으로 검색할 수 있는지 확인

        //given 어떤 데이터를 기반으로?
        Member member = new Member();
        member.setName("hello");

        //when 무엇을 검증하는가?
        Long saveId = memberService.join(member);

        //then 결과
        //join() 메서드에서 반환된 ID를 사용하여 서비스에서 회원을 검색하고, 원래의 member 객체와 동일한 이름을 가진 회원인지 확인
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // memberService.join(member2)를 넣으면 IllegalStateException.class(예외)가 터져야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}