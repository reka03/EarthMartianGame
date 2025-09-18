package hu.tamasireka03.martiangame.controller;

import hu.tamasireka03.martiangame.entities.DiaryEntry;
import hu.tamasireka03.martiangame.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //Összes napló egy userhez
    @GetMapping("/user/{userId}")
    public List<DiaryEntry> getUserDiary(@PathVariable Long userId) {
        return diaryService.getUserDiary(userId);
    }

    //Egy adott mission naplóbejegyzése
    @GetMapping("/user/{userId}/mission/{missionId}")
    public DiaryEntry getDiaryEntry(@PathVariable Long userId,
                                    @PathVariable Long missionId) {
        return diaryService.getDiaryEntry(userId, missionId);
    }

    //Napló mentése
    @PostMapping("/user/{userId}/mission/{missionId}")
    public DiaryEntry saveDiary(@PathVariable Long userId,
                                @PathVariable Long missionId,
                                @RequestParam String userText) {
        return diaryService.saveDiary(userId, missionId, userText);
    }
}