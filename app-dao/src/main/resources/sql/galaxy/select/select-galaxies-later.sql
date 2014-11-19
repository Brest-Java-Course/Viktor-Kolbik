SELECT galaxyId, name, distance, discoverDate,
AVG(STAR.mass) AS "averageMass", AVG(STAR.age) AS "averageAge"
FROM GALAXY LEFT OUTER JOIN STAR
ON GALAXY.galaxyId=STAR.galaxyId
WHERE discoverDate >= :discoverDate
GROUP BY GALAXY.galaxyId;