/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.giveawaybot.database.columns;

import com.jagrosh.giveawaybot.database.SQLColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

/**
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class InstantColumn extends SQLColumn<Instant> {

    public InstantColumn(String name, boolean nullable, Instant defaultValue) {
        this(name, nullable, defaultValue, false);
    }

    public InstantColumn(String name, boolean nullable, Instant defaultValue, boolean primaryKey) {
        super(name, nullable, defaultValue, primaryKey);
    }

    @Override
    public String getDataDescription() {
        return "BIGINT" + (defaultValue == null ? "" : " DEFAULT " + defaultValue.getEpochSecond()) + (nullable ? "" : " NOT NULL") + (primaryKey ? " PRIMARY KEY" : "");
    }

    @Override
    public Instant getValue(ResultSet results) throws SQLException {
        long val = results.getLong(name);
        return val == 0 ? null : Instant.ofEpochSecond(val);
    }

    @Override
    public void updateValue(ResultSet results, Instant newValue) throws SQLException {
        results.updateLong(name, newValue.getEpochSecond());
    }
}
