package pl.webapp.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.security.model.ShopUserDetails;
import pl.webapp.shop.security.model.User;
import pl.webapp.shop.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return createShopUserDetails(userRepository.findByUsername(username).orElseThrow());
    }

    @Transactional
    public UserDetails loadUserByUuid(String uuid) throws UsernameNotFoundException {
        return createShopUserDetails(userRepository.findByUuid(uuid).orElseThrow());
    }

    private static ShopUserDetails createShopUserDetails(User user) {
        ShopUserDetails shopUserDetails = new ShopUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream()
                        .map(userRole -> (GrantedAuthority) userRole::name)
                        .toList());
        shopUserDetails.setUuid(user.getUuid());
        shopUserDetails.setEnabled(user.isEnabled());

        return shopUserDetails;
    }
}
