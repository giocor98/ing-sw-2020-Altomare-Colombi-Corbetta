package it.polimi.ingsw.view.clientSide.viewCore.data.dataClasses;

import it.polimi.ingsw.view.clientSide.viewCore.data.ViewObject;
import it.polimi.ingsw.view.exceptions.*;
import it.polimi.ingsw.view.interfaces.Addressable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.EventObject;

public class ViewNickname extends ViewObject {

    private String name;

    private ViewCard card;

    private static ViewNickname el = null;

    public static ViewNickname getNickname(){ return el; }

    public ViewCard getCard(){ return this.card; }

    /**
     * Method returning a unique string for each object inside the Class.
     *
     * @return (unique String identifying the object)
     */
    public String getId() {
        return name;
    }

    @Override
    /**
     * Method returning a unique String for each class.
     *
     * @return (unique string for each class)
     */
    public String getMyClassId() {
        return getClassId();
    }


    /**
     * function that returns for each Class the Base of its objects identificators as "[ClassId]".
     *
     * @return (String the base of Class identificators)
     */
    public static String getClassId(){
        return "[Nickname]";
    }

    /**
     * Method that will search the object with the passed id.
     *
     * @param id (String, the toString result of the searched Object)
     * @return (The searched Object)
     * @throws NotFoundException (If it doesn't find the object)
     * @throws WrongViewObjectException (If the object is not of this Class).
     */
    public static ViewObject search( @NotNull String id) throws NotFoundException, WrongViewObjectException{
        if(!isOfThisClass(id)||el==null)
            throw new WrongViewObjectException();
        return el;
    }

    /**
     * Method that will search the object with the passed id; if it doesn't exists then try to create it.
     *
     * @param id (String, the toString result of the searched Object)
     * @return (The searched Object)
     * @throws NotFoundException (If it doesn't find the object and cannot build it)
     * @throws WrongViewObjectException (If the object is not of this Class).
     */
    public static ViewObject find( @NotNull String id) throws NotFoundException, WrongViewObjectException{
        if(!isOfThisClass(id)||el==null)
            throw new WrongViewObjectException();
        return el;
    }

    /**
     * Method that will be called on the arrival of an event on this object.
     *
     * @param event (The Event to be notified)
     * @return (true iif the event is notified in the right way)
     * @throws WrongEventException (if the Event is not used for this object)
     */
    public boolean notifyEvent( @NotNull EventObject event) throws WrongEventException{
        //todo: implement it
        return true;
    }

    /**
     * Method that will be called on the arrival of an event to build a new Object.
     *
     * @param event (the Event arrived)
     * @return (the new object created)
     * @throws WrongEventException (if the Event is not supported by this Class)
     */
    public static ViewObject populate( @NotNull EventObject event) throws WrongEventException{
        //todo immplement it
        throw new WrongEventException();
    }

    /**
     * Method to discard all the objects of the Class.
     */
    public static void clear(){
        el = null;
    }

    /**
     * Method that will return a String to write the ViewObject on the Terminal.
     *
     * @return (String representing the object and its status)
     */
    public String toTerminal(){
        return this.toString();
    }

    /**
     * Method that will return a (Object) that will represent the ViewObject on the CLI.
     *
     * @return (representation of Object for the CLI)
     */
    public Object toCLI(){
        return null;
    }

    /**
     * Method that will return a (Object) that will represent the ViewObject on the GUI.
     *
     * @return (representation of Object for the GI)
     */
    public JPanel toGUI(){
        return null;
    }

    /**
     * Method checking weather the given string is identifying this.
     *
     * @param st (String that will possibly represent this)
     * @return (true iif st==this.toString())
     */
    public boolean isThis ( @NotNull String st){
        return (st.equals(this.toString())||st.equals(this.getId()));
    }

    /**
     * Constructor.
     *
     * @param nickname (String, nickname dell'utente)
     */
    private ViewNickname(String nickname){
        this.name = nickname;
        this.card = null;
        el = this;
    }

    public static void setNickname(String nickname) throws AlreadySetException, WrongParametersException {
        if(el != null) throw new AlreadySetException();
        if(false) throw new WrongParametersException();
        new ViewNickname(nickname);
    }

    /**
     * Method that will return the nickname.
     *
     * @return (String: nickname or null)
     */
    public static String getMyNickname(){
        if(el!=null) return el.getId();
        return null;
    }

    /**
     * Method that returns the card choosen by the player.
     *
     * @return (ViewCard choosen by the player; null if not chosen)
     */
    public static ViewCard getMyard(){
        if(el!=null) return el.getCard();
        return null;
    }

    public void setCard(ViewCard card){ this.card = card; }

    public void setCard(String card){
        try {
            this.setCard((ViewCard)ViewCard.search(card));
        } catch (NotFoundException | WrongViewObjectException e) {
        }
    }

    public static void setMyCard(ViewCard card){ if(el!= null) el.setCard(card); }

    public static void setMyCard(String card){ if(el!= null) el.setCard(card); }
}
