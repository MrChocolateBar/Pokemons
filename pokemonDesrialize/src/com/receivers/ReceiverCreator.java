package com.receivers;

import java.io.IOException;
import java.util.List;

public enum ReceiverCreator {
    EMPTY {
        @Override
        public DataReceiver createReceiver(List<String> params) {
            return new EmptyReceiver();
        }
    },
    SOCKET {
        @Override
        public DataReceiver createReceiver(List<String> params) throws IOException {
            return new SocketReceiver(params.get(0), Integer.valueOf(params.get(1)));
        }
    };

    public abstract DataReceiver createReceiver(List<String> params) throws IOException;
}
