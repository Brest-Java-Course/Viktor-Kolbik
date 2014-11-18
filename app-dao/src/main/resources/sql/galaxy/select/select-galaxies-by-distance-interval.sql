SELECT galaxyId, name, distance, discoverDate, AVG(STAR.mass) as "averageMass", AVG(STAR.age) as "averageAge"
FROM GALAXY LEFT OUTER JOIN STAR
ON GALAXY.galaxyId=STAR.galaxyId
WHERE (distance >= :distance1 AND distance <= :distance2)
GROUP BY GALAXY.galaxyId;