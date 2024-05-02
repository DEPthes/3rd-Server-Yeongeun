package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity //JPA가 관리하는 entity임을 명시
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID값을 자동 생성; Identity 전략
    private Long id;
    @Column(name="username") //DB의 column이름이 username으로 mapping됨.
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
