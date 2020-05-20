package it.polimi.ingsw.view.clientSide;

import it.polimi.ingsw.controller.events.NextStatusEvent;
import it.polimi.ingsw.controller.events.RequirePlayersNumberEvent;
import it.polimi.ingsw.controller.events.ServerSendDataEvent;
import it.polimi.ingsw.view.clientSide.viewCore.data.dataClasses.ViewBoard;
import it.polimi.ingsw.view.clientSide.viewCore.executers.Executer;
import it.polimi.ingsw.view.clientSide.viewCore.interfaces.ViewSender;
import it.polimi.ingsw.view.clientSide.viewCore.status.ViewStatus;
import it.polimi.ingsw.view.clientSide.viewers.interfaces.Viewer;
import it.polimi.ingsw.view.clientSide.viewers.messages.ViewMessage;
import it.polimi.ingsw.view.clientSide.viewers.toCLI.CLIViewer;
import it.polimi.ingsw.view.clientSide.viewers.toGUI.GUIViewer;
import it.polimi.ingsw.view.clientSide.viewers.toTerminal.TerminalViewer;
import it.polimi.ingsw.view.exceptions.NotFoundException;
import it.polimi.ingsw.view.exceptions.WrongViewObjectException;

import java.util.*;

public class ViewTester implements ViewSender {

    private final static boolean addWait = true;

    private Object lock = new Object();
    private View view = new View(null, null);

    private void myWait(){
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialization(){
        //new TerminalViewer().start();
        new GUIViewer().start();
        //new CLIViewer().start();


        Executer.setSender(this);
    }

    private void end(){
        Viewer.exitAll();
    }

    private void myMain() {

        Object obj = new Object();

        initialization();
        System.out.println("Hello World");

        ViewStatus.init();

        if(addWait){
            synchronized (obj) {
                try {
                    obj.wait(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        view.update((NextStatusEvent)new NextStatusEvent("vai alla login"));
        myWait();
        view.update((RequirePlayersNumberEvent) new RequirePlayersNumberEvent());
        myWait();
        view.update((NextStatusEvent)new NextStatusEvent("vai alla wait"));

        /*ViewMessage.populateAndSend("test fromServerMEssage", ViewMessage.MessageType.FROM_SERVER_MESSAGE);
        ViewMessage.populateAndSend("test serverError", ViewMessage.MessageType.FROM_SERVER_ERROR);
        ViewMessage.populateAndSend("test executerError", ViewMessage.MessageType.EXECUTER_ERROR_MESSAGE);
        ViewMessage.populateAndSend("test fatalError", ViewMessage.MessageType.FATAL_ERROR_MESSAGE);*/

        if(addWait) {
            synchronized (obj) {
                try {
                    obj.wait(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ViewMessage.populateAndSend("test async", ViewMessage.MessageType.FROM_SERVER_MESSAGE);
        if(addWait){
            synchronized (obj) {
                try {
                    obj.wait(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        view.update((NextStatusEvent)new NextStatusEvent("newGame"));

        {
            int boardXSize = 5;
            int boardYSize = 5;
            List<String> players = new ArrayList<String>();
            Map<String,List<String>> workersToPlayer = new HashMap<String, List<String>>();

            players.add("player1");
            players.add("player2");
            players.add("player3");

            List<String> w1 = new ArrayList<String>();
            List<String> w2 = new ArrayList<String>();
            List<String> w3 = new ArrayList<String>();

            w1.add(("1"));
            w1.add(("2"));
            w2.add(("3"));
            w2.add(("4"));
            w3.add(("5"));
            w3.add(("6"));

            /*workersToPlayer.put(players.get(0), w1);
            workersToPlayer.put(players.get(1), w2);
            workersToPlayer.put(players.get(2), w3);*/

            view.update((ServerSendDataEvent) new ServerSendDataEvent(boardXSize, boardYSize, players, workersToPlayer));
        }

        view.update((NextStatusEvent)new NextStatusEvent("go to gamePreparation"));




        /*
        view.update((NextStatusEvent)new NextStatusEvent("Playing"));*/
    }

    public static void main(String[] args) {
        new ViewTester().myMain();
    }

    @Override
    public void send(EventObject event) {
        synchronized (lock){
            lock.notifyAll();
        }
    }

}
