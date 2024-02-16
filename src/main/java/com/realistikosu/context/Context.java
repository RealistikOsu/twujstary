package com.realistikosu.context;

import com.realistikosu.resources.stats.StatsRepository;
import com.realistikosu.resources.users.UserRepository;

public abstract class Context {
    public abstract StatsRepository getStatsRepository();
    public abstract UserRepository getUserRepository();
}
