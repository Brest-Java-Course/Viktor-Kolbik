SELECT galaxyId, name, distance, discoverDate, AVG(STAR.mass) as "averageMass", AVG(STAR.age) as "averageAge"
FROM GALAXY LEFT OUTER JOIN STAR
ON GALAXY.galaxyId=STAR.galaxyId
WHERE (discoverDate >= :discoverDate1 AND discoverDate <= :discoverDate2)
GROUP BY GALAXY.galaxyId;