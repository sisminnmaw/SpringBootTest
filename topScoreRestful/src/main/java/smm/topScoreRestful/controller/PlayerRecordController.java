package smm.topScoreRestful.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import smm.topScoreRestful.entity.PlayerRecord;
import smm.topScoreRestful.logic.playerHistoryResponse;
import smm.topScoreRestful.logic.playerUtility;
import smm.topScoreRestful.service.PlayerRecordNotFoundException;
import smm.topScoreRestful.service.PlayerRecordRepository;
import smm.topScoreRestful.util.commonUtility;

@RestController
public class PlayerRecordController {

	@Autowired
	private PlayerRecordRepository repository;

	/**
	 * retrieve players' records
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/records")
	public Page<PlayerRecord> getAllRecords(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findAll(paging);
	}

	/**
	 * retrieve players' records 
	 * same with getAllRecords()
	 *   extra adding with custom pagination
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/recordsCustomPagin")
	public ResponseEntity<Map<String, Object>> getAllRecordsCustomPagin(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);
		
		List<PlayerRecord> records = new ArrayList<PlayerRecord>();
		Page<PlayerRecord> pageRecords = repository.findAll(paging);
		records = pageRecords.getContent();
		Map<String, Object> response = new HashMap<>();
	      response.put("content", records);
	      response.put("currentPage", pageRecords.getNumber());
	      response.put("totalItems", pageRecords.getTotalElements());
	      response.put("totalPages", pageRecords.getTotalPages());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


	/**
	 * retrieve player's record
	 * @param id
	 * @return
	 */
	@GetMapping("/record/{id}")
	public EntityModel<PlayerRecord> getRecord(@PathVariable int id) {
		Optional<PlayerRecord> record = repository.findById(id);
		if (!record.isPresent())
			throw new PlayerRecordNotFoundException("id-" + id);
		EntityModel<PlayerRecord> resource = EntityModel.of(record.get());
		return resource;
	}

	/**
	 * create record
	 * @param record
	 * @return
	 */
	@PostMapping("/record")
	public ResponseEntity<Object> createRecord(@Valid @RequestBody PlayerRecord record) {
		//"Edo" or "edo" or "EDO" => "edo"
		record.setPlayer(record.getPlayer().toLowerCase());
		
		PlayerRecord savedRecord = repository.save(record);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRecord.getId())
				.toUri();

		// 201 status for created
		return ResponseEntity.created(location).build();
	}

	/**
	 * delete record
	 * @param id
	 */
	@DeleteMapping("/record/{id}")
	public void deleteRecord(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	/**
	 * get player's history and details
	 * @param player
	 * @return
	 */
	@GetMapping("/history")
	public playerHistoryResponse gethistory(@RequestParam String player) {
		
		List<PlayerRecord> records = (List<PlayerRecord>) repository.findByName(player.toLowerCase());
		
		if (records.size() == 0)
			throw new PlayerRecordNotFoundException("Not found player : " + player);
		
		//SQL data to customize data
		playerHistoryResponse phr = playerUtility.playerHistory(records);
		
		// This is not production comment
		// we can also return with using Map<> and ResponseEntity<> like in getAllRecordsCustomPagin()
		// but created playerHistoryResponse class and return
		// want to show different way
		return phr;
	}
	

	/**
	 * player records filter API
	 * @param before
	 * @param after
	 * @param player
	 * @param page
	 * @param size
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/filter")
	public Page<PlayerRecord> getfilter(
			@RequestParam(required = false) String before,
			@RequestParam(required = false) String after,
			@RequestParam(required = false) String[] player,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) throws ParseException {
		
		Pageable paging = PageRequest.of(page, size);
		Page<PlayerRecord> pageRecords = null;
		
		//all filters are null
		if(before == null && after == null && player == null) {
			pageRecords = repository.findAll(paging);
		}
		
		//filter by date before
		if(before != null && after == null && player == null) {
			Date beforeDate = commonUtility.stringToDate(before);
			pageRecords = repository.filterByDateBefore(beforeDate, paging);
		}
		
		//filter by date after
		if(before == null && after != null && player == null) {
			Date afterDate = commonUtility.stringToDate(after);
			pageRecords = repository.filterByDateAfter(afterDate, paging);
		}
		
		//filter by date player names
		if(before == null && after == null && player != null) {
			List<String> playersList  = commonUtility.stringListToLowerCase(Arrays.asList(player));
			pageRecords = repository.filterByNames(playersList,paging);
		}
		
		return pageRecords;
	}
	
	
	
}
