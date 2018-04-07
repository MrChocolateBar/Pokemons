package com.anime;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class main
{
    public static void main(String [] args) {
        ObjectInputStream in = null;

        try {
            // perhaps 127.0.0.1 ?: / my ip - 10.100.102.11
            Socket PokeSocket = new Socket("127.0.0.1", 9876);
            in = new ObjectInputStream(PokeSocket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // TODO: Continue handling types mismatches - 'AC' as type code, maybe we read too much bits at once?
        // look at how we handle the in data (should be by files or bits? serv/client mismatch).
        try {
            do {
                try{
                Pokemon p = (Pokemon) in.readObject();

                System.out.print(p.toString());
                System.out.println("\n");

                } catch (EOFException eofException) {
                    System.out.print("eof! all pokemons handeled! \n");
                    System.exit(1);
                } catch (IOException e) {
                    System.out.print("Bad data!");
                    // Taltul - leaving these to show you i tried to reset the buffer, maybe it gets only the
                    // first bytes and gets stuck?
//                    in.reset();
//                    in.readObject();
//                    System.out.print(in);
//                    System.out.println("\n");
                } catch (ClassNotFoundException c) {
                    c.printStackTrace();
                    System.exit(1);
                }

            }while(in.readObject() != null);

        } catch (IOException e) {
            System.out.print("Done! \n");
        } catch (ClassNotFoundException c) {
            System.out.print("Class error! \n");
            c.printStackTrace();
            System.exit(1);

//            in.available() > 0
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("\n");

        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
