
package Messenger;
import java.net.*;
import java.io.*;
import Messenger.Messenger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;

public class ConectorCliente extends Thread
{
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;  
    private DataOutputStream salida;          
    private BufferedReader entrada;           
    final int puerto = 8000;   
    
    
    public ConectorCliente()
    {
        try{
           ss=new ServerSocket(puerto);   
           s=ss.accept();
           entradaSocket=new InputStreamReader(s.getInputStream());
           entrada=new BufferedReader(entradaSocket);     
           salida=new DataOutputStream(s.getOutputStream());   
        }catch(Exception e){};
    
    }
    public ConectorCliente(String ip)
    {
        try{

           s=new Socket(ip,this.puerto);      
           entradaSocket = new InputStreamReader(s.getInputStream());
           entrada = new BufferedReader(entradaSocket);       
           salida = new DataOutputStream(s.getOutputStream());

         }catch(Exception e){};
     }
     public void run()
     {             
         String texto="text";
         while(true)
         {
             try{
                  texto=entrada.readLine();
                  VentanaCliente.jTextArea1.setText(VentanaCliente.jTextArea1.getText()+"\n"+texto);
                }catch(IOException e){};
         }
     }
public void enviarMSG(String msg) {
    DateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Date horaActual = new Date();

    try {
        salida.writeUTF(hora.format(horaActual) + " - " + msg + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   
     public String leerMSG()                //method for read a messages
     { 
       try{
            return entrada.readLine();
          }catch(IOException e){};
       return null;
     }
     
     public void desconectar()              
     {  
        try{
            s.close();
           }catch(IOException e){};
        try{
            ss.close();
            }catch(IOException e){};
     }   
}