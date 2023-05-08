/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrario_client;

/**
 *
 * @author utente
 */
public class Cifrario {
    //String testo="";
    //String testo_cifrato="";

    public Cifrario() {
    }
    
    public String CifrarioVigenere(String daCodificare, String chiave){
        int j=0; 
        String criptato="";
        for(int i=0;i<daCodificare.length();i++){//Scorro tt lettere messaggio da criptare
            char newCarat=(char) (daCodificare.charAt(i)+chiave.charAt(j));
            j=(j+1)%chiave.length();//se l'indice sfora la lunghezza della chiave, riparte dall'inizio
            criptato+=newCarat;
        }
        return criptato;
    }       
    
    public String CifrarioCesare(String daCodificare, int chiave){
        String criptato="";
        for(int i=0;i<daCodificare.length();i++){//Scorro tt lettere messaggio da criptare
            char newCarat=(char) (daCodificare.charAt(i)+chiave);
            criptato+=newCarat;
        }
        return criptato;
    }
}
