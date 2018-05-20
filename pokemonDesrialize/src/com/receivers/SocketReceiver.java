package com.receivers;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;

public class SocketReceiver<T extends Serializable> implements DataReceiver<T>  {
    private Socket soc;

    public SocketReceiver(String host, int port) throws IOException {
        soc = new Socket(host, port);
    }

    @Override
    public void close() throws IOException {
        soc.close();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            T next;

            @Override
            public boolean hasNext() {
                try {
                    ObjectInputStream input = new ObjectInputStream(soc.getInputStream());
                    next = (T) input.readObject();
                } catch (ClassNotFoundException | StreamCorruptedException ignored) {
                    next = null;
                } catch (EOFException e) {
                    return false;
                } catch (IOException e) {
                    next = null;
                }

                return true;
            }

            @Override
            public T next() {
                return next;
            }
        };
    }
}
