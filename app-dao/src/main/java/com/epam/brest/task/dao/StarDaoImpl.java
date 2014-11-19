package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Star;
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

public class StarDaoImpl implements StarDao {
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${insert-into-star-path}')).inputStream)}")
    public String addStarSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${update-star-path}')).inputStream)}")
    public String updateStarSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${remove-star-path}')).inputStream)}")
    public String removeStarSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${remove-stars-by-galaxy-id-path}')).inputStream)}")
    public String removeStarsByGalaxyIdSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-star-by-id-path}')).inputStream)}")
    public String selectStarByIdSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-star-by-name-path}')).inputStream)}")
    public String selectStarByNameSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-by-galaxy-id-path}')).inputStream)}")
    public String selectStarsByGalaxyIdSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-all-stars-path}')).inputStream)}")
    public String selectAllStarsSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-younger-than-path}')).inputStream)}")
    public String selectStarsYoungerThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-by-age-diapason-path}')).inputStream)}")
    public String selectStarsByAgeDiapasonSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-older-than-path}')).inputStream)}")
    public String selectStarsOlderThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-heavier-than-path}')).inputStream)}")
    public String selectStarsHeavierThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-lighter-than-path}')).inputStream)}")
    public String selectStarsLighterThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-by-mass-diapason-path}')).inputStream)}")
    public String selectStarsByMassDiapasonSql;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-by-date-path}')).inputStream)}")
    public String selectStarsByDateSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-earlier-than-path}')).inputStream)}")
    public String selectStarsEarlierThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-later-than-path}')).inputStream)}")
    public String selectStarsLaterThanSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-stars-by-date-diapason-path}')).inputStream)}")
    public String selectStarsByDateDiapasonSql;

    private static final Logger LOGGER = LogManager.getLogger(GalaxyDao.class);

    private static final String STAR_ID = "starId";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String MASS = "mass";
    private static final String DATE = "discoverDate";
    private static final String GALAXY_ID = "galaxyId";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setDataSource(final DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addStar(final Star star) {
        LOGGER.debug("starts with " + star);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(STAR_ID, star.getStarId())
                .addValue(NAME, star.getName())
                .addValue(AGE, star.getAge())
                .addValue(MASS, star.getMass())
                .addValue(DATE, star.getDate())
                .addValue(GALAXY_ID, star.getGalaxyId());

        namedParameterJdbcTemplate.update(addStarSql, parameters, keyHolder);

        Long id = (Long) keyHolder.getKey();
        LOGGER.debug("ends with id = " + id);

        return id;
    }

    @Override
    public void updateStar(final Star star) {
        LOGGER.debug("starts with " + star);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(STAR_ID, star.getStarId())
                .addValue(NAME, star.getName())
                .addValue(AGE, star.getAge())
                .addValue(MASS, star.getMass())
                .addValue(DATE, star.getDate())
                .addValue(GALAXY_ID, star.getGalaxyId());

        int i = namedParameterJdbcTemplate.update(updateStarSql, parameters);

        LOGGER.debug("ends with " + i + " updated row(s)");
    }

    @Override
    public void removeStar(final Long id) {
        LOGGER.debug("starts with id = " + id);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(STAR_ID, id);

        int i = namedParameterJdbcTemplate.update(removeStarSql, parameters);

        LOGGER.debug("ends with " + i + " affected row(s) in table 'GALAXY' ");

    }

    @Override
    public void removeStarsByGalaxyId(final Long id) {
        LOGGER.debug("starts with id = " + id);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(GALAXY_ID, id);

        int i = namedParameterJdbcTemplate.update(removeStarsByGalaxyIdSql, parameters);

        LOGGER.debug("ends with " + i + " affected row(s) in table 'GALAXY' ");

    }

    @Override
    public Star getStarById(final Long id) {
        LOGGER.debug("starts for id = " + id);

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(STAR_ID, id);

        Star star = namedParameterJdbcTemplate.queryForObject(selectStarByIdSql, parameters, new StarMapper());

        LOGGER.debug("ends with " + star);

        return star;
    }

    @Override
    public Star getStarByName(final String name) {
        LOGGER.debug("starts for id = " + name);

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(NAME, name);

        Star star = namedParameterJdbcTemplate.queryForObject(selectStarByNameSql, parameters, new StarMapper());

        LOGGER.debug("ends with " + star);

        return star;
    }

    @Override
    public Set<Star> getAllStars() {
        LOGGER.debug("starts");
        Set<Star> set = new HashSet<Star>();

        set.addAll(namedParameterJdbcTemplate.query(selectAllStarsSql, new StarMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByGalaxyId(final Long id) {
        LOGGER.debug("starts for id = " + id);

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(GALAXY_ID, id);

        Set<Star> set = new HashSet<Star>();

        set.addAll(namedParameterJdbcTemplate.query(selectStarsByGalaxyIdSql, parameters, new StarMapper()));

        LOGGER.debug("ends with " + set);

        return set;
    }

    @Override
    public Set<Star> getStarsByAge(final Long age, final Boolean flag) {
        LOGGER.debug("starts with " + age + " and flag = " + flag);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(AGE, age);
        Set<Star> set = new HashSet<Star>();

        if (flag) {
            set.addAll(namedParameterJdbcTemplate.query(selectStarsYoungerThanSql, parameters, new StarMapper()));
        } else {
            set.addAll(namedParameterJdbcTemplate.query(selectStarsOlderThanSql, parameters, new StarMapper()));
        }

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByAge(final Long lowBorder, final Long topBorder) {
        LOGGER.debug("starts with lowBorder = " + lowBorder + ", topBorder = " + topBorder);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(AGE + 1, lowBorder)
                .addValue(AGE + 2, topBorder);
        Set<Star> set = new HashSet<Star>();

        set.addAll(namedParameterJdbcTemplate.query(selectStarsByAgeDiapasonSql, parameters, new StarMapper()));


        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByMass(final Double mass, final Boolean flag) {
        LOGGER.debug("starts with " + mass + " and flag = " + flag);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(MASS, mass);
        Set<Star> set = new HashSet<Star>();

        if (flag) {
            set.addAll(namedParameterJdbcTemplate.query(selectStarsLighterThanSql, parameters, new StarMapper()));
        } else {
            set.addAll(namedParameterJdbcTemplate.query(selectStarsHeavierThanSql, parameters, new StarMapper()));
        }

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByMass(final Double lowBorder, final Double topBorder) {
        LOGGER.debug("starts with lowBorder = " + lowBorder + ", topBorder = " + topBorder);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(MASS + 1, lowBorder)
                .addValue(MASS + 2, topBorder);
        Set<Star> set = new HashSet<Star>();

        set.addAll(namedParameterJdbcTemplate.query(selectStarsByMassDiapasonSql, parameters, new StarMapper()));


        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByDate(final Date date) {
        LOGGER.debug("starts with " + date);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DATE, date);
        Set<Star> set = new HashSet<Star>();

        set.addAll(namedParameterJdbcTemplate.query(selectStarsByDateSql, parameters, new StarMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByDate(final Date date, final Boolean flag) {
        LOGGER.debug("starts with " + date + " and flag = " + flag);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DATE, date);
        Set<Star> set = new HashSet<Star>();

        if (flag) {
            set.addAll(namedParameterJdbcTemplate.query(selectStarsEarlierThanSql, parameters, new StarMapper()));
        } else {
            set.addAll(namedParameterJdbcTemplate.query(selectStarsLaterThanSql, parameters, new StarMapper()));
        }

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    @Override
    public Set<Star> getStarsByDate(final Date lowBorder, final Date topBorder) {
        LOGGER.debug("starts with lowBorder = " + lowBorder + " and topBorder = " + topBorder);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(DATE + 1, lowBorder)
                .addValue(DATE + 2, topBorder);
        Set<Star> set = new HashSet<Star>();

        set.addAll(namedParameterJdbcTemplate.query(selectStarsByDateDiapasonSql, parameters, new StarMapper()));

        LOGGER.debug("ends with " + set + ". Its size is " + set.size());
        return set;
    }

    public class StarMapper implements RowMapper<Star> {
        @Override
        public Star mapRow(ResultSet rs, int i) throws SQLException {
            Star star = new Star();

            star.setStarId(rs.getLong("starId"));
            star.setName(rs.getString("name"));
            star.setAge(rs.getLong("age"));
            star.setMass(rs.getDouble("mass"));
            star.setDate(rs.getDate("discoverDate"));
            star.setGalaxyId(rs.getLong("galaxyId"));
            return star;
        }
    }
}
