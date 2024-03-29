// 0 - pacote
package webTest;
// 1- Bibliotecas

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

//2 - Classe
public class ComprarPassagemWD { // inicio sa classe
    //2.1 Atributos
    private WebDriver driver; //declaramos o objero do selenium WebDriver
    //2.2 Funções e métodos
    // Antes do Teste
    @BeforeEach
    public void setUp(){ // inicio do Before
        // declarar o gerenciador para baixar o chrome driver
        WebDriverManager.chromedriver().setup();
        // configuração especifica a partir do Selenium WebDiver 4.8.1
        ChromeOptions options = new ChromeOptions(); //instancia o ChromeOptions
        // Adicionou ao ChromeOptions a opção de permitir qualquer origem de
        //acesso remoto
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options); // instacia o Chrome Driver com Options
        driver.manage().window().maximize(); //maximiza a janela do navegador
    } // fim do Before

    // Depois do teste
    @AfterEach
    public void tearDown(){ // inicio do After
        driver.quit(); // destroi a instancia do Selenium WebDriver

    } // fim do after

    // o teste

    @Test
    public void comprarPassagemWD(){ // inicio do comprar passagem
        driver.get("https://www.blazedemo.com"); // abre o endereo alvo
        // selecionar a lista/combo de cidades de origem
        driver.findElement(By.name("fromPort")).click();
        // selecionar a cidade na lista
        { // inicio da seleção dentro da lista
            WebElement lista = driver.findElement(By.name("fromPort"));
            lista.findElement (By.xpath("//option[. = 'São Paolo']")).click();

        } // fim da seleção dentro da lista
        driver.findElement(By.name("toPort")).click();
        // selecionar a cidade na lista
        { // inicio da seleção dentro da lista
            WebElement lista = driver.findElement(By.name("toPort"));
            lista.findElement (By.xpath("//option[. = 'Berlin']")).click();

        } // fim da seleção dentro da lista

        // apertar o botão Find Flights
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // validar a frase que indica que o voo de são paolo para berlin
        assertEquals("Flights from São Paolo to Berlin:",driver.findElement(By.cssSelector("h3")).getText());


    } // fim do comprar passagem

} // fim da classe
