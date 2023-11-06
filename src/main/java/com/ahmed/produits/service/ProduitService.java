package com.ahmed.produits.service;

import java.util.List;

import com.ahmed.produits.dto.ProduitDTO;
import com.ahmed.produits.entities.Categorie;
import com.ahmed.produits.entities.Produit;

public interface ProduitService {
	
	Produit saveProduit(Produit p);
	Produit updateProduit(Produit p);
	void deleteProduit(Produit p);
	void deleteProduitById(Long id);
	Produit getProduit(Long id);
	List<Produit> getAllProduits();
	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	List<Produit> findByNomPrix (String nom, Double prix);
	List<Produit> findByCategorie (Categorie categorie);
	List<Produit> findByCategorieIdCat(Long id);
	List<Produit> findByOrderByNomProduitAsc();
	List<Produit> trierProduitsNomsPrix();
	ProduitDTO convertEntityToDto(Produit produit);
	ProduitDTO getProduitDTO(Long id);
	List<ProduitDTO> getAllProduitsDTO();
	Produit convertDtoToEntity(ProduitDTO produitDto);
	ProduitDTO saveProduitDTO(ProduitDTO p);
	ProduitDTO updateProduitDTO(ProduitDTO p);
	
}