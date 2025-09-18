package hu.tamasireka03.martiangame.service;

import hu.tamasireka03.martiangame.entities.*;
import hu.tamasireka03.martiangame.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionPartRepository missionPartRepository;
    private final AnswerRepository answerRepository;
    private final DoneMissionRepository doneMissionRepository;
    private final UserRepository userRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository,
                          MissionPartRepository missionPartRepository,
                          AnswerRepository answerRepository,
                          DoneMissionRepository doneMissionRepository,
                          UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.missionPartRepository = missionPartRepository;
        this.answerRepository = answerRepository;
        this.doneMissionRepository = doneMissionRepository;
        this.userRepository = userRepository;
    }

    //Összes küldetés lekérése
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    //Egy küldetés részletei (részekkel együtt)
    public Mission getMissionWithParts(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Küldetés nem található"));

        List<MissionPart> parts = missionPartRepository.findByMissionId(missionId);
        mission.setParts(parts);

        // minden part-hoz betöltjük a válaszokat
        for (MissionPart part : parts) {
            List<Answer> answers = answerRepository.findByPartId(part.getPartId());
            part.setAnswers(answers);
        }

        return mission;
    }

    //Küldetés teljesítése → csoki adás
    public void completeMission(Long userId, Long missionId) {
        //  már teljesítette?
        if (doneMissionRepository.existsByUserIdAndMissionId(userId, missionId)) {
            throw new RuntimeException("Ezt a küldetést már teljesítetted!");
        }

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Küldetés nem található"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));

        // csoki adás
        int totalReward = mission.getParts().stream()
                .mapToInt(MissionPart::getRewardChocolate)
                .sum();

        user.setChocolateCount(user.getChocolateCount() + totalReward);
        userRepository.save(user);

        // mentjük a teljesítést
        DoneMission doneMission = new DoneMission();
        doneMission.setUser(user);
        doneMission.setMission(mission);
        doneMissionRepository.save(doneMission);
    }
}