package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.AccountRep;
import Final.Project.dodo.model.dto.AccountDto;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.entities.Account;
import Final.Project.dodo.model.mapper.AccountMapper;
import Final.Project.dodo.model.response.OrderHistoryResponse;
import Final.Project.dodo.model.response.UserInfoResponse;
import Final.Project.dodo.service.AccountService;
import Final.Project.dodo.service.UserService;
import Final.Project.dodo.utils.JwtProvider;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRep, AccountDto, AccountMapper> implements AccountService {
    public AccountServiceImpl(AccountRep rep, AccountMapper mapper, JwtProvider jwtProvider, UserService userService) {
        super(rep, mapper);
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    public UserInfoResponse getUserInfo(String token) {
        UserInfoResponse response = new UserInfoResponse();

        Long userId = jwtProvider.validateToken(token);
        UserDto userDto = userService.findById(userId);
        response.setName(userDto.getName());
        response.setEmail(userDto.getEmail());
        List<OrderHistoryResponse> responseList = new ArrayList<>();
        return null;
    }


}
