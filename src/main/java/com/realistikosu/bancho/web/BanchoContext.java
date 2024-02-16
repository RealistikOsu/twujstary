package com.realistikosu.bancho.web;

import com.realistikosu.context.Context;
import com.realistikosu.resources.stats.StatsRepository;
import com.realistikosu.resources.users.UserRepository;

public class BanchoContext extends Context {
    private final StatsRepository statsRepository;
    private final UserRepository userRepository;

    public BanchoContext(
            StatsRepository statsRepository,
            UserRepository userRepository
    ) {
        this.statsRepository = statsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StatsRepository getStatsRepository() {
        return this.statsRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return this.userRepository;
    }

}
