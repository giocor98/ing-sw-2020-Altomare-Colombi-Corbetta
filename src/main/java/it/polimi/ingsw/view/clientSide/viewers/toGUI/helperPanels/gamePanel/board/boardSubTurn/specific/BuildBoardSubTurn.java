package it.polimi.ingsw.view.clientSide.viewers.toGUI.helperPanels.gamePanel.board.boardSubTurn.specific;

import it.polimi.ingsw.view.clientSide.viewCore.executers.executerClasses.BuildBlockExecuter;
import it.polimi.ingsw.view.clientSide.viewers.messages.ViewMessage;
import it.polimi.ingsw.view.clientSide.viewers.toGUI.helperPanels.gamePanel.board.boardSubTurn.BoardSubTurn;
import it.polimi.ingsw.view.clientSide.viewers.toGUI.interfaces.GUISubTurnViewer;
import it.polimi.ingsw.view.exceptions.CannotSendEventException;
import it.polimi.ingsw.view.exceptions.WrongParametersException;

import java.awt.*;

public class BuildBoardSubTurn extends BoardSubTurn {

    public BuildBoardSubTurn(GUISubTurnViewer guiSubTurnViewer)  {
        super(guiSubTurnViewer);
    }

    @Override
    public Cursor getOnEnterCursor(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource("/img/cursor/build.gif"));
        Point hotspot = new Point(16,16);
        return toolkit.createCustomCursor(image, hotspot, "build");
    }

    @Override
    public void onCellSelected(int x, int y){
        super.onCellSelected(x, y);
        BuildBlockExecuter myExecuter = (BuildBlockExecuter) guiSubTurnViewer.getMyExecuter();
        try {
            myExecuter.setCell(x, y);
            myExecuter.doIt();
        } catch (WrongParametersException | CannotSendEventException e) {
            ViewMessage.populateAndSend(e.getMessage(), ViewMessage.MessageType.EXECUTER_ERROR_MESSAGE);
        }

    }
}
