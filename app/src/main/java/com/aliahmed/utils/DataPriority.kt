package com.aliahmed.utils

enum class DataPriority {
    /**
     * Try pulling from remote, if unsuccessful, try local repository.
     */
    REMOTE_FIRST,

    /**
     * Just remote , nothing else.
     */
    REMOTE_ONLY,

    /**
     * Check if exist in local database, if it does not exist, then try remote.
     */
    CHECK_LOCAL_THEN_REMOTE,

    /**
     * Opposiote of Remote only, just check local database
     */
    CHECK_AND_PULL_LOCALLY_ONLY
}
