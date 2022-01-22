package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        /*//같은 이름이 있는 중복 회원은 안된다
        Optional<Member> result = memberRepository.findByName(member.getName());//optional로 한번 감싸면 if(result == null) 이런 것을 안쓰고 메서드를 쓸 수 있게 된다
        //result.orElseGet()//값이 있으면 꺼내고 없으면 아래로..
        result.ifPresent(m -> {//ifPresent : result가 null이 아니라 member에 값이 있으면 동작
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        
        //위에보다 아래처럼 많이 사용
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //ctrl + alt + shift + T => refactor 단축키
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
