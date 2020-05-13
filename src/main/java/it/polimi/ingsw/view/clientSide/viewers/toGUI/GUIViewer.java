package it.polimi.ingsw.view.clientSide.viewers.toGUI;

import it.polimi.ingsw.view.clientSide.viewCore.status.ViewStatus;
import it.polimi.ingsw.view.clientSide.viewers.interfaces.StatusViewer;
import it.polimi.ingsw.view.clientSide.viewers.interfaces.Viewer;
import it.polimi.ingsw.view.exceptions.CheckQueueException;
import it.polimi.ingsw.view.exceptions.EmptyQueueException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GUIViewer extends Viewer {

    JFrame window;

    @Override
    public void refresh() {  }

    public GUIViewer(){
        Viewer.registerViewer(this);
    }

    public void setJPanel(JPanel panel){
        if(panel == null){
            window.setVisible(false);
        }else{
            window.add(panel);
            window.setVisible(true);
        }
    }

    public JFrame getWindow(){ return window; }

    private void setStatus(ViewerQueuedEvent queued){
        System.out.println("[GUI]\t"+ ViewStatus.getActual().toString());
        if(((StatusViewer)queued.getPayload()).toGUI() == null){
            //window.setVisible(false);
            return;
        }
        ((StatusViewer)queued.getPayload()).toGUI().setMyGUIViewer(this);

        try {
            ((StatusViewer)queued.getPayload()).toGUI().execute();
        } catch (CheckQueueException ignore) {}
    }

    @Override
    public void run() {
        init();
        bodyExecute();
        end();
    }

    private void bodyExecute(){
        ViewerQueuedEvent queued;
        while(true){
            waitNextEvent();
            while(true){
                try {
                    queued = getNextEvent();
                } catch (EmptyQueueException e) {
                    break;
                }

                if (queued.getType()== ViewerQueuedEvent.ViewerQueuedEventType.EXIT) return;
                if (queued.getType()== ViewerQueuedEvent.ViewerQueuedEventType.SET_STATUS) setStatus(queued);
            }
        }
    }

    private void end(){
        window.setVisible(false);
    }

    private void init(){
        window = new JFrame("SANTORINI");
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { Viewer.exitAll(); }
        });
        window.setSize(1024, 512);
        try {
            window.setIconImage(ImageIO.read(getClass().getResource("/img/icon/title_islan.png")));
        } catch (IOException ignore) {  }
    }
}
