package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.AccountRep;
import Final.Project.dodo.exception.NotFoundException;
import Final.Project.dodo.model.dto.AccountDto;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.entities.Account;
import Final.Project.dodo.model.mapper.AccountMapper;
import Final.Project.dodo.model.response.UserInfoResponse;
import Final.Project.dodo.service.AccountService;
import Final.Project.dodo.service.AddressService;
import Final.Project.dodo.service.OrderService;
import Final.Project.dodo.service.UserService;
import Final.Project.dodo.utils.JwtProvider;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRep,
        AccountDto, AccountMapper> implements AccountService {
    public AccountServiceImpl(AccountRep rep, AccountMapper mapper,
                              JwtProvider jwtProvider, UserService userService, OrderService orderService, AddressService addressService) {
        super(rep, mapper);
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.orderService = orderService;
        this.addressService = addressService;
    }

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;

    @Override
    public UserInfoResponse getUserInfo(String token, int pageNum, int limit, Integer languageOrdinal) {
        UserInfoResponse response = new UserInfoResponse();

        Long userId = jwtProvider.validateToken(token, languageOrdinal);
        UserDto userDto = userService.findById(userId, languageOrdinal);
        response.setName(userDto.getName());
        response.setEmail(findById(userId, languageOrdinal).getEmail());
        response.setOrderCount(orderService.getAllByUserId(token, languageOrdinal).size());
        response.setHistoryResponses(orderService.getAllByUserId(token, pageNum, limit, languageOrdinal));
        response.setAddressList(addressService.findAllByUserId(token));
        return response;
    }

    @Override
    public AccountDto findByEmail(String email, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        try{
        return mapper.toDto(rep.findByEmail(email), context);
        }catch (NotFoundException n){
            throw new NotFoundException(ResourceBundleLanguage.periodMessage(language, "objectNotFound"));
        }
    }


}
