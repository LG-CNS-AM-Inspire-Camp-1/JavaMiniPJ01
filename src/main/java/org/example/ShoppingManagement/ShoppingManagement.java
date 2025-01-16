package org.example.ShoppingManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ShoppingManagement {
	
	
	// 상품 정보
	private List<Product> products = new ArrayList<>();
	// 동일 상품이 존재하는지 확인
	private Set<String> orderNoSet = new HashSet();
	
	
	
	// 상품 추가
	public void addProduct(Scanner scanner) {
	    System.out.print("orderNo: ");
	    String orderNo = scanner.nextLine();
	    if (orderNoSet.contains(orderNo)) {
	        System.out.printf("orderNo %s 와 동일한 상품이 등록되어 있습니다.\n", orderNo);
	        return;
	    }

	    System.out.print("상품 이름: ");
	    String name = scanner.nextLine();

	    System.out.print("가격: ");
	    int price = scanner.nextInt();
	    scanner.nextLine(); 

	    System.out.print("수량: ");
	    int quantity = scanner.nextInt();
	    scanner.nextLine(); 

	    Product product = new Product(name, orderNo, price, quantity);
	    products.add(product);
	    orderNoSet.add(product.getOrderNum());
	    System.out.println("정상적으로 등록되었습니다: " + product);
	}
	
    // 전체 상품 목록 조회
    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        } 
        
        products.forEach(System.out::println);
    }

    // 상품 검색
    public void searchProduct(Scanner scanner) {
        System.out.print("검색어를 입력하세요: ");
        String keyword = scanner.nextLine();        
        keyword = keyword.trim().toLowerCase();
        
        boolean found = false;
        for (Product product : products) {
            if (product.getName().trim().toLowerCase().contains(keyword)){
                System.out.println(product);
                found = true;
            }
        }
        
        if (!found) {
            System.out.printf("%s를 포함하는 상품이 없습니다.\n", keyword);
        }
    }
    // 상품 삭제
    public void deleteProduct(Scanner scanner) {
        System.out.print("삭제할 상품을 입력하세요: ");
        String orderNo = scanner.nextLine();
        
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getOrderNum().equals(orderNo)) {
                iterator.remove();
                orderNoSet.remove(orderNo);
                System.out.printf("오더넘버 %s 상품을 삭제했습니다.\n", orderNo);
                return;
            }
        }
        
        System.out.printf("오더넘버 %s와 일치하는 상품을 찾을 수 없습니다.\n", orderNo);
    }



}

