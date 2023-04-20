package com.jdbc.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jdbc.controller.Controller;
import com.jdbc.controller.ControllerImpl;
import com.jdbc.model.BoardDto;

public class MainView {
	
	private Controller controller= new ControllerImpl();
	
	public void mainMenu() {
//		BoardController controller= new BoardController(); -> mainMenu안에서만 실행될 거라서 이렇게 선언해도 된다.  
		Scanner sc= new Scanner(System.in);
		while(true) {
			System.out.println("==== BOARD 조회 ====");
			System.out.println("1. 게시글 전체 조회");
			System.out.println("2. 게시글 항목(제목, 내용, 작성자) 조회"); // 다중 검색
			System.out.println("0. 종료");
			System.out.print("메뉴 선택 : ");
			int choice= sc.nextInt();
			switch(choice) {
			case 1 : controller.selectBoardAll(); break;
			case 2 : controller.selectSearchBoard(); break;
			case 0 : System.out.println("프로그램 종료합니다.");return;
			default : System.out.println("없는 메뉴입니다. 다시 선택하세요"); 
			}
		}
		
	}//
	
	public void printBoardAll(List<BoardDto> b) {
		System.out.println("==== 게시글 조회 ====");
		if(b.isEmpty()) {
			System.out.println("작성된 게시글이 없습니다."); // 분기처리
		}else b.forEach((o)->System.out.println(""+o.getComments().size()+o));
	}
	
	public Map inputSearch() {
		Scanner sc= new Scanner(System.in);
		System.out.println("==== 게시글 항목별 검색(번호 입력) ====");
		System.out.println("1. 제목		2. 내용		3. 작성자");
		System.out.print("번호 입력 : ");
		int colCho= sc.nextInt();
		sc.nextLine();
		String col="";
		switch(colCho){
			case 1 : col="board_title"; break;
			case 2 : col="board_content"; break;
			case 3 : col="board_writer"; break;
			default : System.out.println("1~3까지의 숫자를 입력하세요");break;
		}
		System.out.print("검색어 : ");
		String keyword= sc.nextLine();
		
		// col과 keyword를 구분해서 반환하기 위해서 컬럼을 key값으로, keyword를 value값으로 넣어서 map으로 반환한다.
		return Map.of("col",col,"keyword",keyword);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
