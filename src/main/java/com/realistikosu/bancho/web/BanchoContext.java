package com.realistikosu.bancho.web;

import com.realistikosu.bancho.sessions.Session;
import com.realistikosu.context.Context;
import com.realistikosu.resources.stats.StatsRepository;
import com.realistikosu.resources.users.UserRepository;

public class BanchoContext extends Context {
    private final StatsRepository statsRepository;
    private final UserRepository userRepository;
    private final Session session; // TODO: Change to Bancho Session

    public BanchoContext(
            StatsRepository statsRepository,
            UserRepository userRepository,
            Session session
    ) {
        this.statsRepository = statsRepository;
        this.userRepository = userRepository;
        this.session = session;
    }

    @Override
    public StatsRepository getStatsRepository() {
        return this.statsRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public Session getSession() {
        return this.session;
    }

}
