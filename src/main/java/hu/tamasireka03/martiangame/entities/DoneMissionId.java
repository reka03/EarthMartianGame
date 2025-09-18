package hu.tamasireka03.martiangame.entities;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class DoneMissionId {
    private Long userId;
    private Long missionId;

    public DoneMissionId() {}

    public DoneMissionId( Long userId, Long missionId ) {
        this.userId = userId;
        this.missionId = missionId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoneMissionId)) return false;
        DoneMissionId that = (DoneMissionId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(missionId, that.missionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, missionId);
    }
}