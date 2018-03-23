package com.kron.fluentdsample;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
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
    private static FluentLogger logger = FluentLogger.getLogger("tag", "fluentd", 24224);

    public static void main(String[] args) {
        URL url = Main.class.getResource("/pitcher_farside00.csv");

        try {
            Path path = Paths.get(url.toURI());
            Files.lines(path, StandardCharsets.UTF_8).forEach(line -> {
                String[] splited = line.split(",");
                String ip = "0.0.0.0";
                int port = Integer.parseInt(splited[0]);
                String id = String.join("", splited[1].split(" "));
                double rssi = Double.parseDouble(splited[2]);
                long time = Long.valueOf(splited[4]);
                double phase = Double.parseDouble(splited[3]);
                TagData tagdata = new TagData(ip, port, id, rssi, time, phase);

                logger.log("report" + port + id, tagdata.toMap());
                System.out.println(tagdata.toString());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

}


