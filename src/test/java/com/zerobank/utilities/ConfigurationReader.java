package com.zerobank.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    //Create properties object
    public static Properties properties = new Properties();
    static {

        try {
            //Load the file into FileInputStream
            FileInputStream file = new FileInputStream("configuration.properties");
            //load properties object with the file
            properties.load(file);

            //close the file
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in configuration properties.");
        }

    }

    //Use the above created logic to create a re-usable method

    public static String getProperty(String keyword){
        return  properties.getProperty(keyword);
    }

}
