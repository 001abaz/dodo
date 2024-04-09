package Final.Project.dodo.service.impl;


import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.CategoriesRep;
import Final.Project.dodo.model.dto.CategoriesDto;
import Final.Project.dodo.model.entities.Categories;
import Final.Project.dodo.model.mapper.CategoriesMapper;
import Final.Project.dodo.model.request.create.CategoriesCreateRequest;
import Final.Project.dodo.model.request.update.CategoriesUpdateRequest;
import Final.Project.dodo.service.CategoriesService;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl extends BaseServiceImpl<Categories, CategoriesRep, CategoriesDto, CategoriesMapper> implements CategoriesService {


    public CategoriesServiceImpl(CategoriesRep rep, CategoriesMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public String create(CategoriesCreateRequest request, Integer languageOrdinal ) {
        Language language = Language.getLanguage(languageOrdinal);
        CategoriesDto dto = new CategoriesDto();
        dto.setName(request.getName());
        dto.setDefinition(request.getDefinition());
        save(dto);
        return ResourceBundleLanguage.periodMessage(language,"createSuccessful");
    }


    @Override
    public CategoriesDto update(CategoriesUpdateRequest request) {
        CategoriesDto dto = new CategoriesDto();
        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setDefinition(request.getDefinition());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }
}
