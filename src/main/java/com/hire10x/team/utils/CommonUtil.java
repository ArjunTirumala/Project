package com.hire10x.team.utils;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil {

    private static final Logger logger = Logger.getLogger(CommonUtil.class.getName());

    public static void validateNotNull(String str, String message) {
        if (str == null) {
            throw new NullPointerException(message);
        }
    }

    public static void validateNonEmptyObject(Optional<Team> object, String message) {
        if (object.isPresent()) {
            throw new TeamDuplicateException(message);
        }
    }

    public static void validateEmptyObjects(Optional<Team> object, String message) {
        if (!object.isPresent()) {
            throw new EntityNotFoundException(message);
        }
    }

    public static void validateEmptyList(List<Team> object, String message) {
        if (object.isEmpty()) {
            throw new EntityNotFoundException(message);
        }
    }
    public static void logInfo(Level level, String message) {
        logger.log(level, message);
    }

    public static List<String> checkAndConvertNullToEmptyList(List<String> existingUserIds) {
        if(existingUserIds == null) return new ArrayList<>();
        return existingUserIds;
    }
}
