package com.example.spring02.security;

import com.example.spring02.model.Member;
import com.example.spring02.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + nickname));

        return new UserDetailsImpl(member);
    }
}
