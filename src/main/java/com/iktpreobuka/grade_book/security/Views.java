package com.iktpreobuka.grade_book.security;


public class Views {
	public static class Public{}
	
	public static class StudentView extends Public{}
	
	public static class ParentView extends StudentView{}
	
	public static class TeacherView extends ParentView{}

	public static class AdminView extends TeacherView{}
}
