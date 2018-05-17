package com.anime;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DesrializeDemo
{
    public static void main(String[] args) throws IOException
    {
        ObjectInputStream in = null;
        InputStream objInStream;
        boolean loopThru = Boolean.FALSE;

        objInStream = getInStream("127.0.0.1", "9876");
        if(objInStream == null)
        {
            System.out.print("Error opening a connection!");
            System.exit(0);
        }

        while (!loopThru)
        {
            try{
                in = new ObjectInputStream(objInStream);
            }
            catch (EOFException e) {
                System.out.print("Done!" + "\n");
                break;
            }
            catch (StreamCorruptedException e) {}
            loopThru = PrintValidDeserialised(in);
        }
        try {
            in.close();
            System.out.print("Socked closed! \n");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public static InputStream getInStream(String host, String port) throws IOException
    {
        try{

            if( host.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$") && port.matches("^[0-9][0-9][0-9][0-9]"))
            {
                Socket soc = new Socket(host, Integer.parseInt(port));
                return soc.getInputStream();
            }
        } catch (UnknownHostException u) {
            u.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    public static <T> Boolean PrintValidDeserialised(ObjectInputStream in){

        try {
            T myClass = (T) in.readObject();

            System.out.print(myClass.toString());
            System.out.println("\n");

        }
        catch (StreamCorruptedException e) {}
        catch (InvalidClassException e) {}
        catch (EOFException e) {
            System.out.print("Done!" + "\n");
            //e.printStackTrace();
            return Boolean.TRUE;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return Boolean.FALSE;
    }
}

