package Messenger;

public class Messenger 
{
    public static ConectorCliente Cliente;
    public static void main(String[]args)
    {     
        VentanaCliente server=new VentanaCliente();
        server.main();
    }
    
    public static void iniciarcliente()
    {
        Cliente = new ConectorCliente();
       
    }
    
    public static void iniciarcliente(String ip)
    {
        Cliente = new ConectorCliente(ip);
        Cliente.start();
    }
}
