SELECT starId, name, age, mass, discoverDate, galaxyId
FROM STAR
WHERE (mass >= :mass1 AND mass <= :mass2);