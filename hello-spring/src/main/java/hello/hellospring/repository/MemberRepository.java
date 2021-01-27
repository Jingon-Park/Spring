package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //save하면 저장소에 저장되는 기능의 인터페이스
    Optional<Member> findById(Long id); //Optional은 찾는 값이 null일때 처리 하기 위해서
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
