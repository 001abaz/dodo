package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.CategoriesDto;
import Final.Project.dodo.model.request.create.CategoriesCreateRequest;
import Final.Project.dodo.model.request.update.CategoriesUpdateRequest;

public interface CategoriesService extends BaseService<CategoriesDto> {
    String create(CategoriesCreateRequest request, Integer languageOrdinal);

    CategoriesDto update(CategoriesUpdateRequest request);

    Boolean delete(Long id, Integer languageOrdinal);
}
