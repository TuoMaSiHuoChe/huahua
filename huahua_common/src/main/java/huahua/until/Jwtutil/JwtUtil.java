package huahua.until.Jwtutil;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * @author: admin
 * @Date: 2019/4/23 08:50
 * @Description:
 */
@Data
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;
    private long ttl;


    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date data = new Date(nowMillis);
        JwtBuilder role = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(data).claim("roles", roles).signWith(SignatureAlgorithm.HS256, key);
        if(ttl>0){
            role.setExpiration(new Date(System.currentTimeMillis() + ttl));
        }
        return role.compact();
    }
    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){

        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();

    }

}
