package io.dataease.sso.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import io.dataease.api.permissions.login.dto.PwdLoginDTO;
import io.dataease.auth.bo.TokenUserBO;
import io.dataease.auth.vo.TokenVO;
import io.dataease.exception.DEException;
import io.dataease.i18n.Translator;
import io.dataease.sso.user.UserDTO;
import io.dataease.sso.user.UserService;
import io.dataease.utils.LogUtil;
import io.dataease.utils.Md5Utils;
import io.dataease.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Component
@RestController
@RequestMapping
public class LoginServer {

    @Autowired
    private UserService userService;

    @PostMapping("/login/localLogin")
    public TokenVO localLogin(@RequestBody PwdLoginDTO dto) {

        String name = dto.getName();
        name = RsaUtils.decryptStr(name);
        String pwd = dto.getPwd();
        pwd = RsaUtils.decryptStr(pwd);

        dto.setName(name);
        dto.setPwd(pwd);

        UserDTO user = userService.selectUserByName(name);

//        if (!StringUtils.equals("admin", name)) {
//            DEException.throwException("仅admin账号可用");
//        }
//        if (!StringUtils.equals(pwd, SubstituleLoginConfig.getPwd())) {
//            DEException.throwException(Translator.get("i18n_login_name_pwd_err"));
//        }
        // todo 从数据接获取用户信息

        TokenUserBO tokenUserBO = new TokenUserBO();
        tokenUserBO.setUserId(user.getUserId());
        tokenUserBO.setDefaultOid(user.getOId());
        String md5Pwd = Md5Utils.md5(pwd);
        if (!Objects.equals(user.getPwd(), md5Pwd)) {
            DEException.throwException(Translator.get("i18n_id_or_pwd_error"));
        }
        return generate(tokenUserBO, md5Pwd);
    }


    @GetMapping("/logout")
    public void logout() {
        LogUtil.info("substitule logout");
    }

    private TokenVO generate(TokenUserBO bo, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Long userId = bo.getUserId();
        Long defaultOid = bo.getDefaultOid();
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("uid", userId).withClaim("oid", defaultOid);
        String token = builder.sign(algorithm);
        return new TokenVO(token, 0L);
    }
}
