package com.bougsid.util;

import com.bougsid.dao.UserRepository;
import com.bougsid.entities.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bougsid.ayoub on 3/6/2017.
 */
@Component
public class LoggedUserUtil {

    @Autowired
    private UserRepository userRepository;

    public String getUsername(final HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        return claims.getSubject();
    }

    public User getUser(final HttpServletRequest request) {
        return this.userRepository.findByUsername(this.getUsername(request));
    }
}
