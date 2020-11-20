package Lesson1.Team;

public class Human implements Member {
    String name;
    private final int distRun;
    private final int distSwim;
    private final int heightJump;
    private boolean onDistance;

    public Human(String name, int distRun, int distSwim, int heightJump){
        this.name = name;
        this.distRun = distRun;
        this.distSwim = distSwim;
        this.heightJump = heightJump;
        onDistance = true;
    }

    @Override
    public String run(int dist) {
        if(distRun > dist)
            return (name + " пробежал!");
        else {
            return (name + " не пробежал!");
        }
    }

    @Override
    public String swim(int dist) {
        if(distSwim > dist)
            return (name + " проплыл!");
        else {
            return (name + " не проплыл!");
        }
    }

    @Override
    public String jump(int height) {
        if(heightJump > height)
            return (name + " перепрыгнул!");
        else {
            return (name + " не перепрыгнул!");
        }
    }

    @Override
    public boolean onDistance() {
        return (onDistance == true);
    }
}
