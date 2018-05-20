package com;
import com.anime.Pokemon;

import java.io.*;
import java.net.Socket;

public class DesrializeDemo
{
    public static void main(String[] args)
    {
        ObjectInputStream in = null;
        InputStream objInStream;

        try {
            objInStream = getInStream("127.0.0.1", 9876);
            while (true) {
                in = new ObjectInputStream(objInStream);
                printValidDeserialized(in);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static InputStream getInStream(String host, int port) throws IOException {
        Socket soc = new Socket(host, port);
        return soc.getInputStream();
    }

    public static void printValidDeserialized(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Pokemon myClass = (Pokemon) in.readObject();
        System.out.print(myClass.toString());
        System.out.println("\n");
    }
}

