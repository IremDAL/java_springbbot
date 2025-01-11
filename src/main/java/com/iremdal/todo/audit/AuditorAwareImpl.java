package com.iremdal.todo.audit;

import com.iremdal.todo.login.AuthenticationName;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        if(AuthenticationName.isAuthenticationName()!=null){
            return Optional.of(AuthenticationName.isAuthenticationName());
        }
        return Optional.of(AuthenticationName.isAuthenticationName());
    }
}
