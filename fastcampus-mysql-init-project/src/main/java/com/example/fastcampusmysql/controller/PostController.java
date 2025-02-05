package com.example.fastcampusmysql.controller;


import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;

    @PostMapping("")
    public Long create(PostCommand postCommand){
        return postWriteService.create(postCommand);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCOunts(DailyPostCountRequest request){
        return postReadService.getDailyPostCount(request);
    }
    @GetMapping("/members/{memberId}")
    public Page<Post> getPosts(@PathVariable Long memberId, @RequestParam Integer page, @RequestParam Integer size){
        return postReadService.getPosts(memberId, PageRequest.of(page, size));
    }
}
