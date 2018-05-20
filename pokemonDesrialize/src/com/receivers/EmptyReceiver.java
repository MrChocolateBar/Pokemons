package com.receivers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

public class EmptyReceiver<T extends Serializable> implements DataReceiver<T> {

    @Override
    public void close() throws IOException {

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
