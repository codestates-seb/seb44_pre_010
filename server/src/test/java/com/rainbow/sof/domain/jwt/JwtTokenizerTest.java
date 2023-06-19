package com.rainbow.sof.domain.jwt;


import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTokenizerTest {

    private static JwtTokenizer jwtTokenizer;
    private String testkeyString;
    private String testDecodeString;


    @BeforeAll
    public void init(){
        jwtTokenizer = new JwtTokenizer();

        this.testkeyString = "test1234keyStingtest1234keyS4gfhghfhfh5553hhhjjy5444jjkaaaajajh5h5h5h5h5hkeyStingtest1234keySting";
        this.testDecodeString = jwtTokenizer.secretKeyEncodeBase64(testkeyString);
    }

    @Test
    @DisplayName("인코딩, 디코딩 테스트")
    public void secretKeyEncodeBase64Test(){
        assertThat(testkeyString,is(new String(Decoders.BASE64.decode(testDecodeString))) );
    }

    @Test
    @DisplayName("엑세스 토큰 생성 테스트")
    public void generateAccessTokenTest(){

        //give
        Map<String, Object> claims= new HashMap<>();
        claims.put("email","test@test.com");
        String subject = "testtest";

        Date expiration = getDate(10);

        //when
        String testToken = jwtTokenizer.generateAccessToken(claims,subject,expiration,testDecodeString);

        //then
        assertThat(testToken,notNullValue());
    }

    @Test
    @DisplayName("리프레시 토큰 생성 테스트")
    public void generateRefreshTokenTest(){
        //give
        String subject = "testtest";

        Date expiration = getDate(60);

        //when
        String testToken = jwtTokenizer.generateRefreshToken(subject,expiration,testDecodeString);

        //then
        assertThat(testToken,notNullValue());
    }

    @Test
    @DisplayName("Jws 검증 및 파싱 테스트")
    public void getClaimsTest(){
        String testToken = getTestAccessToken();
        Map<String, Object> claims= jwtTokenizer.getClaims(testToken,testDecodeString).getBody();

        String getEmail = (String) claims.get("email");
        assertThat(getEmail,is("test@123test.com"));
    }

    private String getTestAccessToken() {

        Map<String, Object> claims= new HashMap<>();
        claims.put("email","test@123test.com");
        String subject = "testtest";

        Date expiration = getDate(10);
        String testToken = jwtTokenizer.generateAccessToken(claims,subject,expiration,testDecodeString);

        return testToken;
    }

    private static Date getDate(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, amount);
        Date expiration = calendar.getTime();
        return expiration;
    }


}

