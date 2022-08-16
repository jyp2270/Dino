package com.jyp.yoon.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jyp.yoon.domain.dto.products.CartInsertDto;
import com.jyp.yoon.domain.dto.products.CategoryDTO;
import com.jyp.yoon.domain.dto.products.ProdInsertDTO;
import com.jyp.yoon.domain.dto.products.ProdUpdateDto;

public interface ProdService {

	String tempFileupload(MultipartFile file);

	String save(ProdInsertDTO dto);

	String list(Model model);

	String indexList(Model model);

	String detail(long prodNo, Model model);

	List<CategoryDTO> categoryList(long caNo);

	void prodListByCategory(long caNo, Model model);

	String allList(Model model);

	boolean updateData(long prodNo, ProdUpdateDto dto);

	boolean updateIsShow(long prodNo, boolean isShow);


	boolean updateStock(long prodNo, int stock);

	boolean updateSale(long prodNo, int sale);

	boolean updatePrice(long prodNo, int price);

	boolean delete(long prodNo);

	String indexNewList(Model model);

	void getCarts(String name, Model model);

	boolean saveCart(CartInsertDto dto);

	void updateCartEa(long prodNo, int ea);

	void getOrderItem(long[] prodNo, int[] ea, Model model);


}
