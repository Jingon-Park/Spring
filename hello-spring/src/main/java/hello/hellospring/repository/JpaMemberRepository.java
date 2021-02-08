package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입, 식별자 값
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny(); //리턴타입이 Optional이라 그런듯
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //Entity 대상으로 쿼리문을 날린다. Member AS m 이라고 쓴것과 같음
                .getResultList();
    }
}
