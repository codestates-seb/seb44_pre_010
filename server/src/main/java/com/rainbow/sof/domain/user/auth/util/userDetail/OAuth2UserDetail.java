package com.rainbow.sof.domain.user.auth.util.userDetail;
import com.rainbow.sof.domain.user.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Map;

public class OAuth2UserDetail extends UsersDetail implements OAuth2User {
    private final Map<String, Object> attributes;

    public OAuth2UserDetail(User user,Map<String, Object> attributes ) {
        super(user);
        this.updateUserId(user.getUserId());
        this.updateEmail(user.getEmail());
        this.updateName(user.getName());
        this.updatePassword(user.getPassword());
        this.updateQuestionList(user.getQuestionList());
        this.updateOAuth(oAuthCheck.GOOGLE);
        this.attributes=attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
