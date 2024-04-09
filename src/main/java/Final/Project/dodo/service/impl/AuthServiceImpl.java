package Final.Project.dodo.service.impl;

import Final.Project.dodo.exception.AuthException;
import Final.Project.dodo.model.dto.AccountDto;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.enums.Status;
import Final.Project.dodo.model.request.authRequest.AuthRequest;
import Final.Project.dodo.model.request.authRequest.ValidateEmailReq;
import Final.Project.dodo.service.AccountService;
import Final.Project.dodo.service.AuthService;
import Final.Project.dodo.service.UserService;
import Final.Project.dodo.utils.JwtProvider;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    private final MailService mailService;

    public AuthServiceImpl(MailService mailService, UserService userService, JwtProvider jwtProvider, AccountService accountService) {
        this.mailService = mailService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.accountService = accountService;
    }

    @Value("${spring.mail.username}")
    private String from;

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;


    public static String generateTempPassword() {
        Random random = new Random();
        int firstPart = 100 + random.nextInt(900);
        int secondPart = 100 + random.nextInt(900);
        return String.format("%d-%d", firstPart, secondPart);
    }

    @Override
    public String auth(AuthRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        String tempPassword = generateTempPassword();
        Date date = mailService.send(request.getEmail(), tempPassword).getSentDate();
        Instant instant = date.toInstant();
        LocalDateTime sendDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        AccountDto accountDto = accountService.findByEmail(request.getEmail(), languageOrdinal);
//        UserDto userDto = userService.findById(accountDto.getUser().getId(), languageOrdinal);
        if (accountDto != null) {
            accountDto.setTemp_password(tempPassword);
            accountDto.setTempPasswordTime(sendDate);
            accountService.update(accountDto);
            return (ResourceBundleLanguage.periodMessage(language, "singInIsSuccessful"));
        } else {
            UserDto newUser = new UserDto();
            AccountDto newAccount = new AccountDto();
//            String text = "517-123";

            ValidateEmailReq req = new ValidateEmailReq();
            req.setEmail(request.getEmail());
            req.setPassword(tempPassword);

            newUser.setPhone(request.getPhone());
            newUser.setName(request.getName());
            userService.save(newUser);

            newAccount.setTemp_password(tempPassword);
            newAccount.setEmail(request.getEmail());
            newAccount.setTempPasswordTime(sendDate);
            newAccount.setUser(newUser);
            accountService.save(newAccount);

            return validate(req, languageOrdinal);
        }
    }

    public boolean checkTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, currentTime);
        long minutesDifference = duration.toMinutes();
        return minutesDifference < 10;
    }

    @Override
    public String validate(ValidateEmailReq request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        AccountDto dto = accountService.findByEmail(request.getEmail(), languageOrdinal);
//         catch (ExpiredJwtException e) {
//            throw new RuntimeException("Token expiration. Please authenticate again.");
//        }
        if (dto.getTemp_password().equals(request.getPassword())) {
            if (checkTime(dto.getTempPasswordTime())) {
                dto.setStatus(Status.APPROVED);
                return jwtProvider.generateAccessToken(dto.getId());
            } else {
                throw new AuthException(ResourceBundleLanguage.periodMessage(language, "timeExpired"));
            }
        } else {
            throw new AuthException (ResourceBundleLanguage.periodMessage(language, "invalidPassword"));

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


