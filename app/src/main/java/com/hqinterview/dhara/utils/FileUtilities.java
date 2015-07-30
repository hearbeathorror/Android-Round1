package com.hqinterview.dhara.utils;

import android.os.Environment;

import com.hqinterview.dhara.HQInterviewDharaApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by USER on 29-07-2015.
 */
public class FileUtilities {
    private static final String FILE_PATH = "/data/webresponse/";
    private static final String FILE_NAME= "responseholder.txt";

    public static void writeObjectToFile(Object object) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() +
                    "/" +
                    HQInterviewDharaApp.getAppContext().getPackageName() +
                    FILE_PATH);

            if(!file.exists()) {
                file.mkdir();
                file.mkdirs();
            }
            File writingFile = new File(file, FILE_NAME);

            if(!writingFile.exists())
                writingFile.createNewFile();

            FileOutputStream fout = new FileOutputStream(writingFile);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
            oos.close();
        }catch(IOException e) {
            e.printStackTrace();

            // there was an error writing to the file
            // so store in the cache
            CacheUtilities.storeDataIntoCache(object);
        }
    }

    public static Object readObjectFromFile(String whatToGet){
        try {
            File file = new File(Environment.getExternalStorageDirectory() +
                    "/" +
                    HQInterviewDharaApp.getAppContext().getPackageName() +
                    FILE_PATH);

            if(!file.exists()) {
                file.mkdir();
                file.mkdirs();
            }
            File readingFile = new File(file, FILE_NAME);

            FileInputStream fin = new FileInputStream(readingFile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object response = ois.readObject();
            ois.close();
            fin.close();
            return response;
        }catch(IOException e) {
            e.printStackTrace();

            // the file was not found so return data from the cache
            Object response = CacheUtilities.getDataFromCache(whatToGet);
            return response;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
