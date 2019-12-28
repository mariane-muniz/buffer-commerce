package com.buffer.commerce.service;

import com.buffer.commerce.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    public boolean validateGroup(final List<Group> groups) {

        // TODO validate groups received with user groups
        return true;
    }
}
