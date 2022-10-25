package sa.lendo.lendorestwithoauth.users.tokens;

import org.springframework.stereotype.Service;
import sa.lendo.lendorestwithoauth.exceptions.EntityNotFoundException;
import sa.lendo.lendorestwithoauth.users.domain.UserToken;
import sa.lendo.lendorestwithoauth.users.service.UserService;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenJpaRepo tokenJpaRepo;
    private final UserService userService;

    public TokenServiceImpl(TokenJpaRepo tokenJpaRepo, UserService userService) {
        this.tokenJpaRepo = tokenJpaRepo;
        this.userService = userService;
    }


    @Override
    public UserToken updateUserToken(String oldAccessToken, UserToken newToken) {
        // if token already exists then update it by refresh token
        Optional<UserToken> userTokenOptional = tokenJpaRepo.findByAccessToken(oldAccessToken);
        userTokenOptional.ifPresent(token -> newToken.setId(token.getId()));
        return tokenJpaRepo.save(newToken);
    }

    @Override
    public UserToken saveNewUserToken(UserToken userToken) {
        // add user token_id to user table

        userService.addTokenIdToUser(userToken);

        Optional<UserToken> userTokenOptional = tokenJpaRepo.findByAccessToken(userToken.getAccessToken());
        // remove old token
        userTokenOptional.ifPresent(tokenJpaRepo::delete);
        return tokenJpaRepo.save(userToken);
    }


    @Override
    public void deleteUserToken(UserToken userToken) {

        if (tokenJpaRepo.findById(userToken.getId()).isEmpty())
            throw new EntityNotFoundException("UserToken with id " + userToken.getId()
                    + " does not exist");

        tokenJpaRepo.deleteById(userToken.getId());
    }

    @Override
    public void deleteUserTokenById(Long id) {

        Optional<UserToken> userTokenOptional = tokenJpaRepo.findById(id);

        if (userTokenOptional.isEmpty()) {
            throw new EntityNotFoundException("UserToken with id " + id
                    + " does not exist");
        }

        tokenJpaRepo.deleteById(id);

    }

    @Override
    public UserToken findUserTokenByUsername(String  username) {
        Optional<UserToken> userTokenOptional = tokenJpaRepo.findByUsername(username);
        if (userTokenOptional.isEmpty()) {
            throw new EntityNotFoundException("UserToken with id " + username
                    + " does not exist");
        }
        return userTokenOptional.get();
    }

    @Override
    public UserToken findRefreshTokenByAccessToken(String accessToken) {
        Optional<UserToken> userTokenOptional = tokenJpaRepo.findRefreshTokenByAccessToken(accessToken);
        if (userTokenOptional.isEmpty()) {
            throw new EntityNotFoundException("token " + accessToken
                    + " does not exist");
        }
        return userTokenOptional.get();
    }

}
