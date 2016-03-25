package com.superschmalgames.Utilities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Cory on 3/25/2016.
 */
public class DataManager {

    public static void save(Serializable data, String filename) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            out.writeObject(data);
        }
    }

    public static Object load(String filename) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            return in.readObject();
        }
    }
}
