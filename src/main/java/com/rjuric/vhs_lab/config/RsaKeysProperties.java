package com.rjuric.vhs_lab.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "jwt.keys")
public record RsaKeysProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
