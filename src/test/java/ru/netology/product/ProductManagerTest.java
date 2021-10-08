package ru.netology.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book book1 = new Book(1, "Книга1", 300, "Автор1");
    Book book2 = new Book(2, "Книга2", 400, "Автор2");
    Book book3 = new Book(3, "Книга3", 500, "Автор3");
    Smartphone phone1 = new Smartphone(4, "Motorola g9", 17000, "Motorola");
    Smartphone phone2 = new Smartphone(5, "Honor 20", 20000, "Honor");
    Smartphone phone3 = new Smartphone(6, "Samsung s21", 30000, "Samsung");

    @BeforeEach
    void setUp() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
    }

    @Test
    void shouldFindBookByNameIfExists() {
        String textToFind = "Книга1";
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByNameIfNoExists() {
        String textToFind = "Книга4";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByAuthorIfExists() {
        String textToFind = "Автор2";
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByAuthorIfNoExists() {
        String textToFind = "Автор4";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByNameIfExists() {
        String textToFind = "Samsung s21";
        Product[] expected = new Product[]{phone3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByNameIfNoExists() {
        String textToFind = "Alcatel";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByProducerIfExists() {
        String textToFind = "Honor";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByProducerIfNoExists() {
        String textToFind = "Xiaomi";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenMissingProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("нет такого продукта");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByOneProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhoneAndBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1, phone2");
        assertArrayEquals(expected, actual);

    }
}