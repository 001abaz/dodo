package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.AddressDto;
import Final.Project.dodo.model.request.create.AddressCreateRequest;
import Final.Project.dodo.model.request.update.AddressUpdateRequest;

public interface AddressService extends BaseService<AddressDto> {
    AddressDto create(AddressCreateRequest request);

    AddressDto update(AddressUpdateRequest request);

    Boolean delete(Long id);
}
