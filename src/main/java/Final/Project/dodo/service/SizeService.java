package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.CategoriesDto;
import Final.Project.dodo.model.dto.SizeDto;
import Final.Project.dodo.model.request.create.CategoriesCreateRequest;
import Final.Project.dodo.model.request.create.SizeCreateRequest;
import Final.Project.dodo.model.request.update.CategoriesUpdateRequest;
import Final.Project.dodo.model.request.update.SizeUpdateRequest;

public interface SizeService extends BaseService<SizeDto> {
    SizeDto create(SizeCreateRequest request);


    SizeDto update(SizeUpdateRequest request);

    Boolean delete(Long id);
}
