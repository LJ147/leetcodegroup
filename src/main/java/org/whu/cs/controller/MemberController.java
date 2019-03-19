package org.whu.cs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.service.MemberService;
import org.whu.cs.bean.Member;
import org.whu.cs.service.ValidService;

import java.util.*;


@RequestMapping(value = "/api/member")
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ValidService validService;


    @ApiOperation(value = "提交LeetCode地址", notes = "链接需要校验格式，地址不可重复")
    @ApiImplicitParam(paramType = "query", name = "url", value = "用户LeetCode或LeetCode-cn主页地址，参考格式： https://leetcode.com/alexlj/ ", required = true, dataType = "String")
    @PostMapping(value = "/create")
    @ResponseBody
    public String create(Member member) {
        String url = member.getUrl();
        String message = "";
        if (!validService.isValidLeetcodeUrl(url)) {
            message = url + " 格式有误";
        } else if (!memberService.existsByAddress(url)) {
            member.setGmt_create(new Date());
            memberService.save(member);
            message = url + " 已提交";
        } else {
            message = url + " 已存在";
        }
        return message;

    }

    // 后期分页
    @ApiOperation(value = "根据用户状态，查询用户LeetCode地址", notes = "三种状态，normal: 0, deleted：1， locked：2，只爬取normal状态的用户信息")
    @ApiImplicitParam(paramType = "query", name = "status", value = "用户状态 ", required = true, dataType = "int")
    @GetMapping(value = "/getMemberAddressList")
    @ResponseBody
    public List<String> getMemberByStatus(@RequestParam int status) {
        List<Member> members = memberService.findALlByStatus(status);
        List<String> urls = new ArrayList<>();
        for (Member member : members) {
            urls.add(member.getUrl());
        }
        return urls;
    }


}
