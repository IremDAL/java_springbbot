package com.iremdal.todo.login;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Log4j2

public class AuthenticationName {
    public static String isAuthenticationName() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            log.warn("Sistemdeki Kullanıcı Bilgileri: " + authentication.getPrincipal());
            log.warn("Sistemdeki Kullanıcı Adı: " + authentication.getName());
            return authentication.getName();
        }
        return "İrem Dal";
    }
}
