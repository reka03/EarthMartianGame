package hu.tamasireka03.martiangame.controller;

import hu.tamasireka03.martiangame.entities.Mission;
import hu.tamasireka03.martiangame.service.MissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController( final MissionService missionService ) {
        this.missionService = missionService;
    }

    //összes küldetés
    @GetMapping
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    //küldetés részletekkel
    @GetMapping("/{id}")
    public Mission getMission(@PathVariable Long id) {
        return missionService.getMissionWithParts(id);
    }

    //küldetés terjesítése
    @PostMapping("/{id}/complate")
    public void completeMission(@PathVariable Long id,
                                @RequestParam Long userId){
        missionService.completeMission(userId, id);
    }
}