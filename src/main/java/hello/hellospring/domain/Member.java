package hello.hellospring.domain;


import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // IDENTITY = 자동 ID 생성
    // 시스템이 저장하는 임의의 값
    private Long id;

    // 사용자가 직접 입력하는 값값
   private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
