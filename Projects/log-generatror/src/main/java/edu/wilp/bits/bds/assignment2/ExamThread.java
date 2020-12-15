package edu.wilp.bits.bds.assignment2;

import edu.wilp.bits.bds.assignment2.LogGenerator.ExamType;

public class ExamThread implements Runnable {

	private String institute;
	private String course;
	private String subject;
	private ExamType examType;
	private int examDurationInMinutes;
	
	public ExamThread(String institute, String course, 
			String subject, ExamType examType, int examDurationInMinutes){
		
        this.institute = institute;
        this.course = course;
        this.subject = subject;
        this.examType = examType;
        this.examDurationInMinutes = examDurationInMinutes;
    }
	
	@Override
	public void run() {
//		int eventsToCapture = examDurationInMinutes * 60 / LOG_INTERVAL_IN_SEC;
//		
//		for( int eventCount = 0; eventCount < eventsToCapture; eventCount++  ) {
//			for(String studentId : studentIds) {
//				ExamEvent ev = generateEvent();
//				String logMessage = String.format("EventType : %s, StudentId : %s, InstituteName : %s, Course : %s, Subject : %s, ExamType : %s", 
//						ev.toString(), studentId, WILP_BITS, course, subject, examType.toString() );
//				logger.info( logMessage );
//			}
//		}
		
	}

}
