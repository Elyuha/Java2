package Lesson1.Team;

public class Team {
    private Member[] members;
    private String name;
    private StringBuilder result;
    public Team(String name, Member... members){
        this.name = name;
        this.members = members;
        result = new StringBuilder();
    }

    public Member[] getMembers() {
        return members;
    }

    public void setStageResult(String result){
        this.result.append(result).append("\n");
    }

    public void showResult(){
        System.out.println("Результат команды " + name + ": ");
        System.out.println(result);
    }
}
