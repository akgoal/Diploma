package com.librarybooks.server.security;

import com.librarybooks.client.security.SecurityService;
import com.librarybooks.server.security.dao.EmailVerifyDao;
import com.librarybooks.server.security.dao.UserDao;
import com.librarybooks.server.security.data.EmailVerify;
import com.librarybooks.server.security.data.User;
import com.librarybooks.shared.security.EmailAlreadyExistsException;
import com.librarybooks.shared.security.UserDto;
import com.librarybooks.shared.security.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;

    private final UserDao userDao;
    private final EmailVerifyDao emailVerifyDao;

    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDao userDao, EmailVerifyDao emailVerifyDao, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.emailVerifyDao = emailVerifyDao;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Boolean authenticate(String username, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, authorityList);
        authenticationManager.authenticate(authentication);
        return true;
    }

    @Override
    public void register(UserDto userDto) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setEmailVerified(false);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setFirstName(userDto.getFirstName());
            user.setSecondName(userDto.getSecondName());
            user.setMiddleName(userDto.getMiddleName());
            userDao.saveOrUpdate(user);
            verifyEmail(user, userDto);
        } catch (Throwable ex) {
            handleException(ex);
            throw ex;
        }
    }

    private void verifyEmail(User user, final UserDto userDto) {
        final EmailVerify emailVerifies = new EmailVerify();
        emailVerifies.setToken(UUID.randomUUID().toString());
        emailVerifies.setCreatedAt(new Date());
        emailVerifies.setUser(user);
        emailVerifyDao.save(emailVerifies);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
                    String htmlMsg = "Please confirm <a href='http://localhost:8888/librarybooks/rest/confirm/" + emailVerifies.getToken() + "'>email</a>";
                    mimeMessage.setContent(htmlMsg, "text/html");
                    helper.setTo(userDto.getEmail());
                    helper.setSubject("Подтверждение регистрации на сайте bookservice");
                    helper.setFrom("zavgarovbulat@inbox.ru");
                    javaMailSender.send(mimeMessage);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void handleException(Throwable ex) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        Throwable cause = ex.getCause();
        if (cause != null) {
            if (cause.getMessage().contains("uni_user1")) {
                throw new UsernameAlreadyExistsException();
            } else if (cause.getMessage().contains("uni_user2")) {
                throw new EmailAlreadyExistsException();
            }
        }
    }
}
