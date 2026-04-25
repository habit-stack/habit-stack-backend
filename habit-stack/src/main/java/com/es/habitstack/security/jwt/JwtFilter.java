package com.es.habitstack.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component //스프링이 이 클래스를 자동으로 객체로 만들어서 필터로 등록해준다.
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    /*
    1. 이 메서드가 뭘 하는데 사용?
    -> 특정 요청은 JWT 필터를 거치지 않게 하는 메서드
    2. 왜 이렇게 구현?
    -> 로그인/회원가입 요청은 아직 토큰이 없기 때문에 필터에서 막으면 로그인 불가능
    -> 그래서 "api/auth/"로 시작하는 요청은 필터를 건너뛰게 함
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/api/auth/")
                || path.equals("/error");
    }

    /*
    1. 모든 HTTP 요청이 들어올 때 실행되는 필터의 핵심 메서드
    2. 요청을 가로채서 JWT 토큰을 확인하기 위해서 구현,
    지금은 토큰을 꺼내서 제대로 들어오는지만 확인 가능
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //요청 헤더에서 JWT 토큰을 꺼낸다.
        String token = getTokenFromRequest(request);

        //토큰이 잘 들어오는지 확인하기 위해 출력
        System.out.println("토큰: " + token);

        /*
        1. 다음 필터 또는 컨트롤러로 요청을 넘김
        2. 이게 없으면 요청이 여기서 멈춤!
         */
        filterChain.doFilter(request, response);
    }

    /*
    1. HTTP 요청 헤더에서 JWT 토큰만 추출하는 메서드
    2. JWT는 보통 "Authorization: Bearer 토큰값" 형태로 들어옴,
    우리가 필요한 건 "Bearer " 뒤에 있는 토큰값임
     */
    private String getTokenFromRequest(HttpServletRequest request) {

        //Authorization 헤더 값을 가져옴
        String bearerToken = request.getHeader("Authorization");

        /*
        1. 헤더가 존재하고 "Bearer "로 시작하는지 확인
        2. JWT 표준 형식이 "Bearer 토큰값"이기 때문에 필요,
        잘못된 형식의 요청을 걸러내기 위해서 필요
         */
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            /*
            1. substring(7)?
            -> "Bearer "(7글자)를 잘라내고 실제 토큰만 반환
            ex) "Bearer abcdefg12345" -> "abcdefg12345"
             */
            return bearerToken.substring(7);
        }

        //토큰이 없거나 형식이 맞지 않으면 null 반환
        return null;
    }
}
