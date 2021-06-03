package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스 - 인터페이스 : extends
// 인터페이스는 다중상속 가능
// 클래스로 구현체를 만들지 않고 인터페이스로 해도 괜찮은 이유 : JpaRepository를 상속 받으면 스프링 데이터 jpa가 자동으로 구현체를 만들고 bean 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 헐 이게 다래
    @Override
    Optional<Member> findByName(String name);
}
