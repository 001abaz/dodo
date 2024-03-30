package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.UserRep;
import Final.Project.dodo.dao.projection.UserGetProjection;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.entities.User;
import Final.Project.dodo.model.mapper.UserMapper;
import Final.Project.dodo.model.request.create.UserCreateRequest;
import Final.Project.dodo.model.request.update.UserUpdateRequest;
import Final.Project.dodo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRep, UserDto, UserMapper> implements UserService {
    public UserServiceImpl(UserRep rep, UserMapper mapper) {
        super(rep, mapper);
    }


    @Override
    public UserDto create(UserCreateRequest request) {
        UserDto dto = new UserDto();
        dto.setName(request.getName());
        dto.setEmail(request.getEmail());
        dto.setPhone(request.getPhone());
        dto.setDodoCoins(request.getDodoCoins());
        return save(dto);
    }

    @Override
    public UserDto update(UserUpdateRequest request) {
        UserDto dto = new UserDto();
        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setEmail(request.getEmail());
        dto.setPhone(request.getPhone());
        dto.setDodoCoins(request.getDodoCoins());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return delete(findById(id));
    }

    @Override
    public Boolean checkByEmail(String email) {
        return rep.checkByEmail(email);
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<UserGetProjection> userProjectionOptional = rep.findByEmail(email);
        if (userProjectionOptional.isPresent()) {
            UserGetProjection userProjection = userProjectionOptional.get();
            // Map the data from the UserGetProjection to a UserDto object
            UserDto userDto = new UserDto();
            userDto.setId(userProjection.getId());
            userDto.setName(userProjection.getName());
            userDto.setEmail(userProjection.getEmail());
            userDto.setPhone(userProjection.getPhone());
            userDto.setStatus(userProjection.getStatus());
            userDto.setDodoCoins(userProjection.getDodoCoins());
            userDto.setTemp_password(userProjection.getTemp_password());
            // You can set other fields as needed
            return userDto;
        } else {
            // Handle the case where no user is found with the given email
            // You can throw an exception or return null, depending on your requirement
            return null;
        }
    }
}
