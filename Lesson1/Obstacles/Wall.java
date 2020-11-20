package Lesson1.Obstacles;

import Lesson1.Team.Member;

public class Wall implements Obstacle{
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public String doIt(Member member) {
        return member.jump(height);
    }
}
