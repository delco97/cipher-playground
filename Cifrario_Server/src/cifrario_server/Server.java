/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrario_server;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe che crea il server UDP "Secret Inbox".
 * Una volta avviato rimane in attesa di messaggi 
 * da parte della applicazione lato client "Secret Sender".
 * I messaggi ricevuti vengono salvati e resi visibile nella interfaccia grafica.
 * * @author Andrea Del Corto-Simone Giacomelli 5IA07
 */
public class Server {
    
    private SimpleDateFormat sdf;//Utilizzato per stampare le date degli eventi.
    protected TMessageListener mesListener;//Attende nuovi messaggi dagli agenti
    private Cifrario_serverGUI gui;
    /**
     * costruttore della classe server
     * @param report oggetto di tipo StylePanelMessages
     */
    public Server(Cifrario_serverGUI gui){
      this.gui = gui;
      sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      mesListener = new TMessageListener(this);
    }

    public boolean isOn(){
      return mesListener.running && mesListener!=null;
    }

    public boolean turnOn(int porta){
      return mesListener.startListening(porta);
    }
    
    private void addMessageToFile(JTable tab,String filePath,String mesToAdd){
        
        try {
            Files.write(Paths.get(filePath), mesToAdd.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Messaggio aggiunto al file");
        }catch (IOException e) {
            System.out.println("Messaggio non aggiunto al filer");
            //exception handling left as an exercise for the reader
        }
    }
    
    public void newMess(DatagramPacket dp_rx,String mess){
      gui.report.appendMessage(dp_rx.getSocketAddress().toString()+":", mess, Color.blue, Color.black);
      DefaultTableModel model = (DefaultTableModel) gui.jTable_messaggi.getModel();
      Date date = new Date();
      model.addRow(new Object[]{sdf.format(date),dp_rx.getAddress().getHostAddress(), dp_rx.getPort(),mess,"",null});
      String mesForFile = sdf.format(date)+"§"+dp_rx.getAddress().getHostAddress()+"§"+ dp_rx.getPort()+"§"+mess+"§"+" "+"§"+"false";
      addMessageToFile(gui.jTable_messaggi,"src/cifrario_server/messaggi.txt",mesForFile+"\n");
    }

}
