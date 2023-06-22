//package com.rainbow.sof.domain.user.auth.handler;
//
//import com.rainbow.sof.domain.user.auth.util.userDetail.UsersDetail;
//import com.rainbow.sof.domain.user.auth.util.userDetailService.UsersDetailService;
//import com.rainbow.sof.domain.user.entity.User;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithSecurityContextFactory;
//
//public class WithAuthUserSecurityContextFactory implements WithSecurityContextFactory<WithAuthUser> {
//
//    @Override
//    public SecurityContext createSecurityContext(WithAuthUser annotation) {
//        String email = annotation.email();
//
//        User user = User.builder()
//                .name("admin")
//                .password("q12341234")
//                .email("test@test.com")
//                .userId(1L).build();
//        UsersDetail userdetail = new UsersDetail(user);
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(userdetail, null,null );
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.setAuthentication(token);
//        return context;
//    }
//}