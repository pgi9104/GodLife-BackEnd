package com.gen.script.oauth.info;

import java.util.Map;

import com.gen.script.oauth.entity.ProviderType;
import com.gen.script.oauth.info.impl.FacebookOAuth2UserInfo;
import com.gen.script.oauth.info.impl.GitHubOAuth2UserInfo;
import com.gen.script.oauth.info.impl.GoogleOAuth2UserInfo;
import com.gen.script.oauth.info.impl.KakaoOAuth2UserInfo;
import com.gen.script.oauth.info.impl.NaverOAuth2UserInfo;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case FACEBOOK: return new FacebookOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            case GITHUB: return new GitHubOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
