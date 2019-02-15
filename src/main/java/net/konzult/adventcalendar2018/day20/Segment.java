package net.konzult.adventcalendar2018.day20;

import java.util.List;

public class Segment {
     Direction direction;
     List<Segment> segments;

    public Segment(Direction direction, List<Segment> segments) {
        this.direction = direction;
        this.segments = segments;
    }
}
