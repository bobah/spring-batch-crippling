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


import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import com.griddynamics.spring.batch.football.PlayerDao;
import com.griddynamics.spring.batch.football.Player;

/**
 * @author Lucas Ward
 *
 */
public class JdbcPlayerDao  extends SimpleJdbcDaoSupport implements PlayerDao {

	public static final String INSERT_PLAYER =
			"INSERT into players(player_id, last_name, first_name, pos, year_of_birth, year_drafted)" +
			" values (:id, :lastName, :firstName, :position, :birthYear, :debutYear)";
	
	public void savePlayer(Player player) {
		
		getSimpleJdbcTemplate().update(INSERT_PLAYER,
		new BeanPropertySqlParameterSource(player));
		
	}
}
