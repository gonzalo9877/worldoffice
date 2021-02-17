package com.worldoffice.productos.config;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.ItemProcessor;

import com.worldoffice.productos.dominio.Producto;

public class ProductItemProcessor implements ItemProcessor<Producto, Producto>{

	
	@Override
	public Producto process(Producto item) throws Exception {
		ValidatorFactory validationfactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validationfactory.getValidator();
		Set<ConstraintViolation<@Valid Producto>> validation = validator.validate(item);
		if(!validation.isEmpty())throw new Exception();
		return item;
	}

}
