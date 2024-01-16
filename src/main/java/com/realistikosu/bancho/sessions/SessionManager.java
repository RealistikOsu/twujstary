package com.realistikosu.bancho.sessions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.realistikosu.bancho.sessions.Session;

/**
 * A class managing all active connections to the server.
 */
public class SessionManager {
    private final HashMap<String, Session> _sessions;

    public SessionManager() {
        _sessions = new HashMap<>();
    }

    public void add(Session session) {
        _sessions.put(
                session.getSessionId(),
                session
        );
    }

    public boolean removeById(String sessionId) {
        // TODO: Notify session removal or leave that to the business logic?
        return _sessions.remove(sessionId) != null;
    }

    public Session getById(String sessionId) {
        return _sessions.get(sessionId);
    }

    /**
     * Generates a UUID that is guaranteed to be unique at the time of calling.
     * @return - A UUID that can be assigned to a session.
     */
    public String generateUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (!_sessions.containsKey(uuid));

        return  uuid;
    }

    /**
     * Linear searches over the session map, finding all sessions matching
     * a user ID.
     * @param userId - The user ID for which all sessions will be returned for.
     * @return - A list of all sessions
     */
    public ArrayList<Session> getByUserId(int userId) {
        ArrayList<Session> sessions = new ArrayList<>();

        for (Session s : _sessions.values()) {
            if (s.getUserId() == userId) {
                sessions.add(s);
            }
        }

        return sessions;
    }

    public int removeByUserId(int userId) {
        int result = 0;

        for (Session s : _sessions.values()) {
            if (s.getUserId() == userId) {
                _sessions.remove(s.getSessionId());
                result++;
            }
        }

        return result;
    }
}
