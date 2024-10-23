package io.reactivestax.database;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

@Getter
public class ApplicationPropertiesUtils {
    private static ApplicationPropertiesUtils instance;
    private final Logger logger = Logger.getLogger(ApplicationPropertiesUtils.class.getName());

    @Setter
    private long totalNoOfLines;
    private int numberOfChunks;
    private String filePath;
    private String chunkDirectoryPath;
    private String chunkFilePathWithName;
    private int maxRetryCount;
    private String dbName;
    private String portName;
    private String userName;
    private String password;
    private String queueHost;
    private String queueUserName;
    private String queueUsername;
    private String queuePassword;
    private String queueExchangeName;
    private String queueExchangeType;
    private int chunkProcessorThreadCount;
    private int tradeProcessorQueueCount;
    private int tradeProcessorThreadCount;
    private String tradeDistributionCriteria;
    private boolean tradeDistributionUseMap;
    private String tradeDistributionAlgorithm;
    private String persistenceTechnology;
    private String messagingTechnology;

//    private ApplicationPropertiesUtils(String applicationPropertiesFileName){
//        loadApplicationProperties(applicationPropertiesFileName);
//}
    private ApplicationPropertiesUtils(String applicationPropertiesFileName){
        loadApplicationProperties(applicationPropertiesFileName);

    }

    public static synchronized ApplicationPropertiesUtils getInstance(String applicationPropertiesFileName){
        if(instance== null){
            instance =new ApplicationPropertiesUtils(applicationPropertiesFileName);
        }
        return instance;
    }

    public static synchronized ApplicationPropertiesUtils getInstace(){
        if (instance== null){
            instance=new ApplicationPropertiesUtils("application.properties");
        }
        return instance;
    }

   public void loadApplicationProperties(String applicationPropertiesFileName){
       Properties properties=new Properties();

       try{
           InputStream input= ApplicationPropertiesUtils.class.getClassLoader().getResourceAsStream(applicationPropertiesFileName);

           if (input==null){
               logger.warning("sorry , unable to send message ");
               System.exit(1);
           }
        properties.load(input);
           filePath=properties.getProperty("file.path");
           chunkDirectoryPath=properties.getProperty("chunk.directory.path");
           chunkFilePathWithName=properties.getProperty("chunk.file.path");
           dbName=properties.getProperty("db.name");
           userName=properties.getProperty("username");
           password=properties.getProperty("password");
           portName = properties.getProperty("port");
           numberOfChunks = Integer.parseInt(properties.getProperty("chunks.count"));
           maxRetryCount = Integer.parseInt(properties.getProperty("max.retry.count"));
           chunkProcessorThreadCount = Integer.parseInt(properties.getProperty("chunk.processor.thread.count"));
           tradeProcessorQueueCount = Integer.parseInt(properties.getProperty("queue.count"));
           tradeProcessorThreadCount = Integer.parseInt(properties.getProperty("trade.processor.thread.count"));
           tradeDistributionCriteria = properties.getProperty("trade.distribution.criteria");
           tradeDistributionUseMap = Boolean.parseBoolean(properties.getProperty("trade.distribution.use.map"));
           tradeDistributionAlgorithm = properties.getProperty("trade.distribution.algorithm");
           queueHost = properties.getProperty("queue.host");
           queueUsername = properties.getProperty("queue.username");
           queuePassword = properties.getProperty("queue.password");
           queueExchangeName = properties.getProperty("queue.exchange.name");
           queueExchangeType = properties.getProperty("queue.exchange.type");
           persistenceTechnology = properties.getProperty("persistence.technology");
           messagingTechnology = properties.getProperty("messaging.technology");

       } catch (Exception e) {
           logger.warning("file not found exception");
           System.exit(1);
       }
   }
}
