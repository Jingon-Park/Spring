package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    //service는 비즈니스에 의존적으로 설계하는 것이 바람직하다. EX) join이라는 메소드를 보면 회원 가입이라는 이미지가 떠오른다.
    private final MemberRepository memberRepository;

    //DI

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    회원가입
     */
    public Long join(Member member) {
        //같은 이름 중복 X
        //result가 Optional이므로 ifPresent를 사용 가능함.
        //Optional은 바로 꺼내서 쓰지마라 (result.get())
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        });
         */

        //Optional을 reult로 꺼내서 쓰는것 보다 더 좋은 코드
        memberRepository.findByName(member.getName())  //이 자체를 메소드로 만드는 것이 베스트(validateDuplicateMember)
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        //validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();


    }
//    private void private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getName())  //이 자체를 메소드로 만드는 것이 베스트
//                .ifPresent(m ->{
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }

    /**
     * 전체 이름 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);

    }
}
