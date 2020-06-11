package it.polimi.ingsw.view.clientSide.viewCore.executers.executerClasses;

import it.polimi.ingsw.view.clientSide.viewCore.executers.Executer;
import it.polimi.ingsw.view.events.ViewRequestDataEvent;

import java.util.EventObject;

public class RequestDataExecuter extends Executer {

    /**
     * Method that reset the executer with initial values.
     */
    public void clear(){}

    /**
     * constructor
     */
    public RequestDataExecuter(){
        this.clear();
    }

    /**
     * Static method that returns aunivoque string for each type of Exeuter (in this specific case "[Executer]").
     *
     * @return (Stirng univocally identifying the Executer type).
     */
    public static String myType(){ return Executer.myType() + "\tDataRequest"; }

    @Override
    /**
     * Method that returns the event of this Executer
     *
     * @return (The event associated to this Executer)
     */
    public EventObject getMyEvent() {
        return new ViewRequestDataEvent();
    }
}