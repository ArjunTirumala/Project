package com.hire10x.team.utils;

import com.hire10x.team.Entity.Team;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil {

    private static final Logger logger = Logger.getLogger(CommonUtil.class.getName());

    public static void validateNotNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    public static void validateEmptyObject(Optional<Team> object, String message) {
        if (object.isEmpty()) {
            logger.log(Level.SEVERE, message);
            throw new EntityNotFoundException(message);
        }
    }

    public static void logInfo(Level level, String message) {
        logger.log(level, message);
    }

}
