package com.haol.advertisement.service;

public interface EntranceService {

    Boolean weChatVerification(String signature, Long timestamp, String nonce, String echostr);
}
