package com.ahmed.produits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmed.produits.dto.ProduitDTO;
import com.ahmed.produits.entities.Categorie;
import com.ahmed.produits.entities.Produit;
import com.ahmed.produits.repos.ImageRepository;
import com.ahmed.produits.repos.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService {
	
    @Autowired
    ProduitRepository produitRepository;
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired 
    ImageRepository imageRepository;
    
    @Override
	public Produit saveProduit(Produit p) {
    	return produitRepository.save(p);
	}
    
	/*@Override
	public Produit updateProduit(Produit p) { 
		return produitRepository.save(p);
		}*/
    
	@Override
	public Produit updateProduit(Produit p) {
		Long oldProdImageId = this.getProduit(p.getIdProduit()).getImage().getIdImage();
		Long newProdImageId = p.getImage().getIdImage();
		Produit prodUpdated = produitRepository.save(p);
		if (oldProdImageId != newProdImageId) // si l'image a été modifiée
			imageRepository.deleteById(oldProdImageId);
		return prodUpdated;
	}
	
	@Override
	public void deleteProduit(Produit p) { 
		produitRepository.delete(p);
	}
	
	@Override
	public void deleteProduitById(Long id) {
	produitRepository.deleteById(id);
	}
	
	@Override
	public Produit getProduit(Long id) { 
		return produitRepository.findById(id).get();
	}
	
	@Override
	public List<Produit> getAllProduits() {
		return produitRepository.findAll();
	}
	
	@Override
	public List<Produit> findByNomProduit(String nom) {
	return produitRepository.findByNomProduit(nom);
	}
	
	@Override public List<Produit> findByNomProduitContains(String nom) {
	return produitRepository.findByNomProduitContains(nom);
	}
	
	@Override
	public List<Produit> findByNomPrix(String nom, Double prix) {
	return produitRepository.findByNomPrix(nom, prix);
	}
	
	@Override
	public List<Produit> findByCategorie(Categorie categorie) {
	return produitRepository.findByCategorie(categorie);
	}
	@Override
	public List<Produit> findByCategorieIdCat(Long id) {
	return produitRepository.findByCategorieIdCat(id);
	}
	
	@Override
	public List<Produit> findByOrderByNomProduitAsc() {
	return produitRepository.findByOrderByNomProduitAsc();
	}
	
	@Override
	public List<Produit> trierProduitsNomsPrix() {
	return produitRepository.trierProduitsNomsPrix();
	}

	@Override
	public ProduitDTO convertEntityToDto(Produit produit) {
		
	/*return ProduitDTO.builder()
		.idProduit(produit.getIdProduit())
		.nomProduit(produit.getNomProduit())
		//.prixProduit(produit.getPrixProduit())
		.dateCreation(produit.getDateCreation())
		//.categorie(produit.getCategorie())
		.nomCat(produit.getCategorie().getNomCat())
		.build();*/
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
		return produitDTO;
		
	}

	
	@Override
	public ProduitDTO getProduitDTO(Long id) {
	return convertEntityToDto(produitRepository.findById(id).get());
	}

	@Override
	public List<ProduitDTO> getAllProduitsDTO() {
		return produitRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
		//OU BIEN
		/*List<Produit> prods = produitRepository.findAll();
		List<ProduitDTO> listprodDto = new ArrayList<>(prods.size());
		for (Produit p : prods)
		listprodDto.add(convertEntityToDto(p));
		return listprodDto;*/
		
		}
	
	@Override public Produit convertDtoToEntity(ProduitDTO produitDto) {
		/*Produit produit = new Produit();
		produit.setIdProduit(produitDto.getIdProduit());
		produit.setNomProduit(produitDto.getNomProduit());
		produit.setPrixProduit(produitDto.getPrixProduit());
		produit.setDateCreation(produitDto.getDateCreation());
		produit.setCategorie(produitDto.getCategorie());
		return produit;*/
		
		Produit produit = new Produit();
		produit = modelMapper.map(produitDto, Produit.class);
		return produit;
		
	}
	
	@Override
	public ProduitDTO saveProduitDTO(ProduitDTO p) {
	   return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
	}
	
	
	@Override
	public ProduitDTO updateProduitDTO(ProduitDTO p) {
	   return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
	}
	
}