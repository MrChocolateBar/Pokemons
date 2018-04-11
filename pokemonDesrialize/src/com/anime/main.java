package com.anime;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class main
{
    public static void main(String[] args) throws IOException
    {
        ObjectInputStream in = null;
        InputStream PokeObjinStream = null;

        try {
            // perhaps 127.0.0.1 ?: / my ip - 10.100.102.11
            Socket PokeSocket = new Socket("127.0.0.1", 9876);
            PokeObjinStream = PokeSocket.getInputStream();
        } catch (UnknownHostException u) {
            u.printStackTrace();
            System.exit(1);
        }

        while (true)
        {
            try {
                in = new ObjectInputStream(PokeObjinStream);
                Pokemon p = (Pokemon) in.readObject();

                System.out.print(p.toString());
                System.out.println("\n");

            } catch (StreamCorruptedException e) {
                continue;
            } catch (InvalidClassException e) {
                continue;
            } catch (EOFException e) {
                System.out.print("Done!" + "\n");
                //e.printStackTrace();
                break;
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
                break;
            }
        }
        try {
            in.close();
            System.out.print("Socked closed! \n");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}

