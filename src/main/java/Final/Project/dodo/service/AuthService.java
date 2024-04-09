package Final.Project.dodo.service;

import Final.Project.dodo.model.request.authRequest.AuthRequest;
import Final.Project.dodo.model.request.authRequest.ValidateEmailReq;

public interface AuthService {
    String auth(AuthRequest request, Integer languageOrdinal);

    String validate(ValidateEmailReq req, Integer languageOrdinal);
}
