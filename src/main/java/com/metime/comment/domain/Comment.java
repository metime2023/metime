package com.metime.comment.domain;

import java.time.LocalDateTime;

import com.metime.alcohol.domain.Alcohol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Alcohol alcohol;

	@Column(nullable = false)
	private String content; // 댓글 내용

	private LocalDateTime createdAt; // 생성일자

	private Comment(Alcohol alcohol, String content) {
		this.alcohol = alcohol;
		this.alcohol.addComment(this);
		this.content = content;
		this.createdAt = LocalDateTime.now();
	}

	public static Comment create(Alcohol alcohol, String contents) {
		return new Comment(alcohol, contents);
	}
}
