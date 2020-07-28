package com.myjava.rest.conf;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/* *
 * 不需要token拦截类的统一管理类
 * @project
 * @note 需要排除的url,不需要拦截验证
 * @author ljh
 * @Date 2020/7/28
 */
@Component
@ConfigurationProperties(prefix = "request")//只能从默认的全局文件中获取
@Data
@NoArgsConstructor
public class NoLoginRequestCfg {

	private List<String> excludeRequests;
	private List<String> needLoginNoRole; // 需要登录但是不需要权限

	public boolean check(String url){
		boolean res = false;

		if(excludeRequests==null)
			return false;

		if(excludeRequests.contains(url))
			res = true;
		return res;
	}
	public boolean checkLoginNoRole(String url){
		boolean res = false;
		if(needLoginNoRole==null)
			return false;
		if(needLoginNoRole.contains(url))
			res = true;
		return res;
	}
}
