package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
public class JpaMain {
    public static void main(String[] args) { /*psvm*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //DB에 연결됨

        EntityManager em = emf.createEntityManager(); //createEntityManger를 꺼냄

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 추가
        tx.begin();

        try{
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
