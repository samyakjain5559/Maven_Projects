import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.products")
public class ShoppingApiTest {
	
	@Autowired
	private MockMvc mvc;

	/*
	 * Tests by checking if all products are returned from DB
	 * */
	@Test
	public void getProductDetailsTest() throws Exception{
		mvc.perform(get("/products/getProductDetails")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(9)));
	}
	
	/*
	 * Tests by checking if all products added are returned from the cart in session
	 * */
	@Test
	public void getProductfromCartTest() throws Exception{
		
		mvc.perform(get("/products/removeAllProductsfromCart")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
		
		mvc.perform(get("/products/addProductToCart/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));

		mvc.perform(get("/products/addProductToCart/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
		
		mvc.perform(get("/products/getProductfromCart")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(2)));
	}
	
	/*
	 * Tests by checking if product gets properly added
	 * */
	@Test
	public void addProductToCartTest() throws Exception{
		mvc.perform(get("/products/addProductToCart/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
	}
	
	/*
	 * Tests by checking if added product is removed properly
	 * */
	@Test
	public void removeProductfromCartTest() throws Exception{
		mvc.perform(get("/products/addProductToCart/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
		
		mvc.perform(get("/products/removeProductfromCart/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
	}
	
	/*
	 * Tests by checking if all products added are removed from the cart in session
	 * */
	@Test
	public void removeAllProductsfromCartTest() throws Exception{
		mvc.perform(get("/products/addProductToCart/2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
		
		mvc.perform(get("/products/addProductToCart/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
		
		mvc.perform(get("/products/removeAllProductsfromCart")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().string("success"));
		
		mvc.perform(get("/products/getProductfromCart")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(0)));
	}

}
