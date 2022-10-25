package sa.lendo.lendorestwithoauth.users.service;

import sa.lendo.lendorestwithoauth.users.domain.AppUser;
import sa.lendo.lendorestwithoauth.users.domain.AppUserDetails;
import sa.lendo.lendorestwithoauth.users.repo.UserJpaRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;

    public AppUserDetailsService(UserJpaRepo userJpaRepo) {
        this.userJpaRepo = userJpaRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = userJpaRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));

        return new AppUserDetails(appUser);
    }
}
