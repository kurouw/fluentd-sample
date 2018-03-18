package com.kron.fluentdsample;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TagData {
    private int id;
    private double rssi;
    private long time;
    private double phase;

    public TagData(int id, double rssi, long time, double phase) {
        this.id = id;
        this.rssi = rssi;
        this.time = time;
        this.phase = phase;
    }

    public int getId() {
        return id;
    }

    public double getRssi() {
        return rssi;
    }

    public long getTime() {
        return time;
    }

    public double getPhase() {
        return phase;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", this.id);
        data.put("rssi", this.rssi);
        data.put("time", this.time);
        data.put("phase", this.phase);
        return data;
    }

    @Override
    public String toString() {
        return "TagData{" +
                "id=" + id +
                ", rssi=" + rssi +
                ", time=" + time +
                ", phase=" + phase +
                '}';
    }
}
