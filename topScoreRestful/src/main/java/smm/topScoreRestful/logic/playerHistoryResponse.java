package smm.topScoreRestful.logic;

import java.util.List;
import smm.topScoreRestful.entity.PlayerRecord;

public class playerHistoryResponse {

	private Integer topScore;

	private Integer lowScore;

	private Double avgScore;

	List<PlayerRecord> recordList;

	protected playerHistoryResponse() {

	}

	public playerHistoryResponse(Integer topScore, Integer lowScore, Double avgScore, List<PlayerRecord> recordList) {
		super();
		this.topScore = topScore;
		this.lowScore = lowScore;
		this.avgScore = avgScore;
		this.recordList = recordList;
	}

	public Integer getTopScore() {
		return topScore;
	}

	public void setTopScore(Integer topScore) {
		this.topScore = topScore;
	}

	public Integer getLowScore() {
		return lowScore;
	}

	public void setLowScore(Integer lowScore) {
		this.lowScore = lowScore;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public List<PlayerRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<PlayerRecord> recordList) {
		this.recordList = recordList;
	}

	@Override
	public String toString() {
		return "playerHistoryResponse [topScore=" + topScore + ", lowScore=" + lowScore + ", avgScore=" + avgScore
				+ ", recordList=" + recordList + "]";
	}
}
