/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrario_server;
import java.awt.Color;
import javax.swing.*;
/**
 *Classe che definisce lo stile nei messaggi nel jPanel
 * @author Andrea Del Corto-Simone Giacomelli 5IA07
 */
public class StylePanelMessages {
    protected JTextPane panel;
    private String fontH;
    private String fontMsg;
    private int dimH;
    private int dimMsg;
    
     /**
     * Costruttore della classe StylePanelMessages
     * @param p oggetto di tipo JTextPane
     */
    public StylePanelMessages(JTextPane p){
      panel = p;
      fontH = "Impact";
      fontMsg = "Arial";
      dimH = 13;
      dimMsg = 12;      
    }

    public String getFontH() {
        return fontH;
    }

    public String getFontMsg() {
        return fontMsg;
    }

    public int getDimH() {
        return dimH;
    }

    public int getDimMsg() {
        return dimMsg;
    }

    public void setFontH(String fontH) {
        this.fontH = fontH;
    }

    public void setFontMsg(String fontMsg) {
        this.fontMsg = fontMsg;
    }

    public void setDimH(int dimH) {
        this.dimH = dimH;
    }

    public void setDimMsg(int dimMsg) {
        this.dimMsg = dimMsg;
    }

        /**
     * Metodo che stampa messaggi sul jPanel
     * @param header Stringa che dichiara che la scansione è stata interrotta
     * @param msg Stringa che contiene il messaggio da dare
     * @param headerColor oggetto di tipo Color che imposterà il colore al header
     * @param contentColor oggetto di tipo Color che imposterà il colore al msg
     */
    synchronized public void appendMessage(String header,String msg,Color headerColor, Color contentColor){
        if(panel!=null){
        panel.setEditable(true);
        getMsgHeader(header, headerColor);
        getMsgContent(msg, contentColor);
        panel.setEditable(false);
        }
    }
    /**
     * Metodo che imposterà il messaggio di testa
     * @param header Stringa che conterrà il messaggio
     * @param color oggetto Color che darà il colore al messaggio
     */
    private void getMsgHeader(String header, Color color){
        int len = panel.getDocument().getLength();
        panel.setCaretPosition(len);
        panel.setCharacterAttributes(MessageStyle.styleMessageContent(color, fontH, dimH), false);
        panel.replaceSelection(header);
    }
    /**
     * Metodo che imposterà il messaggio 
     * @param msgStringa che conterrà il messaggio
     * @param color oggetto Color che darà il colore al messaggio
     */
    private void getMsgContent(String msg, Color color){
        int len = panel.getDocument().getLength();
        panel.setCaretPosition(len);
        panel.setCharacterAttributes(MessageStyle.styleMessageContent(color, fontMsg,dimMsg), false);
        panel.replaceSelection(msg +"\n");
    }           
}
