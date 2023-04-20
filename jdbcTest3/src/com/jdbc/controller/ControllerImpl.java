package com.jdbc.controller;

import java.util.List;
import java.util.Map;

import com.jdbc.model.BoardDto;
import com.jdbc.model.Service;
import com.jdbc.view.MainView;

public class ControllerImpl implements Controller{
	
	private Service service= new Service();
	
	public void mainMenu() {
		new MainView().mainMenu();
	}
	
	@Override
	public void selectBoardAll() {
		List<BoardDto> b=service.selectBoardAll();
		new MainView().printBoardAll(b);
	}
	
	@Override
	public void selectBoardComment() {
//		int num= new MainView().selectCountComment();
//		List<BoardDto> board= service.selectBoardComment(num);
//		List<BoardDto> b=service.selectBoardAll();
//		new MainView().printBoardAll(b);
	}
	
	@Override
	public void countComment() {
//		int num= new MainView().selectCountComment();
//		List<BoardDto> board= service.selectBoardComment(num);
//		new MainView().printCountComment(board);
	}
	
	@Override
	public void selectSearchBoard() {
		// 검색할 항목(컬럼명), 검색어를 받아야한다. 
		Map param= new MainView().inputSearch();
		List<BoardDto> board= service.searchBoard(param);
		
		new MainView().printBoardAll(board);
	}
	
}	
