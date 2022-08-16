package com.jyp.yoon.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jyp.yoon.domain.dto.member.OrderInsertDTO;
import com.jyp.yoon.domain.dto.products.CartInsertDto;
import com.jyp.yoon.domain.dto.products.CategoryDTO;
import com.jyp.yoon.domain.dto.products.ProdInsertDTO;
import com.jyp.yoon.domain.dto.products.ProdUpdateDto;
import com.jyp.yoon.domain.dto.visual.VisualUpdateDto;
import com.jyp.yoon.domain.entity.CategoryA;
import com.jyp.yoon.service.ProdService;

@Controller
public class ProductsController {

	@Autowired
	private ProdService service;

	// 카테고리별 상품리스트
	@GetMapping("/common/categories/{caNo}/products")
	public String prodListByCategory(@PathVariable long caNo, Model model) {
		service.prodListByCategory(caNo, model);
		return "products/list";
	}

	// ajax로 요청
	@GetMapping("/admin/category/{caNo}")
	public String category(@PathVariable long caNo, Model model) {
		model.addAttribute("option", service.categoryList(caNo));
		return "admin/product/category-data";
	}

	// 상품등록페이지 이동
	@GetMapping("/admin/products/insertProduct")
	public String insertProduct(Model model) {
		model.addAttribute("cateA", CategoryA.values());
		for (CategoryA cate : CategoryA.values()) {
			System.out.println(cate.getKoName());
		}
		return "admin/product/insertProduct";
	}

	// 어드민에서 파일업로드
	@ResponseBody
	@PostMapping("/admin/products/fileupload")
	public String fileupload(MultipartFile file) {

		return service.tempFileupload(file);
	}

	// 어드민에서 상품리스트
	@GetMapping("/admin/products")
	public String list(Model model) {

		return service.list(model);
	}

	// 어드민에서 상품등록
	@PostMapping("/admin/products")
	public String products(ProdInsertDTO dto) {

		return service.save(dto);
	}
	/*
	// 인덱스에있는 상품 리스트
	@GetMapping("/common/products")
	public String indexList(Model model) {

		return service.indexList(model);
	}
	*/
	
	// 상품 디테일 페이지
	@GetMapping("/common/products/{prodNo}")
	public String detail(@PathVariable long prodNo, Model model) {

		return service.detail(prodNo, model);
	}

	// 전체상품 보기에 있는 상품 리스트
	@GetMapping("/common/products/list")
	public String allList(Model model) {

		return service.allList(model);
	}

	@ResponseBody
	@PutMapping("/admin/products/{prodNo}")
	public boolean updateData(@PathVariable long prodNo, ProdUpdateDto dto) {
		System.out.println(dto);
		return service.updateData(prodNo, dto);
	}

	@ResponseBody
	@PutMapping("/admin/products/{prodNo}/isShow")
	public boolean updateData(@PathVariable long prodNo, boolean isShow) {
		return service.updateIsShow(prodNo, isShow);
	}

	@ResponseBody
	@DeleteMapping("/admin/products/{prodNo}")
	public boolean updateData(@PathVariable long prodNo) {
		return service.delete(prodNo);
	}
	
	@GetMapping("/common/products")
	public String indexNewList(Model model) {
		
		return service.indexNewList(model);
	}
	
	//장바구니 읽어오기
	@GetMapping("/member/carts")
	public String cart(Model model, Principal principal) {
		service.getCarts(principal.getName(), model);
		return "member/cart";
	}
	
	//장바구니 저장
	@ResponseBody
	@PostMapping("/products/carts")
	public boolean carts(CartInsertDto dto, Principal principal) {
		//System.out.println("gno:"+gno);
		//System.out.println("ea"+ea);
		//System.out.println("principal: "+principal.getName());
		
		return service.saveCart(dto.email(principal.getName()));
	}
	//카트 상품개수 수정
	@ResponseBody
	@PutMapping("/member/carts/{prodNo}")
	public void carts(@PathVariable long prodNo, int ea) {
		service.updateCartEa(prodNo,ea);
	}
	
	//ajax
	@PostMapping("/member/order")
	public String order(long[] prodNo, int[] ea, Model model) {
		/*
		 * for(int i=0; i<prodNo.length; i++) { System.out.println("prodNo[" + i+"] :"
		 * +prodNo[i]); System.out.println("ea[" + i+ "] :" +ea[i]); }
		 */
		service.getOrderItem(prodNo, ea, model);
		return "member/order";
	}
	
	@PostMapping("/member/order/save")
	public String order(OrderInsertDTO dto) {
		
		return "member/order";
	}
}
