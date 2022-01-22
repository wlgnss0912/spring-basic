package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        return Optional.ofNullable(store.get(Id));//null이어도 클라이언트에서 무언가를 할 수 있게 된다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//store에서 돌면서 name을 찾는다. 하나라도 찾으면 반환이 되고, 없다면 optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//store에 values = Member
    }

    public void clearStore(){
        store.clear();
    }
}
