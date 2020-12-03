package smm.topScoreRestful.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PlayerRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min=1)
	private String player;

	@NotNull
	private Integer score;

	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	protected PlayerRecord() {
		
	}

	public PlayerRecord(Integer id, String player, Integer score, Date time) {
		super();
		this.id = id;
		this.player = player;
		this.score = score;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "UserRecord [id=" + id + ", player=" + player + ", score=" + score + ", time=" + time + "]";
	}

}
