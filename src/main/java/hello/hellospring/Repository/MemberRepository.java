package hello.hellospring.Repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 4가지 기능
public interface MemberRepository {
    Member save(Member member);

    // Optional(java8 기능) : find로 받아올때 null일 경우 Optional로 감싸서 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    // 이제까지 저장한 모든 회원 리스트 반환
    List<Member> findAll();
}
