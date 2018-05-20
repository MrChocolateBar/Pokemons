package com.receivers;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

public interface DataReceiver<T extends Serializable> extends Closeable, Iterable<T> {
}
