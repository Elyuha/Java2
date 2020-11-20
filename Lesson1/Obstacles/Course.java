package Lesson1.Obstacles;

import Lesson1.Team.Member;
import Lesson1.Team.Team;

public class Course {
    Obstacle[] obstacles;

    public Course(Obstacle... obstacles){
        this.obstacles = obstacles;
    }

    public void doIt(Team team){
        if (team.getMembers().length == 0){
            System.out.println("Дык в ващей команде никого нет!\n");
            return;
        }
        for(Member member:team.getMembers()){
            for(Obstacle obstacle:obstacles){
                team.setStageResult(obstacle.doIt(member));
                if (!member.onDistance())
                    break;
            }
        }
    }
}
