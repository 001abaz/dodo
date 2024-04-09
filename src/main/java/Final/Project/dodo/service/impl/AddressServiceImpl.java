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
import Final.Project.dodo.utils.JwtProvider;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRep, AddressDto, AddressMapper> implements AddressService {


    public AddressServiceImpl(AddressRep rep, AddressMapper mapper, UserService userService, JwtProvider jwtProvider) {
        super(rep, mapper);
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Override
    public String create(AddressCreateRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        AddressDto dto = new AddressDto();
        dto.setStreet(request.getStreet());
        dto.setNum(request.getNum());
        dto.setComment(request.getComment());
        dto.setUser(userService.findById(request.getUserId(), languageOrdinal));
        save(dto);
        return ResourceBundleLanguage.periodMessage(language, "createSuccessful");
    }


    @Override
    public AddressDto update(AddressUpdateRequest request, Integer languageOrdinal) {
        AddressDto dto = new AddressDto();
        dto.setId(request.getId());
        dto.setStreet(request.getStreet());
        dto.setNum(request.getNum());
        dto.setComment(request.getComment());
        dto.setUpdateDate(LocalDateTime.now());
        dto.setUser(userService.findById(request.getUserId(), languageOrdinal));
        return update(dto);

    }

    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }

    @Override
    public AddressResponse findByAddressId(Long id) {
        return rep.findByAddressId(id);
    }
    @Override
    public List<AddressResponse> findAllByUserId(String token) {
        Integer languageOrdinal = null;
        Long userId = jwtProvider.validateToken(token, languageOrdinal);
        return rep.findAllByUserId(userId);
    }
}
