//package com.sweetsdelight_bk.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import com.sweetsdelight_bk.Exceptions.SweetDelightBkException;
//import com.sweetsdelight_bk.Model.SweetItem;
//import com.sweetsdelight_bk.Repository.SweetItemRepository;
//
//@Service
//public class SweetItemServiceImpl implements SweetItemService {
//	
//	@Autowired
//	private SweetItemRepository sweetRepo;
//
//	@Override
//	public SweetItem addSweet(SweetItem sweet) {
//		if(sweet==null)throw new SweetDelightBkException("sweet is null");
//		return sweetRepo.save(sweet);
//	}
//
//	@Override
//	public SweetItem updateSweetItem(SweetItem sweet) {
//		Optional<SweetItem> swet=sweetRepo.findById(sweet.getSweetItemId());
//		if(swet.isEmpty())throw new SweetDelightBkException("No sweet is available of "+sweet.getSweetItemId());
//		return sweetRepo.save(sweet);
//	}
//
//	@Override
//	public SweetItem deleteSweetItem(Integer sweet) {
//		Optional<SweetItem> swet=sweetRepo.findById(sweet);
//		if(swet.isEmpty())throw new SweetDelightBkException("No sweet is available of "+sweet);
//		SweetItem temp= swet.get();
//		sweetRepo.deleteById(sweet);
//		return temp;
//	}
//
//	@Override
//	public List<SweetItem> showAllSweetItem() {
//		Pageable p= PageRequest.of(0, 10, Sort.by("sweetItemId"));
//		List<SweetItem> list= sweetRepo.findAll(p).getContent();
//		if(list.isEmpty())throw new SweetDelightBkException("No SweetItem is available");
//		return list;
//	}
//
//}
