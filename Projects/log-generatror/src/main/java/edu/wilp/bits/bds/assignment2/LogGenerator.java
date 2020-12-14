package edu.wilp.bits.bds.assignment2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGenerator {

	final static int EXAM_DURATION_IN_MINUTES = 180, LOG_INTERVAL_IN_SEC = 10;
	
	enum Event {
		USER_CONNECTED, USER_DISCONNECTED, NORMAL_OPERATION, FACE_NOT_VISIBLE, AUDIO_IN_THE_ROOM, 
		PROCTOR_INITIATED_CHAT, STUDENT_INITIATED_CHAT, IMAGE_UPLOAD_FAILED, IMAGE_NOT_VISIBLE;
	}

	private static Logger logger = LoggerFactory.getLogger(LogGenerator.class);

	final static int STUDENT_COUNT = 6000, SUBJECT_COUNT = 120;
	
	private static Set<String> studentIds = new HashSet<>(STUDENT_COUNT);
	private static Set<String> subjectIds = new HashSet<>(SUBJECT_COUNT);
	
	static {
		for(int i = 0; i < STUDENT_COUNT; i++) {
			studentIds.add(generateStringOfLength(16));
		}
		
		for(int i = 0; i < SUBJECT_COUNT; i++) {
			subjectIds.add(generateStringOfLength(6));
		}
	}
	
	public static void main(String[] args) {
		
		int loopSize = EXAM_DURATION_IN_MINUTES * 60 / LOG_INTERVAL_IN_SEC;
		
		for( int logCount = 0; logCount < loopSize; logCount++  ) {
			for(String studentId : studentIds) {
				Event ev = generateEvent();
				logger.debug( "EVENT_TYPE : " + ev.toString() + ", STUDENT_ID : " + studentId);
			}
		}

		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.error("Error log message");

	}

	private static String generateStringOfLength(int n) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int randomIndex = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(randomIndex));
		}
		return sb.toString();
	}
	
	private static Event generateEvent() {
		Event returnEvent = Event.NORMAL_OPERATION;
		
		Random random = new Random();
		int r = random.nextInt(10000);
		
		if( r < 7600) {
			returnEvent = Event.NORMAL_OPERATION;
		}
		else if( r < 8600){
			returnEvent = Event.FACE_NOT_VISIBLE;
		}
		else if( r < 9400){
			returnEvent = Event.AUDIO_IN_THE_ROOM;
		}
		else if( r < 9500){
			returnEvent = Event.PROCTOR_INITIATED_CHAT;
		}
		else if( r < 9600){
			returnEvent = Event.STUDENT_INITIATED_CHAT;
		}
		else if( r < 9700){
			returnEvent = Event.IMAGE_UPLOAD_FAILED;
		}
		else if( r < 9800){
			returnEvent = Event.IMAGE_NOT_VISIBLE;
		}
		else if( r < 9900){
			returnEvent = Event.USER_CONNECTED;
		}
		else if( r <= 10000){
			returnEvent = Event.USER_DISCONNECTED;
		}
		return returnEvent;
	}
}
