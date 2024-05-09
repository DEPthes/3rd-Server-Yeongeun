package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity // JPA를 사용하는구나라고 인식하고 관리함
//@Table(name ="User")
public class Member {
    @Id //꼭 persistence로 선택. PK와 매핑
    private Long id;
//    @Column(name = "userName")
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
