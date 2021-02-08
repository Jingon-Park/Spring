package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //이 어노테이션을  Test 케이스에 넣으면 디비에 저장했다가 Rollback해준다.

class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void join() {
        //given << 주어지는 data
        Member member = new Member();
        member.setName("hello");

        //when << 어느 상황을 테스트 하는지
        Long saveId = memberService.join(member);

        //then << 어느 결과가 나와야 하는지
        Member findMember = memberService.findOne(saveId).get();

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //테스트 가능은 하나 별로 좋지는 않ㅇ므
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1231231");
        }*/

        //then
    }
}