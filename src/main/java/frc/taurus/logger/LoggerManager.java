package frc.taurus.logger;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.flatbuffers.FlatBufferBuilder;

import edu.wpi.first.wpilibj.Timer;
import frc.taurus.config.Channel;
import frc.taurus.config.ChannelIntf;
import frc.taurus.config.Configuration;


public class LoggerManager {

    SortedMap<String, FlatBuffersLogger> loggerMap = new TreeMap<>();
    static ArrayList<ChannelIntf> channels = new ArrayList<ChannelIntf>();

    
    public LoggerManager() {}
    
    // TODO: add timestamp to filename or folder
    public void register(ChannelIntf channel) {
        if (!channels.contains(channel)) {
            channels.add(channel);
            createLogger(channel);
        }
    }
    
    
    void createLogger(ChannelIntf channel) {
        // get the filename listed in Config
        String filename = channel.getLogFilename();
        
        // if log filename is not empty, log it
        if (!filename.isEmpty()) {
            // if filename has not been seen before, create a logger for that file
            if (!loggerMap.containsKey(filename)) {
                loggerMap.put(filename, new FlatBuffersLogger(filename));
            }
            FlatBuffersLogger logger = loggerMap.get(filename);
            logger.register(channel);
        }
    }
    
    public void update() {
        for (var logger : loggerMap.values()) {
            logger.update();
        }
    }
    
    public void close() {
        for (var logger : loggerMap.values()) {
            logger.close();
        }
    }
    


    
    public static ByteBuffer getFileHeader() {
        FlatBufferBuilder builder = new FlatBufferBuilder(256);
        
        // create Channels
        int[] channelOffsets = new int[channels.size()];
        int k = 0;
        for (var channel : channels) {
            channelOffsets[k] = Channel.createChannel(builder, 
                                    channel.getNum(),
                                    builder.createString(channel.getName()),
                                    builder.createString(channel.getLogFilename()));
        }                            
        // create Channel vector
        int channelVectorOffset = Configuration.createChannelsVector(builder, channelOffsets);
        
        // create Config
        int configOffset = Configuration.createConfiguration(builder, channelVectorOffset);
        
        // create LogFileHeader
        double timestamp = Timer.getFPGATimestamp();
        int offset = LogFileHeader.createLogFileHeader(builder, timestamp, configOffset);
        LogFileHeader.finishSizePrefixedLogFileHeaderBuffer(builder, offset);
        ByteBuffer fileHeader = builder.dataBuffer();

        return fileHeader;
    }    

    public String getLogFilename(ChannelIntf channel) {
        FlatBuffersLogger logger = loggerMap.get(channel.getLogFilename());
        return logger.getBasePath() + "/" + channel.getLogFilename();
    }

}