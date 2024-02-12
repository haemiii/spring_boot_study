package com.study.spring_web.account;

import lombok.Data;

@Data
public class SignUpForm {

    private String nickname;

    private String email;

    private String password;
}
