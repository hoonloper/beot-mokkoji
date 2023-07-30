package com.example.server.application.controllers;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.vo.BeotFollowingsVo;
import com.example.server.domains.beot.service.BeotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beots")
public class BeotController {
    @Autowired
    private BeotService beotService;

    @GetMapping("/following/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BeotFollowingsVo> getFollowingBeots(@PathVariable("memberId") String memberId) {
        return beotService.getFollowingBeots(memberId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@RequestBody @Valid BeotDto beotDto) {
        beotService.follow(beotDto);
    }

    @DeleteMapping("/following")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@RequestBody @Valid BeotDto beotDto) {
        beotService.unfollow(beotDto);
    }
}
