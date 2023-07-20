package com.example.server.application.controllers;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.vo.BeotFollowingsVO;
import com.example.server.domains.beot.services.BeotService;
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

    @GetMapping("/following/:id")
    public List<BeotFollowingsVO> getFollowingBeots(@RequestParam("id") String id) {
        return beotService.getFollowingBeots(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@RequestBody @Valid BeotDto beot) {
        beotService.follow(beot);
    }
}
