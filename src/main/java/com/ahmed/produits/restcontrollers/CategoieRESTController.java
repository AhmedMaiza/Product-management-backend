package com.ahmed.produits.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.produits.entities.Categorie;
import com.ahmed.produits.repos.CategorieRepository;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin
public class CategoieRESTController {

	@Autowired
	CategorieRepository categorieRepository;
	@RequestMapping(method=RequestMethod.GET)
	public List<Categorie> getAllCategories()
	{
	return categorieRepository.findAll();
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Categorie getCategorieById(@PathVariable("id") Long id) {
	return categorieRepository.findById(id).get();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Categorie createCatregorie(@RequestBody Categorie categorie) {
		return categorieRepository.save(categorie);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Categorie updateCategorie(@RequestBody Categorie categorie) {
		return categorieRepository.save(categorie);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteProduit(@PathVariable("id") Long id) {
		       categorieRepository.deleteById(id);
	}
	
	

}
