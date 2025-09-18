package hu.tamasireka03.martiangame.service;

import hu.tamasireka03.martiangame.entities.DiaryEntry;
import hu.tamasireka03.martiangame.entities.Mission;
import hu.tamasireka03.martiangame.entities.User;
import hu.tamasireka03.martiangame.repository.DiaryEntryRepository;
import hu.tamasireka03.martiangame.repository.MissionRepository;
import hu.tamasireka03.martiangame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    private final DiaryEntryRepository diaryEntryRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Autowired
    public DiaryService(DiaryEntryRepository diaryEntryRepository,
                        MissionRepository missionRepository,
                        UserRepository userRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    //Összes naplóbejegyzés egy userhez
    public List<DiaryEntry> getUserDiary(Long userId) {
        return diaryEntryRepository.findByUserId(userId);
    }

    //Napló egy adott mission-höz
    public DiaryEntry getDiaryEntry(Long userId, Long missionId) {
        return diaryEntryRepository.findByUserIdAndMissionId(userId, missionId)
                .orElse(null); // ha nincs még, frontend majd üresként kezeli
    }

    //Napló mentése / frissítése
    public DiaryEntry saveDiary(Long userId, Long missionId, String userText) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Küldetés nem található"));

        DiaryEntry entry = diaryEntryRepository.findByUserIdAndMissionId(userId, missionId)
                .orElse(new DiaryEntry());
        entry.setUser(user);
        entry.setMission(mission);
        entry.setUserText(userText);

        return diaryEntryRepository.save(entry);
    }
}