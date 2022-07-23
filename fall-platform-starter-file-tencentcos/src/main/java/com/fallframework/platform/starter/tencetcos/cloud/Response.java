package com.fallframework.platform.starter.tencetcos.cloud;

public class Response {
	// credentials
	public Credentials credentials = new Credentials();
	public String requestId;
	public String expiration;
	// 密钥的起始时间，是 UNIX 时间戳
	public long startTime;
	// 密钥的失效时间，是 UNIX 时间戳
	public long expiredTime;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RequestId:").append(requestId)
				.append(", TmpSecretId:").append(credentials.tmpSecretId)
				.append(", StartTime:").append(startTime)
				.append(", ExpiredTime:").append(expiredTime)
				.append(", Expiration:").append(expiration);
		return sb.toString();
	}
}
