package edu.wilp.bits.bds.assignment2;

import java.util.Random;

import edu.wilp.bits.bds.assignment2.LogGenerator.ExamEvent;

public class ExamUtil {
	
	public static ExamEvent generateEvent() {
		ExamEvent returnEvent = ExamEvent.NORMAL_OPERATION;
		
		Random random = new Random();
		int r = random.nextInt(10000);
		
		if( r < 7600) {
			returnEvent = ExamEvent.NORMAL_OPERATION;
		}
		else if( r < 8600){
			returnEvent = ExamEvent.FACE_NOT_VISIBLE;
		}
		else if( r < 9400){
			returnEvent = ExamEvent.AUDIO_IN_THE_ROOM;
		}
		else if( r < 9500){
			returnEvent = ExamEvent.PROCTOR_INITIATED_CHAT;
		}
		else if( r < 9600){
			returnEvent = ExamEvent.STUDENT_INITIATED_CHAT;
		}
		else if( r < 9700){
			returnEvent = ExamEvent.IMAGE_UPLOAD_FAILED;
		}
		else if( r < 9800){
			returnEvent = ExamEvent.IMAGE_NOT_VISIBLE;
		}
		else if( r < 9900){
			returnEvent = ExamEvent.USER_CONNECTED;
		}
		else if( r <= 10000){
			returnEvent = ExamEvent.USER_DISCONNECTED;
		}
		return returnEvent;
	}
	
	public static String generateStringOfLength(int n) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int randomIndex = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(randomIndex));
		}
		return sb.toString();
	}
}
