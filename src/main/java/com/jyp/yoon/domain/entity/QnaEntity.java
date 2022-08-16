package com.jyp.yoon.domain.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jyp.yoon.domain.dto.qna.QnaUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity(name="qna")
public class QnaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qnum;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "text not null") 
	private String content;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	private int readCount;
	
	@JoinColumn(name = "mno")
	@ManyToOne 
	private Member member;
	
	@Builder.Default
	@OneToMany(mappedBy = "qnaEntity", cascade = CascadeType.ALL)
	private Collection<ReplyEntity> replies = new Vector<ReplyEntity>();
	
	public QnaEntity addMember(Member member) {
		this.member=member;
		return this;
	}
	public QnaEntity update(QnaUpdateDTO dto) {
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;
	}
	
	//조회수 증가 편의 메서드
	public QnaEntity incrementReadCount() {
		readCount++;
		return this;
	}
}
