package Lesson1.Obstacles;

import Lesson1.Team.Member;

public class Cross implements Obstacle{
    private final int dist;

    public Cross(int dist) {
        this.dist = dist;
    }

    @Override
    public String doIt(Member member) {
        return member.run(dist);
    }
}
