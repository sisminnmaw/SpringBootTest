package smm.topScoreRestful.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import smm.topScoreRestful.entity.PlayerRecord;

public interface PlayerRecordRepository extends JpaRepository<PlayerRecord, Integer>{

	//fetch data by player name
	@Query("select pr from PlayerRecord pr where pr.player = ?1")
	List<PlayerRecord> findByName(String palyerName);
	
	//filter by date after 
	@Query("select pr from PlayerRecord pr where pr.time >= ?1")
	Page<PlayerRecord> filterByDateAfter(Date date, Pageable pageable);
	
	//filter by date before 
	@Query("select pr from PlayerRecord pr where pr.time <= ?1")
	Page<PlayerRecord> filterByDateBefore(Date date, Pageable pageable);
	
	//filter by players' names
	@Query( "select pr from PlayerRecord pr where player in :players" )
	Page<PlayerRecord> filterByNames(@Param("players") List<String> playername, Pageable pageable);
	
}
