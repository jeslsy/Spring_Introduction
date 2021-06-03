package hello.hellospring;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // jpa 전까지
//  private final DataSource dataSource;
//
//  public SpringConfig(DataSource dataSource) {
//      this.dataSource = dataSource;
//  }

    // jpa 사용시
//  private EntityManager em;
//
//  @Autowired
//  public SpringConfig(EntityManager em) {
//      this.em = em;
//  }

    // 아까 memberRepository 인터페이스 여도 자동 구현체 생성 & 빈 주입 해준다고 했잖음
    // 그래서 여기 바로 DI 되는것
    private final MemberRepository memberRepository;

    @Autowired // 생성자가 한개면 생략해도됨
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 스프링빈에 등록하라는 뜻
    @Bean
    public MemberService memberService(){
        // 아래 memberRepository
        return new MemberService(memberRepository);  // 이 로직을 실행해서 스프링빈에 등록해줌
    }



// 스프링 데이터 JPA 쓰면 위에 MemberService에 memberRepository만 적어주면 됨.
// 밑에 다 주석처리


//    @Bean
//    public MemberRepository memberRepository(){
        // Memory 사용
        // return new MemoryMemberRepository();

        // 순수 jdbc 사용
        //return new JdbcMemberRepository(dataSource);

        // JdbcTemplate 사용
        //return new JdbcTemplateMemberRepository(dataSource);

        // Jpa 사용
        //return new JpaMemberRepository(em);

//        return
//    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }


}
