package com;
import com.anime.Pokemon;
import com.receivers.DataReceiver;
import com.receivers.ReceiverCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeserializeDemo
{
    public static void main(String[] args) {
        try {
            DataReceiver<Pokemon> pokemonDataReceiver = getReceiverFromUser();
            printPokemons(pokemonDataReceiver);
            pokemonDataReceiver.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DataReceiver<Pokemon> getReceiverFromUser() throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter receiver type(EMPTY/SOCKET):");
        String receiverType = reader.next();
        System.out.println("Enter receiver parameters:");
        List<String> receiverParams = new ArrayList<>();

        String arg;
        while (!(arg = reader.next()).equals("stop")) {
            receiverParams.add(arg);
        }

        return ReceiverCreator.valueOf(receiverType).createReceiver(receiverParams);
    }

    private static void printPokemons(DataReceiver<Pokemon> pokemonDataReceiver) {
        for (Pokemon pokemon : pokemonDataReceiver) {
            if (pokemon == null) {
                continue;
            }

            System.out.println(pokemon);
        }
    }
}
