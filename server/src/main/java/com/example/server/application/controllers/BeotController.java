package com.example.server.application.controllers;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.dto.BeotFollowingsVO;
import com.example.server.domains.beot.services.BeotReadService;
import com.example.server.domains.beot.services.BeotWirteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beots")
public class BeotController {
    @Autowired
    private BeotWirteService beotWirteService;

    @Autowired
    private BeotReadService beotReadService;

    @GetMapping("/following/:id")
    public List<BeotFollowingsVO> getFollowingBeots(@RequestParam("id") String id) {
        return beotReadService.getFollowingBeots(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@RequestBody() BeotDto beot) {
        beotWirteService.follow(beot);
    }
}