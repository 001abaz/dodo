package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.request.create.UserCreateRequest;
import Final.Project.dodo.model.request.update.UserUpdateRequest;

public interface UserService extends BaseService<UserDto> {
    String create(UserCreateRequest request, Integer languageOrdinal);

    UserDto update(UserUpdateRequest request);

    Boolean delete(Long id, Integer languageOrdinal);



}
