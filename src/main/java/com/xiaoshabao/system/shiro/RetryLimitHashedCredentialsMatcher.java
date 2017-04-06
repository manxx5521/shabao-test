package com.xiaoshabao.system.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.xiaoshabao.baseframework.component.cache.redis.VCache;

/**
 * 密码凭证匹配
 * <br>
 * 密码验证次数限制
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	//密码次数验证
	final static String PASSWORD_NUM = RetryLimitHashedCredentialsMatcher.class.getCanonicalName()+ "_password_num";

    public RetryLimitHashedCredentialsMatcher() {
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        String key=PASSWORD_NUM+username;
        
        @SuppressWarnings("unchecked")
		AtomicInteger number=VCache.get(key, AtomicInteger.class);
        if(number == null) {
        	number=new AtomicInteger(0);
        	VCache.set(key,number);
        }
        if(number.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
        	VCache.delByKey(key);
        }else{
        	VCache.set(key, number);
        }
        return matches;
    }
}

