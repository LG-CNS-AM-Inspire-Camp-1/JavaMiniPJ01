package org.example.ShoppingManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Product {

	private String name;
	private String orderNum;
	private int price;
	private int quanity;
}
