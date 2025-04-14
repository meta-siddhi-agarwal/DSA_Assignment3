package pac;

public class Bowlers {
    //name of the bowler
    String name;
    //no. of bowls he has
    int numberOfBowls;
    
    /**
     * constructor for initialzing variables of bowler
     * @param {name}->denotes name of the bowler
     * @param {numberOfBowls}->no. of bowls he has
     */
    Bowlers(String name, int numberOfBowls){
        this.name=name;
        this.numberOfBowls=numberOfBowls;
    }

    /**
     * will get no. of bowls a bowler has
     * @return-> no. of bowls a bowler has
     */
    public int getNumberOfBowls(){
        return numberOfBowls;
    }

    /**
     * will get name of the bowler
     * @return-> name of the bowler
     */
    public String getName(){
        return name;
    }
}
