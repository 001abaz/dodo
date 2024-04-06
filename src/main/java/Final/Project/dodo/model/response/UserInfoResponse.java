package Final.Project.dodo.model.response;

import Final.Project.dodo.model.dto.AddressDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserInfoResponse {
    String name;
    String email;
    Integer dodCoins;
    List<OrderHistoryResponse> historyResponses;
    List<AddressDto> addressList;
}
