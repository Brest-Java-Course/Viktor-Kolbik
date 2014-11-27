package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Galaxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class GalaxyDaoImpl implements GalaxyDao {

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${insert-into-galaxy-path}')).inputStream)}")
    public String addGalaxySql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${remove-galaxy-path}')).inputStream)}")
    public String removeGalaxySql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${update-galaxy-path}')).inputStream)}")
    public String updateGalaxySql;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxy-by-id-path}')).inputStream)}")
    public String selectGalaxyByIdSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxy-by-name-path}')).inputStream)}")
    public String selectGalaxyByNameSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-all-galaxies-path}')).inputStream)}")
    public String selectAllGalaxiesSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-by-date-path}')).inputStream)}")
    public String selectGalaxiesByDateSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-earlier-path}')).inputStream)}")
    public String selectGalaxiesEarlierThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-later-path}')).inputStream)}")
    public String selectGalaxiesLaterThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-by-time-period-path}')).inputStream)}")
    public String selectGalaxiesByPeriodOfTimeSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-closer-path}')).inputStream)}")
    public String selectGalaxiesCloserThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-farther-path}')).inputStream)}")
    public String selectGalaxiesFartherThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-galaxies-by-distance-interval-path}')).inputStream)}")
    public String selectGalaxiesByDistanceIntervalSql;

    private static final Logger LOGGER = LogManager.getLogger(GalaxyDao.class);

    private static final String GALAXY_ID = "galaxyId";
    private static final String NAME = "name";
    private static final String DISTANCE = "distance";
    private static final String DATE = "discoverDate";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setDataSource(final DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addGalaxy(final Galaxy galaxy) {
        LOGGER.debug("starts with " + galaxy);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(GALAXY_ID, galaxy.getGalaxyId())
                .addValue(NAME, galaxy.getName())
                .addValue(DISTANCE, galaxy.getDistance())
                .addValue(DATE, galaxy.getDate());

        namedParameterJdbcTemplate.update(addGalaxySql, parameters, keyHolder);

        Long id = (Long) keyHolder.getKey();
        LOGGER.debug("ends with id = " + id);

        return id;
    }

    @Override
    public void updateGalaxy(final Galaxy galaxy) {
        LOGGER.debug("starts with " + galaxy);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(GALAXY_ID, galaxy.getGalaxyId())
                .addValue(NAME, galaxy.getName())
                .addValue(DISTANCE, galaxy.getDistance())
                .addValue(DATE, galaxy.getDate());

        int i = namedParameterJdbcTemplate.update(updateGalaxySql, parameters);

        LOGGER.debug("ends with " + i + " updated row(s)");
    }

    @Override
    public void removeGalaxy(final Long id) {
        LOGGER.debug("starts with id = " + id);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(GALAXY_ID, id);

        int i = namedParameterJdbcTemplate.update(removeGalaxySql, parameters);

        LOGGER.debug("ends with " + i + " affected row(s) in table 'GALAXY' ");
    }

    @Override
    public Galaxy getGalaxyById(final Long id) {
        LOGGER.debug("starts for id = " + id);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(GALAXY_ID, id);

        Galaxy galaxy = namedParameterJdbcTemplate.queryForObject(selectGalaxyByIdSql, parameters, new GalaxyMapper());
        LOGGER.debug("ends with " + galaxy);
        return galaxy;
    }

    @Override
    public Galaxy getGalaxyByName(final String name) {
        LOGGER.debug("starts for name = " + name);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(NAME, name);

        Galaxy galaxy = namedParameterJdbcTemplate.queryForObject(selectGalaxyByNameSql, parameters, new GalaxyMapper());

        LOGGER.debug("ends with " + galaxy);
        return galaxy;
    }

    @Override
    public Set<Galaxy> getAllGalaxies() {
        LOGGER.debug("starts");
        Set<Galaxy> set = new HashSet<Galaxy>();

        set.addAll(namedParameterJdbcTemplate.query(selectAllGalaxiesSql, new GalaxyMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Galaxy> getGalaxiesByDate(final Date date) {
        LOGGER.debug("starts with " + date);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DATE, date);
        Set<Galaxy> set = new HashSet<Galaxy>();

        set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesByDateSql, parameters,new GalaxyMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Galaxy> getGalaxiesByDate(final Date date, final Boolean flag) {
        LOGGER.debug("starts with " + date + " and flag = " + flag);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DATE, date);
        Set<Galaxy> set = new HashSet<Galaxy>();

        if(flag) {
            set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesEarlierThanSql, parameters, new GalaxyMapper()));
        }else{
            set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesLaterThanSql, parameters, new GalaxyMapper()));
        }

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Galaxy> getGalaxiesByDate(final Date lowBorder, final Date topBorder) {
        LOGGER.debug("starts with " + lowBorder + " and " + topBorder);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DATE + 1, lowBorder)
                .addValue(DATE + 2, topBorder);
        Set<Galaxy> set = new HashSet<Galaxy>();

        set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesByPeriodOfTimeSql, parameters, new GalaxyMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Galaxy> getGalaxiesByDistance(final Long distance, final Boolean flag) {
        LOGGER.debug("starts with distance = " + distance + " and flag = " + flag);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DISTANCE, distance);
        Set<Galaxy> set = new HashSet<Galaxy>();

        if(flag) {
            set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesCloserThanSql, parameters, new GalaxyMapper()));
        }else{
            set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesFartherThanSql, parameters, new GalaxyMapper()));
        }

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Galaxy> getGalaxiesByDistanceInterval(final Long lowBorder, final Long topBorder) {
        LOGGER.debug("starts with lowBorder = " + lowBorder + " and topBorder = " + topBorder);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DISTANCE + 1, lowBorder)
                .addValue(DISTANCE + 2, topBorder);
        Set<Galaxy> set = new HashSet<Galaxy>();

        set.addAll(namedParameterJdbcTemplate.query(selectGalaxiesByDistanceIntervalSql, parameters, new GalaxyMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    public class GalaxyMapper implements RowMapper<Galaxy> {
        @Override
        public Galaxy mapRow(ResultSet rs, int i) throws SQLException {
            Galaxy galaxy = new Galaxy();

            galaxy.setGalaxyId(rs.getLong("galaxyId"));
            galaxy.setName(rs.getString("name"));
            galaxy.setDistance(rs.getLong("distance"));
            galaxy.setDate(rs.getDate("discoverDate"));
            galaxy.setAverageAge(rs.getLong("averageAge"));
            galaxy.setAverageMass(rs.getLong("averageMass"));
            return galaxy;
        }
    }
}
