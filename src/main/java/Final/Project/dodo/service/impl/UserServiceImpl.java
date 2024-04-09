package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.UserRep;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.entities.User;
import Final.Project.dodo.model.mapper.UserMapper;
import Final.Project.dodo.model.request.create.UserCreateRequest;
import Final.Project.dodo.model.request.update.UserUpdateRequest;
import Final.Project.dodo.service.UserService;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRep, UserDto, UserMapper> implements UserService {
    public UserServiceImpl(UserRep rep, UserMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public String create(UserCreateRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        UserDto dto = new UserDto();
        dto.setName(request.getName());
        dto.setPhone(request.getPhone());
        return ResourceBundleLanguage.periodMessage(language, "createSuccessful");
    }

    @Override
    public UserDto update(UserUpdateRequest request) {

        UserDto dto = new UserDto();

        dto.setAddDate(LocalDateTime.now());

        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setPhone(request.getPhone());
        dto.setDodoCoins(request.getDodoCoins());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }

}
