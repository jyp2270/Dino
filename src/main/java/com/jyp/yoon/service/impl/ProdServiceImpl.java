package com.jyp.yoon.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.jyp.yoon.domain.dto.products.CartInsertDto;
import com.jyp.yoon.domain.dto.products.CategoryDTO;
import com.jyp.yoon.domain.dto.products.OrderListDTO;
import com.jyp.yoon.domain.dto.products.ProdDetailDTO;
import com.jyp.yoon.domain.dto.products.ProdInsertDTO;
import com.jyp.yoon.domain.dto.products.ProdListDTO;
import com.jyp.yoon.domain.dto.products.ProdUpdateDto;
import com.jyp.yoon.domain.entity.Cart;
import com.jyp.yoon.domain.entity.CartRepository;
import com.jyp.yoon.domain.entity.CategoryA;
import com.jyp.yoon.domain.entity.CategoryRepository;
import com.jyp.yoon.domain.entity.FileEntity;
import com.jyp.yoon.domain.entity.MemberRepository;
import com.jyp.yoon.domain.entity.ProductEntity;
import com.jyp.yoon.domain.entity.ProductEntityRepository;
import com.jyp.yoon.service.ProdService;

@Service
public class ProdServiceImpl implements ProdService {
	
	@Autowired
	ProductEntityRepository repository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public void prodListByCategory(long caNo, Model model) {
		
		model.addAttribute("caNo", caNo);
		model.addAttribute("cateA", CategoryA.values());
		
		//하위카테고리인경우
		if(caNo%100>0) {
			model.addAttribute("list", repository.findAllByCategoriesCaNo(caNo).stream()
					.map(ProdListDTO::new)
					.toList());
			return;
		}
		
		model.addAttribute("list",
									repository.findAllByCategoriesCaNoBetween(caNo, caNo+99).stream()
									.map(ProdListDTO::new)
									.toList());
	}

	@Override
	public String tempFileupload(MultipartFile file) {
		String path="/images/products/temp/";
		ClassPathResource cpr = new ClassPathResource("static"+path);
		
		try {
			File location=cpr.getFile();
			File targetFile= new File(location, file.getOriginalFilename());
			file.transferTo(targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path+file.getOriginalFilename(); //   /images/products/temp/파일이름.jpg
				
	}

	@Override
	public String save(ProdInsertDTO dto) {
		System.out.println(dto);
		
		
		String def=dto.getDefImgName();//서버에
		String add=dto.getAddImgName();
		String path="/images/products/temp/";
		ClassPathResource cpr = new ClassPathResource("static"+path);
		
		ProductEntity entity = dto.toEntity();
		try {
			File root = cpr.getFile();
			//ClassPathResource target = new ClassPathResource("static"+"/images/products/");
			////////////////////////////////////////////////////////
			File defFile= new File(root, def); 
			defFile.renameTo(new File(root.getParent(), def));
			String name=defFile.getName();
			System.out.println(name);
			long size =defFile.length();
			System.out.println(size);
			
			FileEntity defProdFile = FileEntity.builder()
			.newName(name).orgName(name).size(size).isDefImg(true).url("/images/products/")
			.build();
			
			
			////////////////////////////////////////////////////////
			File addFile= new File(root, add); 
			addFile.renameTo(new File(root.getParent(), add));
			name=addFile.getName();
			System.out.println(name);
			size =addFile.length();
			System.out.println(size);
			
			FileEntity addProdFile = FileEntity.builder()
			.newName(name).orgName(name).size(size).url("/images/products/")
			.build();
			////파일추가
			entity
			.addFile(defProdFile)
			.addFile(addProdFile);
			/////카테고리들 추가
			for(long cano:dto.getCaNo()) {
				System.out.println("caNo:"+cano);
				entity.addCategory(categoryRepository.findById(cano).get());
			}
			
			repository.save(entity);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:products";
	}

	@Override
	public String list(Model model) {
		List<ProdListDTO> result= repository.findAll().stream()
				.map(ProdListDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("list",result);
		return "admin/product/prodList";
	}

	@Override
	public String indexList(Model model) {
		List<ProdListDTO> result= repository.findAll().stream()
				.map(ProdListDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("list",result);
		return "admin/product/list-data";
	}

	@Override
	public String detail(long prodNo, Model model) {
		model.addAttribute("detail",repository.findById(prodNo).map(ProdDetailDTO::new).get());
		
		return "admin/product/detail";
	}

	@Override
	public List<CategoryDTO> categoryList(long caNo) {
		//1100 --> 1101~1199
		
		/*
		for(CategoryA catea : CategoryA.values()) {
			if(catea.getCode()==caNo) {
				categoryRepository.findByCateA(catea);
			}
		}
		*/
		return categoryRepository.findByCaNoBetween(caNo, caNo+99).stream()
				.map(CategoryDTO::new)
				.toList();
	}

	@Override
	public String allList(Model model) {
		List<ProdListDTO> result= repository.findAll().stream()
				.map(ProdListDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("list",result);
		return "products/allList";
	}

	@Override
	public boolean updateData(long prodNo, ProdUpdateDto dto) {
		Optional<ProductEntity> result=repository.findById(prodNo);
		if(result.isEmpty())return false;
		repository.save(result.get().updateData(dto));
		return true;
	}

	@Override
	public boolean updateIsShow(long prodNo, boolean isShow) {
		Optional<ProductEntity> result=repository.findById(prodNo);
		if(result.isEmpty())return false;
		repository.save(result.get().updateIsShow(isShow));
		return true;
	}
	@Override
	public boolean updatePrice(long prodNo, int price) {
		
		Optional<ProductEntity> result=repository.findById(prodNo);
		if(result.isEmpty())return false;
		repository.save(result.get().updatePrice(price));
		return true;
	}

	@Override
	public boolean updateStock(long prodNo, int stock) {
		Optional<ProductEntity> result=repository.findById(prodNo);
		if(result.isEmpty())return false;
		repository.save(result.get().updateStock(stock));
		return true;
	}
	
	@Override
	public boolean updateSale(long prodNo, int sale) {
		Optional<ProductEntity> result=repository.findById(prodNo);
		if(result.isEmpty())return false;
		repository.save(result.get().updateStock(sale));
		return true;
	}
	

	@Override
	public boolean delete(long prodNo) {
		
		repository.delete(repository.findById(prodNo).map(e->e.removeCategoryAll()).get());
		return true;
	}

	@Override
	public String indexNewList(Model model) {
		model.addAttribute("newList",
				repository.findAllByCategoriesCaNo(1101).stream()
				.map(ProdListDTO::new)
				.toList());
		model.addAttribute("bestList",
				repository.findAllByCategoriesCaNo(1201).stream()
				.map(ProdListDTO::new)
				.toList());
		return "admin/product/nbList-data";
	}

	@Override
	public void getCarts(String email, Model model) {
		model.addAttribute("list", cartRepository.findAllByMemberEmail(email));		
	}

	@Override
	public boolean saveCart(CartInsertDto dto) {
		
		Optional<Cart> result = cartRepository.findByProdsProdNo(dto.getProdNo());
		if(result.isEmpty()) {
		Cart entity=cartRepository.save(dto.toEntity()
				.goods(ProductEntity.builder().prodNo(dto.getProdNo()).build())//회원
				.member(memberRepository.findByEmail(dto.getEmail()).orElseThrow())
				);
		}else {
			Cart entity = result.get().plusEa(dto.getEa());
			cartRepository.save(entity);
		}
		//if(entity==null)return false;//카드 저장 실패
		return true;//카드 저장 성공
	}
	
	@Transactional
	@Override
	public void updateCartEa(long prodNo, int ea) {
		cartRepository.findByProdsProdNo(prodNo).map(cart->cart.updateEa(ea));
	}

	@Override
	public void getOrderItem(long[] prodNo, int[] ea, Model model) {
		List<OrderListDTO> list=new ArrayList<>();
		for(int i=0; i<prodNo.length; i++) {
			list.add(repository.findByProdNoAndStockGreaterThanEqual(prodNo[i],ea[i]).map(OrderListDTO::new).orElseThrow().ea(ea[i]));
		}
		model.addAttribute("list",list);
	}

}
