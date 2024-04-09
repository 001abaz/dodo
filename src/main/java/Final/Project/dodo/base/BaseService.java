package Final.Project.dodo.base;

import java.util.List;

public interface BaseService<D extends BaseDto> {

    D save(D e);
    D findById(Long id, Integer languageOrdinal);
    List<D> findAll();

    D update(D e);

    boolean delete(D e, Integer languageOrdinal);

}
