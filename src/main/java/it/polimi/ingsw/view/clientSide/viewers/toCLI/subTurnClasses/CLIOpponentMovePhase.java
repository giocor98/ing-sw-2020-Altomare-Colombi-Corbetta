package it.polimi.ingsw.view.clientSide.viewers.toCLI.subTurnClasses;

import it.polimi.ingsw.view.clientSide.viewCore.data.dataClasses.ViewBoard;
import it.polimi.ingsw.view.clientSide.viewCore.status.ViewSubTurn;
import it.polimi.ingsw.view.clientSide.viewers.subTurnViewers.OpponentMoveViewer;
import it.polimi.ingsw.view.clientSide.viewers.toCLI.enumeration.ANSIStyle;
import it.polimi.ingsw.view.clientSide.viewers.toCLI.interfaces.CLIPrintFunction;
import it.polimi.ingsw.view.clientSide.viewers.toCLI.interfaces.CLISubTurnViewer;
import it.polimi.ingsw.view.clientSide.viewers.toCLI.statusClasses.CLIPlayingViewer;

public class CLIOpponentMovePhase extends CLISubTurnViewer {

    private CLIPlayingViewer myCLIStatusViewer = null;
    private OpponentMoveViewer opponentMoveViewer;

    private final int STARTING_SPACE = 7;

    public CLIOpponentMovePhase(OpponentMoveViewer opponentMoveViewer) {
        this.opponentMoveViewer = opponentMoveViewer;
    }


    /**
     * Prints the board, the god's details and a waiting message
     */
    @Override
    public void show() {

        final String WAITING_MESSAGE = "A player is moving his worker, please wait";

        CLIPrintFunction.printRepeatString(ANSIStyle.RESET, "\n", 2);
        ViewBoard.getBoard().toCLI();

        System.out.println();
        this.showCardsDetails(STARTING_SPACE);

        CLIPrintFunction.printRepeatString(ANSIStyle.RESET, "\n", 2);
        CLIPrintFunction.printRepeatString(ANSIStyle.RESET, " ", STARTING_SPACE);
        System.out.println(WAITING_MESSAGE);
        //todo: maybe add a little animation like WaitingViewer

    }

    @Override
    public ViewSubTurn getSubTurn() {
        return opponentMoveViewer.getMySubTurn();
    }

    /**
     * Overloading of CLISubTurnViewer's setMyCLIStatusViewer to set the correct CLIStatusViewer
     * @param myCLIStatusViewer
     */
    public void setMyCLIStatusViewer( CLIPlayingViewer myCLIStatusViewer) {
        this.myCLIStatusViewer = myCLIStatusViewer;
    }
}