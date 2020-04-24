package it.polimi.ingsw.controller;

/**
 * Interface for general information exchange (from View [Client]).
 *
 * VCEvent (Events form Client)
 *
 * @author AndreaAltomare
 */
public interface ClientGeneralListener {
    public void onNicknameSubmit(String nickname);
    public void onPlayerQuit(String nickname);
    public void viewRequestData(); // Game match general data
}