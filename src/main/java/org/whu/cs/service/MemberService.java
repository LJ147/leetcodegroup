package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.Member;
import org.whu.cs.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public boolean existsByAddress(String url) {
        return memberRepository.existsByUrl(url);
    }

    public List<Member> findALlByStatus(int status) {
        return memberRepository.findByStatus(status);
    }

    
    public void save(Member member) {
        memberRepository.save(member);
    }
}
