package com.microservice.gateway.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Service
public class VerifyPathBuilder {
    @Autowired
    private AccessPathBuilder accessPathBuilder;

    private String path;
    private List<String> roles;

    public VerifyPathBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public VerifyPathBuilder setRoles(String[] obj) {
        this.roles = Arrays.asList(obj);
        return this;
    }

    public boolean build() {
        return verifyPath();
    }

    private boolean verifyPath() {
        if (Objects.nonNull(path)) {
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (Map.Entry<String, List<String>> entry : accessPathBuilder.getAccess().entrySet()) {
                List<String> accessVerifyRoles = entry.getValue();
                this.roles.retainAll(accessVerifyRoles);
                if (antPathMatcher.match(entry.getKey(), this.path) && !this.roles.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
}
