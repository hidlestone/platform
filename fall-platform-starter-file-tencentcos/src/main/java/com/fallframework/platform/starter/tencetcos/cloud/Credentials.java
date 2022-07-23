package com.fallframework.platform.starter.tencetcos.cloud;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Credentials {
    // 临时密钥 Id，可用于计算签名
    public String tmpSecretId;
    // 临时密钥 Key，可用于计算签名
    public String tmpSecretKey;
    // 请求时需要用的 token 字符串，最终请求 COS API 时，需要放在 Header 的 x-cos-security-token 字段
    public String sessionToken;
    // 为了兼容，需要解析这个字段
    public String token;
}
