package com.SQLsupport;

public class Constants {
    public static final String DB_NAME="mydb";

    public static final String USER_SCHEMA="user";
    public static final String USER_ID="id_user";
    public static final String USER_LOGIN="login";
    public static final String USER_PASSWORD="password";
    public static final String USER_ROLE="role";
    public static final String USER_FIRST_NAME="first_name";
    public static final String USER_LAST_NAME="last_name";
    public static final String USER_PHONE="phone";

    public static final String MARK_SCHEMA ="mark";
    public static final String MARK_ID ="idmark";
    public static final String MARK_MATHS ="maths";
    public static final String MARK_PROGRAMMING ="programming";
    public static final String MARK_UNITY ="unity";
    public static final String MARK_COURSEWORK ="course_work";

    public static final String STUDENT_SCHEMA ="student";
    public static final String STUDENT_ID ="idstudent";
    public static final String STUDENT_FIRSTNAME ="first_name";
    public static final String STUDENT_LASTNAME ="last_name";
    public static final String STUDENT_AVEGRADE ="average_grade";
    public static final String STUDENT_MARK_ID ="idmark";
    public static final String STUDENT_SCHOLARSHIP_ID ="idscholarship";
    public static final String STUDENT_USER_ID ="user_id_user";

    public static final String REVIEW_SCHEMA="review";
    public static final String REVIEW_ID="idreview";
    public static final String REVIEW_USER="id_user";
    public static final String REVIEW_STUDENT ="idstudent";
    public static final String REVIEW_TEXT="review_text";

    public static final String SCHOLARSHIP_SCHEMA ="scholarship";
    public static final String SCHOLARSHIP_ID ="idscholarship";
    public static final String SCHOLARSHIP_SCHOLARSHIP ="scholarship";
    public static final String SCHOLARSHIP_COEFFICIENT ="coefficient";


    public static final String FAQ_SCHEMA="faq";
    public static final String FAQ_QUESTION="question";
    public static final String FAQ_ANSWER="answer";
    public static final String FAQ_USER_ID="user_id_user";

    public static final String COEFFICIENT_SCHEMA ="coefficient";
    public static final String COEFFICIENT_ID ="idcoefficient";
    public static final String COEFFICIENT_USER ="id_user";
    public static final String COEFFICIENT_COEFFICIENT ="coefficient";
    public static final String COEFFICIENT_MARK_ID ="idmark";

}
