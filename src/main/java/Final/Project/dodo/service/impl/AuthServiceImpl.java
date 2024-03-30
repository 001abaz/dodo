package Final.Project.dodo.service.impl;

import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.enums.Status;
import Final.Project.dodo.model.request.authRequest.AuthRequest;
import Final.Project.dodo.model.request.authRequest.ValidateEmailReq;
import Final.Project.dodo.service.AuthService;
import Final.Project.dodo.service.UserService;
import Final.Project.dodo.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    private final MailService mailService;

    public AuthServiceImpl(MailService mailService, UserService userService, JwtProvider jwtProvider) {
        this.mailService = mailService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Value("${spring.mail.username}")
    private String from;

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private Date sendDate;

    public static String generateTempPassword() {
        Random random = new Random();
        int firstPart = 100 + random.nextInt(900);
        int secondPart = 100 + random.nextInt(900);
        return String.format("%d-%d", firstPart, secondPart);
    }

    @Override
    public String auth(AuthRequest request) {
        String tempPassword = generateTempPassword();
        // Send email and set sendDate
        sendDate = mailService.send(request.getEmail(), tempPassword).getSentDate();

        if (userService.checkByEmail(request.getEmail())) {
            UserDto dto = userService.findByEmail(request.getEmail());

            dto.setTemp_password(tempPassword);
            userService.update(dto);
            return "Sign in is successful";
        } else {
            UserDto newUser = new UserDto();
//            String text = "517-123";

            ValidateEmailReq req = new ValidateEmailReq();
            req.setEmail(request.getEmail());
            req.setPassword(tempPassword);

            newUser.setTemp_password(tempPassword);
            newUser.setPhone(request.getPhone());
            newUser.setName(request.getName());
            newUser.setEmail(request.getEmail());
            userService.save(newUser);
            return validate(req);
        }
    }

    public boolean checkTime(Date date) {
        if (date == null) {
            return false;
        }
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(date.toInstant(), currentTime.atZone(ZoneId.systemDefault()).toInstant());
        return duration.toMinutes() < 10;
    }

    @Override
    public String validate(ValidateEmailReq request) {
        UserDto dto = userService.findByEmail(request.getEmail());
//         catch (ExpiredJwtException e) {
//            throw new RuntimeException("Token expiration. Please authenticate again.");
//        }
        if (dto.getTemp_password().equals(request.getPassword())) {
            if (checkTime(sendDate)) {
                dto.setStatus(Status.APPROVED);
                return jwtProvider.generateAccessToken(dto.getId());
            } else {
                return "Time expired";
            }
        } else {
            return "Invalid password";
        }
    }
}


//сравнение пароля из реквеста и из бд
        // если время отправленного пароля истекло (дается 10 мин) то кидаем ошибку
        //если пароль неверный то кидаем ошибку
        //если не прошло 10 минут и пароль верны идем дальше
        //делаем статус у account (status approved)
        //найти юзера по аккаунту
        //формируем токен из userId и роли
        //возвращаем токен
//        return jwtProvider.generateAccessToken(dto.getId());


