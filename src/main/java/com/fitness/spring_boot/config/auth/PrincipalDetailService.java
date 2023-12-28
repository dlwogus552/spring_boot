package com.fitness.spring_boot.config.auth;


import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member=memberRepository.findById(id);
        if(member==null){
            return null;
        }
        PrincipalDetails puser=new PrincipalDetails(member);
        log.info(puser);
        return puser;
    }
}
