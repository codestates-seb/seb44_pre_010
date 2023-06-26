package com.rainbow.sof.domain.user.auth.util.service;

import com.rainbow.sof.domain.user.auth.util.userDetail.OAuth2UserDetail;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public CustomOAuth2UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        validateAttributes(oAuth2User.getAttributes());
        OAuth2User newOAuth2User= validateOAuth2User(oAuth2User.getAttributes());

        log.info("유저 데이터 체크: {} ",oAuth2User);

        return newOAuth2User;
    }

    private OAuth2User validateOAuth2User(Map<String, Object> attributes) {
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        Optional<User> optionalUser = repository.findByEmail(email);
        return optionalUser.map(user ->
                        new OAuth2UserDetail(user, attributes)
                ).orElseGet(
                        () -> saveOAuthUser(email, name))
                ;


    }

    private OAuth2UserDetail saveOAuthUser(String email, String name) {
        String password = encoder.encode("oauthuserpassword1234");
        Map<String,Object> attribute  =new HashMap<>();
        attribute.put("email",email);
        User createUser = User.builder().email(email)
                .name(name)
                .password(password)
                .build();
        createUser.updateOAuth(User.oAuthCheck.GOOGLE);
        User saveUser = repository.save(createUser);
        return new OAuth2UserDetail(saveUser,attribute);
    }

    private void validateAttributes(Map<String, Object> attributes) throws IllegalAccessException {
        if (!attributes.containsKey("email")){
            throw new IllegalAccessException("응답 유저 정보에 이메일이 없습니다..");
        }
    }
}
