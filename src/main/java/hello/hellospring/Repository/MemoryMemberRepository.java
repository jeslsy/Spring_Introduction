package hello.hellospring.Repository;

import hello.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        // member의 id값 세팅
        member.setId(++sequence);
        // store에 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프로 돌린대
        return store.values().stream()
                // 람다를 사용
                // 가져온 name이 넘어온 parameter의 name이랑 같은지 확인
                .filter(member -> member.getName().equals(name))
                // 찾으면 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // Map의 값인 member들을 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        // store 싹 비움
        store.clear();
    }
}
