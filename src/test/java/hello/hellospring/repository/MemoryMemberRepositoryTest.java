package hello.hellospring.repository;

import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 순서 상관없이 test 진행됨
// 다른 test 진행할 때 이미 저장되어 있는 객체 땜에 오류 가능
// clear해주자.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // clear해주는 코드
    // 이제 에러 안남
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // id 꺼내기
        // get()하면 optional 한 번 까서 나옴
        Member result = repository.findById(member.getId()).get();

        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);

    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        //Assertions.assertEquals(member2, result);
        assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //Assertions.assert(member2, result);
        assertThat(result.size()).isEqualTo(2);
   }




}
