/*
 * Copyright 2006-2007 the original author or authors.
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

package com.griddynamics.spring.batch.football.internal;

import java.util.List;

import org.springframework.batch.item.ClearFailedException;
import org.springframework.batch.item.FlushFailedException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import com.griddynamics.spring.batch.football.PlayerSummary;

public class JdbcPlayerSummaryDao extends SimpleJdbcDaoSupport implements ItemWriter<PlayerSummary> {

	private static final String INSERT_SUMMARY = "INSERT into player_summary (ID, YEAR_NO, COMPLETES, ATTEMPTS, PASSING_YARDS, PASSING_TD, "
			+ "INTERCEPTIONS, RUSHES, RUSH_YARDS, RECEPTIONS, RECEPTIONS_YARDS, TOTAL_TD) "
			+ "values(:id, :year, :completes, :attempts, :passingYards, :passingTd, "
			+ ":interceptions, :rushes, :rushYards, :receptions, :receptionYards, :totalTd)";

	public void write(List<? extends PlayerSummary> summaries) {

		for (PlayerSummary summary : summaries) {

			MapSqlParameterSource args = new MapSqlParameterSource().addValue("id", summary.getId()).addValue("year",
					summary.getYear()).addValue("completes", summary.getCompletes()).addValue("attempts",
					summary.getAttempts()).addValue("passingYards", summary.getPassingYards()).addValue("passingTd",
					summary.getPassingTd()).addValue("interceptions", summary.getInterceptions()).addValue("rushes",
					summary.getRushes()).addValue("rushYards", summary.getRushYards()).addValue("receptions",
					summary.getReceptions()).addValue("receptionYards", summary.getReceptionYards()).addValue(
					"totalTd", summary.getTotalTd());

			getSimpleJdbcTemplate().update(INSERT_SUMMARY, args);

		}

	}

	public void close() throws Exception {
	}

	public void clear() throws ClearFailedException {
	}

	public void flush() throws FlushFailedException {
	}

}
