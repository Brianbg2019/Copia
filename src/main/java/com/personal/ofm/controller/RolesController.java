package com.personal.ofm.controller;

import java.util.List;

import javax.management.relation.Role;
import javax.management.relation.RoleResult;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personal.ofm.entity.Roles;
import com.personal.ofm.repository.IRoles;

@Controller
@RequestMapping("roles")
public class RolesController {

	@Autowired
	IRoles iroles;
	
	@GetMapping("listar")
	public String listar(Model m) {
		m.addAttribute("items", (List<Roles>) iroles.findAll());
		
		return "Roles/RolesList";
	}
	@GetMapping(value = "save")
	public String guardar() {
		return "Roles/RolesSave";
	}
	
	@PostMapping(value = "save")
	public String nuevo(HttpServletRequest request) {
		Roles roles = new Roles();
		roles.getRol();
		return "redirect:/Roles/RolesSave";
	}
	
	@PostMapping(value = "verificar")
	public String save(@RequestParam(value = "rol", required = true) String rol) {
		Roles saverol = new Roles();
		saverol.setRol(rol);
		iroles.save(saverol);
		return "redirect:/roles/listar";
	}
	
	@GetMapping(value = "modificar/{idRol}")
	public String editar(@PathVariable Integer idRol, Model m) {
		Roles roles = iroles.findById(idRol).get();
		m.addAttribute("items", roles);
		return new String("Roles/RolesEdit");
	}
	@PostMapping(value = "modificar")
	public String modificar(@RequestParam Integer idRol, @RequestParam String rol) {
		Roles roles = new Roles();
		roles.setIdRol(idRol);
		roles.setRol(rol);
		iroles.save(roles);
		return "redirect:/roles/listar";
	}
	
	@GetMapping(value = "eliminar/{idRol}")
	public String borrar(@PathVariable Integer idRol) {
		Roles roles = iroles.findById(idRol).get();
		iroles.delete(roles);
		return "redirect:/roles/listar";
	}
}
