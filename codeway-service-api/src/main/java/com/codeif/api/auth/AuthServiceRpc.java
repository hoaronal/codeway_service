package com.codeif.api.auth;

import com.codeif.constant.FeignConst;
import com.codeif.fallback.auth.AuthServiceRpcFallbackFactory;
import com.codeif.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 鉴权服务api
 **/
@FeignClient(contextId = "authClient",
		value = FeignConst.SERVICE_AUTHORIZATION_AUTH,
		path = FeignConst.SERVICE_AUTHORIZATION_AUTH_PATH,
		fallbackFactory = AuthServiceRpcFallbackFactory.class)
public interface AuthServiceRpc {

	/**
	 * 调用签权服务，判断用户是否有权限
	 */
	@PostMapping(value = "auth/permission")
	JsonData authPermission(@RequestParam("url") String url,
	                        @RequestParam("method") String method,
							@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication);
}