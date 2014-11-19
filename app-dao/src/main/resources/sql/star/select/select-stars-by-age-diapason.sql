SELECT starId, name, age, mass, discoverDate, galaxyId
FROM STAR
WHERE (age >= :age1 AND age <= :age2);