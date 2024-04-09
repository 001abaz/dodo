package Final.Project.dodo.base;

import Final.Project.dodo.exception.DeleteException;
import Final.Project.dodo.exception.NotFoundException;
import Final.Project.dodo.model.enums.Status;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;
@MappedSuperclass
public abstract class BaseServiceImpl<E extends BaseEntity,
        R extends  BaseRep<E>,
        D extends BaseDto,
        M extends BaseMapper<E,D>>
        implements BaseService<D> {

    protected final R rep;
    protected final M mapper;

    @Autowired
    protected CycleAvoidingMappingContext context;


    public BaseServiceImpl(R rep, M mapper) {
        this.rep = rep;
        this.mapper = mapper;
    }


    @Override
    public D save(D e) {
        return mapper.toDto(rep.save(mapper.toEntity(e,context)),context);
    }

    @Override
    public D findById(Long id, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        return mapper.toDto(rep.findById(id).orElseThrow(()->new NotFoundException(ResourceBundleLanguage.periodMessage(language, "objectNotFound"))), context);
    }

    @Override
    public List<D> findAll() {
        return mapper.toDtos(rep.findAll(),context);
    }

    @Override
    public D update(D e) {

        return mapper.toDto(rep.saveAndFlush(mapper.toEntity(e,context)),context);
    }

    @Override
    public boolean delete(D e, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        try {
            e.setStatus(Status.DELETE);
            save(e);
            return true;
        }catch (DeleteException ex){
            throw new DeleteException(ResourceBundleLanguage.periodMessage(language,""));
        }


    }
}
