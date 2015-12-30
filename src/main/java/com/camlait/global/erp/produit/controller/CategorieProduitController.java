package com.camlait.global.erp.produit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camlait.global.erp.domain.produit.CategorieProduit;
import com.camlait.global.erp.service.produit.IProduitService;

@RestController
@RequestMapping(value = "/categorie")
public class CategorieProduitController {

	@Autowired
	private IProduitService service;

	@RequestMapping(value = "/add/{categorieParentId}", method = RequestMethod.POST)
	public CategorieProduit ajouterCategorie(@RequestBody CategorieProduit categorie,
			@PathVariable Long categorieParentId) {
		if (categorieParentId != null) {
			categorie.setCategorieParent(service.obtenirCategorieProduit(categorieParentId));
		}
		service.ajouterCategorieProduit(categorie);
		return categorie;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public CategorieProduit ajouterCategorie(@RequestBody CategorieProduit categorie) {
		service.ajouterCategorieProduit(categorie);
		return categorie;
	}

	@RequestMapping(value = "/supprimer/{categorieId}", method = RequestMethod.GET)
	public void supprimerCategorie(@PathVariable Long categorieId) {
		service.supprimerCategorieProduit(categorieId);
	}

	@RequestMapping(value = "/trouver/{categorieId}", method = RequestMethod.GET)
	public CategorieProduit trouverCategorie(@PathVariable Long categorieId) {
		return service.obtenirCategorieProduit(categorieId);
	}

	@RequestMapping(value = "/lister/{page}/{limit}", method = RequestMethod.GET)
	public Collection<CategorieProduit> listerCategorie(@PathVariable int page, @PathVariable int limit) {
		return service.listerCategorieProduit();
	}

	@RequestMapping(value = "/{motCle}", method = RequestMethod.GET)
	public Collection<CategorieProduit> listerCategorie(@PathVariable String motCle, @PathVariable int page,
			@PathVariable int limit) {
		return service.listerCategorieProduit(motCle);
	}
}
