package com.es.habitstack.security.jwt;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

/*
provider 구현하려고 했는데 jjwt 라이브러리, spring security 둘 다 build.gradle에 추가 해야 만들 수 있다고 해서..
Spring Security는 보니까 건드릴 수가 없어서,, 일단 이렇게 만들어만 놨어요
토큰에는 email, role 넣고 만료 시간은 10분 생각했는데 JWT secret key 이건 어떻게??
 */

@Component
public class JwtProvider {

    //private final SecretKey secretKey;
    //private final long expirationMs;
    /*
    1. 로그인 성공 시 JWT 토큰을 생성하는 메서드
    2. 서버가 클라이언트에게 "이 사용자를 로그인됨"을 증명하기 위해 토큰을 만들어서 전달해야 하기 때문에 필요
    (현재 구현 안 해서 null 반환)
     */
    public String generateToken(String email, String role) {
        return null;
    }

    /*
    1. 전달받은 JWT 토큰이 유효한지 검사하는 메서드
    2. 클라이언트가 보낸 토큰은 위조되었을 수도, 만료되었을 수도 있기 때문에 검증
    (현재 구현 안 해서 false 반환)
     */
    public boolean validateToken(String token) {
        return false;
    }

    /*
    1. JWT 토큰을 기반으로 "인증 정보 객체"를 만드는 메서드
    2. Spring Security는 토큰 자체를 이해하지 못하고 "Authentication 객체" 형태로만 인증 정보를 관리
    -> 즉: 토큰 -> 사용자 정보 추출 -> Authentication 객체 생성

    (현재 Security 미사용이라 Object로 임시 처리)
     */
    public Object getAuthentication(String token) {
        return null;
    }

}
