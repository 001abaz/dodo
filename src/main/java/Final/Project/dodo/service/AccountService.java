package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.AccountDto;
import Final.Project.dodo.model.response.UserInfoResponse;

public interface AccountService extends BaseService<AccountDto> {
    UserInfoResponse getUserInfo(String token, int pageNum, int limit, Integer languageOrdinal);
    AccountDto findByEmail(String email, Integer languageOrdinal);

}
