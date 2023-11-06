package com.ahmed.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.produits.entities.Image;
import com.ahmed.produits.entities.Produit;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
