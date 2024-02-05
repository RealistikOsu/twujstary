package com.realistikosu.context;

import com.realistikosu.resources.stats.StatsRepository;

public abstract class Context {
    public abstract StatsRepository getStatsRepository();
}
