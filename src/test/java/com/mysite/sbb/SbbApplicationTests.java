package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testCreateAnswer() {
		Optional<Question> oq = questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q =oq.get();

		Answer a1 = new Answer();
		a1.setContent("네 자동으로 생성됩니다");
		a1.setCreateDate(LocalDateTime.now());
		a1.setQuestion(q);
		answerRepository.save(a1);
	}

	@Test
	void testRemoveQuestino() {
		assertEquals(2,questionRepository.count());
		Question q =questionRepository.findBySubject("sbb가 무엇인가요?");
		questionRepository.delete(q);
		assertEquals(1,questionRepository.count());


		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);
	}


	@Test
	void testModifyQuestinoSubject() {
		Optional<Question> oq = questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		questionRepository.save(q);
	}


	@Test
	void testFindBySubjectLike() {
		List<Question> qList =questionRepository.findBySubjectLike("%sbb%");
		Question q = qList.get(0);
		assertEquals(q.getId(),1);
	}


	@Test
	void testFindBySubjectAndContent() {
		Question q =questionRepository.findBySubjectAndContent("sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
		assertEquals(q.getId(),1);
	}

	@Test
	void testFindBySubject() {
		Question q =questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(q.getId(),1);
	}

	@Test
	void testFindById() {
		Optional<Question> oq = questionRepository.findById(1);


		if(oq.isPresent()){
			Question q= oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}


	@Test
	void testFindAll() {
		List<Question> all = questionRepository.findAll();
		assertEquals(2,all.size());

		Question q= all.get(0);
		assertEquals("sbb가 무엇인가요?",q.getSubject());
	}

	@Test
	void testCreateQuestions() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);
	}

	@Test
	void contextLoads() {
	}

}
