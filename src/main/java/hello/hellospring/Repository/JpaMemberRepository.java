package hello.hellospring.Repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// jpa 쓰면 저장, 삽입, 삭제 쿼리 짜지 않아도 됨
// 다 자동으로 됨
// 근데 pk 기반인 findByName이나 findAll은 jp 쿼리문 짜줘야함
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // insert 쿼리 만들어서 db에 넣고 id까지 set해줌
        // 영속성
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 특별하게 jpql이라는 객체지향 쿼리 언어를 써야 됨
        // 원래 테이블을 대상으로 쿼리를 날리는데, 객체를 대상으로 쿼리를 날리는 것
        // 여기서는 Member Entity를 대상으로 쿼리를 날림
        // select m = member자체를 select 함
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
