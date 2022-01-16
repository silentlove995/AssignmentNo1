package com.microservice.gateway.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Service
public class AccessPathBuilder {
    private Map<String, List<String>> access = new HashMap<>();
    private String pathAccess;
    private List<String> roleAccess;

    public AccessPathBuilder setPathAccess(String pathAccess) {
        this.pathAccess = pathAccess;
        return this;
    }

    public AccessPathBuilder setRoleAccess(Object... objects) {
        this.roleAccess = Arrays.stream(objects).sequential().map(Object::toString).collect(Collectors.toList());
        this.access.put(this.pathAccess, roleAccess);
        return this;
    }
}
