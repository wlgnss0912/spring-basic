package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//Test가 하나 끝날 때 마다 수행
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("JHJUN");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//Optional은 get으로 꺼낼 수 있다

        //Assertions.assertEquals(result, member);//기대 , 결과
        assertThat(member).isEqualTo(result);
        //assertThat(member).isEqualTo(null);
    }
    
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("JHJUN1");
        repository.save(member1);
        
        Member member2 = new Member();//shift + F6 모두 rename
        member2.setName("JHJUN2");
        repository.save(member2);
        
        Member result = repository.findByName("JHJUN1").get();//get을 쓰면 optional을 따서 꺼낼 수 있다.

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("JHJUN1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("JHJUN2");
        repository.save(member2);

        List<Member> result = repository.findAll();//ctrl + alt + v : 자동완성

        assertThat(result.size()).isEqualTo(2);
    }
}
