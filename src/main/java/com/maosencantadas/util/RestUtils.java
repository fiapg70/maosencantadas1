package com.maosencantadas.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestUtils {

    public static URI getUri(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
