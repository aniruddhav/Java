package edu.wilp.bits.bds.assignment2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGenerator {

	final static int CONCURRENT_EXAMS_COUNT = 10;
	final static int EXAM_DURATION_IN_MINUTES = 180, LOG_INTERVAL_IN_SEC = 10;
	final static int STUDENT_COUNT = 6000, SUBJECT_COUNT = 100, PROCTOR_COUNT = 10, COURSE_COUNT = 5;
	
	private static Set<String> studentIds = new HashSet<>(STUDENT_COUNT);
	private static List<String> subjectIds = new ArrayList<>(SUBJECT_COUNT);
	private static Set<String> proctorIds = new HashSet<>(PROCTOR_COUNT);
	private static List<String> courseIds = new ArrayList<>(COURSE_COUNT);
	
	// exam : client - BITS-WILP, course - one of the set, subject - one of the set, examType : one-of-four, 
	static {
		for(int i = 0; i < STUDENT_COUNT; i++) {
			studentIds.add(ExamUtil.generateStringOfLength(16));
		}
		
		for(int i = 0; i < SUBJECT_COUNT; i++) {
			subjectIds.add(ExamUtil.generateStringOfLength(6));
		}
		
		for(int i = 0; i < PROCTOR_COUNT; i++) {
			proctorIds.add(ExamUtil.generateStringOfLength(3));
		}
		
		for(int i = 0; i < COURSE_COUNT; i++) {
			courseIds.add(ExamUtil.generateStringOfLength(3));
		}
	}
	
	final static String WILP_BITS = "WILP-BITS";
	
	enum ExamType {
		REGULAR_MIDSEM, MAKEUP_MIDSEM, REGULAR_COMPRE, MAKEUP_COMPRE;
	}
	
	enum ExamEvent {
		USER_CONNECTED, USER_DISCONNECTED, NORMAL_OPERATION, FACE_NOT_VISIBLE, AUDIO_IN_THE_ROOM, 
		PROCTOR_INITIATED_CHAT, STUDENT_INITIATED_CHAT, IMAGE_UPLOAD_FAILED, IMAGE_NOT_VISIBLE;
	}

	private static Logger logger = LoggerFactory.getLogger(LogGenerator.class);

	public static void main(String[] args) {
		
		//conductSimultaneousExams();
		
		String course = courseIds.get(new Random().nextInt(COURSE_COUNT));
		String subject = subjectIds.get(new Random().nextInt(SUBJECT_COUNT));
		ExamType examType = ExamType.values()[ new Random().nextInt(ExamType.values().length) ];
		conductExam(WILP_BITS, course, subject, examType, 180);
	}

	private static void conductSimultaneousExams() {
		List<ExamThread> concurrentExams = new ArrayList<>(CONCURRENT_EXAMS_COUNT);
		for(int i = 0; i < CONCURRENT_EXAMS_COUNT; i++) {
			String course = courseIds.get(new Random().nextInt(COURSE_COUNT));
			String subject = subjectIds.get(new Random().nextInt(SUBJECT_COUNT));
			ExamType examType = ExamType.values()[ new Random().nextInt(ExamType.values().length) ];
			
			ExamThread examThread = new ExamThread(WILP_BITS, course, subject, examType, 180);
			concurrentExams.add(examThread);
		}
		
		// TODO: Create an executorservice and execute threads
	}
	
	private static void conductExam(String institute, String course, String subject, ExamType examType, int examDurationInMinutes ) {
		int eventsToCapture = examDurationInMinutes * 60 / LOG_INTERVAL_IN_SEC;
		
		for( int eventCount = 0; eventCount < eventsToCapture; eventCount++  ) {
			for(String studentId : studentIds) {
				ExamEvent ev = ExamUtil.generateEvent();
				String logMessage = String.format("EventType : %s, StudentId : %s, InstituteName : %s, Course : %s, Subject : %s, ExamType : %s", 
						ev.toString(), studentId, WILP_BITS, course, subject, examType.toString() );
				logger.info( logMessage );
			}
		}
	}
	
}
