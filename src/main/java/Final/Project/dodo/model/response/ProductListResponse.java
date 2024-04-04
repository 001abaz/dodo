package Final.Project.dodo.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ProductListResponse {
    Long id;
    String name;
    String logo;
    String description;
    String categoryName;


}
