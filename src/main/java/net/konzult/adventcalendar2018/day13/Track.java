package net.konzult.adventcalendar2018.day13;

import java.util.Objects;

public class Track {
    private final Coordinates position;
    private final TrackType trackType;

    public Track(Coordinates position, TrackType trackType) {
        this.position = position;
        this.trackType = trackType;
    }

    public Coordinates getPosition() {
        return position;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    @Override
    public String toString() {
        return "Track{" +
                "position=" + position +
                ", trackType=" + trackType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return position.equals(track.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
