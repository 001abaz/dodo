package Final.Project.dodo.model.response;

import Final.Project.dodo.dao.projection.AddressResponse;
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
    Integer OrderCount;
    List<OrderHistoryResponse> historyResponses;
    List<AddressResponse> addressList;
}

// eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MTI1MDcwNzYsImV4cCI6NzExMjUwNzA3NiwidXNlcklkIjoxfQ.UfEUCt7TT-vOs77FVR1u-cFzzv2aiNFmA_HPqSdUm16pXdJHj2Wdf0kRSlyucQubA6W93duMSKY64JFO3VjU3Q
// eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MTI0ODQ5NTksImV4cCI6MTcxMjQ4NTAxOSwidXNlcklkIjoxfQ.9kMbQX000ZhXAmNDYc2FsqYPwMT1w-sF14klqADQR6G8FjZf8yfeInS3vbgBTdV6NWylaO_ob__MEpl0VQVeLg