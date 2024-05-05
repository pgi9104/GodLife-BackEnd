package com.gen.script.oauth.info.impl;

import java.util.Map;

import com.gen.script.oauth.info.OAuth2UserInfo;

public class GitHubOAuth2UserInfo extends OAuth2UserInfo  {
    public GitHubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("login");
    }

    @Override
    public String getName() {
        return (String) attributes.get("login");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("imageUrl");
    }
}
