package com.springboot.ecommerce;

import com.springboot.ecommerce.auth.AuthenticationService;
import com.springboot.ecommerce.auth.Role;
import com.springboot.ecommerce.config.JwtService;
import com.springboot.ecommerce.dtos.user.UserRegDto;
import com.springboot.ecommerce.models.*;
import com.springboot.ecommerce.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


	// Insert sample rows in initialization
	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService,
											   ProductItemService productItemService,
											   CategoryService categoryService,
											   StoreService storeService,
											   VoucherService voucherService,
											   UserService userService,
											   JwtService jwtService,
											   AddressService addressService,
											   CartService cartService,
											   CartItemService cartItemService,
											   OrderService orderService,
											   OrderItemService orderItemService) {
		return args -> {
			for(int i = 0; i < 20; i++) {

				User user = new User("User" + i, "user" + i +"password", "user" + i + "@gmail.com", Role.USER);
				userService.save(user);
				jwtService.generateToken(user);

				Address address = new Address("country " + i, "city " + i, "street " + i);
				address.setUser(user);
				addressService.save(address);

				Store store = new Store("usa", "store " + i, "good");
				storeService.save(store);

				Category category = new Category("category " + i, "New category " + i);
				categoryService.save(category);

				Product product = new Product("product " + i, "new product " + i, "good");
				product.setCategory(category);
				product.setStore(store);
				productService.save(product);

				ProductItem productItem = new ProductItem("color" + i, "size " + i, 0, 10, 150.52);
				productItem.setProduct(product);
				productItemService.save(productItem);

				Cart cart = new Cart();
				cart.setTotalPrice(103.32);
				cart.setUser(user);
				cartService.save(cart);

				CartItem cartItem = new CartItem(productItem.getCurrentQuantity(), productItem.getPrice());
				cartItem.setStore(store);
				cartItem.setCart(cart);
				cartItem.setProductItem(productItem);
				cartItemService.save(cartItem);

				Order order = new Order(cart.getTotalPrice(), "cash", "processing");
				order.setUser(user);
				orderService.save(order);

				OrderItem orderItem = new OrderItem();
				orderItem.setPrice(productItem.getPrice());
				orderItem.setQuantity(productItem.getCurrentQuantity());
				orderItem.setOrder(order);
				orderItem.setProductItem(productItem);
				orderItemService.save(orderItem);

				Voucher voucher = new Voucher("voucher " + i, "discount for product " + i, "150$", LocalDate.now().plusMonths(1));
				voucher.setProduct(product);
				voucherService.save(voucher);

			}
		};
	}

}
