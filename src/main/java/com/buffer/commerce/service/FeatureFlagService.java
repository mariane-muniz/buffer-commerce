package com.buffer.commerce.service;

import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

    public boolean active(final String featureName) {

        // TODO validate if the feature are available
        return true;
    }
}
