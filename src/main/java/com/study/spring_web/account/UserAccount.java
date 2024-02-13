package com.study.spring_web.account;

import com.study.spring_web.domain.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

// security가 다루는 user정보랑 도메인에서 필요로하는 user정보의 갭을 메워주기 위함
// UserAccount를 pricipal 객체로 사용
@Getter
public class UserAccount extends User {
    private final Account account;
    public UserAccount(Account account) {
        super(account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }
}
