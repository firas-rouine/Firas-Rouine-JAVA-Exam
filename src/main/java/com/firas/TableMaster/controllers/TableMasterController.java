package com.firas.TableMaster.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firas.TableMaster.models.TableMaster;
import com.firas.TableMaster.models.User;
import com.firas.TableMaster.services.TableMasterService;
import com.firas.TableMaster.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TableMasterController {
	// Add once service is implemented:
	@Autowired
	private UserService userServ;
	@Autowired
	private TableMasterService tableMasterSer;

	@RequestMapping("/tables")
	public String Tables(Model model, HttpSession session) {
		// grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// ROUTE GUARD
		if (userId == null) {
			return "redirect:/";
		} else {

//			List<TableMaster> allTheTableMaster = tableMasterSer.allTableMaster();
//			model.addAttribute("TableMasterList", allTheTableMaster);
			// 2. fetch the user from the DB
			User currentUser = userServ.findOne(userId);
			// pass the currentUser the jsp page
			model.addAttribute("user", currentUser);
			//1. grab the current user's ID from Session
			Long userID = (Long) session.getAttribute("user_id");
			//2. fetch the user from the DB
			List<TableMaster>giveUpTable=currentUser.getTableGiveUp();
			model.addAttribute("giveUpTable",giveUpTable);

			return "table.jsp";
		}
	}

	@RequestMapping("/home")
	public String Home(Model model, HttpSession session) {
		// grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");
		// ROUTE GUARD
		if (userId == null) {
			return "redirect:/";
		} else {

			List<TableMaster> allTheTableMasterByUserId = tableMasterSer.allTableMasterByUserId(userId);
			model.addAttribute("TableMasterListByUserId", allTheTableMasterByUserId);
			// 2. fetch the user from the DB
			User currentUser = userServ.findOne(userId);
			// pass the currentUser the jsp page
			model.addAttribute("user", currentUser);
			return "home.jsp";
		}
	}

	// DISPLAY ROUTE - FORM
	@GetMapping("/tables/new")
	public String create(@ModelAttribute("table") TableMaster table, Model m, HttpSession session) {
		// grab the user id from session
		Long userId = (Long) session.getAttribute("user_id");

		// ROUTE GUARD
		if (userId == null) {
			return "redirect:/";
		} else {

			return "create.jsp";
		}
	}

	// ACTION ROUTE - Process Form
	@PostMapping("/tables")
	public String createBook(@Valid @ModelAttribute("table") TableMaster table, BindingResult result, Model m,
			HttpSession session) {

		if (result.hasErrors()) {
			return "create.jsp";
		} else {
			// save the table
			// 1. grab the current user's ID from Session
			Long userID = (Long) session.getAttribute("user_id");
			// 2. fetch the user from the DB
			User currentUser = userServ.findOne(userID);
//			System.out.println(currentUser);
			// 3. setAuthor to be the the current user
			table.setPoster(currentUser);
			TableMaster newTable = tableMasterSer.create(table);

			return "redirect:/home";
		}

	}

	// Display Route - Edit form
	@GetMapping("tables/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model, HttpSession session) {

		// Grab current user's id and compare it to the book's author id
		Long userID = (Long) session.getAttribute("user_id");
		// find that book with provided id
		TableMaster thisTable = tableMasterSer.findTableMaster(id);
		// grab the the author's id from thisBook
		Long posterId = thisTable.getPoster().getId();

		if (userID.equals(posterId)) {
			model.addAttribute("table", thisTable);
			return "edit.jsp";
		} else {
			// pass thisBook to the jsp page
			return "redirect:/home";

		}
	}

	// ACTION ROUTE - EDIT
	@PutMapping("/tables/{id}/edit")
	public String updateBook(@Valid @ModelAttribute("table") TableMaster table, BindingResult result,
			HttpSession session, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {

			// ======== Fetch the old poster =========
			// 1. grab the current user's ID from Session
			Long userID = (Long) session.getAttribute("user_id");
			// 2. fetch the user from the DB
			User currentUser = userServ.findOne(userID);
			table.setPoster(currentUser);
			TableMaster thisTable = tableMasterSer.findTableMaster(id);

			tableMasterSer.update(table);
			return "redirect:/home";
		}
	}

	// DELETE

	@DeleteMapping("/tables/{id}")
	public String delete(@PathVariable("id") Long id,HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
		tableMasterSer.delete(id);
		return "redirect:/home";
		}
	}

	@GetMapping("/giveup/{tableId}")
	public String giveup(@PathVariable("tableId") Long tableId, HttpSession session) {
		// 1. grab the current user's ID from Session
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
		
		
		// 2. fetch the user from the DB
		User currentUser = userServ.findOne(userId);
		// grab the book from DB
		TableMaster thisTable = tableMasterSer.findTableMaster(tableId);
		thisTable.getUserGiveUp().add(currentUser);
		// save
		tableMasterSer.update(thisTable);

		return "redirect:/home";
		}

	}

	@GetMapping("/pickup/{tableId}")
	public String pickup(@PathVariable("tableId") Long tableId, HttpSession session) {
		// 1. grab the current user's ID from Session
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
		
		// 2. fetch the user from the DB
		User currentUser = userServ.findOne(userId);
		// grab the book from DB
		TableMaster thisTable = tableMasterSer.findTableMaster(tableId);
		thisTable.getUserGiveUp().remove(currentUser);
		// save
		tableMasterSer.update(thisTable);

		return "redirect:/home";
		}

	}
	
	

}
