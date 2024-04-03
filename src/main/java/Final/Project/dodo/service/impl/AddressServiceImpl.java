package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.AddressRep;
import Final.Project.dodo.dao.projection.AddressResponse;
import Final.Project.dodo.model.dto.AddressDto;
import Final.Project.dodo.model.entities.Address;
import Final.Project.dodo.model.mapper.AddressMapper;
import Final.Project.dodo.model.request.create.AddressCreateRequest;
import Final.Project.dodo.model.request.update.AddressUpdateRequest;
import Final.Project.dodo.service.AddressService;
import Final.Project.dodo.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRep, AddressDto, AddressMapper> implements AddressService {


    public AddressServiceImpl(AddressRep rep, AddressMapper mapper, UserService userService) {
        super(rep, mapper);
        this.userService = userService;
    }
    private final UserService userService;

    @Override
    public AddressDto create(AddressCreateRequest request) {
        AddressDto dto = new AddressDto();
        dto.setStreet(request.getStreet());
        dto.setNum(request.getNum());
        dto.setComment(request.getComment());
        dto.setUser(userService.findById(request.getUserId()));
        return save(dto);
    }


    @Override
    public AddressDto update(AddressUpdateRequest request) {
        AddressDto dto = new AddressDto();
        dto.setId(request.getId());
        dto.setStreet(request.getStreet());
        dto.setNum(request.getNum());
        dto.setComment(request.getComment());
        dto.setUpdateDate(LocalDateTime.now());
        dto.setUser(userService.findById(request.getUserId()));
        return update(dto);

    }

    @Override
    public Boolean delete(Long id) {
        AddressDto dto = findById(id);
        return delete(dto);
    }

    @Override
    public AddressResponse findByAddressId(Long id) {
        return rep.findByAddressId(id);
    }
}
