package com.realistikosu.bancho.web;

import com.realistikosu.context.Context;
import com.realistikosu.resources.stats.StatsRepository;

public class BanchoContext extends Context {
    private final StatsRepository statsRepository;

    public BanchoContext(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public StatsRepository getStatsRepository() {
        return this.statsRepository;
    }

}
