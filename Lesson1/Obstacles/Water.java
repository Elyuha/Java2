package Lesson1.Obstacles;

import Lesson1.Team.Member;

public class Water implements Obstacle{
    private final int dist;

    public Water(int dist) {
        this.dist = dist;
    }

    @Override
    public String doIt(Member member) {
        return member.swim(dist);
    }
}
