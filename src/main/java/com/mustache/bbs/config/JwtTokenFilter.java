package com.mustache.bbs.config;

import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.service.UserService;
import com.mustache.bbs.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization Header : {}", header);

        if(header == null || !header.startsWith("Bearer ")){
            log.error("authorization Header가 없거나 오류가 존재합니다.");
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.split(" ")[1];

        if(JwtTokenUtil.isExpired(token, secretKey)){
            log.error("토큰이 만료되었습니다");
            filterChain.doFilter(request, response);
            return;
        }

        //claim에서 userName 꺼내기 (즉, 토큰에서 유저네임을 꺼낸다는 말)
        String userName = JwtTokenUtil.getUserName(token, secretKey);

        //Token에서 꺼내온 userName으로 UserDetail 가져오기.
        User user = userService.getUserByUserName(userName);

        //filterChain에서 권한 부여, principal에 userName 전달
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), null,
                List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response); //request 안쪽에 인증 도장이 찍히는 것. (인증이 되었다고 체크가 되는것)
    }
}
