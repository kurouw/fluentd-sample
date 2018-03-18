package com.kron.fluentdsample;

import org.fluentd.logger.FluentLogger;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static FluentLogger logger = FluentLogger.getLogger("tag", "localhost", 24224);

    public static void main(String[] args) {
        URL url = Main.class.getResource("/pitcher_farside00.csv");

        try {
            Path path = Paths.get(url.toURI());
            Files.lines(path, StandardCharsets.UTF_8).forEach(line -> {
                String[] splited = line.split(",");
                int id = Integer.parseInt(splited[1].split(" ")[1]);
                double rssi = Double.parseDouble(splited[2]);
                long time = Long.valueOf(splited[4]);
                double phase = Double.parseDouble(splited[3]);
                TagData tagdata = new TagData(id, rssi, time, phase);

                logger.log("report", tagdata.toMap());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

}


