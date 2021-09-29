import com.example.shopapp.command.ICommand;
import com.example.shopapp.command.SortProductsCommand;
import com.example.shopapp.entity.Product;
import com.example.shopapp.exception.CommandException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SortProductsCommandTest {
    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    ICommand sortProductsCommand;
    List<Product> products;
    Product product1;
    Product product2;
    Product product3;
    Product product4;


    @Before
    public void setUp(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        products = new ArrayList<>();
        sortProductsCommand = new SortProductsCommand();
        product1 = new Product();
        product1.setName("A");
        product1.setPrice(1);
        product2 = new Product();
        product2.setName("B");
        product2.setPrice(4);
        product3 = new Product();
        product3.setName("C");
        product3.setPrice(3);
        product4 = new Product();
        product4.setName("D");
        product4.setPrice(2);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("allProducts")).thenReturn(products);
    }

    @Test
    public void shouldReturnSortListZA() throws CommandException {
        when(request.getParameter("sortBy")).thenReturn("nameZA");
        List<Product> sortingNameZA = new ArrayList<>();
        sortingNameZA.add(product4);
        sortingNameZA.add(product3);
        sortingNameZA.add(product2);
        sortingNameZA.add(product1);
        sortProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", sortingNameZA);
    }

    @Test
    public void shouldReturnSortListAZ() throws CommandException {
        when(request.getParameter("sortBy")).thenReturn("nameAZ");
        List<Product> sortingNameAZ = new ArrayList<>();
        sortingNameAZ.add(product1);
        sortingNameAZ.add(product2);
        sortingNameAZ.add(product3);
        sortingNameAZ.add(product4);
        sortProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", sortingNameAZ);
    }

    @Test
    public void shouldReturnSortListPriceL2H() throws CommandException {
        when(request.getParameter("sortBy")).thenReturn("priceL2H");
        List<Product> priceL2H = new ArrayList<>();
        priceL2H.add(product1);
        priceL2H.add(product4);
        priceL2H.add(product3);
        priceL2H.add(product2);
        sortProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", priceL2H);
    }

    @Test
    public void shouldReturnSortListPriceH2L() throws CommandException {
        when(request.getParameter("sortBy")).thenReturn("priceH2L");
        List<Product> priceH2L = new ArrayList<>();
        priceH2L.add(product2);
        priceH2L.add(product3);
        priceH2L.add(product4);
        priceH2L.add(product1);
        sortProductsCommand.execute(request, response);
        verify(session).setAttribute("allProducts", priceH2L);
    }

    @After
    public void after(){
        response = null;
        request = null;
        session = null;
        products = null;
        product1 = null;
        product2 = null;
        product3 = null;
        product4 = null;
    }

}
