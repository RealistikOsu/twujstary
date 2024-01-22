package com.realistikosu.resources.stats;

import com.realistikosu.osu.CustomModes;
import com.realistikosu.osu.Modes;

public class Stats {
    public Modes mode;
    public CustomModes customMode;
    public int userId;
    public double performancePoints;
    public float accuracy;
    public long totalScore;
    public long rankedScore;
    public long playCount;

    public Stats(Modes mode, CustomModes customMode, int userId, double performancePoints, float accuracy, long totalScore, long rankedScore, long playCount) {
        this.mode = mode;
        this.customMode = customMode;
        this.userId = userId;
        this.performancePoints = performancePoints;
        this.accuracy = accuracy;
        this.totalScore = totalScore;
        this.rankedScore = rankedScore;
        this.playCount = playCount;
    }
}
