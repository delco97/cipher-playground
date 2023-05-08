/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrario_server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread che 
 * @author dca
 */
public class TMessageListener extends Thread{
  private byte[] buffer;
  protected DatagramPacket dp_rx;//Busta UDP
  protected DatagramSocket reciver; //postino UDP
  protected boolean running;
  private final int MAX_CARAT=1024;//Numero massimo di caratteri del messaggio.
  private Server server;//Riferimento al server che gestisce i messaggi ricevuti
  
  
  public TMessageListener(Server server){
          buffer = new byte[MAX_CARAT];
          this.server = server;
          reciver  = null;
       
  }    
  
  public boolean startListening(int porta){
      try {
          reciver  = new DatagramSocket(porta);
          if(!running)start();
          return true;
      } catch (Exception ex) {
         reciver=null; 
         return false;
      }
    }

    @Override
    public void run() {
        running=true;
        while(running){      
            try {
                buffer = new byte[MAX_CARAT];
                dp_rx = new DatagramPacket(buffer,MAX_CARAT);
                reciver.receive(dp_rx); // bloccante
                String mess=new String(dp_rx.getData(),0, dp_rx.getData().length, "UTF-8").trim();
                System.out.println(mess);
                server.newMess(dp_rx,mess);
            } catch (Exception e) {
                System.out.println("Errore in ascolto!");
                reciver = null;
                running=false;
                //stopListening();Errore ricezione messaggio
                //Logger.getLogger(TMessageListener.class.getName()).log(Level.SEVERE, null, e);
            }    
        }      
    }
}
