package com.sangwon97.firstspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sangwon97.firstspring.domain.Member;
import com.sangwon97.firstspring.repository.MemberRepository;

@Service
@Component
public class MemberService {

    private final MemberRepository memberRepository;
    
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;

    }

    //회원 가입
    public Long join(Member member){
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
        
    //중복회원 가입 불가
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }    
    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
    
}
