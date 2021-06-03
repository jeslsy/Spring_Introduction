package hello.hellospring.service;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
// @Transactional 데이터 변경할때 있어야됨
public class MemberService {

    private final MemberRepository memberRepository;

    // Autowired = memberService는 memberRepository가 필요함
    //@Autowired
    // test케이스에서 memberRepository new로 새로 생성하는거 해결하려고 하는 코드임.
    // 외부에서 넣어주도록 바꿔줌
    // 이거 생성자임
    // 지금 구현체는 MemberRepository 인터페이스를 상속받은 MemoryMemberRepository 클래스로 존재
    // 이 구현체가 여기로 오는 것
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /***
     *회원 가입
     */
    // 가입하기
    public Long join(Member member){

        // 일단 이름을 찾아
        // 값이 있으면
        // 예전에는 if null 이면 이런식으로 코딩함.
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    // 중복 거르기
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 결과가 return Optional<Member> result 이니까
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     *
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
