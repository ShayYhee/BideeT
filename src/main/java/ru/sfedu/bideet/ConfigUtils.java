package ru.sfedu.bideet;

import java.io.*;
import java.util.Properties;


/**
 *
 * @author Osebi
 */
public class ConfigUtils {
    private static String ConfigPath = "src/main/resources/config.properties";
    private static final Properties configuration = new Properties();

	//TODO make this a Singleton
    public ConfigUtils() {
        String path = System.getProperty("path");
        if (path != null){
            ConfigPath = path;
        }
        //loadProperties(PROPERTIES_FILE);
    }
    
    private static Properties getConfiguration() throws IOException{
        if (configuration.isEmpty()){
            loadConfiguration();
        }
        return configuration;
    }
    
    private static void loadConfiguration() throws IOException{
        try(FileInputStream in = new FileInputStream(ConfigPath)) {
            configuration.load(in);
        }catch (IOException ex){
            throw new IOException(ex);
        }
    }
    
    public static String getConfigurationEntry(String key) throws IOException{
        return getConfiguration().getProperty(key);
    }

     
}
