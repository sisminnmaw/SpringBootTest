package smm.topScoreRestful.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import smm.topScoreRestful.entity.PlayerRecord;
import smm.topScoreRestful.logic.playerHistoryResponse;
import smm.topScoreRestful.service.PlayerRecordRepository;
import smm.topScoreRestful.util.commonUtility;

@ExtendWith(MockitoExtension.class)
class PlayerRecordControllerTest {

	@InjectMocks
	PlayerRecordController prController;

	@Mock
	PlayerRecordRepository prRepository;

	//Test data
	Optional<PlayerRecord> playerRecordOptional;
	
	PlayerRecord playerRecord;
	
	PlayerRecord playerRecord2;
	
	PlayerRecord playerRecord3;
	
	PlayerRecord playerRecord4;
	
	List<PlayerRecord> recordList;
	
	Page<PlayerRecord> recordPage;
	
	Integer userId = 999;

	@BeforeEach
	void setUp() throws Exception {
		playerRecord = new PlayerRecord(999, "edo", 10, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-01"));
		playerRecord2 = new PlayerRecord(888, "bob", 20, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-02"));
		playerRecord3 = new PlayerRecord(777, "john", 30, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-03"));
		playerRecord4 = new PlayerRecord(666, "edo", 80, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-04"));
		playerRecordOptional = Optional.of(new PlayerRecord(999, "edo", 10, new Date()));
		recordList = new ArrayList<PlayerRecord>();
		recordList.add(playerRecord);
		recordPage = new PageImpl<>(recordList);
	}

	@Test
	void testGetAllRecords() {
		Pageable paging = PageRequest.of(0, 3);

		Mockito.lenient().when(prRepository.findAll(paging)).thenReturn(recordPage);
		Page<PlayerRecord> resultPage = prController.getAllRecords(0, 3);
		
		assertEquals(1,resultPage.getContent().size());
	}

	@Test
	void testGetRecord() {
		
		Mockito.when(prRepository.findById(userId)).thenReturn(playerRecordOptional);
		
		EntityModel<PlayerRecord> emRecord = prController.getRecord(userId);
		
		assertNotNull(emRecord);
		assertEquals("edo",emRecord.getContent().getPlayer());
	}

	@Test
	void testCreateRecord() throws URISyntaxException {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Mockito.when(prRepository.save(any(PlayerRecord.class))).thenReturn(playerRecord);
				
		ResponseEntity<Object> em = prController.createRecord(playerRecord);
		
		assertEquals(201,em.getStatusCodeValue());
	}

	@Test
	void testDeleteRecord() {
		 
		prRepository.save(playerRecord);
		prController.deleteRecord(userId);
		Mockito.verify(prRepository).deleteById(userId);
	}

	@Test
	void testGethistory() {
		//recordList already have 1 record
		//999, "edo", 10, 2020-12-01

		//add one more record for test
		//999, "edo", 10, 2020-12-01
		//666, "edo", 80, 2020-12-04
		recordList.add(playerRecord4);
		
		Mockito.when(prRepository.findByName("edo")).thenReturn(recordList);
		
		playerHistoryResponse phr = prController.gethistory("edo");
		assertEquals(80,phr.getTopScore());
		assertEquals(10,phr.getLowScore());
		assertEquals(45.0,phr.getAvgScore());
		assertEquals(recordList,phr.getRecordList());
	}

	@Test
	void testGetfilter_noParam() throws ParseException {
		
		Pageable paging = PageRequest.of(0, 3);
		Mockito.lenient().when(prRepository.findAll(paging)).thenReturn(recordPage);
		Page<PlayerRecord> resultPage = prController.getfilter(null, null, null, 0, 3);
		
		assertEquals(1,resultPage.getContent().size());
	}

	@Test
	void testGetfilter_dateAfter() throws ParseException {
		
		recordList = new ArrayList<PlayerRecord>();
		recordList.add(playerRecord4);
		recordPage = new PageImpl<>(recordList);
		
		Pageable paging = PageRequest.of(0, 3);
		Date date = commonUtility.stringToDate("2020-12-03 00:00:00");
		Mockito.when(prRepository.filterByDateAfter(date,paging)).thenReturn(recordPage);
		Page<PlayerRecord> resultPage = prController.getfilter(null, "2020-12-03 00:00:00", null, 0, 3);
		
		assertEquals(1,resultPage.getContent().size());
		assertEquals("edo",resultPage.getContent().get(0).getPlayer());
	}

	@Test
	void testGetfilter_dateBefore() throws ParseException {
		
		recordList = new ArrayList<PlayerRecord>();
		recordList.add(playerRecord);
		recordList.add(playerRecord2);
		recordPage = new PageImpl<>(recordList);
		
		Pageable paging = PageRequest.of(0, 3);
		Date date = commonUtility.stringToDate("2020-12-03 00:00:00");
		Mockito.when(prRepository.filterByDateBefore(date,paging)).thenReturn(recordPage);
		Page<PlayerRecord> resultPage = prController.getfilter("2020-12-03 00:00:00", null, null, 0, 3);
		
		assertEquals(2,resultPage.getContent().size());
		assertEquals("edo",resultPage.getContent().get(0).getPlayer());
		assertEquals("bob",resultPage.getContent().get(1).getPlayer());
	}
	

	@Test
	void testGetfilter_players() throws ParseException {
		
		recordList = new ArrayList<PlayerRecord>();
		recordList.add(playerRecord2);
		recordList.add(playerRecord3);
		recordPage = new PageImpl<>(recordList);
		
		String[] players = {"bob", "john"};
		
		Pageable paging = PageRequest.of(0, 3);
		Mockito.when(prRepository.filterByNames(Arrays.asList(players),paging)).thenReturn(recordPage);
		Page<PlayerRecord> resultPage = prController.getfilter(null, null, players, 0, 3);
		
		assertEquals(2,resultPage.getContent().size());
		assertEquals("bob",resultPage.getContent().get(0).getPlayer());
		assertEquals("john",resultPage.getContent().get(1).getPlayer());
	}

}
