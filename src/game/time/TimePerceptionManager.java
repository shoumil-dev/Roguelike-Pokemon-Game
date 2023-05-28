package game.time;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Abdul Harith Abdul Halim ID : 32871341
 *
 */
public class TimePerceptionManager {
    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;

    private int turn;
    public static int turn1;


    private TimePeriod shift; // DAY or NIGHT

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     *
     * @return TimePerceptionManager singleton instance
     * <p>
     * FIXME: create a singleton instance.
     */

    public static TimePerceptionManager getInstance() {

        if (instance == null) {
            instance = new TimePerceptionManager();
        }
        return instance;

    }

    /**
     * Private constructor
     */
    public TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        this.turn = 0;
        this.turn1=0;


    }

    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     * <p>
     * FIXME: write a relevant logic (i.e., increment turns choose day or night) and call this method once at every turn.
     */
    public void run() {


        if (this.turn > 10) {
            this.turn = 0;
            this.turn1=0;
        }
        if (turnDay(this.turn)) {




            for (TimePerception perceptionManager : timePerceptionList) {
                perceptionManager.dayEffect();

            }
            System.out.println("IT IS DAY TIME- TURN " + this.turn);

        }
        if (turnNight(this.turn)) {
            for (TimePerception perceptionManager : timePerceptionList) {
                perceptionManager.nightEffect();
            }


            System.out.println("IT IS NIGHT TIME- TURN " + this.turn);

        }
        this.turn += 1;
        this.turn1+=1;
    }





    /**
     * Add the TimePerception instance to the list
     * FIXME: add objInstance to the list.
     *
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {

        timePerceptionList.add(objInstance);
    }


    /**
     * Remove a TimePerception instance from the list
     * <p>
     * FIXME: [OPTIONAL] run cleanUp once every turn if you don't want to
     * have too many instances in the list (e.g., memory leak)
     *
     * @param objInstance object instance
     */
    public void cleanUp(TimePerception objInstance) {
    }

    public boolean turnDay(int count){
        boolean flag=false;
        if(count>=0 && count<=5){
            flag=true;
        }
        return flag;
    }

    public boolean turnNight(int count){
        boolean flag=false;
        if(count>=6 && count<=10){
            flag=true;
        }
        return flag;

    }











}
