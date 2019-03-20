package org.whu.cs.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.whu.cs.bean.Upvote;
import org.whu.cs.service.UpvoteService;

import java.util.List;

/**
 * The type Upvote controller.
 */
@Controller
@RequestMapping(value = "/api/upvote")
public class UpvoteController {
    @Autowired
    private UpvoteService upvoteService;


    /**
     * 用户某日被赞详细信息
     *
     * @param toMemberId the 被赞人Id
     * @param date       the 日期
     * @return the 该用户被赞的详细信息
     */

    @ApiOperation(value="用户某日被赞详细信息",notes = "根据用户Id和日期来查询该用户被赞详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "toMemberId",value = "该用户(被赞)Id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="date",value = "查询日期，格式yyyy-MM-dd",required = true,dataType = "String")
    })
    @GetMapping(value = "/votedList")
    @ResponseBody
    public List<Upvote> getUpvoteListByToAndDate(@RequestParam String toMemberId, @RequestParam String date) {
        return upvoteService.getUpvoteListByToAndDate(toMemberId, date,1);
    }


    /**
     * 用户某日点赞别人的详细信息
     *
     * @param fromMemberId 用户Id
     * @param date 日期
     * @return 该用户点赞的详细信息
     */
    @ApiOperation(value="用户某日点赞别人的详细信息",notes = "根据用户Id和日期来查询该用户点赞别人的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "fromMemberId",value = "该用户(赞别人的)Id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="date",value = "查询日期，格式yyyy-MM-dd",required = true,dataType = "String")
    })
    @GetMapping(value = "/voteList")
    @ResponseBody
    public List<Upvote> getUpvoteListByFromAndDate(@RequestParam String fromMemberId,@RequestParam String date){
        return upvoteService.getUpvoteListByFromAndDate(fromMemberId,date,1);
    }

    /**
     * 用户每日被赞次数
     *
     * @param toMemberId the toMemberId
     * @param date       the date
     * @return the upvote count by from and date
     */

    @ApiOperation(value="用户每日被赞次数",notes = "根据用户Id和日期来查询用户被赞次数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "toMemberId",value = "被赞的用户Id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="date",value = "查询日期，格式yyyy-MM-dd",required = true,dataType = "String")
    })
    @GetMapping(value = "/votedCount")
    @ResponseBody
    public Integer getUpvoteCountByToAndDate(@RequestParam String toMemberId, @RequestParam String date) {
        return upvoteService.getUpvoteCountByToAndDate(toMemberId, date,1);
    }

    /**
     * 用户点赞或者取消点赞操作
     * @param fromMemberId 点赞人id
     * @param toMemberId 被点赞人Id
     * @return upvote 实体类
     */

    @ApiOperation(value="用户点赞或者取消点赞操作",notes = "根据点赞用户id和被赞用户id来进行点赞或者取消点赞 返回 更新后的数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "fromMemberId",value = "点赞用户Id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="toMemberId",value = "被赞用户Id",required = true,dataType = "String")
    })
    @GetMapping(value="/vote")
    @ResponseBody
    public Upvote UpVoteOrUnUpvote(@RequestParam String fromMemberId,@RequestParam String toMemberId){
        return upvoteService.UpvoteOrUnUpvote(fromMemberId,toMemberId);
    }
}
