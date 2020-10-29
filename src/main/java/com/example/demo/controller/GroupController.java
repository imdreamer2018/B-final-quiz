package com.example.demo.controller;

import com.example.demo.dto.Group;
import com.example.demo.service.GroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/auto-grouping")
    public List<Group> autoGrouping() {
        return groupService.autoGrouping();
    }
}
