package com.realistikosu.resources.stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.realistikosu.osu.CustomModes;
import com.realistikosu.osu.Modes;

public class StatsRepository {
    private final Connection _connection;

    private static final Logger logger = LogManager.getLogger(StatsRepository.class.getName());

    public StatsRepository(Connection connection) {
        _connection = connection;
    }

    public Stats fromUserId(int userId, CustomModes customMode, Modes mode) {
        // Working with the weirdness of the Ripple database....
        String columnSuffix = switch (mode) {
            case STANDARD -> "std";
            case CATCH -> "ctb";
            case MANIA -> "mania";
            case TAIKO -> "taiko";
        };

        String statsTable = switch (customMode) {
            case VANILLA -> "users_stats";
            case RELAX -> "rx_stats";
            case AUTOPILOT -> "ap_stats";
        };

        try {
            PreparedStatement preparedStatement = _connection.prepareStatement(
                    String.format(
                            "SELECT * FROM %s WHERE id = ?",
                            statsTable
                    )
            );

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new Stats(
                    mode,
                    customMode,
                    userId,
                    resultSet.getDouble(
                            String.format("pp_%s", columnSuffix)
                    ),
                    resultSet.getFloat(
                            String.format("accuracy_%s", columnSuffix)
                    ),
                    resultSet.getLong(
                            String.format("total_score_%s", columnSuffix)
                    ),
                    resultSet.getLong(
                            String.format("ranked_score_%s", columnSuffix)
                    ),
                    resultSet.getLong(
                            String.format("playcount_%s", columnSuffix)
                    )
            );
        } catch (SQLException e) {
            logger.fatal("Failed to fetch user from MySQL with exception", e);
            return null;
        }
    }
}
