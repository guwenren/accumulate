package com.guwr.accumulate.service.authority.core.shiro;

import com.guwr.accumulate.common.cache.RedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.shiro.filter.SessionDAO
 * Date 2016/9/7
 * Time 13:59
 */
public class SessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(SessionDAO.class);

    @Override
    protected Serializable doCreate(Session session) {
        logger.info("SessionDAO.doCreate");
        logger.info("session = " + session);
        Serializable sessionId = this.generateSessionId(session);
        logger.info("sessionId = " + sessionId);
        assignSessionId(session, sessionId);
        RedisUtils.save(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.info("SessionDAO.doReadSession");
        logger.info("sessionId = " + sessionId);
        Session session = (Session) RedisUtils.get(sessionId);
        RedisUtils.save(sessionId, session);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        logger.info("SessionDAO.update");
        Serializable sessionId = session.getId();
        logger.info("sessionId = " + sessionId);
        RedisUtils.save(sessionId, session);
    }

    @Override
    public void delete(Session session) {
        logger.info("SessionDAO.delete");
        Serializable sessionId = session.getId();
        logger.info("sessionId = " + sessionId);
        RedisUtils.del(sessionId);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.info("SessionDAO.getActiveSessions");
        return null;
    }
}
