package hello.hellospring.service;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
// @transactional = test 실행 전 트랙잭션 먼저 실행, db insert 쿼리하고, 다시 롤백을 해서 test에 했던 db 사용을 되돌려줌
// 기존처럼 aftereach, beforeeach를 안해줘도 db가 다시 초기화됨
class MemberServiceIntergrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //@Commit
    // @Commit 하면 db에 들어가
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring!!!!!!");

        //when
        Long saveId = memberService.join(member); // return 저장 아이디

        //then
        Member findMember = memberService.findOne(saveId).get();  // get으로 optional 바로 받아온다 했나?
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 위에는 반쪽짜리 테스트임
    // 예외 핸들링이 더 중요
    @Test
    public void 증복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));  // 검사

    }
}