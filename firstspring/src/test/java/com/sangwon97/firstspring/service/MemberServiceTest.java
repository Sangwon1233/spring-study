package com.sangwon97.firstspring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sangwon97.firstspring.domain.Member;
import com.sangwon97.firstspring.repository.MemoryMemberRepository;

public class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //테스트 메소드가 끝날때마다 깔끔하게 지워주는거
    public void afterEach(){
        memberRepository.clearStore();

    }

    @Test
    void join회원가입(){
        //given
        Member member = new Member();
        member.setName("hello");


        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // try{
        //     memberService.join(member2);
        //     fail("예외가 발생해야 합니다");
        // } catch (IllegalStateException e){
        //     assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // }

        //then
    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }
}
