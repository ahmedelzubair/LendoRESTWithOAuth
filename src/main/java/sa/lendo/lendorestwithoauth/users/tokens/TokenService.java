package sa.lendo.lendorestwithoauth.users.tokens;


import sa.lendo.lendorestwithoauth.users.domain.UserToken;

public interface TokenService {

    UserToken updateUserToken(UserToken userToken);

    UserToken saveNewUserToken(UserToken userToken);


    void deleteUserToken(UserToken userToken);

    void deleteUserTokenById(Long id);

    UserToken findUserTokenByUsername(String username);

    UserToken findRefreshTokenByAccessToken(String accessToken);

}
