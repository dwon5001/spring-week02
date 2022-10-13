package com.example.spring02.dto;

import com.example.spring02.model.Authority;
import com.example.spring02.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {


    @Pattern(regexp = "([a-zA-Z0-9])",
            message = "최소 4자 이상, 12자 이하 알파벳 대소문자와 숫자만 허용")
    @Size(min = 4, max = 12)
    private String nickname;
    @Pattern(regexp = "([a-z0-9])",
            message = "최소 4자 이상이며, 32자 이하 알파벳 소문자와 숫자만 허용")
    @Size(min = 4, max = 32)
    private String password;
    private String passwordConfirm;
    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(nickname, password);
    }
}