package com.librarybooks.server.security;

import com.librarybooks.server.security.dao.EmailVerifyDao;
import com.librarybooks.server.security.dao.UserDao;
import com.librarybooks.server.security.data.EmailVerify;
import com.librarybooks.server.security.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EmailConfirmController {

    private final EmailVerifyDao emailVerifyDao;
    private final UserDao userDao;

    @Autowired
    public EmailConfirmController(EmailVerifyDao emailVerifyDao, UserDao userDao) {
        this.emailVerifyDao = emailVerifyDao;
        this.userDao = userDao;
    }

    @RequestMapping("/confirm/{token}")
    public void confirm(@PathVariable("token") String token, HttpServletResponse response) throws IOException {
        EmailVerify emailVerify = emailVerifyDao.getByToken(token);
        User user = emailVerify.getUser();
        user.setEmailVerified(true);
        userDao.saveOrUpdate(user);

        response.getWriter().write("Email verified. <a href='http://localhost:8888'/>Go to LibraryBooks</a>");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
