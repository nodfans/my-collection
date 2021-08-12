#================== 递归查询自己ID下面的节点 ==================#
SELECT id
FROM (
         SELECT t1.id,
                IF
                    (find_in_set(p_id, @pids) > 0, @pids := concat( @pids, ',', id ), 0) AS ischild
         FROM (SELECT id, p_id FROM sys_admin t ORDER BY p_id, id) t1,
              (SELECT @pids := 18555972869325) t2
     ) t3
WHERE ischild != 0


# ================== 按天统计 没有补0 ==================#
SELECT dayTb.cday 'date', IFNULL( tNumTb.num, 0 ) 'count'
FROM (
         SELECT @cdate := DATE_ADD( @cdate, INTERVAL + 1 DAY ) cday
         FROM
             ( SELECT @cdate := DATE_ADD( '2021-07-15', INTERVAL - 1 DAY ) FROM CardFace LIMIT 30 ) t0
         WHERE
             date ( @cdate ) <= DATE_ADD( "2021-08-11"
             , INTERVAL - 1 DAY )
     ) dayTb
         LEFT JOIN (
    SELECT
        date ( t.faceTime) cday,
     count(DISTINCT t.faceId) num FROM
		CardFace t
WHERE
    t.faceTime >= '2021-07-15'
GROUP BY
    cday
    ) tNumTb
ON tNumTb.cday = dayTb.cday


# ================== 按周统计 没有补0 ==================#
SELECT DATE_FORMAT(faceTime, '%Y-%u') AS WEEK,
       DATE_FORMAT(date_sub(faceTime, INTERVAL dayofweek( faceTime ) -2  DAY ), '%Y-%m-%d') AS date,
	 count( * ) AS count
FROM
    CardFace
WHERE
    faceTime >= "2021-07-15"
  AND faceTime <= "2021-08-12"
  AND cardId in (250)
GROUP BY
    WEEK;

#================== 按小时统计 没有补0 ==================#
SELECT t1.HOUR hour,
       COUNT(t2.HOUR) count
FROM
    (
    SELECT
    DATE_FORMAT( @cdate := DATE_ADD( @cdate, INTERVAL - 1 HOUR ), '%y-%m-%d %H' ) HOUR
    FROM
    ( SELECT @cdate := DATE_ADD( DATE_FORMAT( NOW( ), '%y-%m-%d %H' ), INTERVAL + 1 HOUR ) FROM CardFace ) t0
    LIMIT 24
    ) t1
    LEFT JOIN ( SELECT DATE_FORMAT( faceTime, '%y-%m-%d %H' ) HOUR FROM CardFace WHERE cardId in (174, 243) and faceTime >= ( NOW( ) - INTERVAL 24 HOUR ) ) t2
ON t1.HOUR = t2.HOUR
GROUP BY
    t1.HOUR
ORDER BY
    t1.HOUR DESC