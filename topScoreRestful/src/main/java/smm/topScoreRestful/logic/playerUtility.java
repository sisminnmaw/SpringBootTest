package smm.topScoreRestful.logic;

import java.util.ArrayList;
import java.util.List;

import smm.topScoreRestful.entity.PlayerRecord;

public class playerUtility {

	public static playerHistoryResponse playerHistory(List<PlayerRecord> records){
		int topScore = records.get(0).getScore();
		int lowScore = records.get(0).getScore();
		double avgScore = 0.0;
		List<PlayerRecord> recordList = new ArrayList<PlayerRecord>();
		for(PlayerRecord pr: records) {
			if(pr.getScore() > topScore) topScore = pr.getScore();
			if(pr.getScore() < lowScore) lowScore = pr.getScore();
			avgScore += pr.getScore();
			recordList.add(pr);
		}
		avgScore = avgScore/records.size();
		playerHistoryResponse phr = new playerHistoryResponse();
		phr.setTopScore(topScore);
		phr.setLowScore(lowScore);
		phr.setAvgScore(avgScore);
		phr.setRecordList(recordList);
		return phr;
	}
	
}
