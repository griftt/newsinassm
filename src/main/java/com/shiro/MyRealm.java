package com.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.Admin;
import com.serviceImpl.AdminService;

public class MyRealm extends AuthorizingRealm{
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		 System.out.println("开始授权");
		 System.out.println(principalCollection.getRealmNames());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();    
        Admin principal = (Admin) principalCollection.getPrimaryPrincipal();//获取登录的用户名    
      System.out.println(principal);
        if("10001".equals(principal.getAccount())){       
        	System.out.println("10001来啦");
        	//两个if根据判断赋予登录用户权限
            info.addRole("superadmin");
        }
        if("10003".equals(principal.getAccount())){
            info.addRole("admin");
        }
        
        info.addRole("admin");
        
        return info;
		
	}
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 System.out.println("开始认证");
		Object principal = token.getPrincipal();
		Admin admin = adminService.findByAccount((String)principal);
		System.out.println(admin+"222222222222222222");
		String pass=null;
		if(admin==null){
			throw new UnknownAccountException("用户账号有误");
		}
		pass=admin.getPwd();
        String credentials = pass;
        //3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
       // String source = "abcdefg";
        //ByteSource credentialsSalt = new Md5Hash(source);
		 String realmName = getName();
	        //返回值实例化
	        SimpleAuthenticationInfo info = 
	                new SimpleAuthenticationInfo(admin, credentials,realmName);
	        System.out.println("认证结束");
		return info;
	}
	 //init-method 配置. 
    public void setCredentialMatcher(){
       /* HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();    
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
        credentialsMatcher.setHashIterations(1024);//1024次循环加密      
        setCredentialsMatcher(credentialsMatcher);*/
    }
    
	
	
}
