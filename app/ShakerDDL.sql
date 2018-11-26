
        CREATE TABLE IF NOT EXISTS `Feature`(
            `type`          TEXT, 
            `id`            TEXT        NOT NULL, 
            `mag`           REAL, 
            `place`         TEXT, 
            `time`          INTEGER, 
            `updated`       INTEGER, 
            `url`           TEXT, 
            `felt`          INTEGER, 
            `alert`         TEXT, 
            `status`        TEXT,
            `net`           TEXT, 
            `ids`           TEXT, 
            `nst`           INTEGER, 
            `title`         TEXT, 
            `latitude`      REAL, 
            `longitude`     REAL, 
            `depth`         REAL, 
        );
    
      CREATE TABLE IF NOT EXISTS room_master_table (
            'id'             INTEGER     PRIMARY KEY,
            'identity_hash'   TEXT)
      );