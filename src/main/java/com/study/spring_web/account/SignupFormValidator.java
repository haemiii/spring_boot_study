package com.study.spring_web.account;

import com.study.spring_web.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@RequiredArgsConstructor  //private-final 생성자 자동 생성
public class SignupFormValidator implements Validator {

    private final AccountRepository accountRepository;

    // 검증하려는 클래스 체크
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    // 검증
    @Override
    public void validate(Object object, Errors errors) {
        // TODO email, nickname
        SignUpForm signUpForm = (SignUpForm)object;
        if (accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }
        if (accountRepository.existsByNickname(signUpForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getEmail()}, "이미 사용중인 닉네임입니다.");

        }
    }
}
